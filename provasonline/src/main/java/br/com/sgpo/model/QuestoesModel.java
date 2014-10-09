package br.com.sgpo.model;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.QuestaoDTO;

/**
 * @author Roseli
 * 
 */
public interface QuestoesModel {

	public List<QuestaoDTO> listarQuestoes(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException;

	public Integer getTotalRegistrosQuestoes() throws ClassNotFoundException,
			SQLException;

	public void gravar(QuestaoDTO questaoDTO) throws ClassNotFoundException,
			SQLException;

}
