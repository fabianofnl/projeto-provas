package br.com.sgpo.model;

import java.io.Serializable;

/**
 * Classe de categorização/perfil de cada usuário, fornece o nível de
 * autorização do sistema.
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
	 * Método retorna o <b>id</b> do perfil.
	 * 
	 * @return perfilId
	 */
	public Integer getPerfilId() {
		return perfilId;
	}

	/**
	 * Método atribui <b>id</b> ao perfil.
	 * 
	 * @param perfilId
	 */
	public void setPerfilId(Integer perfilId) {
		this.perfilId = perfilId;
	}

	/**
	 * Método retorna a <b>descrição</b> do perfil.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Método atribui <b>descricao</b> ao perfil.
	 * 
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Método retorna <b>role</b> do perfil.
	 * 
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Método atribui <b>role</b> ao perfil.
	 * 
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
