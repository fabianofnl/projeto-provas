package br.com.sgpo.service;

import br.com.sgpo.model.Usuario;


/**
 * @author Roseli
 *
 */
public interface EsqueciSenhaService {

	public Usuario buscarUsuario(String nomeUsuario, String email);

	public void alterarSenha(Usuario usuario);

}
