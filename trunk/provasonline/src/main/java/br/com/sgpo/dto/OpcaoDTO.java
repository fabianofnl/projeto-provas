package br.com.sgpo.dto;

import java.io.Serializable;

/**
 * @author Roseli
 * 
 */
public class OpcaoDTO extends QuestaoDTO implements Serializable {

	private static final long serialVersionUID = 4420791152482835526L;
	private Integer opcaoId;
	private String tituloOpcao;
	private Integer quantidadeProvas;
	private Boolean flag;

	public OpcaoDTO() {
	}

	public Integer getOpcaoId() {
		return opcaoId;
	}

	public void setOpcaoId(Integer opcaoId) {
		this.opcaoId = opcaoId;
	}

	public String getTituloOpcao() {
		return tituloOpcao;
	}

	public void setTituloOpcao(String tituloOpcao) {
		this.tituloOpcao = tituloOpcao;
	}

	public Integer getQuantidadeProvas() {
		return quantidadeProvas;
	}

	public void setQuantidadeProvas(Integer quantidadeProvas) {
		this.quantidadeProvas = quantidadeProvas;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

}
