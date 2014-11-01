package br.com.sgpo.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Roseli
 *
 */
public class ProvaRealizadaDTO implements Serializable {

	private static final long serialVersionUID = -4842133775845875099L;

	private Integer provaRealizadaId;
	private Integer provaId;
	private String tituloProva;
	private AgendaDTO agenda;
	private Date dataHoraInicio;
	private Date dataHoraFim;
	private Date dataHoraFinalizado;
	private Integer quantidadeQuestoes;
	private Integer quantidadeAcertos;

	public ProvaRealizadaDTO() {
	}

	public Integer getProvaRealizadaId() {
		return provaRealizadaId;
	}

	public void setProvaRealizadaId(Integer provaRealizadaId) {
		this.provaRealizadaId = provaRealizadaId;
	}

	public Integer getProvaId() {
		return provaId;
	}

	public void setProvaId(Integer provaId) {
		this.provaId = provaId;
	}

	public String getTituloProva() {
		return tituloProva;
	}

	public void setTituloProva(String tituloProva) {
		this.tituloProva = tituloProva;
	}

	public AgendaDTO getAgenda() {
		return agenda;
	}

	public void setAgenda(AgendaDTO agenda) {
		this.agenda = agenda;
	}

	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	public Date getDataHoraFinalizado() {
		return dataHoraFinalizado;
	}

	public void setDataHoraFinalizado(Date dataHoraFinalizado) {
		this.dataHoraFinalizado = dataHoraFinalizado;
	}

	public Integer getQuantidadeQuestoes() {
		return quantidadeQuestoes;
	}

	public void setQuantidadeQuestoes(Integer quantidadeQuestoes) {
		this.quantidadeQuestoes = quantidadeQuestoes;
	}

	public Integer getQuantidadeAcertos() {
		return quantidadeAcertos;
	}

	public void setQuantidadeAcertos(Integer quantidadeAcertos) {
		this.quantidadeAcertos = quantidadeAcertos;
	}
}
