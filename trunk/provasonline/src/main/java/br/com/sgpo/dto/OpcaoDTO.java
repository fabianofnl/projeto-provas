package br.com.sgpo.dto;

import java.io.Serializable;


/**
 * @author Roseli
 *
 */
public class OpcaoDTO implements Serializable {

	private static final long serialVersionUID = 4420791152482835526L;
	private Integer opcaoId;
	private String titulo;
	private Boolean flag;

	public OpcaoDTO() {
	}

	public Integer getOpcaoId() {
		return opcaoId;
	}

	public void setOpcaoId(Integer opcaoId) {
		this.opcaoId = opcaoId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

}
