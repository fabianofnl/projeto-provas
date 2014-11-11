package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.UUID;

import br.com.sgpo.constants.SGPOConstants.Agenda;
import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.model.SenhaModel;
import br.com.sgpo.model.SenhaModelImpl;

/**
 * @author Roseli
 * 
 */
public class SenhaServiceImpl implements SenhaService {

	private SenhaModel senhaModel = new SenhaModelImpl();

	@Override
	public FuncionarioDTO buscarUsuario(String nomeUsuario, String email)
			throws ClassNotFoundException, SQLException {
		return senhaModel.buscarUsuario(nomeUsuario, email);
	}

	@Override
	public void alterarSenha(FuncionarioDTO funcionario, String contextPath)
			throws ClassNotFoundException, SQLException {

		funcionario.setSenha(UUID.randomUUID().toString().substring(0, 9));

		senhaModel.alterarSenha(funcionario);

		MailServiceImpl mailService = new MailServiceImpl(funcionario,
				Agenda.NOVASENHA, contextPath);

		mailService.start();
	}
}
