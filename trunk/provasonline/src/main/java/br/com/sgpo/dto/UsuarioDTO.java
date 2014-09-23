package br.com.sgpo.dto;

import java.io.Serializable;

/**
 * Classe para realiza��o da autentica��o do sistema.
 * 
 * @author Roseli
 * 
 */
public class UsuarioDTO extends PerfilDTO implements Serializable {

	private static final long serialVersionUID = -1003024660686069159L;

	private String usuario;
	private String senha;

	public UsuarioDTO() {
	}

	/**
	 * M�todo retorna <b>nomeUsuario</b> do usuario.
	 * 
	 * @return usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * M�todo atribui <b>usuario</b> ao usuario.
	 * 
	 * @param nomeUsuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

}
