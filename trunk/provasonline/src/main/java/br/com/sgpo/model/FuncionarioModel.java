package br.com.sgpo.model;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.PerfilDTO;

public interface FuncionarioModel {

	public List<FuncionarioDTO> listarFuncionarios(Integer offSet,
			Integer recordPerPage) throws SQLException, ClassNotFoundException;

	public List<PerfilDTO> listarPerfis() throws SQLException,
			ClassNotFoundException;

	public void gravar(FuncionarioDTO funcionario) throws SQLException,
			ClassNotFoundException;

	public Integer getTotalRegistros() throws SQLException,
			ClassNotFoundException;

}
