package br.com.sgpo.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.ProvaRealizadaDTO;
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
			Date dataAgendada, String contextPath)
			throws ClassNotFoundException, SQLException;

	public List<AgendaDTO> listarAgenda(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException;

	public Integer getTotalRegistrosAgenda() throws ClassNotFoundException,
			SQLException;

	public AgendaDTO buscarAgendaPorId(Integer agendaId)
			throws ClassNotFoundException, SQLException;

	public void removerAgenda(AgendaDTO agendaDTO, String contextPath)
			throws ClassNotFoundException, SQLException;

	public void atualizarAgenda(AgendaDTO agendaDTO, String context)
			throws ClassNotFoundException, SQLException;

	public ProvaRealizadaDTO buscarProvaRealizadaPorAgendaId(Integer agendaId)
			throws ClassNotFoundException, SQLException;

	public Integer realizarProva(ProvaRealizadaDTO provaRealizada)
			throws ClassNotFoundException, SQLException;

	public ProvaRealizadaDTO buscarProvaRealizadaPorColaboradorMat(
			Integer matricula) throws ClassNotFoundException, SQLException;

	public long buscarPorDataHoraFim(Integer provaRealizadaId)
			throws ClassNotFoundException, SQLException;

	public void entregarProva(ProvaRealizadaDTO provaRealizada)
			throws ClassNotFoundException, SQLException;

	public boolean existeProvaAgendadaMesmaData(Integer matricula,
			Date dataAgendada) throws ClassNotFoundException, SQLException;

	public void alterarProva(ProvaDTO provaSelecionada)
			throws ClassNotFoundException, SQLException;

	public void gravarQuestao(QuestaoDTO questaoNova)
			throws ClassNotFoundException, SQLException;

	public void alterarQuestao(QuestaoDTO questaoSelecionada)
			throws ClassNotFoundException, SQLException;

	public void gravarOpcao(OpcaoDTO opcaoSelecionada)
			throws ClassNotFoundException, SQLException;

	public List<OpcaoDTO> listarOpcoesPorQuestaoId(Integer questaoId)
			throws ClassNotFoundException, SQLException;

	public void definirOpcao(OpcaoDTO opcaoSelecionada)
			throws ClassNotFoundException, SQLException;

	public void excluirOpcao(OpcaoDTO opcaoSelecionada)
			throws ClassNotFoundException, SQLException;

}
