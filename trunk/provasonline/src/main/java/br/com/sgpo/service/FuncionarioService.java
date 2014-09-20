package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.model.Funcionario;
import br.com.sgpo.model.Perfil;

/**
 * @author Roseli
 * 
 */
public interface FuncionarioService {

	public List<Funcionario> listarFuncionarios() throws SQLException,
			ClassNotFoundException;

	public List<Perfil> listarPerfis() throws SQLException,
			ClassNotFoundException;

	public void gravar(Funcionario funcionario) throws SQLException,
			ClassNotFoundException;

}
