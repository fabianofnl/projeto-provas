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

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.PerfilDTO;
import br.com.sgpo.service.FuncionarioService;
import br.com.sgpo.service.FuncionarioServiceImpl;

/**
 * Classe responsável pelo controle e gerenciamento dos Funcionários
 * 
 * @author Roseli
 * 
 */
@ManagedBean
@ViewScoped
public class FuncionarioManagedBean implements Serializable {

	private static final long serialVersionUID = 6346351023373255811L;
	private static final Logger LOG = Logger
			.getLogger(FuncionarioManagedBean.class);

	private List<FuncionarioDTO> listaFuncionarios = new ArrayList<FuncionarioDTO>();
	private List<PerfilDTO> listaPerfis = new ArrayList<PerfilDTO>();
	private FuncionarioDTO funcionarioSelecionado = new FuncionarioDTO();
	private FuncionarioDTO funcionarioNovo = new FuncionarioDTO();

	/**
	 * Método que carrega a lista de funcionários cadastrados na base de dados,
	 * essa chamada é feito através de uma requisição ajax do Primefaces/JSF
	 * 
	 * @param event
	 */
	public void carregarTabela(ActionEvent event) {

		try {
			FuncionarioService funcionarioService = new FuncionarioServiceImpl();
			listaFuncionarios = funcionarioService.listarFuncionarios();

			LOG.info("Total de funcionarios: " + listaFuncionarios.size());

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
	 * Método que cadastra o funcionário no sistema.
	 * 
	 * @param event
	 */
	public void cadastrarFuncionario(ActionEvent event) {

		try {
			FuncionarioService funcionarioService = new FuncionarioServiceImpl();
			funcionarioService.gravar(funcionarioNovo);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							funcionarioNovo.getNome()
									+ " cadastrado(a) com sucesso."));

			carregarTabela(event);
			limparSessao();

		} catch (ClassNotFoundException e) {

			/**
			 * Linha abaixo força um erro de conversão ou validação Devido ao
			 * uso do componente Dialog, foi necessário utilizar para que o
			 * Dialog permaneça aberto durante o erro.
			 */
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {

			/**
			 * Linha abaixo força um erro de conversão ou validação Devido ao
			 * uso do componente Dialog, foi necessário utilizar para que o
			 * Dialog permaneça aberto durante o erro.
			 */
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}

	}

	/**
	 * Método que efetiva a alteração de dados do funcionário.
	 * 
	 * @param event
	 */
	public void editarFuncionario(ActionEvent event) {

		try {
			FuncionarioService funcionarioService = new FuncionarioServiceImpl();

			LOG.info("Matricula atual: "
					+ funcionarioSelecionado.getMatricula()
					+ " - matrícula antiga: "
					+ funcionarioSelecionado.getMatriculaAntiga());

			funcionarioService.alterar(funcionarioSelecionado,
					funcionarioSelecionado.getMatriculaAntiga());

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							funcionarioSelecionado.getNome()
									+ " alterado(a) com sucesso."));
			carregarTabela(event);
			limparSessao();
		} catch (ClassNotFoundException e) {

			/**
			 * Linha abaixo força um erro de conversão ou validação Devido ao
			 * uso do componente Dialog, foi necessário utilizar para que o
			 * Dialog permaneça aberto durante o erro.
			 */
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
	 * Método que inativa um funcionário.
	 * 
	 * @param event
	 */
	public void inativarFuncionario(ActionEvent event) {

		try {
			FuncionarioService funcionarioService = new FuncionarioServiceImpl();
			funcionarioService.inativar(funcionarioSelecionado.getMatricula());

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							funcionarioSelecionado.getNome()
									+ " inativado(a) com sucesso."));
			carregarTabela(event);
			limparSessao();
		} catch (ClassNotFoundException e) {

			/**
			 * Linha abaixo força um erro de conversão ou validação Devido ao
			 * uso do componente Dialog, foi necessário utilizar para que o
			 * Dialog permaneça aberto durante o erro.
			 */
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
	 * Método que carrega a lista de perfis do sistema.
	 * 
	 * @param event
	 */
	public void carregarPerfil(ActionEvent event) {
		try {
			FuncionarioService funcionarioService = new FuncionarioServiceImpl();
			listaPerfis = funcionarioService.listarPerfis();
			LOG.info("Total de Perfis: " + listaPerfis.size());
		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	/**
	 * Método que limpa os dados da sessão após o cadastro, edição ou inativação
	 * de um funcionario
	 */
	private void limparSessao() {
		funcionarioNovo = new FuncionarioDTO();
		funcionarioSelecionado = new FuncionarioDTO();

	}

	public List<FuncionarioDTO> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<FuncionarioDTO> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public FuncionarioDTO getFuncionarioSelecionado() {
		LOG.info("GET funcionario selecionado: "
				+ funcionarioSelecionado.getNome());
		funcionarioSelecionado.setMatriculaAntiga(funcionarioSelecionado
				.getMatricula());
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(FuncionarioDTO funcionarioSelecionado) {
		LOG.info("SET funcionario selecionado: "
				+ funcionarioSelecionado.getNome());
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public FuncionarioDTO getFuncionarioNovo() {
		return funcionarioNovo;
	}

	public void setFuncionarioNovo(FuncionarioDTO funcionarioNovo) {
		this.funcionarioNovo = funcionarioNovo;
	}

	public List<PerfilDTO> getListaPerfis() {
		return listaPerfis;
	}

	public void setListaPerfis(List<PerfilDTO> listaPerfis) {
		this.listaPerfis = listaPerfis;
	}
}
