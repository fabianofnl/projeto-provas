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
import br.com.sgpo.dto.NotaMediaColaboradorDTO;
import br.com.sgpo.dto.NotaMediaEquipesDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.ProvaRealizadaDTO;
import br.com.sgpo.dto.RelatorioDadosGeraisDTO;
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

	private static final String SELECT_PROVAS_POR_MATRICULA = "SELECT pr.* FROM funcionario f, agenda a, provasRealizadas pr "
			+ "WHERE f.matricula = a.matcolaborador AND a.agendaId = pr.agendaId "
			+ "AND f.matricula = ?";

	private static final String SELECT_NOTA_MEDIA_EQUIPE = "SELECT "
			+ "SUM(pr.quantidadeAcertos) AS quantidadeAcertos, SUM(pr.quantidadeQuestoes) AS quantidadeQuestoes "
			+ "FROM funcionario f, agenda a, provasRealizadas pr WHERE f.matricula = a.matcolaborador AND a.agendaId = pr.agendaId "
			+ "AND f.matricula != ?";

	private static final String SELECT_RELATORIO_DADOS_GERAIS = "SELECT "
			+ "(SELECT COUNT(matricula) FROM funcionario WHERE status = 'Ativo') AS qtdFuncionariosAtivos, "
			+ "(SELECT COUNT(matricula) FROM funcionario WHERE status = 'Inativo') AS qtdFuncionariosInativos, "
			+ "(SELECT COUNT(DISTINCT matgerente) FROM equipes) AS qtdEquipes, "
			+ "(SELECT COUNT(agendaId) FROM agenda WHERE flag = false AND dataProva > CURRENT_DATE) AS qtdProvasAgendadas, "
			+ "(SELECT COUNT(agendaId) FROM agenda WHERE flag = true) AS qtdProvasRealizadas, "
			+ "(SELECT COUNT(agendaId) FROM agenda WHERE flag = false) AS qtdProvasNaoRealizadas, "
			+ "(SELECT COUNT(temaId) FROM temas) AS qtdTemas, "
			+ "(SELECT COUNT(provaId) FROM provas) AS qtdProvas, "
			+ "(SELECT COUNT(questaoId) FROM questoes) AS qtdQuestoes, "
			+ "(SELECT COUNT(opcaoId) FROM opcoes) AS qtdOpcoes, "
			+ "(SELECT COUNT(apostilaId) FROM apostilas) AS qtdApostilas";

	private static final String SELECT_GERENTES = "SELECT DISTINCT ON (matgerente) e.matgerente, f.nome "
			+ "FROM equipes e, funcionario f WHERE e.matgerente = f.matricula";

	private static final String SELECT_NOTA_MEDIA_POR_GERENTE = "SELECT "
			+ "SUM(pr.quantidadeAcertos) AS acertos, SUM(pr.quantidadeQuestoes) AS questoes "
			+ "FROM funcionario f, equipes e, agenda a, provasRealizadas pr "
			+ "WHERE f.matricula = a.matcolaborador AND a.agendaId = pr.agendaId "
			+ "AND f.matricula = e.matcolaborador AND e.matgerente = ?";

	private static final String SELECT_NOTA_MEDIA_COLABORADOR_POR_GERENTE_MAT = "SELECT f.matricula, f.nome "
			+ "FROM equipes e, funcionario f "
			+ "WHERE f.matricula = e.matcolaborador AND matgerente = ?";

	private static final String SELECT_NOTA_MEDIA_COLABORADOR = "SELECT "
			+ "SUM(pr.quantidadeQuestoes) AS questoes, SUM(pr.quantidadeAcertos) AS acertos "
			+ "FROM provasRealizadas pr, agenda a "
			+ "WHERE a.agendaId = pr.agendaId AND a.matcolaborador = ?";

	@Override
	public List<AgendaDTO> listarAgendas(Integer matricula)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando m�todo listarAgendas");

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

		LOG.info("Chamando m�todo listarApostilas");

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

	@Override
	public List<ProvaRealizadaDTO> listarProvasRealizadasPorMatricula(
			Integer matricula) throws ClassNotFoundException, SQLException {
		LOG.info("Chamando m�todo listarApostilas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ProvaRealizadaDTO> listaProvas = new ArrayList<ProvaRealizadaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_PROVAS_POR_MATRICULA);
		pstmt.setInt(1, matricula);
		rs = pstmt.executeQuery();
		ProvaRealizadaDTO prova = null;
		AgendaDTO agenda = null;

		while (rs.next()) {
			prova = new ProvaRealizadaDTO();
			agenda = new AgendaDTO();

			prova.setProvaRealizadaId(rs.getInt("provaRealizadaId"));
			agenda.setAgendaId(rs.getInt("agendaId"));
			prova.setAgenda(agenda);
			prova.setProvaId(rs.getInt("provaId"));
			prova.setTituloProva(rs.getString("tituloProva"));

			Object obj = rs.getObject("dataHoraInicio");
			prova.setDataHoraInicio(obj == null ? null : new Date(
					((java.sql.Timestamp) obj).getTime()));

			obj = rs.getObject("dataHoraFim");
			prova.setDataHoraFim(obj == null ? null : new Date(
					((java.sql.Timestamp) obj).getTime()));

			obj = rs.getObject("dataHoraFinalizado");
			prova.setDataHoraFinalizado(obj == null ? null : new Date(
					((java.sql.Timestamp) obj).getTime()));

			prova.setQuantidadeQuestoes(rs.getInt("quantidadeQuestoes"));
			prova.setQuantidadeAcertos(rs.getInt("quantidadeAcertos"));

			listaProvas.add(prova);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaProvas;
	}

	@Override
	public ProvaRealizadaDTO consultarMediaEquipe(Integer matricula)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando m�todo consultaMediaEquipe");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_NOTA_MEDIA_EQUIPE);
		pstmt.setInt(1, matricula);
		rs = pstmt.executeQuery();
		ProvaRealizadaDTO mediaEquipe = null;

		if (rs.next()) {
			mediaEquipe = new ProvaRealizadaDTO();
			mediaEquipe.setTituloProva("M�dia Equipe");
			mediaEquipe.setQuantidadeQuestoes(rs.getInt("quantidadeQuestoes"));
			mediaEquipe.setQuantidadeAcertos(rs.getInt("quantidadeAcertos"));
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return mediaEquipe;
	}

	@Override
	public RelatorioDadosGeraisDTO consultarRelatorioDadosGerais()
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando m�todo consultaRelatorioDadosGerais");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RelatorioDadosGeraisDTO relatorio = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_RELATORIO_DADOS_GERAIS);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			relatorio = new RelatorioDadosGeraisDTO();
			relatorio.setQtdFuncionariosAtivos(rs.getInt("qtdFuncionariosAtivos"));
			relatorio.setQtdFuncionariosInativos(rs.getInt("qtdFuncionariosInativos"));
			relatorio.setQtdEquipes(rs.getInt("qtdEquipes"));
			relatorio.setQtdProvasAgendadas(rs.getInt("qtdProvasAgendadas"));
			relatorio.setQtdProvasRealizadas(rs.getInt("qtdProvasRealizadas"));
			relatorio.setQtdProvasNaoRealizadas(rs.getInt("qtdProvasNaoRealizadas"));
			relatorio.setQtdTemas(rs.getInt("qtdTemas"));
			relatorio.setQtdProvas(rs.getInt("qtdProvas"));
			relatorio.setQtdQuestoes(rs.getInt("qtdQuestoes"));
			relatorio.setQtdOpcoes(rs.getInt("qtdOpcoes"));
			relatorio.setQtdApostilas(rs.getInt("qtdApostilas"));
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return relatorio;
	}

	@Override
	public List<NotaMediaEquipesDTO> listarGerentes()
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando m�todo consultaRelatorioDadosGerais");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NotaMediaEquipesDTO notaMedia = null;
		List<NotaMediaEquipesDTO> listaGerentes = new ArrayList<NotaMediaEquipesDTO>();

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_GERENTES);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			notaMedia = new NotaMediaEquipesDTO();
			notaMedia.setMatriculaGerente(rs.getInt("matgerente"));
			notaMedia.setNomeGerente(rs.getString("nome"));
			listaGerentes.add(notaMedia);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaGerentes;
	}

	@Override
	public NotaMediaEquipesDTO consultarMediaEquipePorGerente(
			NotaMediaEquipesDTO notaMediaEquipesDTO)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando m�todo consultaMediaEquipePorGerente");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_NOTA_MEDIA_POR_GERENTE);
		pstmt.setInt(1, notaMediaEquipesDTO.getMatriculaGerente());
		rs = pstmt.executeQuery();

		if (rs.next()) {
			notaMediaEquipesDTO.setQtdQuestoes(rs.getInt("questoes"));
			notaMediaEquipesDTO.setQtdAcertos(rs.getInt("acertos"));
		} else {
			notaMediaEquipesDTO.setQtdQuestoes(null);
			notaMediaEquipesDTO.setQtdAcertos(null);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return notaMediaEquipesDTO;
	}

	@Override
	public List<NotaMediaColaboradorDTO> listarNotaMediaColaboradorPorGerenteMat(
			Integer matricula) throws ClassNotFoundException, SQLException {

		LOG.info("Chamando m�todo listarNotaMediaColaboradorPorGerenteMat");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NotaMediaColaboradorDTO notaMediaColaboradorDTO = null;
		List<NotaMediaColaboradorDTO> listaNotaMedia = new ArrayList<NotaMediaColaboradorDTO>();

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn
				.prepareStatement(SELECT_NOTA_MEDIA_COLABORADOR_POR_GERENTE_MAT);
		pstmt.setInt(1, matricula);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			notaMediaColaboradorDTO = new NotaMediaColaboradorDTO();
			notaMediaColaboradorDTO.setNome(rs.getString("nome"));
			notaMediaColaboradorDTO.setMatricula(rs.getInt("matricula"));
			listaNotaMedia.add(notaMediaColaboradorDTO);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaNotaMedia;
	}

	@Override
	public NotaMediaColaboradorDTO consultarNotaMediaColaborador(
			NotaMediaColaboradorDTO notaMediaColaboradorDTO)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando m�todo consultarNotaMediaColaborador");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_NOTA_MEDIA_COLABORADOR);
		pstmt.setInt(1, notaMediaColaboradorDTO.getMatricula());
		rs = pstmt.executeQuery();

		while (rs.next()) {
			notaMediaColaboradorDTO.setQuestoes(rs.getInt("questoes"));
			notaMediaColaboradorDTO.setAcertos(rs.getInt("acertos"));
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return notaMediaColaboradorDTO;
	}
}