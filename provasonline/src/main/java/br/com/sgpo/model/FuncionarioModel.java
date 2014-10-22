package br.com.sgpo.model;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.EquipeDTO;
import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.PerfilDTO;

public interface FuncionarioModel {

	public List<FuncionarioDTO> listarFuncionarios(Integer offSet,
			Integer recordPerPage) throws SQLException, ClassNotFoundException;

	public List<PerfilDTO> listarPerfis() throws SQLException,
			ClassNotFoundException;

	public void gravar(FuncionarioDTO funcionario) throws SQLException,
			ClassNotFoundException;

	public Integer getTotalRegistrosFuncionarios() throws SQLException,
			ClassNotFoundException;

	public FuncionarioDTO buscarFuncionarioPorMatricula(Integer matricula)
			throws SQLException, ClassNotFoundException;

	public void inativar(Integer matricula) throws SQLException,
			ClassNotFoundException;

	public void alterar(FuncionarioDTO funcionario, Integer matriculaAntiga)
			throws SQLException, ClassNotFoundException;

	public List<FuncionarioDTO> listarGerentes() throws SQLException,
			ClassNotFoundException;

	public List<FuncionarioDTO> listarColaboradoresSemEquipes()
			throws SQLException, ClassNotFoundException;

	public void associarEquipes(Integer matGerente, Integer[] matColaborador)
			throws SQLException, ClassNotFoundException;

	public List<EquipeDTO> listarEquipes(Integer offSet, Integer recordPerPage)
			throws SQLException, ClassNotFoundException;

	public Integer getTotalRegistrosEquipes() throws SQLException,
			ClassNotFoundException;

	public List<FuncionarioDTO> listarColaboradorPorGerente(Integer matricula)
			throws SQLException, ClassNotFoundException;

	public void removerColaborador(Integer matricula) throws SQLException,
			ClassNotFoundException;

	public List<FuncionarioDTO> listarColaboradores() throws SQLException,
			ClassNotFoundException;

}
