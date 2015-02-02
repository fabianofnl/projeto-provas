package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.constants.SGPOConstants.Agenda;
import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.model.AgendaModel;
import br.com.sgpo.model.AgendaModelImpl;

/**
 * Classe que implementa os método de gerenciamento da Agenda
 * 
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
	public void agendarProva(AgendaDTO agendaNova, String contextPath)
			throws ClassNotFoundException, SQLException {

		FuncionarioService funcionarioService = new FuncionarioServiceImpl();
		ProvasService provasService = new ProvasServiceImpl();

		agendaModel.agendarProva(agendaNova);

		agendaNova.setFuncionario(funcionarioService
				.buscarFuncionarioPorMatricula(agendaNova.getMatColaborador()));

		agendaNova.setProva(provasService.buscarProvaPorId(agendaNova
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
	public void excluirAgenda(AgendaDTO agendaSelecionada, String contextPath)
			throws ClassNotFoundException, SQLException {

		FuncionarioService funcionarioService = new FuncionarioServiceImpl();
		ProvasService provasService = new ProvasServiceImpl();

		agendaModel.excluirProva(agendaSelecionada);

		agendaSelecionada.setFuncionario(funcionarioService
				.buscarFuncionarioPorMatricula(agendaSelecionada
						.getMatColaborador()));

		agendaSelecionada.setProva(provasService
				.buscarProvaPorId(agendaSelecionada.getProvaId()));

		MailServiceImpl mailService = new MailServiceImpl(
				agendaSelecionada.getFuncionario(),
				agendaSelecionada.getProva(),
				agendaSelecionada.getProvaAgendada(), contextPath,
				Agenda.CANCELAR);
		mailService.start();
	}
}
