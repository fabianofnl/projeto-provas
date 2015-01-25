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
import br.com.sgpo.util.ConexaoBaseDados;

/**
 * @author Roseli
 * 
 */
public class AgendaModelImpl implements AgendaModel {

	private static final Logger LOG = Logger.getLogger(AgendaModelImpl.class);

	private static final String SELECT_AGENDA = "SELECT * FROM agenda ORDER BY dataprova DESC";

	private static final String SELECT_AGENDA_NAO_REALIZADA = "SELECT * FROM agenda "
			+ "WHERE flag = false AND dataprova >= CURRENT_DATE";

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

		while (rs.next()) {
			agenda = new AgendaDTO();
			agenda.setAgendaId(rs.getInt("agendaId"));
			agenda.setProvaId(rs.getInt("provaId"));
			agenda.setMatColaborador(rs.getInt("matcolaborador"));
			agenda.setProvaAgendada(new Date(rs.getDate("dataprova").getTime()));
			agenda.setFlag(rs.getBoolean("flag"));
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
		LOG.info("Chamando método listar agenda completa");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<AgendaDTO> listaAgendas = new ArrayList<AgendaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_AGENDA_NAO_REALIZADA);
		rs = pstmt.executeQuery();
		AgendaDTO agenda = null;

		while (rs.next()) {
			agenda = new AgendaDTO();
			agenda.setAgendaId(rs.getInt("agendaId"));
			agenda.setProvaId(rs.getInt("provaId"));
			agenda.setMatColaborador(rs.getInt("matcolaborador"));
			agenda.setProvaAgendada(new Date(rs.getDate("dataprova").getTime()));
			agenda.setFlag(rs.getBoolean("flag"));
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

}
