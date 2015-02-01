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
	private Long questoes;
	private Long acertos;
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

	public Long getQuestoes() {
		return questoes;
	}

	public void setQuestoes(Long questoes) {
		this.questoes = questoes;
	}

	public Long getAcertos() {
		return acertos;
	}

	public void setAcertos(Long acertos) {
		this.acertos = acertos;
	}

	public Double getMedia() {
		return media;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

}
