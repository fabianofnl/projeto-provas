package br.com.sgpo.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.model.FuncionarioDTO;
import br.com.sgpo.model.PerfilDTO;

public interface FuncionarioDao {

	public List<FuncionarioDTO> listarFuncionarios() throws SQLException,
			ClassNotFoundException;

	public List<PerfilDTO> listarPerfis() throws SQLException,
			ClassNotFoundException;

	public void gravar(FuncionarioDTO funcionario) throws SQLException,
			ClassNotFoundException;

}
