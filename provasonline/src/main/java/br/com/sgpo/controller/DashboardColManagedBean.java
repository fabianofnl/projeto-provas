package br.com.sgpo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.sgpo.constants.SGPOConstants;
import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.ProvaRealizadaDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.service.DashboardService;
import br.com.sgpo.service.DashboardServiceImpl;

/**
 * @author Roseli
 * 
 */
@ManagedBean
@SessionScoped
public class DashboardColManagedBean implements Serializable {

	private static final long serialVersionUID = -8521165965503236100L;

	private static final Logger LOG = Logger
			.getLogger(DashboardColManagedBean.class);

	private List<AgendaDTO> listaAgendas = new ArrayList<AgendaDTO>();
	private List<QuestaoDTO> listaQuestoes = new ArrayList<QuestaoDTO>();
	private ApostilaDTO apostilaSelecionada = new ApostilaDTO();
	private ProvaDTO provaSelecionada = new ProvaDTO();
	private ProvaRealizadaDTO provaRealizadaSelecionada = new ProvaRealizadaDTO();
	private Boolean barChartFlag = false;
	private int height;

	private CartesianChartModel categoryModel;

	@PostConstruct
	public void carregarNotas() {

		try {
			DashboardService dashboardService = new DashboardServiceImpl();

			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext()
					.getSession(true);
			FuncionarioDTO colaborador = (FuncionarioDTO) session
					.getAttribute("funcionario");

			List<ProvaRealizadaDTO> listaProvasRealizadas = dashboardService
					.listarProvasRealizadasPorMatricula(colaborador
							.getMatricula());
			height = 250;
			if (listaProvasRealizadas.size() > 3) {
				height += ((listaProvasRealizadas.size() - 2) * 60);
			}

			LOG.info(String.format("Quantidade de provas realizadas [%d]",
					listaProvasRealizadas.size()));

			categoryModel = new CartesianChartModel();

			ChartSeries series;
			Integer qtdAcertos = 0;
			Integer qtdQuestoes = 0;

			barChartFlag = false;
			if (listaProvasRealizadas.size() != 0) {
				for (ProvaRealizadaDTO provaRealizadaDTO : listaProvasRealizadas) {

					/**
					 * Indica que o colaborador não fez prova então vai pular a
					 * um loop
					 */
					if (provaRealizadaDTO.getQuantidadeAcertos() == null
							|| provaRealizadaDTO.getQuantidadeQuestoes() == null){
						LOG.info("Pulo 1 for");
						continue;
					}

					series = new ChartSeries();
					series.setLabel(provaRealizadaDTO.getTituloProva());

					double value = ((provaRealizadaDTO.getQuantidadeAcertos()
							.doubleValue() / provaRealizadaDTO
							.getQuantidadeQuestoes().doubleValue()) * 100.0);

					long factor = (long) Math.pow(10, 2);
					value = value * factor;
					value = Math.round(value);
					value = value / factor;

					series.set("Nota", value);
					categoryModel.addSeries(series);

					/**
					 * Soma o total de acertos e questões das provas para
					 * cálculo da média
					 */
					qtdAcertos += provaRealizadaDTO.getQuantidadeAcertos();
					qtdQuestoes += provaRealizadaDTO.getQuantidadeQuestoes();
				}

				/**
				 * Cria uma nova serie para a média
				 */
				series = new ChartSeries();
				series.setLabel("Média");

				double value = ((qtdAcertos.doubleValue() / qtdQuestoes
						.doubleValue()) * 100.0);

				long factor = (long) Math.pow(10, 2);
				value = value * factor;
				value = Math.round(value);
				value = value / factor;

				series.set("Nota", value);
				categoryModel.addSeries(series);

				barChartFlag = true;
			}

		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	public void carregarDashboard(ActionEvent event) {

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext()
					.getSession(true);
			FuncionarioDTO funcionario = (FuncionarioDTO) session
					.getAttribute("funcionario");

			DashboardService dashboardService = new DashboardServiceImpl();
			listaAgendas = dashboardService.listarAgendas(funcionario
					.getMatricula());

		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	@SuppressWarnings("unused")
	public void carregarArquivo() {

		try {
			String filePath = apostilaSelecionada.getServerPath()
					+ apostilaSelecionada.getHashName() + "_"
					+ apostilaSelecionada.getNome();

			String fileName = apostilaSelecionada.getNome();

			String contentType = FacesContext.getCurrentInstance()
					.getExternalContext().getMimeType(filePath);

			File file = new File(filePath);

			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			response.setContentType(contentType);
			response.setHeader("Content-Disposition",
					String.format("attachment; filename=%s", fileName));
			response.setContentLength((int) file.length());

			InputStream fis = new FileInputStream(file);
			ServletOutputStream out = response.getOutputStream();
			byte[] buf = new byte[SGPOConstants.BUFFER_SIZE];
			int i = 0;

			while ((i = fis.read(buf)) != -1) {
				out.write(buf);
				out.flush();
			}
			fis.close();

			FacesContext.getCurrentInstance().responseComplete();
			response.getOutputStream().close();

			LOG.info("Nome do arquivo: " + fileName);
			LOG.info("Content Type: " + contentType);

		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um erro ao processar o arquivo para download", e);
		}

	}

	public String carregarProva() {

		try {
			DashboardService dashboardService = new DashboardServiceImpl();

			listaQuestoes = dashboardService
					.listarQuestoesPorProva(provaSelecionada);

			provaRealizadaSelecionada.setProvaId(provaSelecionada.getProvaId());
			provaRealizadaSelecionada.setTituloProva(provaSelecionada
					.getTitulo());
			provaRealizadaSelecionada.setDataHoraInicio(new Date());
			provaRealizadaSelecionada.setDataHoraFim(new Date(new Date()
					.getTime() + SGPOConstants.TEMPO_DE_PROVA));
			provaRealizadaSelecionada.setQuantidadeAcertos(0);
			provaRealizadaSelecionada.setQuantidadeQuestoes(listaQuestoes
					.size());
			provaRealizadaSelecionada.setProvaRealizadaId(dashboardService
					.realizarProva(provaRealizadaSelecionada));

		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}

		return "/pages/system/realizar_prova";
	}

	public String submitProva() {

		try {
			DashboardService dashboardService = new DashboardServiceImpl();
			int acertos = 0;

			provaRealizadaSelecionada.setDataHoraFinalizado(new Date());

			if (provaRealizadaSelecionada.getDataHoraFim().getTime() >= provaRealizadaSelecionada
					.getDataHoraFinalizado().getTime()) {

				for (QuestaoDTO quest : listaQuestoes) {
					for (OpcaoDTO op : quest.getListaOpcoes()) {
						if (op.getOpcaoId().intValue() == Integer
								.parseInt(quest.getOpcaoSelecionada())
								&& op.getFlag()) {
							acertos++;
						}
					}
				}
				provaRealizadaSelecionada.setQuantidadeAcertos(acertos);
				dashboardService.entregarProva(provaRealizadaSelecionada);

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
								"Prova finalizada com sucesso"));
				LOG.info("Tempo de prova OK");
			} else {

				provaRealizadaSelecionada.setQuantidadeAcertos(0);
				dashboardService.entregarProva(provaRealizadaSelecionada);

				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_WARN,
										"Atenção",
										"Seu tempo para realização da prova havia terminado, a prova não foi finalizada."));
				LOG.info("Tempo de prova expirado");
			}

			LOG.info("Fim submit prova");
			limparSessao();
			carregarNotas();
		} catch (ClassNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}

		return "/pages/system/dashboard/dashboard";
	}

	private void limparSessao() {
		provaRealizadaSelecionada = new ProvaRealizadaDTO();
		provaSelecionada = new ProvaDTO();
	}

	public ApostilaDTO getApostilaSelecionada() {
		return apostilaSelecionada;
	}

	public void setApostilaSelecionada(ApostilaDTO apostilaSelecionada) {
		this.apostilaSelecionada = apostilaSelecionada;
	}

	public ProvaDTO getProvaSelecionada() {
		return provaSelecionada;
	}

	public void setProvaSelecionada(ProvaDTO provaSelecionada) {
		this.provaSelecionada = provaSelecionada;
	}

	public ProvaRealizadaDTO getProvaRealizadaSelecionada() {
		return provaRealizadaSelecionada;
	}

	public void setProvaRealizadaSelecionada(
			ProvaRealizadaDTO provaRealizadaSelecionada) {
		this.provaRealizadaSelecionada = provaRealizadaSelecionada;
	}

	public List<AgendaDTO> getListaAgendas() {
		return listaAgendas;
	}

	public void setListaAgendas(List<AgendaDTO> listaAgendas) {
		this.listaAgendas = listaAgendas;
	}

	public List<QuestaoDTO> getListaQuestoes() {
		return listaQuestoes;
	}

	public void setListaQuestoes(List<QuestaoDTO> listaQuestoes) {
		this.listaQuestoes = listaQuestoes;
	}

	public CartesianChartModel getCategoryModel() {
		LOG.info("CHAMANDO CATEGORYMODEL");
		return categoryModel;
	}

	public Boolean getBarChartFlag() {
		return barChartFlag;
	}

	public void setBarChartFlag(Boolean barChartFlag) {
		this.barChartFlag = barChartFlag;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
