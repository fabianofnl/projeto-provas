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
import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.util.ConexaoBaseDados;

public class DashboardModelImpl implements DashboardModel {

	private static final Logger LOG = Logger
			.getLogger(DashboardModelImpl.class);
	private static final String SELECT_AGENDAS_POR_MATRICULA = "SELECT p.*, a.*, "
			+ "(SELECT COUNT(a1.agendaId) FROM agenda a1 WHERE a1.dataProva = CURRENT_DATE AND a1.agendaId = a.agendaId) as hoje "
			+ "FROM agenda a, funcionario f, provas p WHERE f.matricula = a.matcolaborador AND "
			+ "a.provaId = p.provaId AND f.matricula = ? AND a.dataProva >= CURRENT_DATE AND a.flag = false";

	private static final String SELECT_APOSTILAS_POR_MATRICULA = "SELECT DISTINCT ON(ap.apostilaId, ap.nome) ap.* "
			+ "FROM apostilas ap, vincularApostilas vp, provas p, agenda a "
			+ "WHERE ap.apostilaId = vp.apostilaId AND vp.provaId = p.provaId AND p.provaId = a.provaId "
			+ "AND a.flag = false AND a.dataProva > CURRENT_DATE AND a.matcolaborador = ? ORDER BY ap.nome";

	@Override
	public List<AgendaDTO> listarAgendas(Integer matricula)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarAgendas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<AgendaDTO> listaAgendas = new ArrayList<AgendaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_AGENDAS_POR_MATRICULA);
		pstmt.setInt(1, matricula);
		rs = pstmt.executeQuery();
		AgendaDTO agenda = null;
		ProvaDTO prova = null;

		while (rs.next()) {
			agenda = new AgendaDTO();
			prova = new ProvaDTO();

			prova.setProvaId(rs.getInt("provaId"));
			prova.setTitulo(rs.getString("titulo"));

			agenda.setAgendaId(rs.getInt("agendaId"));
			agenda.setProva(prova);
			agenda.setProvaAgendada(new Date(rs.getDate("dataProva").getTime()));
			agenda.setFlag(rs.getBoolean("flag"));
			agenda.setHoje(rs.getInt("hoje"));

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
	public List<ApostilaDTO> listarApostilas(Integer matricula)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarApostilas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ApostilaDTO> listaApostilas = new ArrayList<ApostilaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_APOSTILAS_POR_MATRICULA);
		pstmt.setInt(1, matricula);
		rs = pstmt.executeQuery();
		ApostilaDTO apostila = null;

		while (rs.next()) {
			apostila = new ApostilaDTO();
			apostila.setApostilaId(rs.getInt("apostilaId"));
			apostila.setNome(rs.getString("nome"));
			apostila.setExtensao(rs.getString("extensao"));
			apostila.setHashName(rs.getString("hashName"));
			apostila.setServerPath(rs.getString("serverPath"));
			listaApostilas.add(apostila);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaApostilas;
	}

}
