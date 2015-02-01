package br.com.sgpo.dto;

import java.io.Serializable;

/**
 * Classe POJO/BEAN/DTO que representa o RelatorioDadosGerais do sistema
 * 
 * @author Roseli
 * 
 */
public class RelatorioDadosGeraisDTO implements Serializable {

	private static final long serialVersionUID = -4255508881439686992L;

	private Integer qtdFuncionariosAtivos;
	private Integer qtdFuncionariosInativos;
	private Integer qtdEquipes;
	private Integer qtdProvasAgendadas;
	private Integer qtdProvasRealizadas;
	private Integer qtdProvasNaoRealizadas;
	private Integer qtdTemas;
	private Integer qtdProvas;
	private Integer qtdQuestoes;
	private Integer qtdOpcoes;
	private Integer qtdApostilas;

	public RelatorioDadosGeraisDTO() {
	}

	public Integer getQtdFuncionariosAtivos() {
		return qtdFuncionariosAtivos;
	}

	public void setQtdFuncionariosAtivos(Integer qtdFuncionariosAtivos) {
		this.qtdFuncionariosAtivos = qtdFuncionariosAtivos;
	}

	public Integer getQtdFuncionariosInativos() {
		return qtdFuncionariosInativos;
	}

	public void setQtdFuncionariosInativos(Integer qtdFuncionariosInativos) {
		this.qtdFuncionariosInativos = qtdFuncionariosInativos;
	}

	public Integer getQtdEquipes() {
		return qtdEquipes;
	}

	public void setQtdEquipes(Integer qtdEquipes) {
		this.qtdEquipes = qtdEquipes;
	}

	public Integer getQtdProvasAgendadas() {
		return qtdProvasAgendadas;
	}

	public void setQtdProvasAgendadas(Integer qtdProvasAgendadas) {
		this.qtdProvasAgendadas = qtdProvasAgendadas;
	}

	public Integer getQtdProvasRealizadas() {
		return qtdProvasRealizadas;
	}

	public void setQtdProvasRealizadas(Integer qtdProvasRealizadas) {
		this.qtdProvasRealizadas = qtdProvasRealizadas;
	}

	public Integer getQtdProvasNaoRealizadas() {
		return qtdProvasNaoRealizadas;
	}

	public void setQtdProvasNaoRealizadas(Integer qtdProvasNaoRealizadas) {
		this.qtdProvasNaoRealizadas = qtdProvasNaoRealizadas;
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
