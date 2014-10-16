package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.QuestaoDTO;

public interface ProvasService {

	public List<ProvaDTO> listarProvas(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException;

	public void gravar(ProvaDTO provaDTO) throws ClassNotFoundException,
			SQLException;

	public Integer getTotalRegistrosProvas() throws ClassNotFoundException,
			SQLException;

	public ProvaDTO buscarProvaPorId(Integer provaId)
			throws ClassNotFoundException, SQLException;

	public void removerQuestao(QuestaoDTO questaoDTO, ProvaDTO provaDTO)
			throws ClassNotFoundException, SQLException;

	public void associarProvaQuestoes(Integer provaId, Integer[] questoesId)
			throws ClassNotFoundException, SQLException;

	public void remover(ProvaDTO provaDTO) throws ClassNotFoundException,
			SQLException;

	public List<ApostilaDTO> listarApostilas(Integer offSet,
			Integer recordPerPage) throws ClassNotFoundException, SQLException;

	public Integer getTotalRegistrosApostilas() throws ClassNotFoundException,
			SQLException;

	public void gravarApostila(ApostilaDTO apostilaDTO)
			throws ClassNotFoundException, SQLException;

}
