package br.com.sgpo.model;

import java.io.Serializable;

/**
 * Classe para realização da autenticação do sistema.
 * 
 * @author Roseli
 * 
 */
public class Usuario extends Perfil implements Serializable {

	private static final long serialVersionUID = -1003024660686069159L;

	private String nomeUsuario;
	private String senha;

	public Usuario() {
	}

	/**
	 * Método retorna <b>nomeUsuario</b> do usuario.
	 * 
	 * @return nomeUsuario
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * Método atribui <b>nomeUsuario</b> ao usuario.
	 * 
	 * @param nomeUsuario
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	/**
	 * Método retorna <b>senha</b> do usuario.
	 * 
	 * @return senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Método atribui <b>senha</b> ao usuario.
	 * 
	 * @param senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
