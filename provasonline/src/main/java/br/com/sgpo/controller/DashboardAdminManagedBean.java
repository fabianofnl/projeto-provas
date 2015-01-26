package br.com.sgpo.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 * @author Roseli
 * 
 */
@ManagedBean
@ViewScoped
public class DashboardAdminManagedBean implements Serializable {

	private static final long serialVersionUID = 3771915445327651323L;

	private CartesianChartModel categoryModel;

	@PostConstruct
	public void init() {

		int mb = 1024 * 1024;

		categoryModel = new CartesianChartModel();
		ChartSeries memoriaMax = new ChartSeries();
		memoriaMax.setLabel("Mem�ria m�xima");
		memoriaMax.set("Mem�ria", (Runtime.getRuntime().maxMemory() / mb));

		ChartSeries memoriaTotal = new ChartSeries();
		memoriaTotal.setLabel("Mem�ria total");
		memoriaTotal.set("Mem�ria", (Runtime.getRuntime().totalMemory() / mb));

		ChartSeries memoriaLivre = new ChartSeries();
		memoriaLivre.setLabel("Mem�ria livre");
		memoriaLivre.set("Mem�ria", (Runtime.getRuntime().freeMemory() / mb));

		categoryModel.addSeries(memoriaMax);
		categoryModel.addSeries(memoriaTotal);
		categoryModel.addSeries(memoriaLivre);
	}

	public void carregarDashboard(ActionEvent event) {
	}

	public CartesianChartModel getCategoryModel() {
		return categoryModel;
	}
}
