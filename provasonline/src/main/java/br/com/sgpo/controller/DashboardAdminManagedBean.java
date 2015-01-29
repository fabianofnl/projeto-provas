package br.com.sgpo.controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
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

	private CartesianChartModel categoryModel;
	private RelatorioDadosGeraisDTO relatorio = new RelatorioDadosGeraisDTO();

	@PostConstruct
	public void carregarDadosMemoria() {

		int mb = 1024 * 1024;

		categoryModel = new CartesianChartModel();
		ChartSeries memoriaMax = new ChartSeries();
		memoriaMax.setLabel("Memória máxima");
		memoriaMax.set("Memória", (Runtime.getRuntime().maxMemory() / mb));

		ChartSeries memoriaTotal = new ChartSeries();
		memoriaTotal.setLabel("Memória total (pico)");
		memoriaTotal.set("Memória", (Runtime.getRuntime().totalMemory() / mb));

		ChartSeries memoriaLivre = new ChartSeries();
		memoriaLivre.setLabel("Memória livre");
		memoriaLivre.set("Memória", (Runtime.getRuntime().freeMemory() / mb));

		categoryModel.addSeries(memoriaMax);
		categoryModel.addSeries(memoriaTotal);
		categoryModel.addSeries(memoriaLivre);
	}

	public void carregarDashboard(ActionEvent event) {

		try {
			DashboardService dashboardService = new DashboardServiceImpl();
			relatorio = dashboardService.consultarRelatorioDadosGerais();
		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	public CartesianChartModel getCategoryModel() {
		return categoryModel;
	}

	public RelatorioDadosGeraisDTO getRelatorio() {
		return relatorio;
	}
}
