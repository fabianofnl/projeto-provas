package br.com.sgpo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe POJO/BEAN/DTO que representa a Equipe do sistema
 * 
 * @author Roseli
 * 
 */
public class EquipeDTO implements Serializable {

	private static final long serialVersionUID = -4094261979164714253L;

	private FuncionarioDTO gerente = new FuncionarioDTO();
	private List<FuncionarioDTO> listaColaboradores = new ArrayList<FuncionarioDTO>();

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gerente == null) ? 0 : gerente.hashCode());
		result = prime
				* result
				+ ((listaColaboradores == null) ? 0 : listaColaboradores
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquipeDTO other = (EquipeDTO) obj;
		if (gerente == null) {
			if (other.gerente != null)
				return false;
		} else if (!gerente.equals(other.gerente))
			return false;
		if (listaColaboradores == null) {
			if (other.listaColaboradores != null)
				return false;
		} else if (!listaColaboradores.equals(other.listaColaboradores))
			return false;
		return true;
	}
}
