package br.com.sgpo.dto;

import java.io.Serializable;

/**
 * @author Roseli
 * 
 */
public class RelatorioDadosGeraisDTO implements Serializable {

	private static final long serialVersionUID = 4149780642095282343L;
	private Integer qtdTemas;
	private Integer qtdProvas;
	private Integer qtdQuestoes;
	private Integer qtdOpcoes;
	private Integer qtdApostilas;

	public RelatorioDadosGeraisDTO() {
	}

	public Integer getQtdTemas() {
		return qtdTemas;
	}

	public void setQtdTemas(Integer qtdTemas) {
		this.qtdTemas = qtdTemas;
	}

	public Integer getQtdProvas() {
		return qtdProvas;
	}

	public void setQtdProvas(Integer qtdProvas) {
		this.qtdProvas = qtdProvas;
	}

	public Integer getQtdQuestoes() {
		return qtdQuestoes;
	}

	public void setQtdQuestoes(Integer qtdQuestoes) {
		this.qtdQuestoes = qtdQuestoes;
	}

	public Integer getQtdOpcoes() {
		return qtdOpcoes;
	}

	public void setQtdOpcoes(Integer qtdOpcoes) {
		this.qtdOpcoes = qtdOpcoes;
	}

	public Integer getQtdApostilas() {
		return qtdApostilas;
	}

	public void setQtdApostilas(Integer qtdApostilas) {
		this.qtdApostilas = qtdApostilas;
	}

}
