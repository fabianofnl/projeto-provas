package br.com.sgpo.controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.CartesianChartModel;

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

	private CartesianChartModel categoryModel = new CartesianChartModel();
	private RelatorioDadosGeraisDTO relatorio = new RelatorioDadosGeraisDTO();

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

	public RelatorioDadosGeraisDTO getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(RelatorioDadosGeraisDTO relatorio) {
		this.relatorio = relatorio;
	}

	public CartesianChartModel getCategoryModel() {
		return categoryModel;
	}

}
