package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.constants.SGPOConstants.Agenda;
import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.model.AgendaModel;
import br.com.sgpo.model.AgendaModelImpl;

/**
 * @author Roseli
 * 
 */
public class AgendaServiceImpl implements AgendaService {

	private AgendaModel agendaModel = new AgendaModelImpl();

	/**
	 * Método que busca pela lista de agendamento realizado
	 * 
	 * @return List<AgendaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<AgendaDTO> listarAgendas() throws ClassNotFoundException,
			SQLException {
		return agendaModel.listarAgendas();
	}

	/**
	 * Método que busca pela lista de agendamento atual (as não realizadas)
	 * 
	 * @return List<AgendaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<AgendaDTO> listarAgendasNaoRealizadas()
			throws ClassNotFoundException, SQLException {
		return agendaModel.listarAgendasNaoRealizadas();
	}

	/**
	 * Método que efetiva o agendamento da prova
	 * 
	 * @param agendaNova
	 * @param contextPath
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void agendarProva(AgendaDTO agendaNova, String contextPath)
			throws ClassNotFoundException, SQLException {
		agendaModel.agendarProva(agendaNova);

		agendaNova.setFuncionario(new FuncionarioServiceImpl()
				.buscarFuncionarioPorMatricula(agendaNova.getMatColaborador()));

		agendaNova.setProva(new ProvasServiceImpl().buscarProvaPorId(agendaNova
				.getProvaId()));

		MailServiceImpl mailService = new MailServiceImpl(
				agendaNova.getFuncionario(), agendaNova.getProva(),
				agendaNova.getProvaAgendada(), contextPath, Agenda.AGENDAR);
		mailService.start();

	}

	/**
	 * Método que excluir um agendamento
	 * 
	 * @param agendaSelecionada
	 * @param contextPath
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void excluirAgenda(AgendaDTO agendaSelecionada, String contextPath)
			throws ClassNotFoundException, SQLException {
		agendaModel.excluirProva(agendaSelecionada);

		agendaSelecionada.setFuncionario(new FuncionarioServiceImpl()
				.buscarFuncionarioPorMatricula(agendaSelecionada
						.getMatColaborador()));

		agendaSelecionada.setProva(new ProvasServiceImpl()
				.buscarProvaPorId(agendaSelecionada.getProvaId()));

		MailServiceImpl mailService = new MailServiceImpl(
				agendaSelecionada.getFuncionario(),
				agendaSelecionada.getProva(),
				agendaSelecionada.getProvaAgendada(), contextPath,
				Agenda.CANCELAR);
		mailService.start();
	}
}
