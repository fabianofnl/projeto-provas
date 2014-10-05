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
	private Integer quantidadeQuestoes;

	public TemasDTO() {
	}

	/**
	 * M�todo retorna o <b>id</b> do Tema.
	 * 
	 * @return temaId
	 */
	public Integer getTemaId() {
		return temaId;
	}

	/**
	 * M�todo atribui <b>id</b> ao Tema.
	 * 
	 * @param temaId
	 */
	public void setTemaId(Integer temaId) {
		this.temaId = temaId;
	}

	/**
	 * M�todo retorna o <b>titulo</b> do Tema.
	 * 
	 * @return titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * M�todo atribui <b>titulo</b> ao Tema.
	 * 
	 * @param titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * M�todo retorna o <b>descricao</b> do Tema.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * M�todo atribui <b>descricao</b> ao Tema.
	 * 
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * M�todo retorna o <b>quantidadeQuestoes</b> do Tema.
	 * 
	 * @return quantidadeQuestoes
	 */
	public Integer getQuantidadeQuestoes() {
		return quantidadeQuestoes;
	}

	/**
	 * M�todo atribui <b>quantidadeQuestoes</b> ao Tema.
	 * 
	 * @param quantidadeQuestoes
	 */
	public void setQuantidadeQuestoes(Integer quantidadeQuestoes) {
		this.quantidadeQuestoes = quantidadeQuestoes;
	}
	
	
}
