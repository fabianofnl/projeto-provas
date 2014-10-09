package br.com.sgpo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roseli
 * 
 */
public class QuestaoDTO extends TemasDTO implements Serializable {

	private static final long serialVersionUID = 1670332519379095776L;

	private Integer questaoId;
	private String tituloQuestao;
	private String descricaoQuestao;
	private Integer quantidadeOpcoes;
	private List<OpcaoDTO> listaOpcoes = new ArrayList<OpcaoDTO>();

	public QuestaoDTO() {
	}

	public Integer getQuestaoId() {
		return questaoId;
	}

	public void setQuestaoId(Integer questaoId) {
		this.questaoId = questaoId;
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

	public List<OpcaoDTO> getListaOpcoes() {
		return listaOpcoes;
	}

	public void setListaOpcoes(List<OpcaoDTO> listaOpcoes) {
		this.listaOpcoes = listaOpcoes;
	}
}
