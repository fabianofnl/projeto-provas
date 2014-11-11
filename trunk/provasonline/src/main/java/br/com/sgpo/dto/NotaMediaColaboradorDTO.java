package br.com.sgpo.dto;

import java.io.Serializable;

/**
 * @author Roseli
 * 
 */
public class NotaMediaColaboradorDTO implements Serializable {

	private static final long serialVersionUID = 3933485695695256565L;

	private String nome;
	private Integer matricula;
	private Integer questoes;
	private Integer acertos;

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

	public Integer getQuestoes() {
		return questoes;
	}

	public void setQuestoes(Integer questoes) {
		this.questoes = questoes;
	}

	public Integer getAcertos() {
		return acertos;
	}

	public void setAcertos(Integer acertos) {
		this.acertos = acertos;
	}
}
