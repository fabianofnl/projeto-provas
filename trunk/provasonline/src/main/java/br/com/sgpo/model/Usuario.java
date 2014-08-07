package br.com.sgpo.model;

import java.io.Serializable;

/**
 * Classe para realiza��o da autentica��o do sistema.
 * 
 * @author Roseli
 * 
 */
public class Usuario implements Serializable {

	private static final long serialVersionUID = -1003024660686069159L;

	private Integer matricula;
	private String nome;
	private String nomeUsuario;
	private String senha;
	private Perfil perfil;

	public Usuario() {
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
	 * M�todo retorna <b>nomeUsuario</b> do usuario.
	 * 
	 * @return nomeUsuario
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * M�todo atribui <b>nomeUsuario</b> ao usuario.
	 * 
	 * @param nomeUsuario
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	/**
	 * M�todo retorna <b>senha</b> do usuario.
	 * 
	 * @return senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * M�todo atribui <b>senha</b> ao usuario.
	 * 
	 * @param senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * M�todo retorna objeto <b>perfil</b> do usuario.
	 * 
	 * @return perfil
	 */
	public Perfil getPerfil() {
		return perfil;
	}

	/**
	 * M�todo atribui objeto <perfil> ao usuario.
	 * 
	 * @param perfil
	 */
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
