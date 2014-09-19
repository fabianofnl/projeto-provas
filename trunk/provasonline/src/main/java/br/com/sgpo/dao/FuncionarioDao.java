package br.com.sgpo.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.model.Funcionario;
import br.com.sgpo.model.Perfil;

public interface FuncionarioDao {

	public List<Funcionario> listarFuncionarios() throws SQLException, ClassNotFoundException;

	public List<Perfil> listarPerfis() throws SQLException, ClassNotFoundException;

}
