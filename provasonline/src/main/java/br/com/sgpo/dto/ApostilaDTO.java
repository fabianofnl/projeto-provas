package br.com.sgpo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roseli
 * 
 */
public class ApostilaDTO implements Serializable {

	private static final long serialVersionUID = 474045842166882818L;

	private Integer apostilaId;
	private String nome;
	private String hashName;
	private String extensao;
	private String serverPath;
	private List<ProvaDTO> listaProvas = new ArrayList<ProvaDTO>();

	public ApostilaDTO() {
	}

	public Integer getApostilaId() {
		return apostilaId;
	}

	public void setApostilaId(Integer apostilaId) {
		this.apostilaId = apostilaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getHashName() {
		return hashName;
	}

	public void setHashName(String hashName) {
		this.hashName = hashName;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	public List<ProvaDTO> getListaProvas() {
		return listaProvas;
	}

	public void setListaProvas(List<ProvaDTO> listaProvas) {
		this.listaProvas = listaProvas;
	}
}
