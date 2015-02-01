package br.com.sgpo.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.ProvaRealizadaDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.dto.TemaDTO;
import br.com.sgpo.model.ProvasModel;
import br.com.sgpo.model.ProvasModelImpl;

/**
 * Classe que implementa os m�todos de gerenciamento de provas do sistema
 * 
 * @author Roseli
 * 
 */
public class ProvasServiceImpl implements ProvasService {

	private ProvasModel provasModel = new ProvasModelImpl();

	@Override
	public List<ProvaDTO> listarProvas(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException {

		List<ProvaDTO> listaProvas = provasModel.listarProvas(offSet,
				recordPerPage);

		for (ProvaDTO provaDTO : listaProvas) {
			provaDTO.setListaTemas(provasModel.listarTemasPorProva(provaDTO
					.getProvaId()));
			provaDTO.setQuantidadeTemas(provaDTO.getListaTemas().size());

			Integer totalQuestoes = 0;
			for (TemaDTO temaDTO : provaDTO.getListaTemas()) {
				temaDTO.setListaQuestoes(provasModel.listarQuestoesPorTemas(
						temaDTO.getTemaId(), provaDTO.getProvaId()));
				totalQuestoes += temaDTO.getListaQuestoes().size();
			}
			provaDTO.setQuantidadeQuestoes(totalQuestoes);

		}
		return listaProvas;
	}

	/**
	 * M�todo que cadastra a prova no sistema
	 * 
	 * @param provaDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void gravar(ProvaDTO provaDTO) throws ClassNotFoundException,
			SQLException {
		provasModel.gravar(provaDTO);
	}

	@Override
	public Integer getTotalRegistrosProvas() throws ClassNotFoundException,
			SQLException {
		return provasModel.getTotalRegistrosProvas();
	}

	/**
	 * M�todo que consulta dados da prova por provaId
	 * 
	 * @param provaId
	 * @return ProvaDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public ProvaDTO buscarProvaPorId(Integer provaId)
			throws ClassNotFoundException, SQLException {
		return provasModel.buscarProvaPorId(provaId);
	}

	@Override
	public void removerQuestao(QuestaoDTO questaoDTO, ProvaDTO provaDTO)
			throws ClassNotFoundException, SQLException {
		provasModel.removerQuestao(questaoDTO, provaDTO);
	}

	@Override
	public void associarProvaQuestoes(Integer provaId, Integer[] questoesId)
			throws ClassNotFoundException, SQLException {
		provasModel.associaProvaQuestoes(provaId, questoesId);
	}

	@Override
	public void remover(ProvaDTO provaDTO) throws ClassNotFoundException,
			SQLException {
		provasModel.remover(provaDTO);
	}

	@Override
	public List<ApostilaDTO> listarApostilas(Integer offSet,
			Integer recordPerPage) throws ClassNotFoundException, SQLException {

		List<ApostilaDTO> listaApostilas = provasModel.listarApostilas(offSet,
				recordPerPage);

		for (ApostilaDTO apostilaDTO : listaApostilas) {
			apostilaDTO.setListaProvas(provasModel
					.listarProvasPorApostilas(apostilaDTO.getApostilaId()));
		}
		return listaApostilas;
	}

	@Override
	public Integer getTotalRegistrosApostilas() throws ClassNotFoundException,
			SQLException {
		return provasModel.getTotalRegistrosApostilas();
	}

	/**
	 * M�todo respons�vel pelo upload dos arquivos (apostilas)
	 * 
	 * @param apostilaDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void gravarApostila(ApostilaDTO apostilaDTO)
			throws ClassNotFoundException, SQLException {
		provasModel.gravaApostila(apostilaDTO);
	}

	@Override
	public ApostilaDTO buscarApostilaPorId(Integer apostilaId)
			throws ClassNotFoundException, SQLException {
		return provasModel.buscarApostilaPorId(apostilaId);
	}

	/**
	 * M�todo que remove a apostila
	 * 
	 * @param apostilaDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	// TODO utilizado
	public void removerApostila(ApostilaDTO apostilaDTO)
			throws ClassNotFoundException, SQLException, IOException {

		String fileNameFull = apostilaDTO.getServerPath()
				+ apostilaDTO.getHashName() + "_" + apostilaDTO.getNome();
		File file = new File(fileNameFull);
		if (file.delete()) {
			provasModel.removerApostila(apostilaDTO);
		} else {
			throw new IOException(
					"Arquivo nao foi apagado, verificar a existencia do mesmo");
		}
	}

	@Override
	public List<ProvaDTO> listarProvasPorApostila(Integer apostilaId)
			throws ClassNotFoundException, SQLException {
		return provasModel.listarProvasPorApostila(apostilaId);
	}

	@Override
	public void associarApostilaProvas(Integer apostilaId,
			Integer[] provasIdInteger) throws ClassNotFoundException,
			SQLException {
		provasModel.associarApostilaProvas(apostilaId, provasIdInteger);

	}

	@Override
	public void removerApostilaProva(ApostilaDTO apostilaDTO, ProvaDTO provaDTO)
			throws ClassNotFoundException, SQLException {
		provasModel.removerApostilaProva(apostilaDTO, provaDTO);
	}

	/**
	 * M�todo que consulta a lista de provas criadas no sistema
	 * 
	 * @return List<ProvaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<ProvaDTO> listarProvas() throws ClassNotFoundException,
			SQLException {
		return provasModel.listarProvas();
	}

	@Override
	public List<AgendaDTO> listarAgenda(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException {
		return provasModel.listarAgenda(offSet, recordPerPage);
	}

	@Override
	public Integer getTotalRegistrosAgenda() throws ClassNotFoundException,
			SQLException {
		return provasModel.getTotalRegistrosAgenda();
	}

	@Override
	public AgendaDTO buscarAgendaPorId(Integer agendaId)
			throws ClassNotFoundException, SQLException {
		return provasModel.buscarAgendaPorId(agendaId);
	}

	@Override
	public ProvaRealizadaDTO buscarProvaRealizadaPorAgendaId(Integer agendaId)
			throws ClassNotFoundException, SQLException {
		return provasModel.buscarProvaRealizadaPorAgendaId(agendaId);
	}

	@Override
	public Integer realizarProva(ProvaRealizadaDTO provaRealizada)
			throws ClassNotFoundException, SQLException {
		return provasModel.realizarProva(provaRealizada);
	}

	@Override
	public ProvaRealizadaDTO buscarProvaRealizadaPorColaboradorMat(
			Integer matricula) throws ClassNotFoundException, SQLException {
		return provasModel.buscarProvaRealizadaPorColaboradorMat(matricula);
	}

	@Override
	public long buscarPorDataHoraFim(Integer provaRealizadaId)
			throws ClassNotFoundException, SQLException {
		return provasModel.buscarPorDataHoraFim(provaRealizadaId);
	}

	@Override
	public void entregarProva(ProvaRealizadaDTO provaRealizada)
			throws ClassNotFoundException, SQLException {
		provasModel.entregarProva(provaRealizada);
	}

	@Override
	public boolean existeProvaAgendadaMesmaData(Integer matricula,
			Date dataAgendada) throws ClassNotFoundException, SQLException {
		return provasModel.existeProvaAgendadaMesaData(matricula, dataAgendada);
	}

	/**
	 * M�todo que altera a prova
	 * 
	 * @param provaSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void alterarProva(ProvaDTO provaSelecionada)
			throws ClassNotFoundException, SQLException {
		provasModel.alterarProva(provaSelecionada);

	}

	/**
	 * M�todo que cadastra um quest�o conforme prova selecionada
	 * 
	 * @param questaoNova
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void gravarQuestao(QuestaoDTO questaoNova)
			throws ClassNotFoundException, SQLException {
		provasModel.gravarQuestao(questaoNova);

	}

	/**
	 * M�todo que edita a quest�o selecionada
	 * 
	 * @param questaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void alterarQuestao(QuestaoDTO questaoSelecionada)
			throws ClassNotFoundException, SQLException {
		provasModel.alterarQuestao(questaoSelecionada);
	}

	/**
	 * M�todo que cadastra uma op��o a quest�o selecionada
	 * 
	 * @param opcaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void gravarOpcao(OpcaoDTO opcaoNova) throws ClassNotFoundException,
			SQLException {
		provasModel.gravarOpcao(opcaoNova);
	}

	/**
	 * M�todo que consulta a lista de op��es (respostas) por questaoId
	 * 
	 * @param questaoId
	 * @return List<OpcaoDAO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<OpcaoDTO> listarOpcoesPorQuestaoId(Integer questaoId)
			throws ClassNotFoundException, SQLException {
		return provasModel.listarOpcoesPorQuestaoId(questaoId);
	}

	/**
	 * M�todo que define a op��o correta (qual � a resposta correta)
	 * 
	 * @param opcaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void definirOpcao(OpcaoDTO opcaoSelecionada)
			throws ClassNotFoundException, SQLException {
		provasModel.definirOpcao(opcaoSelecionada);
	}

	/**
	 * M�todo que excluir uma op��o (resposta)
	 * 
	 * @param opcaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void excluirOpcao(OpcaoDTO opcaoSelecionada)
			throws ClassNotFoundException, SQLException {
		provasModel.excluirOpcao(opcaoSelecionada);
	}

	/**
	 * M�todo que exclui uma quest�o, essa quest�o � excluida juntamento com as
	 * op��es
	 * 
	 * @param questaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void excluirQuestao(QuestaoDTO questaoSelecionada)
			throws ClassNotFoundException, SQLException {
		provasModel.excluirQuestao(questaoSelecionada);
	}

	/**
	 * M�todo que exclui a prova, essa exclus�o � em cascata. Remove a prova, as
	 * apostilas associadas, as quest�es e as op��es
	 * 
	 * @param provaSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	// TODO utilizado
	public void excluirProva(ProvaDTO provaSelecionada)
			throws ClassNotFoundException, SQLException, IOException {

		List<ApostilaDTO> listaApostilas = provasModel
				.listarApostilasPorProvaId(provaSelecionada.getProvaId());

		for (ApostilaDTO apostilaDTO : listaApostilas) {
			removerApostila(apostilaDTO);
		}

		provasModel.excluirProva(provaSelecionada);

	}
}
