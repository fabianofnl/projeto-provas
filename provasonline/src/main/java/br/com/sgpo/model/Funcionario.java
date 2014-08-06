package br.com.sgpo.model;

import java.io.Serializable;

/**
 * Classe para identificação do funcionário do sistema.
 * 
 * @author Roseli
 * 
 */
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 3576824392171045517L;

	private Integer funcionarioId;
	private Integer matricula;
	private String nome;
	private Usuario usuario;

	public Funcionario() {
	}

	/**
	 * Método retorna <b>funcionarioId</b> do funcionário.
	 * 
	 * @return funcionarioId
	 */
	public Integer getFuncionarioId() {
		return funcionarioId;
	}

	/**
	 * Método atribui <b>funcionarioId</b> ao funcionário.
	 * 
	 * @param funcionarioId
	 */
	public void setFuncionarioId(Integer funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	/**
	 * Método retorna <b>matricula</b> do funcionário.
	 * 
	 * @return
	 */
	public Integer getMatricula() {
		return matricula;
	}

	/**
	 * Método atribui <b>matricula</b> ao funcionário.
	 * 
	 * @param matricula
	 */
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	/**
	 * Método retorna <b>nome</b> do funcionário.
	 * 
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Método atribui <b>nome</b> ao funcionário.
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Método retorna <b>usuario</b> do funcionário.
	 * 
	 * @return usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Método atribui objeto <b>usuario</b> ao funcionário.
	 * 
	 * @param usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
