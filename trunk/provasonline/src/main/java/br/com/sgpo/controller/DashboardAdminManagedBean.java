package br.com.sgpo.controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.sgpo.dto.RelatorioDadosGeraisDTO;
import br.com.sgpo.service.DashboardService;
import br.com.sgpo.service.DashboardServiceImpl;

/**
 * @author Roseli
 * 
 */
@ManagedBean
@ViewScoped
public class DashboardAdminManagedBean implements Serializable {

	private static final long serialVersionUID = 3771915445327651323L;

	private static final Logger LOG = Logger
			.getLogger(DashboardAdminManagedBean.class);

	private CartesianChartModel memoryChart = new CartesianChartModel();
	private RelatorioDadosGeraisDTO relatorio = new RelatorioDadosGeraisDTO();

	private Boolean barChartFlag = false;

	public void carregarDashboard(ActionEvent event) {

		try {
			int mb = 1024 * 1024;

			memoryChart = new CartesianChartModel();
			ChartSeries memoriaMax = new ChartSeries();
			memoriaMax.setLabel("Memória máxima");
			memoriaMax.set("Memória", (Runtime.getRuntime().maxMemory() / mb));

			ChartSeries memoriaTotal = new ChartSeries();
			memoriaTotal.setLabel("Memória total (pico)");
			memoriaTotal.set("Memória",
					(Runtime.getRuntime().totalMemory() / mb));

			ChartSeries memoriaLivre = new ChartSeries();
			memoriaLivre.setLabel("Memória livre");
			memoriaLivre.set("Memória",
					(Runtime.getRuntime().freeMemory() / mb));

			LOG.info("memorias:" + memoriaMax + ", " + memoriaTotal + ", "
					+ memoriaLivre);

			memoryChart.addSeries(memoriaMax);
			memoryChart.addSeries(memoriaTotal);
			memoryChart.addSeries(memoriaLivre);
			barChartFlag = true;

			LOG.info("GET MEMORY LOGs");

			DashboardService dashboardService = new DashboardServiceImpl();
			relatorio = dashboardService.consultarRelatorioDadosGerais();
		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	public CartesianChartModel getMemoryChart() {
		LOG.info("CATEGORY MODEL");
		return memoryChart;
	}

	public RelatorioDadosGeraisDTO getRelatorio() {
		return relatorio;
	}

	public Boolean getBarChartFlag() {
		return barChartFlag;
	}

	public void setBarChartFlag(Boolean barChartFlag) {
		this.barChartFlag = barChartFlag;
	}
}
