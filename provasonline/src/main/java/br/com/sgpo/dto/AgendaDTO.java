package br.com.sgpo.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Roseli
 * 
 */
public class AgendaDTO implements Serializable {

	private static final long serialVersionUID = 219758127481788900L;
	private Integer agendaId;
	private FuncionarioDTO funcionario;
	private ProvaDTO prova;
	private Date provaAgendada;
	private Boolean flag;
	private Integer vencido;
	private Integer hoje;

	public AgendaDTO() {
	}

	public Integer getAgendaId() {
		return agendaId;
	}

	public void setAgendaId(Integer agendaId) {
		this.agendaId = agendaId;
	}

	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
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

	public Integer getVencido() {
		return vencido;
	}

	public void setVencido(Integer vencido) {
		this.vencido = vencido;
	}

	public Integer getHoje() {
		return hoje;
	}

	public void setHoje(Integer hoje) {
		this.hoje = hoje;
	}
}
