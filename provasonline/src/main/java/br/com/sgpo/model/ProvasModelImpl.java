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
import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.ProvaRealizadaDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.dto.TemaDTO;
import br.com.sgpo.util.ConexaoBaseDados;

public class ProvasModelImpl implements ProvasModel {

	private static final Logger LOG = Logger.getLogger(ProvasModelImpl.class);

	private static final String SELECT_PROVAS_PAGINADO = "SELECT * FROM provas ORDER BY titulo LIMIT ? OFFSET ?";

	private static final String SELECT_TOTAL_REGISTROS_PROVAS = "SELECT COUNT(provaId) AS total FROM provas";

	private static final String SELECT_TEMAS_POR_PROVA = "SELECT DISTINCT ON(t.temaId) t.* "
			+ "FROM provas p, montarProvas mp, questoes q, temas t "
			+ "WHERE t.temaId = q.temaId AND q.questaoId = mp.questaoId AND mp.provaId = p.provaId AND p.provaId = ?";

	private static final String SELECT_QUESTOES_POR_TEMA = "SELECT q.* FROM temas t, questoes q, montarProvas mp "
			+ "WHERE t.temaId = q.temaId AND q.questaoId = mp.questaoId AND mp.provaId = ? AND t.temaId = ?";

	private static final String INSERT_PROVA = "INSERT INTO provas (titulo) VALUES (?)";

	private static final String SELECT_PROVA_POR_ID = "SELECT * FROM provas WHERE provaId = ?";

	private static final String DELETE_QUESTAO_PROVA = "DELETE FROM montarProvas WHERE questaoId = ? AND provaId = ?";

	private static final String INSERT_PROVAS_QUESTOES = "INSERT INTO montarProvas (provaId, questaoId) VALUES (?, ?)";

	private static final String DELETE_PROVA = "DELETE FROM provas WHERE provaId = ?";

	private static final String SELECT_APOSTILAS_PAGINADO = "SELECT * FROM apostilas ORDER BY nome LIMIT ? OFFSET ?";

	private static final String SELECT_PROVAS_POR_APOSTILAS = "SELECT * FROM provas p, vincularApostilas a "
			+ "WHERE p.provaId = a.provaId AND a.apostilaId = ?";

	private static final String SELECT_TOTAL_REGISTROS_APOSTILAS = "SELECT COUNT(apostilaId) AS total FROM apostilas";

	private static final String INSERT_APOSTILA = "INSERT INTO apostilas (nome, extensao, hashName, serverPath) "
			+ "VALUES (?, ?, ?, ?)";

	private static final String SELECT_APOSTILA_POR_ID = "SELECT * FROM apostilas WHERE apostilaId = ?";

	private static final String DELETE_APOSTILA = "DELETE FROM apostilas WHERE apostilaId = ?";

	private static final String INSERT_APOSTILAS_PROVAS = "INSERT INTO vincularApostilas (apostilaId, provaId) VALUES (?, ?)";

	private static final String SELECT_PROVAS_POR_APOSTILA = "SELECT p.* FROM provas p "
			+ "WHERE p.provaId NOT IN (SELECT v.provaId FROM vincularApostilas v WHERE v.apostilaId = ?)";

	private static final String DELETE_APOSTILA_PROVA = "DELETE FROM vincularApostilas WHERE apostilaId = ? AND provaId = ?";

	private static final String SELECT_PROVAS = "SELECT * FROM provas";

	private static final String INSERT_AGENDA = "INSERT INTO agenda (matcolaborador, provaId, dataProva) VALUES (?, ?, ?)";

	private static final String SELECT_AGENDAS_PAGINADA = "SELECT f.*, p.*, a.*, "
			+ "(SELECT COUNT(a1.agendaId) as vencido FROM agenda a1 WHERE a1.dataProva <= CURRENT_DATE AND a.agendaId = a1.agendaId)"
			+ "FROM funcionario f, provas p, agenda a "
			+ "WHERE a.matcolaborador = f.matricula AND a.provaId = p.provaId ORDER BY a.dataProva DESC LIMIT ? OFFSET ?";

	private static final String SELECT_TOTAL_REGISTROS_AGENDAS = "SELECT COUNT(a.agendaId) AS total FROM agenda a, provas p "
			+ "WHERE a.provaId = p.provaId";

	private static final String SELECT_AGENDA_POR_ID = "SELECT f.*, p.*, a.* FROM funcionario f, provas p, agenda a "
			+ "WHERE a.matcolaborador = f.matricula AND a.provaId = p.provaId AND a.agendaId = ?";

	private static final String DELETE_AGENDA = "DELETE FROM agenda WHERE agendaId = ?";

	private static final String UPDATE_AGENDA = "UPDATE agenda SET provaId = ?, dataProva = ? WHERE agendaId = ?";

	@Override
	public List<ProvaDTO> listarProvas(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarProvas paginados");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ProvaDTO> listaProvas = new ArrayList<ProvaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_PROVAS_PAGINADO);
		pstmt.setInt(1, recordPerPage);
		pstmt.setInt(2, offSet);
		rs = pstmt.executeQuery();
		ProvaDTO prova = null;

		while (rs.next()) {
			prova = new ProvaDTO();
			prova.setProvaId(rs.getInt("provaId"));
			prova.setTitulo(rs.getString("titulo"));
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
	public List<TemaDTO> listarTemasPorProva(Integer provaId)
			throws ClassNotFoundException, SQLException {
		LOG.info("Chamando método listarTemas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<TemaDTO> listaTemas = new ArrayList<TemaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TEMAS_POR_PROVA);
		pstmt.setInt(1, provaId);
		rs = pstmt.executeQuery();
		TemaDTO tema = null;

		while (rs.next()) {
			tema = new TemaDTO();
			tema.setTemaId(rs.getInt("temaId"));
			tema.setTitulo(rs.getString("titulo"));
			listaTemas.add(tema);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaTemas;
	}

	@Override
	public List<QuestaoDTO> listarQuestoesPorTemas(Integer temaId,
			Integer provaId) throws ClassNotFoundException, SQLException {
		LOG.info("Chamando método listarQuestoes");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<QuestaoDTO> listaQuestoes = new ArrayList<QuestaoDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_QUESTOES_POR_TEMA);
		pstmt.setInt(1, provaId);
		pstmt.setInt(2, temaId);
		rs = pstmt.executeQuery();
		QuestaoDTO questao = null;

		while (rs.next()) {
			questao = new QuestaoDTO();
			questao.setQuestaoId(rs.getInt("questaoId"));
			questao.setTituloQuestao(rs.getString("titulo"));
			questao.setDescricaoQuestao(rs.getString("descricao"));
			listaQuestoes.add(questao);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaQuestoes;
	}

	@Override
	public void gravar(ProvaDTO provaDTO) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método gravar Prova");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_PROVA);
		pstmt.setString(1, provaDTO.getTitulo());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public Integer getTotalRegistrosProvas() throws ClassNotFoundException,
			SQLException {
		LOG.info("Chamando método get Total Provas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer totalRegistros = 0;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TOTAL_REGISTROS_PROVAS);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			totalRegistros = rs.getInt("total");
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return totalRegistros;
	}

	@Override
	public ProvaDTO buscarProvaPorId(Integer provaId)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método buscar Prova por id");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProvaDTO provaDTO = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_PROVA_POR_ID);
		pstmt.setInt(1, provaId);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			provaDTO = new ProvaDTO();
			provaDTO.setProvaId(rs.getInt("provaId"));
			provaDTO.setTitulo(rs.getString("titulo"));
		}

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		// TODO verificar o retorno no controller, pode acontecer NULLPOINTER

		return provaDTO;
	}

	@Override
	public void removerQuestao(QuestaoDTO questaoDTO, ProvaDTO provaDTO)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método remover questão provas");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DELETE_QUESTAO_PROVA);
		pstmt.setInt(1, questaoDTO.getQuestaoId());
		pstmt.setInt(2, provaDTO.getProvaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public void associaProvaQuestoes(Integer provaId, Integer[] questoesId)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método associar provas questões");

		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_PROVAS_QUESTOES);

		for (int i = 0; i < questoesId.length; i++) {
			pstmt.setInt(1, provaId);
			pstmt.setInt(2, questoesId[i]);
			pstmt.addBatch();
		}

		pstmt.executeBatch();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public void remover(ProvaDTO provaDTO) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método remover");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DELETE_PROVA);
		pstmt.setInt(1, provaDTO.getProvaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public List<ApostilaDTO> listarApostilas(Integer offSet,
			Integer recordPerPage) throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarApostilas paginados");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ApostilaDTO> listaApostilas = new ArrayList<ApostilaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_APOSTILAS_PAGINADO);
		pstmt.setInt(1, recordPerPage);
		pstmt.setInt(2, offSet);
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
	public List<ProvaDTO> listarProvasPorApostilas(Integer apostilaId)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarProvasPorApostilas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ProvaDTO> listaProvas = new ArrayList<ProvaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_PROVAS_POR_APOSTILAS);
		pstmt.setInt(1, apostilaId);
		rs = pstmt.executeQuery();
		ProvaDTO prova = null;

		while (rs.next()) {
			prova = new ProvaDTO();
			prova.setProvaId(rs.getInt("provaId"));
			prova.setTitulo(rs.getString("titulo"));
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
	public Integer getTotalRegistrosApostilas() throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método get Total Apostilas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer totalRegistros = 0;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TOTAL_REGISTROS_APOSTILAS);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			totalRegistros = rs.getInt("total");
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return totalRegistros;
	}

	@Override
	public void gravaApostila(ApostilaDTO apostilaDTO)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método gravar Apostila");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_APOSTILA);
		pstmt.setString(1, apostilaDTO.getNome());
		pstmt.setString(2, apostilaDTO.getExtensao());
		pstmt.setString(3, apostilaDTO.getHashName());
		pstmt.setString(4, apostilaDTO.getServerPath());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public ApostilaDTO buscarApostilaPorId(Integer apostilaId)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método buscar Prova por id");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ApostilaDTO apostilaDTO = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_APOSTILA_POR_ID);
		pstmt.setInt(1, apostilaId);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			apostilaDTO = new ApostilaDTO();
			apostilaDTO.setApostilaId(rs.getInt("apostilaId"));
			apostilaDTO.setNome(rs.getString("nome"));
			apostilaDTO.setExtensao(rs.getString("extensao"));
			apostilaDTO.setHashName(rs.getString("hashName"));
			apostilaDTO.setServerPath(rs.getString("serverPath"));
		}

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return apostilaDTO;
	}

	@Override
	public void removerApostila(ApostilaDTO apostilaDTO)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método remover apostila");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DELETE_APOSTILA);
		pstmt.setInt(1, apostilaDTO.getApostilaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public List<ProvaDTO> listarProvasPorApostila(Integer apostilaId)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarProvas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ProvaDTO> listaProvas = new ArrayList<ProvaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_PROVAS_POR_APOSTILA);
		pstmt.setInt(1, apostilaId);
		rs = pstmt.executeQuery();
		ProvaDTO prova = null;

		while (rs.next()) {
			prova = new ProvaDTO();
			prova.setProvaId(rs.getInt("provaId"));
			prova.setTitulo(rs.getString("titulo"));
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
	public void associarApostilaProvas(Integer apostilaId,
			Integer[] provasIdInteger) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método associar Apostila Provas");

		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_APOSTILAS_PROVAS);

		for (int i = 0; i < provasIdInteger.length; i++) {
			pstmt.setInt(1, apostilaId);
			pstmt.setInt(2, provasIdInteger[i]);
			pstmt.addBatch();
		}

		pstmt.executeBatch();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public void removerApostilaProva(ApostilaDTO apostilaDTO, ProvaDTO provaDTO)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método remover apostila prova");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DELETE_APOSTILA_PROVA);
		pstmt.setInt(1, apostilaDTO.getApostilaId());
		pstmt.setInt(2, provaDTO.getProvaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public List<ProvaDTO> listarProvas() throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método listarProvas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ProvaDTO> listaProvas = new ArrayList<ProvaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_PROVAS);
		rs = pstmt.executeQuery();
		ProvaDTO prova = null;

		while (rs.next()) {
			prova = new ProvaDTO();
			prova.setProvaId(rs.getInt("provaId"));
			prova.setTitulo(rs.getString("titulo"));
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
	public void agendarProva(FuncionarioDTO funcionario, ProvaDTO prova,
			Date dataAgendada) throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método agendar Provas");

		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_AGENDA);
		pstmt.setInt(1, funcionario.getMatricula());
		pstmt.setInt(2, prova.getProvaId());
		pstmt.setDate(3, new java.sql.Date(dataAgendada.getTime()));
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public List<AgendaDTO> listarAgenda(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarProvas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<AgendaDTO> listaAgendas = new ArrayList<AgendaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_AGENDAS_PAGINADA);
		pstmt.setInt(1, recordPerPage);
		pstmt.setInt(2, offSet);
		rs = pstmt.executeQuery();
		AgendaDTO agenda = null;
		FuncionarioDTO funcionario = null;
		ProvaDTO prova = null;

		while (rs.next()) {
			funcionario = new FuncionarioDTO();
			prova = new ProvaDTO();
			agenda = new AgendaDTO();

			funcionario.setMatricula(rs.getInt("matricula"));
			funcionario.setNome(rs.getString("nome"));
			funcionario.setFuncao(rs.getString("funcao"));
			funcionario.setEmail(rs.getString("email"));
			funcionario.setStatus(rs.getString("status"));
			funcionario.setUsuario(rs.getString("usuario"));

			prova.setProvaId(rs.getInt("provaId"));
			prova.setTitulo(rs.getString("titulo"));

			agenda.setAgendaId(rs.getInt("agendaId"));
			agenda.setFuncionario(funcionario);
			agenda.setProva(prova);
			agenda.setProvaAgendada(new Date(rs.getDate("dataProva").getTime()));
			agenda.setFlag(rs.getBoolean("flag"));
			agenda.setVencido(rs.getInt("vencido"));

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
	public Integer getTotalRegistrosAgenda() throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método get Total Provas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer totalRegistros = 0;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TOTAL_REGISTROS_AGENDAS);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			totalRegistros = rs.getInt("total");
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return totalRegistros;
	}

	@Override
	public AgendaDTO buscarAgendaPorId(Integer agendaId)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método buscar Agenda por id");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AgendaDTO agenda = null;
		FuncionarioDTO funcionario = null;
		ProvaDTO prova = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_AGENDA_POR_ID);
		pstmt.setInt(1, agendaId);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			funcionario = new FuncionarioDTO();
			prova = new ProvaDTO();
			agenda = new AgendaDTO();

			funcionario.setMatricula(rs.getInt("matricula"));
			funcionario.setNome(rs.getString("nome"));
			funcionario.setFuncao(rs.getString("funcao"));
			funcionario.setEmail(rs.getString("email"));
			funcionario.setStatus(rs.getString("status"));
			funcionario.setUsuario(rs.getString("usuario"));

			prova.setProvaId(rs.getInt("provaId"));
			prova.setTitulo(rs.getString("titulo"));

			agenda.setAgendaId(rs.getInt("agendaId"));
			agenda.setFuncionario(funcionario);
			agenda.setProva(prova);
			agenda.setProvaAgendada(new Date(rs.getDate("dataProva").getTime()));
			agenda.setFlag(rs.getBoolean("flag"));
		}

		return agenda;
	}

	@Override
	public void removerAgenda(AgendaDTO agendaDTO)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método remover agenda");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DELETE_AGENDA);
		pstmt.setInt(1, agendaDTO.getAgendaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public void atualizarAgenda(AgendaDTO agendaDTO)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método atualizar agenda");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(UPDATE_AGENDA);
		pstmt.setInt(1, agendaDTO.getProva().getProvaId());
		pstmt.setDate(2, new java.sql.Date(agendaDTO.getProvaAgendada()
				.getTime()));
		pstmt.setInt(3, agendaDTO.getAgendaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

	}

	@Override
	public ProvaRealizadaDTO buscarProvaRealizadaPorAgendaId(Integer agendaId)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
