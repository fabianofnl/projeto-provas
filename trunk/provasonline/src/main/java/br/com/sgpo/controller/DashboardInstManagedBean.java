package br.com.sgpo.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.sgpo.dto.NotaMediaEquipesDTO;
import br.com.sgpo.dto.RelatorioDadosGeraisDTO;
import br.com.sgpo.service.DashboardService;
import br.com.sgpo.service.DashboardServiceImpl;

/**
 * @author Roseli
 * 
 */
@ManagedBean
@ViewScoped
public class DashboardInstManagedBean implements Serializable {

	private static final long serialVersionUID = 4312407946174547134L;

	private static final Logger LOG = Logger
			.getLogger(DashboardInstManagedBean.class);

	private List<NotaMediaEquipesDTO> listaNotaMediaEquipes = new ArrayList<NotaMediaEquipesDTO>();

	private CartesianChartModel mediaGerenteChart = new CartesianChartModel();
	private RelatorioDadosGeraisDTO relatorio = new RelatorioDadosGeraisDTO();

	private int height = 0;
	private Boolean barChartFlag = false;

	public void carregarDashboard(ActionEvent event) {

		try {
			DashboardService dashboardService = new DashboardServiceImpl();

			listaNotaMediaEquipes = dashboardService.listarNotaMediaEquipes();

			height = 250;
			if (listaNotaMediaEquipes.size() > 3) {
				height += ((listaNotaMediaEquipes.size() - 2) * 60);
			}

			LOG.info(String.format("Quantidade de provas realizadas [%d]",
					listaNotaMediaEquipes.size()));

			mediaGerenteChart = new CartesianChartModel();

			ChartSeries series;
			double qtdMedia = 0.0;

			barChartFlag = false;
			if (listaNotaMediaEquipes.size() != 0) {
				for (NotaMediaEquipesDTO notaMediaEquipesDTO : listaNotaMediaEquipes) {

					series = new ChartSeries();
					series.setLabel(notaMediaEquipesDTO.getNomeGerente());

					double value = notaMediaEquipesDTO.getMedia();

					long factor = (long) Math.pow(10, 2);
					value = value * factor;
					value = Math.round(value);
					value = value / factor;

					series.set("Nota", value);
					mediaGerenteChart.addSeries(series);

					/**
					 * Soma o total de acertos e questões das provas para
					 * cálculo da média
					 */
					qtdMedia += notaMediaEquipesDTO.getMedia();
				}

				/**
				 * Cria uma nova serie para a média
				 */
				series = new ChartSeries();
				series.setLabel("Média");

				double value = (qtdMedia / Double.parseDouble(String
						.valueOf(listaNotaMediaEquipes.size())));

				long factor = (long) Math.pow(10, 2);
				value = value * factor;
				value = Math.round(value);
				value = value / factor;

				series.set("Nota", value);
				mediaGerenteChart.addSeries(series);

				barChartFlag = true;
			}

			relatorio = dashboardService.consultarRelatorioDadosGerais();
		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	public RelatorioDadosGeraisDTO getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(RelatorioDadosGeraisDTO relatorio) {
		this.relatorio = relatorio;
	}

	public CartesianChartModel getMediaGerenteChart() {
		return mediaGerenteChart;
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

	public List<NotaMediaEquipesDTO> getListaNotaMediaEquipes() {
		return listaNotaMediaEquipes;
	}

	public void setListaNotaMediaEquipes(
			List<NotaMediaEquipesDTO> listaNotaMediaEquipes) {
		this.listaNotaMediaEquipes = listaNotaMediaEquipes;
	}
}
