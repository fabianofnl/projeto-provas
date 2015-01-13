package br.com.sgpo.dto;

import java.io.Serializable;

/**
 * Classe para realização da autenticação do sistema.
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
	 * Método retorna <b>nomeUsuario</b> do usuario.
	 * 
	 * @return usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Método atribui <b>usuario</b> ao usuario.
	 * 
	 * @param nomeUsuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioDTO other = (UsuarioDTO) obj;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
}
