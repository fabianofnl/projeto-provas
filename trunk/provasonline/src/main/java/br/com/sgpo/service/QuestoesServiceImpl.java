package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

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
		return questoesModel.listarQuestoes(offSet, recordPerPage);
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
}
