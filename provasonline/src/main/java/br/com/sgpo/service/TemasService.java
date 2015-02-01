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

	public List<TemaDTO> listarTemas(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que cadastra os tema no sistema
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void gravar(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException;

	public Integer getTotalRegistrosTemas() throws ClassNotFoundException,
			SQLException;

	public TemaDTO buscarTemaPorId(Integer temaId)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que altera um tema selecionado
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void alterar(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException;

	/**
	 * M�todo que exclui um tema selecionado
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void remover(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException;

	/**
	 * M�todo que consulta a lista de temas cadastradas no sistema
	 * 
	 * @return List<TemaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<TemaDTO> listarTemas() throws ClassNotFoundException,
			SQLException;

}
