package br.com.sgpo.service;

import br.com.sgpo.dto.UsuarioDTO;


/**
 * @author Roseli
 *
 */
public interface EsqueciSenhaService {

	public UsuarioDTO buscarUsuario(String nomeUsuario, String email);

	public void alterarSenha(UsuarioDTO usuario);

}
