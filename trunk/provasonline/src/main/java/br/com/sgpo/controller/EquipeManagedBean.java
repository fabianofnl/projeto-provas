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

import br.com.sgpo.dto.EquipeDTO;
import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.service.FuncionarioService;
import br.com.sgpo.service.FuncionarioServiceImpl;

/**
 * @author Roseli
 * 
 */
@ManagedBean
@ViewScoped
public class EquipeManagedBean implements Serializable {

	private static final long serialVersionUID = -9080620132205241355L;

	private static final Logger LOG = Logger.getLogger(EquipeManagedBean.class);
	private List<EquipeDTO> listaEquipes = new ArrayList<EquipeDTO>();
	private List<FuncionarioDTO> listaGerentes = new ArrayList<FuncionarioDTO>();
	private List<FuncionarioDTO> listaColaboradores = new ArrayList<FuncionarioDTO>();
	private List<String> listaColaboradoresSelecionados = new ArrayList<String>();
	private FuncionarioDTO gerenteSelecionado = new FuncionarioDTO();
	private FuncionarioDTO colaboradorSelecionado = new FuncionarioDTO();

	/**
	 * Método que carrega a lista de equipes com seus colaboradores associados.
	 * 
	 * @param event
	 */
	public void carregarTabela(ActionEvent event) {

		try {
			FuncionarioService funcionarioService = new FuncionarioServiceImpl();
			listaEquipes = funcionarioService.listarEquipes();

			LOG.info("Total de equipes: " + listaEquipes.size());

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

	/**
	 * Método que associa colaboradores a equipe selecionada
	 * 
	 * @param event
	 */
	public void associarEquipe(ActionEvent event) {

		try {
			FuncionarioService funcionarioService = new FuncionarioServiceImpl();
			Integer integerMatArray[] = new Integer[listaColaboradoresSelecionados
					.size()];
			int i = 0;

			for (String stringMat : listaColaboradoresSelecionados) {
				integerMatArray[i] = Integer.parseInt(stringMat);
				i++;
			}
			funcionarioService.associarEquipes(
					gerenteSelecionado.getMatricula(), integerMatArray);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							"Equipe cadastrada com sucesso."));

			carregarTabela(event);
			limparSessao();
		} catch (ClassNotFoundException e) {
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}

	}

	/**
	 * Método que remover um colaborador da equipe que foi selecionado.
	 * 
	 * @param event
	 */
	public void removerColaborador(ActionEvent event) {

		try {
			FuncionarioService funcionarioService = new FuncionarioServiceImpl();
			funcionarioService.removerColaborador(colaboradorSelecionado
					.getMatricula());

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							colaboradorSelecionado.getNome()
									+ " removido(a) com sucesso."));

			carregarTabela(event);
			limparSessao();
		} catch (ClassNotFoundException e) {
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um problema inexperado", e);
		}

	}

	/**
	 * Método que carrega a lista de gerente e colaboradores ativos que não
	 * estejam associados com alguma equipe.
	 * 
	 * @param event
	 */
	public void carregarGerentesColaboradores(ActionEvent event) {

		try {
			FuncionarioService funcionarioService = new FuncionarioServiceImpl();
			listaGerentes = funcionarioService.listarGerentes();
			listaColaboradores = funcionarioService
					.listarColaboradoresSemEquipes();
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
		colaboradorSelecionado = new FuncionarioDTO();
		gerenteSelecionado = new FuncionarioDTO();
	}

	public List<EquipeDTO> getListaEquipes() {
		return listaEquipes;
	}

	public void setListaEquipes(List<EquipeDTO> listaEquipes) {
		this.listaEquipes = listaEquipes;
	}

	public List<FuncionarioDTO> getListaGerentes() {
		return listaGerentes;
	}

	public void setListaGerentes(List<FuncionarioDTO> listaGerentes) {
		this.listaGerentes = listaGerentes;
	}

	public List<FuncionarioDTO> getListaColaboradores() {
		return listaColaboradores;
	}

	public void setListaColaboradores(List<FuncionarioDTO> listaColaboradores) {
		this.listaColaboradores = listaColaboradores;
	}

	public List<String> getListaColaboradoresSelecionados() {
		return listaColaboradoresSelecionados;
	}

	public void setListaColaboradoresSelecionados(
			List<String> listaColaboradoresSelecionados) {
		this.listaColaboradoresSelecionados = listaColaboradoresSelecionados;
	}

	public FuncionarioDTO getGerenteSelecionado() {
		return gerenteSelecionado;
	}

	public void setGerenteSelecionado(FuncionarioDTO gerenteSelecionado) {
		this.gerenteSelecionado = gerenteSelecionado;
	}

	public FuncionarioDTO getColaboradorSelecionado() {
		return colaboradorSelecionado;
	}

	public void setColaboradorSelecionado(FuncionarioDTO colaboradorSelecionado) {
		this.colaboradorSelecionado = colaboradorSelecionado;
	}
}
