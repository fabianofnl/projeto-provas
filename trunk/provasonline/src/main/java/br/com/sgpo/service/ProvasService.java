package br.com.sgpo.service;

import java.io.IOException;
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

	public ApostilaDTO buscarApostilaPorId(Integer apostilaId)
			throws ClassNotFoundException, SQLException;

	public void removerApostila(ApostilaDTO apostilaDTO)
			throws ClassNotFoundException, SQLException, IOException;

	public List<ProvaDTO> listarProvas(Integer apostilaId)
			throws ClassNotFoundException, SQLException;

	public void associarApostilaProvas(Integer apostilaId,
			Integer[] provasIdInteger) throws ClassNotFoundException,
			SQLException;

	public void removerApostilaProva(ApostilaDTO apostilaDTO, ProvaDTO provaDTO)
			throws ClassNotFoundException, SQLException;

}
