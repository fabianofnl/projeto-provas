package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.model.FuncionarioDTO;
import br.com.sgpo.model.PerfilDTO;

/**
 * @author Roseli
 * 
 */
public interface FuncionarioService {

	public List<FuncionarioDTO> listarFuncionarios() throws SQLException,
			ClassNotFoundException;

	public List<PerfilDTO> listarPerfis() throws SQLException,
			ClassNotFoundException;

	public void gravar(FuncionarioDTO funcionario) throws SQLException,
			ClassNotFoundException;

}
