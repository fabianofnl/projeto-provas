package br.com.sgpo.model;

import java.io.Serializable;

/**
 * Classe de categoriza��o/perfil de cada usu�rio, fornece o n�vel de
 * autoriza��o do sistema.
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

}
