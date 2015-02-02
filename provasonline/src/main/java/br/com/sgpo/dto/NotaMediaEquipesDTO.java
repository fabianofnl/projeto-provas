package br.com.sgpo.dto;

import java.io.Serializable;

/**
 * Classe POJO/BEAN/DTO que representa a NotaMediaEquipe do sistema
 * 
 * @author Roseli
 * 
 */
public class NotaMediaEquipesDTO implements Serializable {

	private static final long serialVersionUID = 271346341585539205L;

	private Integer matriculaGerente;
	private String nomeGerente;
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

	public Double getMedia() {
		return media;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

}
