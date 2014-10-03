package br.com.sgpo.dto;

import java.io.Serializable;
import java.util.List;

public class EquipeDTO implements Serializable {

	private static final long serialVersionUID = -4094261979164714253L;

	private FuncionarioDTO gerente;
	private List<FuncionarioDTO> listaColaboradores;

	public FuncionarioDTO getGerente() {
		return gerente;
	}

	public void setGerente(FuncionarioDTO gerente) {
		this.gerente = gerente;
	}

	public List<FuncionarioDTO> getListaColaboradores() {
		return listaColaboradores;
	}

	public void setListaColaboradores(List<FuncionarioDTO> listaColaboradores) {
		this.listaColaboradores = listaColaboradores;
	}
}
