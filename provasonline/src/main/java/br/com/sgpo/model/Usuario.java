package br.com.sgpo.model;

import java.io.Serializable;

/**
 * Classe para realização da autenticação do sistema.
 * 
 * @author Roseli
 * 
 */
public class Usuario implements Serializable {

	private static final long serialVersionUID = -1003024660686069159L;

	private Integer usuarioId;
	private String nomeUsuario;
	private String senha;
	private Perfil perfil;

	public Usuario() {
	}

	/**
	 * Método retorna <b>usuarioId</b> do usuario.
	 * 
	 * @return usuarioId
	 */
	public Integer getUsuarioId() {
		return usuarioId;
	}

	/**
	 * Método atribui <b>usuarioId</b> ao usuario.
	 * 
	 * @param usuarioId
	 */
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
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

	/**
	 * Método retorna objeto <b>perfil</b> do usuario.
	 * 
	 * @return perfil
	 */
	public Perfil getPerfil() {
		return perfil;
	}

	/**
	 * Método atribui objeto <perfil> ao usuario.
	 * 
	 * @param perfil
	 */
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
