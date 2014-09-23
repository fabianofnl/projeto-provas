package br.com.sgpo.model;

import java.io.Serializable;

public class FuncionarioDTO extends UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 3576824392171045517L;

	private Integer matricula;
	private String nome;
	private String email;
	private String funcao;

	public FuncionarioDTO() {
	}

	/**
	 * Método retorna <b>matricula</b> do usuario.
	 * 
	 * @return matricula
	 */
	public Integer getMatricula() {
		return matricula;
	}

	/**
	 * Método atribui <b>matricula</b> ao usuario.
	 * 
	 * @param matricula
	 */
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	/**
	 * Método retorna <b>nome</b> do usuario.
	 * 
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Método atribui <b>nome</b> ao usuario.
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Método retorna objeto <b>email</b> do usuario.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Método atribui objeto <b>email</b> ao usuario
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Método retorna objeto <b>email</b> do usuario.
	 * 
	 * @return funcao
	 */
	public String getFuncao() {
		return funcao;
	}

	/**
	 * Método atribui objeto <b>funcao</b> ao usuario
	 * 
	 * @param funcao
	 */
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

}
