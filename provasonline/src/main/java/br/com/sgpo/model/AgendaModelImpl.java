package br.com.sgpo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.util.ConexaoBaseDados;

/**
 * @author Roseli
 * 
 */
public class AgendaModelImpl implements AgendaModel {

	private static final Logger LOG = Logger.getLogger(AgendaModelImpl.class);

	private static final String SELECT_AGENDA = "SELECT * "
			+ "FROM agenda a, provas p, funcionario f "
			+ "WHERE a.provaId = p.provaId AND a.matcolaborador = f.matricula "
			+ "ORDER BY dataprova DESC";

	private static final String SELECT_AGENDA_NAO_REALIZADA = "SELECT *, "
			+ "(SELECT COUNT(a1.agendaId) FROM agenda a1 WHERE a1.dataProva <= CURRENT_DATE AND a1.agendaId = a.agendaId) as hoje "
			+ "FROM agenda a, provas p, funcionario f "
			+ "WHERE a.provaId = p.provaId AND a.matcolaborador = f.matricula "
			+ "AND flag = false AND dataprova >= CURRENT_DATE ORDER BY a.dataprova DESC";

	private static final String INSERT_AGENDA = "INSERT INTO agenda (matcolaborador, provaId, dataprova) "
			+ "VALUES (?, ?, ?)";

	private static final String DELETE_AGENDA = "DELETE FROM agenda WHERE agendaId = ?";

	public List<AgendaDTO> listarAgendas() throws ClassNotFoundException,
			SQLException {
		LOG.info("Chamando método listar agenda completa");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<AgendaDTO> listaAgendas = new ArrayList<AgendaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_AGENDA);
		rs = pstmt.executeQuery();
		AgendaDTO agenda = null;
		ProvaDTO prova = null;
		FuncionarioDTO funcionario = null;

		while (rs.next()) {
			agenda = new AgendaDTO();
			agenda.setAgendaId(rs.getInt("agendaId"));
			agenda.setProvaId(rs.getInt("provaId"));
			agenda.setMatColaborador(rs.getInt("matcolaborador"));
			agenda.setProvaAgendada(new Date(rs.getDate("dataprova").getTime()));
			agenda.setFlag(rs.getBoolean("flag"));

			prova = new ProvaDTO();
			prova.setProvaId(rs.getInt("provaId"));
			prova.setTitulo(rs.getString("titulo"));
			agenda.setProva(prova);

			funcionario = new FuncionarioDTO();
			funcionario.setMatricula(rs.getInt("matricula"));
			funcionario.setNome(rs.getString("nome"));
			funcionario.setEmail(rs.getString("email"));
			funcionario.setFuncao(rs.getString("funcao"));
			funcionario.setStatus(rs.getString("status"));
			agenda.setFuncionario(funcionario);

			listaAgendas.add(agenda);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaAgendas;
	}

	public List<AgendaDTO> listarAgendasNaoRealizadas()
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listar agenda não realizadas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<AgendaDTO> listaAgendas = new ArrayList<AgendaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_AGENDA_NAO_REALIZADA);
		rs = pstmt.executeQuery();
		AgendaDTO agenda = null;
		ProvaDTO prova = null;
		FuncionarioDTO funcionario = null;

		while (rs.next()) {
			agenda = new AgendaDTO();
			agenda.setAgendaId(rs.getInt("agendaId"));
			agenda.setProvaId(rs.getInt("provaId"));
			agenda.setMatColaborador(rs.getInt("matcolaborador"));
			agenda.setProvaAgendada(new Date(rs.getDate("dataprova").getTime()));
			agenda.setFlag(rs.getBoolean("flag"));
			agenda.setHoje(rs.getInt("hoje"));

			prova = new ProvaDTO();
			prova.setProvaId(rs.getInt("provaId"));
			prova.setTitulo(rs.getString("titulo"));
			agenda.setProva(prova);

			funcionario = new FuncionarioDTO();
			funcionario.setMatricula(rs.getInt("matricula"));
			funcionario.setNome(rs.getString("nome"));
			funcionario.setEmail(rs.getString("email"));
			funcionario.setFuncao(rs.getString("funcao"));
			funcionario.setStatus(rs.getString("status"));
			agenda.setFuncionario(funcionario);

			listaAgendas.add(agenda);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaAgendas;
	}

	@Override
	public void agendarProva(AgendaDTO agendaNova)
			throws ClassNotFoundException, SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_AGENDA);
		pstmt.setInt(1, agendaNova.getMatColaborador());
		pstmt.setInt(2, agendaNova.getProvaId());
		pstmt.setDate(3, new java.sql.Date(agendaNova.getProvaAgendada()
				.getTime()));
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

	}

	@Override
	public void excluirProva(AgendaDTO agendaSelecionada)
			throws ClassNotFoundException, SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DELETE_AGENDA);
		pstmt.setInt(1, agendaSelecionada.getAgendaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

	}
}
