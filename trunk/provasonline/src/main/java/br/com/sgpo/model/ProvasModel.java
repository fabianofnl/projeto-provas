package br.com.sgpo.model;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.dto.TemaDTO;

public interface ProvasModel {

	public List<ProvaDTO> listarProvas(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException;

	public List<TemaDTO> listarTemasPorProva(Integer provaId)
			throws ClassNotFoundException, SQLException;

	public List<QuestaoDTO> listarQuestoesPorTemas(Integer temaId,
			Integer provaId) throws ClassNotFoundException, SQLException;

	public void gravar(ProvaDTO provaDTO) throws ClassNotFoundException,
			SQLException;

	public Integer getTotalRegistrosProvas() throws ClassNotFoundException,
			SQLException;

	public ProvaDTO buscarProvaPorId(Integer provaId)
			throws ClassNotFoundException, SQLException;

	public void removerQuestao(QuestaoDTO questaoDTO, ProvaDTO provaDTO)
			throws ClassNotFoundException, SQLException;

	public void associaProvaQuestoes(Integer provaId, Integer[] questoesId)
			throws ClassNotFoundException, SQLException;

	public void remover(ProvaDTO provaDTO) throws ClassNotFoundException,
			SQLException;

	public List<ApostilaDTO> listarApostilas(Integer offSet,
			Integer recordPerPage) throws ClassNotFoundException, SQLException;

	public List<ProvaDTO> listarProvasPorApostilas(Integer apostilaId)
			throws ClassNotFoundException, SQLException;

	public Integer getTotalRegistrosApostilas() throws ClassNotFoundException,
			SQLException;

	public void gravaApostila(ApostilaDTO apostilaDTO)
			throws ClassNotFoundException, SQLException;

	public ApostilaDTO buscarApostilaPorId(Integer apostilaId)
			throws ClassNotFoundException, SQLException;

	public void removerApostila(ApostilaDTO apostilaDTO)
			throws ClassNotFoundException, SQLException;

	public List<ProvaDTO> listarProvas(Integer apostilaId) throws ClassNotFoundException,
			SQLException;

	public void associarApostilaProvas(Integer apostilaId,
			Integer[] provasIdInteger) throws ClassNotFoundException,
			SQLException;
}
