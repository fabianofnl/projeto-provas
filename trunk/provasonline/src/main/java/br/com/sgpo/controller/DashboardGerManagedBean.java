package br.com.sgpo.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.NotaMediaColaboradorDTO;
import br.com.sgpo.service.DashboardService;
import br.com.sgpo.service.DashboardServiceImpl;

/**
 * @author Roseli
 * 
 */
@ManagedBean
@ViewScoped
public class DashboardGerManagedBean implements Serializable {

	private static final long serialVersionUID = 2825442688669164686L;

	private static final Logger LOG = Logger
			.getLogger(DashboardGerManagedBean.class);

	private CartesianChartModel mediaEquipeChart = new CartesianChartModel();

	private Boolean barChartFlag = false;
	private int height;

	public void carregarDashboard(ActionEvent event) {

		LOG.info("chamando carregar dashboard GER");

		try {
			DashboardService dashboardService = new DashboardServiceImpl();

			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext()
					.getSession(true);
			FuncionarioDTO gerente = (FuncionarioDTO) session
					.getAttribute("funcionario");

			List<NotaMediaColaboradorDTO> listaNotaMediaColaboradores = dashboardService
					.listarNotaMediaColaboradores(gerente.getMatricula());

			height = 250;
			if (listaNotaMediaColaboradores.size() > 3) {
				height += ((listaNotaMediaColaboradores.size() - 2) * 60);
			}

			LOG.info(String.format(
					"Quantidade de notas medias de colaboradores [%d]",
					listaNotaMediaColaboradores.size()));

			mediaEquipeChart = new CartesianChartModel();

			ChartSeries series;
			Double media = 0.0;

			barChartFlag = false;
			if (listaNotaMediaColaboradores.size() != 0) {
				for (NotaMediaColaboradorDTO notaMediaColaborador : listaNotaMediaColaboradores) {

					series = new ChartSeries();
					series.setLabel(notaMediaColaborador.getNome());

					double value = notaMediaColaborador.getMedia();

					long factor = (long) Math.pow(10, 2);
					value = value * factor;
					value = Math.round(value);
					value = value / factor;

					series.set("Nota Média", value);
					mediaEquipeChart.addSeries(series);

					/**
					 * Soma o total de acertos e questões dos colaboradores para
					 * cálculo da média
					 */
					media += notaMediaColaborador.getMedia();
				}

				/**
				 * Cria uma nova serie para a média
				 */
				series = new ChartSeries();
				series.setLabel("Média Equipe");

				double value = media.doubleValue();

				long factor = (long) Math.pow(10, 2);
				value = value * factor;
				value = Math.round(value);
				value = value / factor;

				series.set(
						"Nota",
						value
								/ Double.parseDouble(String
										.valueOf(listaNotaMediaColaboradores
												.size())));
				mediaEquipeChart.addSeries(series);

				barChartFlag = true;
			}

			Collections.reverse(mediaEquipeChart.getSeries());

		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Boolean getBarChartFlag() {
		return barChartFlag;
	}

	public void setBarChartFlag(Boolean barChartFlag) {
		this.barChartFlag = barChartFlag;
	}

	public CartesianChartModel getMediaEquipeChart() {
		return mediaEquipeChart;
	}
}
