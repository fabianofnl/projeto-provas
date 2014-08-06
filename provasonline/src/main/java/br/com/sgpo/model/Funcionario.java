package br.com.sgpo.model;

import java.io.Serializable;

/**
 * Classe para identifica��o do funcion�rio do sistema.
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
	 * M�todo retorna <b>funcionarioId</b> do funcion�rio.
	 * 
	 * @return funcionarioId
	 */
	public Integer getFuncionarioId() {
		return funcionarioId;
	}

	/**
	 * M�todo atribui <b>funcionarioId</b> ao funcion�rio.
	 * 
	 * @param funcionarioId
	 */
	public void setFuncionarioId(Integer funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	/**
	 * M�todo retorna <b>matricula</b> do funcion�rio.
	 * 
	 * @return
	 */
	public Integer getMatricula() {
		return matricula;
	}

	/**
	 * M�todo atribui <b>matricula</b> ao funcion�rio.
	 * 
	 * @param matricula
	 */
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	/**
	 * M�todo retorna <b>nome</b> do funcion�rio.
	 * 
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * M�todo atribui <b>nome</b> ao funcion�rio.
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * M�todo retorna <b>usuario</b> do funcion�rio.
	 * 
	 * @return usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * M�todo atribui objeto <b>usuario</b> ao funcion�rio.
	 * 
	 * @param usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
