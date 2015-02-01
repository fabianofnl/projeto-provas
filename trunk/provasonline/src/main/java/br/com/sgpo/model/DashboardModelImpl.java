package br.com.sgpo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.NotaMediaColaboradorDTO;
import br.com.sgpo.dto.NotaMediaEquipesDTO;
import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.ProvaRealizadaDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.dto.RelatorioDadosGeraisDTO;
import br.com.sgpo.util.ConexaoBaseDados;

public class DashboardModelImpl implements DashboardModel {

	private static final Logger LOG = Logger
			.getLogger(DashboardModelImpl.class);

	// TODO utilizado
	private static final String SELECT_AGENDAS_POR_MATRICULA = "SELECT p.*, a.*, "
			+ "(SELECT COUNT(a1.agendaId) FROM agenda a1 WHERE a1.dataProva = CURRENT_DATE AND a1.agendaId = a.agendaId) as hoje "
			+ "FROM agenda a, funcionario f, provas p WHERE f.matricula = a.matcolaborador AND "
			+ "a.provaId = p.provaId AND f.matricula = ? AND a.dataProva >= CURRENT_DATE AND a.flag = false";

	private static final String SELECT_APOSTILAS_POR_MATRICULA = "SELECT DISTINCT ON(ap.apostilaId, ap.nome) ap.* "
			+ "FROM apostilas ap, provas p, agenda a "
			+ "WHERE p.provaId = a.provaId AND ap.provaId = p.provaId"
			+ "AND a.flag = false AND a.dataProva > CURRENT_DATE AND a.matcolaborador = ? ORDER BY ap.nome";

	// private static final String SELECT_PROVAS_POR_MATRICULA =
	// "SELECT pr.* FROM funcionario f, agenda a, provasRealizadas pr "
	// + "WHERE f.matricula = a.matcolaborador AND a.agendaId = pr.agendaId "
	// + "AND f.matricula = ?";

	private static final String SELECT_NOTA_MEDIA_EQUIPE = "SELECT "
			+ "SUM(pr.quantidadeAcertos) AS quantidadeAcertos, SUM(pr.quantidadeQuestoes) AS quantidadeQuestoes "
			+ "FROM funcionario f, agenda a, provasRealizadas pr WHERE f.matricula = a.matcolaborador AND a.agendaId = pr.agendaId "
			+ "AND f.matricula != ?";

	// TODO utilizado
	private static final String SELECT_RELATORIO_DADOS_GERAIS = "SELECT "
			+ "(SELECT COUNT(matricula) FROM funcionario WHERE status = 'Ativo') AS qtdFuncionariosAtivos, "
			+ "(SELECT COUNT(matricula) FROM funcionario WHERE status = 'Inativo') AS qtdFuncionariosInativos, "
			+ "(SELECT COUNT(DISTINCT matgerente) FROM equipes) AS qtdEquipes, "
			+ "(SELECT COUNT(agendaId) FROM agenda WHERE flag = false AND dataProva >= CURRENT_DATE) AS qtdProvasAgendadas, "
			+ "(SELECT COUNT(agendaId) FROM agenda WHERE flag = true) AS qtdProvasRealizadas, "
			+ "(SELECT COUNT(agendaId) FROM agenda WHERE flag = false) AS qtdProvasNaoRealizadas, "
			+ "(SELECT COUNT(temaId) FROM temas) AS qtdTemas, "
			+ "(SELECT COUNT(provaId) FROM provas) AS qtdProvas, "
			+ "(SELECT COUNT(questaoId) FROM questoes) AS qtdQuestoes, "
			+ "(SELECT COUNT(opcaoId) FROM opcoes) AS qtdOpcoes, "
			+ "(SELECT COUNT(apostilaId) FROM apostilas) AS qtdApostilas";

	// TODO utilizado
	private static final String SELECT_GERENTES = "SELECT DISTINCT ON (matgerente) e.matgerente, f.nome "
			+ "FROM equipes e, funcionario f WHERE e.matgerente = f.matricula";

	// private static final String SELECT_NOTA_MEDIA_POR_GERENTE = "SELECT "
	// +
	// "SUM(pr.quantidadeAcertos) AS acertos, SUM(pr.quantidadeQuestoes) AS questoes "
	// + "FROM funcionario f, equipes e, agenda a, provasRealizadas pr "
	// + "WHERE f.matricula = a.matcolaborador AND a.agendaId = pr.agendaId "
	// + "AND f.matricula = e.matcolaborador AND e.matgerente = ?";

	// TODO utilizado
	private static final String SELECT_NOTA_MEDIA_POR_GERENTE = "SELECT "
			+ "sum(((pr.quantidadeAcertos :: float / pr.quantidadeQuestoes :: float)*100):: float)/ count(a.agendaId) as media "
			+ "FROM provasRealizadas pr, agenda a, funcionario f, equipes e "
			+ "WHERE f.matricula = a.matcolaborador AND a.agendaId = pr.agendaId AND f.matricula = e.matcolaborador AND e.matgerente = ?";

	// private static final String SELECT_NOTA_MEDIA_COLABORADOR_POR_GERENTE_MAT
	// = "SELECT f.matricula, f.nome "
	// + "FROM equipes e, funcionario f "
	// + "WHERE f.matricula = e.matcolaborador AND matgerente = ?";

	private static final String SELECT_PROVAS_POR_MATRICULA = "SELECT pr.*, "
			+ "sum(((pr.quantidadeAcertos :: float / pr.quantidadeQuestoes :: float)*100):: float) as media "
			+ "FROM funcionario f, agenda a, provasRealizadas pr "
			+ "WHERE f.matricula = a.matcolaborador AND a.agendaId = pr.agendaId "
			+ "AND f.matricula = ? " + "GROUP BY pr.provarealizadaid";

	// TODO utilizado
	private static final String SELECT_NOTA_MEDIA_COLABORADOR_POR_GERENTE_MAT = "SELECT f.matricula, f.nome,  "
			+ "sum(((pr.quantidadeAcertos :: float / pr.quantidadeQuestoes :: float)*100):: float)/ count(a.agendaId) as media "
			+ "FROM provasRealizadas pr, agenda a, equipes e, funcionario f "
			+ "WHERE f.matricula = a.matcolaborador AND a.agendaId = pr.agendaId AND f.matricula = e.matcolaborador AND e.matgerente = ? "
			+ "GROUP BY f.matricula, f.nome";

	private static final String SELECT_NOTA_MEDIA_COLABORADOR = "SELECT "
			+ "SUM(pr.quantidadeQuestoes) AS questoes, SUM(pr.quantidadeAcertos) AS acertos "
			+ "FROM provasRealizadas pr, agenda a "
			+ "WHERE a.agendaId = pr.agendaId AND a.matcolaborador = ?";

	// TODO utilizado
	private static final String SELECT_QUESTOES_POR_PROVA = "SELECT q.*, "
			+ "t.temaId, t.titulo as titulotema, t.descricao as descricaotema "
			+ "FROM questoes q, temas t "
			+ "WHERE q.temaId = t.temaId AND q.provaId = ?";

	// TODO utilizada
	private static final String SELECT_OPCOES_POR_QUESTAO = "SELECT * FROM opcoes WHERE questaoId = ?";

	private static final String UPDATE_AGENDA_FLAG = "UPDATE agenda SET flag = true WHERE agendaId = ?";

	private static final String INSERT_DETALHES_PROVA_REALIZADA = "INSERT INTO provasRealizadas "
			+ "(agendaId, provaId, tituloProva, dataHoraInicio, dataHoraFim, quantidadeQuestoes, quantidadeAcertos) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING provaRealizadaId";

	// TODO utilizado
	private static final String UPDATE_PROVA_REALIZADA = "UPDATE provasRealizadas SET dataHoraFinalizado = ?, quantidadeAcertos = ? "
			+ "WHERE provaRealizadaId = ?";

	/**
	 * Método que consulta a lista de provas agendadas para o colaborador
	 * visualizar
	 * 
	 * @param matricula
	 * @return List<AgendaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
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

		ProvasModel provasModel = new ProvasModelImpl();
		List<ApostilaDTO> apostilas;
		for (AgendaDTO agendaDTO : listaAgendas) {
			apostilas = new ArrayList<ApostilaDTO>();
			apostilas = provasModel.listarApostilasPorProvaId(agendaDTO
					.getProva().getProvaId());
			agendaDTO.setListaApostilas(apostilas);
		}

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

	@Override
	public List<ProvaRealizadaDTO> listarProvasRealizadasPorMatricula(
			Integer matricula) throws ClassNotFoundException, SQLException {
		LOG.info("Chamando método listarApostilas");

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

			prova.setMedia(rs.getDouble("media"));

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

		LOG.info("Chamando método consultaMediaEquipe");

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
			mediaEquipe.setTituloProva("Média Equipe");
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

	/**
	 * Método que consulta dados gerais do sistema, como a quantidade de
	 * funcionarios cadastrados, provas criadas e demais informações.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public RelatorioDadosGeraisDTO consultarRelatorioDadosGerais()
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método consultaRelatorioDadosGerais");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RelatorioDadosGeraisDTO relatorio = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_RELATORIO_DADOS_GERAIS);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			relatorio = new RelatorioDadosGeraisDTO();
			relatorio.setQtdFuncionariosAtivos(rs
					.getInt("qtdFuncionariosAtivos"));
			relatorio.setQtdFuncionariosInativos(rs
					.getInt("qtdFuncionariosInativos"));
			relatorio.setQtdEquipes(rs.getInt("qtdEquipes"));
			relatorio.setQtdProvasAgendadas(rs.getInt("qtdProvasAgendadas"));
			relatorio.setQtdProvasRealizadas(rs.getInt("qtdProvasRealizadas"));
			relatorio.setQtdProvasNaoRealizadas(rs
					.getInt("qtdProvasNaoRealizadas"));
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

	/**
	 * Método que consulta a lista de gerentes (perfil de gerente)
	 * 
	 * @return List<NotaMediaEquipesDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<NotaMediaEquipesDTO> listarGerentes()
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método consultaRelatorioDadosGerais");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NotaMediaEquipesDTO notaMedia = null;
		List<NotaMediaEquipesDTO> listaGerentes = new ArrayList<NotaMediaEquipesDTO>();

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_GERENTES);
		rs = pstmt.executeQuery();

		while (rs.next()) {
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

	/**
	 * Método que consulta a nota média da equipe
	 * 
	 * @param notaMediaEquipesDTO
	 * @return NotaMediaEquipesDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public NotaMediaEquipesDTO consultarMediaEquipePorGerente(
			NotaMediaEquipesDTO notaMediaEquipesDTO)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método consultaMediaEquipePorGerente");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_NOTA_MEDIA_POR_GERENTE);
		pstmt.setInt(1, notaMediaEquipesDTO.getMatriculaGerente());
		rs = pstmt.executeQuery();

		if (rs.next()) {
			notaMediaEquipesDTO.setMedia(rs.getDouble("media"));
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
	public NotaMediaColaboradorDTO consultarNotaMediaColaborador(
			NotaMediaColaboradorDTO notaMediaColaboradorDTO)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método consultarNotaMediaColaborador");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_NOTA_MEDIA_COLABORADOR);
		pstmt.setInt(1, notaMediaColaboradorDTO.getMatricula());
		rs = pstmt.executeQuery();

		while (rs.next()) {
			notaMediaColaboradorDTO.setQuestoes(Long.parseLong(String
					.valueOf(rs.getInt("questoes"))));
			notaMediaColaboradorDTO.setAcertos(Long.parseLong(String.valueOf(rs
					.getInt("acertos"))));
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return notaMediaColaboradorDTO;
	}

	/**
	 * Método que consulta as questões da prova selecionada para a realização da
	 * mesma
	 * 
	 * @param provaSelecionada
	 * @return List<QuestaoDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<QuestaoDTO> listarQuestoesPorProva(ProvaDTO provaSelecionada)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarQuestoesPorProva");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_QUESTOES_POR_PROVA);
		pstmt.setInt(1, provaSelecionada.getProvaId());
		rs = pstmt.executeQuery();
		List<QuestaoDTO> listaQuestoes = new ArrayList<QuestaoDTO>();
		QuestaoDTO questao = null;

		while (rs.next()) {
			questao = new QuestaoDTO();
			questao.setQuestaoId(rs.getInt("questaoId"));
			questao.setTituloQuestao(rs.getString("titulo"));
			questao.setDescricaoQuestao(rs.getString("descricao"));

			questao.setProvaId(rs.getInt("provaId"));

			questao.setTemaId(rs.getInt("temaId"));
			questao.setTitulo(rs.getString("titulotema"));
			questao.setDescricao(rs.getString("descricaotema"));
			listaQuestoes.add(questao);
		}

		if (pstmt != null)
			pstmt.close();
		if (rs != null)
			rs.close();
		if (conn != null)
			conn.close();
		return listaQuestoes;
	}

	/**
	 * Método que consulta as opções da questão selecionada
	 * 
	 * @param provaSelecionada
	 * @return List<QuestaoDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<OpcaoDTO> listarOpcoesPorQuestao(QuestaoDTO questaoDTO)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarOpcoesPorQuestao");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_OPCOES_POR_QUESTAO);
		pstmt.setInt(1, questaoDTO.getQuestaoId());
		rs = pstmt.executeQuery();
		List<OpcaoDTO> listaOpcoes = new ArrayList<OpcaoDTO>();
		OpcaoDTO opcao = new OpcaoDTO();

		while (rs.next()) {
			opcao = new OpcaoDTO();
			opcao.setOpcaoId(rs.getInt("opcaoId"));
			opcao.setTituloOpcao(rs.getString("titulo"));
			opcao.setFlag(rs.getBoolean("flag"));

			opcao.setQuestaoId(rs.getInt("questaoId"));
			listaOpcoes.add(opcao);
		}

		if (pstmt != null)
			pstmt.close();
		if (rs != null)
			rs.close();
		if (conn != null)
			conn.close();

		return listaOpcoes;
	}

	@Override
	public Integer realizarProva(ProvaRealizadaDTO provaRealizadaSelecionada)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método realizar prova");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer provaRealizadaId = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(UPDATE_AGENDA_FLAG);
		pstmt.setInt(1, provaRealizadaSelecionada.getAgenda().getAgendaId());
		pstmt.execute();

		pstmt = conn.prepareStatement(INSERT_DETALHES_PROVA_REALIZADA);
		pstmt.setInt(1, provaRealizadaSelecionada.getAgenda().getAgendaId());
		pstmt.setInt(2, provaRealizadaSelecionada.getProvaId());
		pstmt.setString(3, provaRealizadaSelecionada.getTituloProva());
		pstmt.setTimestamp(4, new Timestamp(provaRealizadaSelecionada
				.getDataHoraInicio().getTime()));
		pstmt.setTimestamp(5, new Timestamp(provaRealizadaSelecionada
				.getDataHoraFim().getTime()));
		pstmt.setInt(6, provaRealizadaSelecionada.getQuantidadeQuestoes());
		pstmt.setInt(7, provaRealizadaSelecionada.getQuantidadeAcertos());
		rs = pstmt.executeQuery();

		if (rs.next()) {
			provaRealizadaId = rs.getInt("provaRealizadaId");
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return provaRealizadaId;
	}

	/**
	 * Método que processa o término da prova
	 * 
	 * @param provaRealizadaSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void entregarProva(ProvaRealizadaDTO provaRealizadaSelecionada)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método entregar prova");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(UPDATE_PROVA_REALIZADA);
		pstmt.setTimestamp(1, new java.sql.Timestamp(provaRealizadaSelecionada
				.getDataHoraFinalizado().getTime()));
		pstmt.setInt(2, provaRealizadaSelecionada.getQuantidadeAcertos());
		pstmt.setInt(3, provaRealizadaSelecionada.getProvaRealizadaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

	}

	/**
	 * Método que consulta a lista de notas médias dos colaboradores
	 * 
	 * @param matricula
	 * @return List<NotaMediaColaboradorDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<NotaMediaColaboradorDTO> listarNotaMediaColaboradores(
			Integer matricula) throws ClassNotFoundException, SQLException {

		LOG.info("chamando listar nota media colaboradores");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NotaMediaColaboradorDTO notaMediaColaborador = null;
		List<NotaMediaColaboradorDTO> listaNotaMedia = new ArrayList<NotaMediaColaboradorDTO>();

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn
				.prepareStatement(SELECT_NOTA_MEDIA_COLABORADOR_POR_GERENTE_MAT);
		pstmt.setInt(1, matricula);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			notaMediaColaborador = new NotaMediaColaboradorDTO();
			notaMediaColaborador.setNome(rs.getString("nome"));
			notaMediaColaborador.setMatricula(rs.getInt("matricula"));
			notaMediaColaborador.setMedia(rs.getDouble("media"));
			listaNotaMedia.add(notaMediaColaborador);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaNotaMedia;
	}
}
