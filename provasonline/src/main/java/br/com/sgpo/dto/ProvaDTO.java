package br.com.sgpo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roseli
 * 
 */
public class ProvaDTO implements Serializable {

	private static final long serialVersionUID = -565165123693169778L;
	private Integer provaId;
	private String titulo;
	private Integer quantidadeTemas;
	private Integer quantidadeQuestoes;
	private List<TemaDTO> listaTemas = new ArrayList<TemaDTO>();

	public ProvaDTO() {
	}

	public Integer getProvaId() {
		return provaId;
	}

	public void setProvaId(Integer provaId) {
		this.provaId = provaId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getQuantidadeTemas() {
		return quantidadeTemas;
	}

	public void setQuantidadeTemas(Integer quantidadeTemas) {
		this.quantidadeTemas = quantidadeTemas;
	}

	public Integer getQuantidadeQuestoes() {
		return quantidadeQuestoes;
	}

	public void setQuantidadeQuestoes(Integer quantidadeQuestoes) {
		this.quantidadeQuestoes = quantidadeQuestoes;
	}

	public List<TemaDTO> getListaTemas() {
		return listaTemas;
	}

	public void setListaTemas(List<TemaDTO> listaTemas) {
		this.listaTemas = listaTemas;
	}

}
