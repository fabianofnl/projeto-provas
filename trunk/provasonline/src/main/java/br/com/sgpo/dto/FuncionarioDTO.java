package br.com.sgpo.dto;

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
	 * M�todo retorna <b>matricula</b> do usuario.
	 * 
	 * @return matricula
	 */
	public Integer getMatricula() {
		return matricula;
	}

	/**
	 * M�todo atribui <b>matricula</b> ao usuario.
	 * 
	 * @param matricula
	 */
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	/**
	 * M�todo retorna <b>nome</b> do usuario.
	 * 
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * M�todo atribui <b>nome</b> ao usuario.
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * M�todo retorna objeto <b>email</b> do usuario.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * M�todo atribui objeto <b>email</b> ao usuario
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * M�todo retorna objeto <b>email</b> do usuario.
	 * 
	 * @return funcao
	 */
	public String getFuncao() {
		return funcao;
	}

	/**
	 * M�todo atribui objeto <b>funcao</b> ao usuario
	 * 
	 * @param funcao
	 */
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

}