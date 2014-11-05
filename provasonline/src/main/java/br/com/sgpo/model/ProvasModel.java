package br.com.sgpo.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.ProvaRealizadaDTO;
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

	public List<ProvaDTO> listarProvasPorApostila(Integer apostilaId)
			throws ClassNotFoundException, SQLException;

	public void associarApostilaProvas(Integer apostilaId,
			Integer[] provasIdInteger) throws ClassNotFoundException,
			SQLException;

	public void removerApostilaProva(ApostilaDTO apostilaDTO, ProvaDTO provaDTO)
			throws ClassNotFoundException, SQLException;

	public List<ProvaDTO> listarProvas() throws ClassNotFoundException,
			SQLException;

	public void agendarProva(FuncionarioDTO funcionario, ProvaDTO prova,
			Date dataAgendada) throws ClassNotFoundException, SQLException;

	public List<AgendaDTO> listarAgenda(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException;

	public Integer getTotalRegistrosAgenda() throws ClassNotFoundException,
			SQLException;

	public AgendaDTO buscarAgendaPorId(Integer agendaId)
			throws ClassNotFoundException, SQLException;

	public void removerAgenda(AgendaDTO agendaDTO)
			throws ClassNotFoundException, SQLException;

	public void atualizarAgenda(AgendaDTO agendaDTO)
			throws ClassNotFoundException, SQLException;

	public ProvaRealizadaDTO buscarProvaRealizadaPorAgendaId(Integer agendaId)
			throws ClassNotFoundException, SQLException;

	public Integer realizarProva(ProvaRealizadaDTO provaRealizada)
			throws ClassNotFoundException, SQLException;

	public ProvaRealizadaDTO buscarProvaRealizadaPorColaboradorMat(
			Integer matricula) throws ClassNotFoundException, SQLException;
}
