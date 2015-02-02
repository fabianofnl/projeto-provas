package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.TemaDTO;

/**
 * Interface que possui os m�todos que gerenciam os Temas
 * 
 * @author Roseli
 * 
 */
public interface TemasService {

	/**
	 * M�todo que cadastra os tema no sistema
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void gravar(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException;

	/**
	 * M�todo que altera um tema selecionado
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterar(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException;

	/**
	 * M�todo que exclui um tema selecionado
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void remover(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException;

	/**
	 * M�todo que consulta a lista de temas cadastradas no sistema
	 * 
	 * @return List<TemaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<TemaDTO> listarTemas() throws ClassNotFoundException,
			SQLException;

}
