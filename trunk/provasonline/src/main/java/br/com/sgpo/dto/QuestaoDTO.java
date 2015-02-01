package br.com.sgpo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe POJO/BEAN/DTO que representa a Questao do sistema
 * 
 * @author Roseli
 * 
 */
public class QuestaoDTO extends TemaDTO implements Serializable {

	private static final long serialVersionUID = 2156652037096892053L;

	private Integer questaoId;
	private Integer provaId;
	private String tituloQuestao;
	private String descricaoQuestao;
	private Integer quantidadeOpcoes;
	private String opcaoSelecionada;
	private List<OpcaoDTO> listaOpcoes = new ArrayList<OpcaoDTO>();

	public QuestaoDTO() {
	}

	public Integer getQuestaoId() {
		return questaoId;
	}

	public void setQuestaoId(Integer questaoId) {
		this.questaoId = questaoId;
	}

	public Integer getProvaId() {
		return provaId;
	}

	public void setProvaId(Integer provaId) {
		this.provaId = provaId;
	}

	public String getTituloQuestao() {
		return tituloQuestao;
	}

	public void setTituloQuestao(String tituloQuestao) {
		this.tituloQuestao = tituloQuestao;
	}

	public String getDescricaoQuestao() {
		return descricaoQuestao;
	}

	public void setDescricaoQuestao(String descricaoQuestao) {
		this.descricaoQuestao = descricaoQuestao;
	}

	public Integer getQuantidadeOpcoes() {
		return quantidadeOpcoes;
	}

	public void setQuantidadeOpcoes(Integer quantidadeOpcoes) {
		this.quantidadeOpcoes = quantidadeOpcoes;
	}

	public String getOpcaoSelecionada() {
		return opcaoSelecionada;
	}

	public void setOpcaoSelecionada(String opcaoSelecionada) {
		this.opcaoSelecionada = opcaoSelecionada;
	}

	public List<OpcaoDTO> getListaOpcoes() {
		return listaOpcoes;
	}

	public void setListaOpcoes(List<OpcaoDTO> listaOpcoes) {
		this.listaOpcoes = listaOpcoes;
	}
}
