package br.com.sgpo.dto;

import java.io.Serializable;

/**
 * Classe POJO/BEAN/DTO que representa a NotaMediaColaborador do sistema
 * 
 * @author Roseli
 * 
 */
public class NotaMediaColaboradorDTO implements Serializable {

	private static final long serialVersionUID = 3933485695695256565L;

	private String nome;
	private Integer matricula;
	private Double media;

	public NotaMediaColaboradorDTO() {
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getMedia() {
		return media;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

}
