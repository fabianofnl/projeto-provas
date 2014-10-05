package br.com.sgpo.dto;

import java.io.Serializable;

/**
 * @author Roseli
 * 
 */
public class TemasDTO implements Serializable {

	private static final long serialVersionUID = -4052547986860370131L;

	private Integer temaId;
	private String titulo;
	private String descricao;

	public TemasDTO() {
	}

	/**
	 * Método retorna o <b>id</b> do Tema.
	 * 
	 * @return temaId
	 */
	public Integer getTemaId() {
		return temaId;
	}

	/**
	 * Método atribui <b>id</b> ao Tema.
	 * 
	 * @param temaId
	 */
	public void setTemaId(Integer temaId) {
		this.temaId = temaId;
	}

	/**
	 * Método retorna o <b>titulo</b> do Tema.
	 * 
	 * @return titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Método atribui <b>titulo</b> ao Tema.
	 * 
	 * @param titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Método retorna o <b>descricao</b> do Tema.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Método atribui <b>descricao</b> ao Tema.
	 * 
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
