package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.model.QuestoesModel;
import br.com.sgpo.model.QuestoesModelImpl;

/**
 * @author Roseli
 * 
 */
public class QuestoesServiceImpl implements QuestoesService {

	private QuestoesModel questoesModel = new QuestoesModelImpl();

	@Override
	public List<QuestaoDTO> listarQuestoes(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException {

		List<QuestaoDTO> listaQuestoes = questoesModel.listarQuestoes(offSet,
				recordPerPage);

		for (QuestaoDTO questaoDTO : listaQuestoes) {
			questaoDTO.setListaOpcoes(questoesModel
					.listarOpcoesPorQuestaoId(questaoDTO.getQuestaoId()));
		}

		return listaQuestoes;
	}

	@Override
	public Integer getTotalRegistrosQuestoes() throws ClassNotFoundException,
			SQLException {
		return questoesModel.getTotalRegistrosQuestoes();
	}

	@Override
	public void gravar(QuestaoDTO questaoDTO) throws ClassNotFoundException,
			SQLException {
		questoesModel.gravar(questaoDTO);
	}

	@Override
	public QuestaoDTO buscarQuestaoPorId(Integer questaoId)
			throws ClassNotFoundException, SQLException {
		return questoesModel.buscarQuestaoPorId(questaoId);
	}

	@Override
	public void remover(QuestaoDTO questaoDTO) throws ClassNotFoundException,
			SQLException {
		questoesModel.remover(questaoDTO);
	}

	@Override
	public void alterar(QuestaoDTO questaoDTO, Integer questaoIdAntiga)
			throws ClassNotFoundException, SQLException {
		questoesModel.alterar(questaoDTO, questaoIdAntiga);
	}

	@Override
	public void gravarOpcao(OpcaoDTO opcao) throws ClassNotFoundException,
			SQLException {
		questoesModel.gravarOpcao(opcao);
	}

	@Override
	public OpcaoDTO buscarOpcaoPorId(Integer opcaoId)
			throws ClassNotFoundException, SQLException {

		return questoesModel.buscarOpcaoPorId(opcaoId);
	}

	@Override
	public void removerOpcao(OpcaoDTO opcaoDTO) throws ClassNotFoundException,
			SQLException {
		questoesModel.removerOpcao(opcaoDTO);
	}

	@Override
	public List<OpcaoDTO> listarOpcoesPorQuestaoId(Integer questaoId)
			throws ClassNotFoundException, SQLException {
		return questoesModel.listarOpcoesPorQuestaoId(questaoId);
	}

	@Override
	public void definirOpcao(List<OpcaoDTO> listaOpcoes)
			throws ClassNotFoundException, SQLException {
		questoesModel.definirOpcao(listaOpcoes);
	}

	@Override
	public void alterarOpcao(OpcaoDTO opcaoDTO) throws ClassNotFoundException,
			SQLException {
		questoesModel.alterarOpcao(opcaoDTO);
	}
}
