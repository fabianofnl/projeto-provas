package br.com.sgpo.dto;

import java.io.Serializable;

/**
 * Classe POJO/BEAN/DTO que representa a Agenda do sistema.
 * Categoriza��o/perfil
 * de cada usu�rio, fornece o n�vel de autoriza��o do sistema.
 * 
 * @author Roseli
 * 
 */
public class PerfilDTO implements Serializable {

	private static final long serialVersionUID = -456106320628876359L;

	private Integer perfilId;
	private String descricao;
	private String role;

	public PerfilDTO() {
	}

	/**
	 * M�todo retorna o <b>id</b> do perfil.
	 * 
	 * @return perfilId
	 */
	public Integer getPerfilId() {
		return perfilId;
	}

	/**
	 * M�todo atribui <b>id</b> ao perfil.
	 * 
	 * @param perfilId
	 */
	public void setPerfilId(Integer perfilId) {
		this.perfilId = perfilId;
	}

	/**
	 * M�todo retorna a <b>descri��o</b> do perfil.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * M�todo atribui <b>descricao</b> ao perfil.
	 * 
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * M�todo retorna <b>role</b> do perfil.
	 * 
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * M�todo atribui <b>role</b> ao perfil.
	 * 
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((perfilId == null) ? 0 : perfilId.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilDTO other = (PerfilDTO) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (perfilId == null) {
			if (other.perfilId != null)
				return false;
		} else if (!perfilId.equals(other.perfilId))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
}
