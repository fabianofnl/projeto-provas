package br.com.sgpo.dto;

import java.io.Serializable;

/**
 * @author Roseli
 * 
 */
public class NotaMediaEquipesDTO implements Serializable {

	private static final long serialVersionUID = 271346341585539205L;

	private Integer matriculaGerente;
	private String nomeGerente;
	private Integer qtdAcertos;
	private Integer qtdQuestoes;
	private Double media;

	public NotaMediaEquipesDTO() {
	}

	public Integer getMatriculaGerente() {
		return matriculaGerente;
	}

	public void setMatriculaGerente(Integer matriculaGerente) {
		this.matriculaGerente = matriculaGerente;
	}

	public String getNomeGerente() {
		return nomeGerente;
	}

	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}

	public Integer getQtdAcertos() {
		return qtdAcertos;
	}

	public void setQtdAcertos(Integer qtdAcertos) {
		this.qtdAcertos = qtdAcertos;
	}

	public Integer getQtdQuestoes() {
		return qtdQuestoes;
	}

	public void setQtdQuestoes(Integer qtdQuestoes) {
		this.qtdQuestoes = qtdQuestoes;
	}

	public Double getMedia() {
		return media;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

}
