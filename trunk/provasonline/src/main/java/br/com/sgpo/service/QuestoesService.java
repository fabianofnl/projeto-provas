package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.QuestaoDTO;

/**
 * @author Roseli
 * 
 */
public interface QuestoesService {

	public List<QuestaoDTO> listarQuestoes(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException;

	public Integer getTotalRegistrosQuestoes() throws ClassNotFoundException,
			SQLException;

	public void gravar(QuestaoDTO questaoDTO) throws ClassNotFoundException,
			SQLException;

	public QuestaoDTO buscarQuestaoPorId(Integer questaoId)
			throws ClassNotFoundException, SQLException;

	public void remover(QuestaoDTO questaoDTO) throws ClassNotFoundException,
			SQLException;

	public void alterar(QuestaoDTO questaoDTO, Integer questaoIdAntiga)
			throws ClassNotFoundException, SQLException;

	public void gravarOpcao(OpcaoDTO opcao) throws ClassNotFoundException,
			SQLException;

	public OpcaoDTO buscarOpcaoPorId(Integer opcaoId)
			throws ClassNotFoundException, SQLException;

	public void removerOpcao(OpcaoDTO opcaoDTO) throws ClassNotFoundException,
			SQLException;

	public List<OpcaoDTO> listarOpcoesPorQuestaoId(Integer questaoId)
			throws ClassNotFoundException, SQLException;

	public void definirOpcao(List<OpcaoDTO> listaOpcoes)
			throws ClassNotFoundException, SQLException;

}