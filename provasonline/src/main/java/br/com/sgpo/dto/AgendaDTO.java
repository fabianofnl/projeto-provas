package br.com.sgpo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe POJO/BEAN/DTO que representa a Agenda do sistema
 * 
 * @author Roseli
 * 
 */
public class AgendaDTO implements Serializable {

	private static final long serialVersionUID = 219758127481788900L;
	private Integer agendaId;
	private Integer matColaborador;
	private FuncionarioDTO funcionario;
	private Integer provaId;
	private ProvaDTO prova;
	private Date provaAgendada;
	private Boolean flag;
	private Integer hoje;
	private List<ApostilaDTO> listaApostilas = new ArrayList<ApostilaDTO>();

	public AgendaDTO() {
	}

	public Integer getAgendaId() {
		return agendaId;
	}

	public void setAgendaId(Integer agendaId) {
		this.agendaId = agendaId;
	}

	public Integer getMatColaborador() {
		return matColaborador;
	}

	public void setMatColaborador(Integer matColaborador) {
		this.matColaborador = matColaborador;
	}

	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getProvaId() {
		return provaId;
	}

	public void setProvaId(Integer provaId) {
		this.provaId = provaId;
	}

	public ProvaDTO getProva() {
		return prova;
	}

	public void setProva(ProvaDTO prova) {
		this.prova = prova;
	}

	public Date getProvaAgendada() {
		return provaAgendada;
	}

	public void setProvaAgendada(Date provaAgendada) {
		this.provaAgendada = provaAgendada;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Integer getHoje() {
		return hoje;
	}

	public void setHoje(Integer hoje) {
		this.hoje = hoje;
	}

	public List<ApostilaDTO> getListaApostilas() {
		return listaApostilas;
	}

	public void setListaApostilas(List<ApostilaDTO> listaApostilas) {
		this.listaApostilas = listaApostilas;
	}
}
