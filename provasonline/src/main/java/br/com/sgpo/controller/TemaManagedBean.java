package br.com.sgpo.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.TemaDTO;
import br.com.sgpo.service.TemasService;
import br.com.sgpo.service.TemasServiceImpl;

/**
 * @author Roseli
 * 
 */
@ManagedBean
@ViewScoped
public class TemaManagedBean implements Serializable {

	private static final long serialVersionUID = -4196071999126361341L;
	private static final Logger LOG = Logger.getLogger(TemaManagedBean.class);

	private TemaDTO temaNovo = new TemaDTO();
	private TemaDTO temaSelecionado = new TemaDTO();

	private List<TemaDTO> listaTemas = new ArrayList<TemaDTO>();

	public void carregarTabela(ActionEvent event) {

		try {
			TemasService temasService = new TemasServiceImpl();
			listaTemas = temasService.listarTemas();

			LOG.info("Total de temas: " + listaTemas.size());
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

	}

	public void cadastrarTema(ActionEvent event) {

		try {
			TemasService temasService = new TemasServiceImpl();
			temasService.gravar(temaNovo);
			LOG.info("Tema cadastrado: " + temaNovo.getTitulo());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							temaNovo.getTitulo() + " cadastrado com sucesso."));

			carregarTabela(event);
			limparSessao();
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

	}

	public void editarTema(ActionEvent event) {

		try {
			TemasService temasService = new TemasServiceImpl();
			temasService.alterar(temaSelecionado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							temaSelecionado.getTitulo()
									+ " alterado com sucesso."));
			carregarTabela(event);
			limparSessao();
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

	}

	public void excluirTema(ActionEvent event) {

		try {
			TemasService temasService = new TemasServiceImpl();
			temasService.remover(temaSelecionado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							temaSelecionado.getTitulo()
									+ " removido com sucesso."));
			carregarTabela(event);
			limparSessao();
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

	}

	private void limparSessao() {
		temaNovo = new TemaDTO();
		temaSelecionado = new TemaDTO();
	}

	public TemaDTO getTemaNovo() {
		return temaNovo;
	}

	public void setTemaNovo(TemaDTO temaNovo) {
		this.temaNovo = temaNovo;
	}

	public TemaDTO getTemaSelecionado() {
		return temaSelecionado;
	}

	public void setTemaSelecionado(TemaDTO temaSelecionado) {
		this.temaSelecionado = temaSelecionado;
	}

	public List<TemaDTO> getListaTemas() {
		return listaTemas;
	}

	public void setListaTemas(List<TemaDTO> listaTemas) {
		this.listaTemas = listaTemas;
	}

}
