package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.TemaDTO;
import br.com.sgpo.model.TemasModel;
import br.com.sgpo.model.TemasModelImpl;

/**
 * Classe que implementa os m�todos de gerenciamento dos Temas
 * 
 * @author Roseli
 * 
 */
public class TemasServiceImpl implements TemasService {

	TemasModel temasModel = new TemasModelImpl();

	/**
	 * M�todo que cadastra os tema no sistema
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void gravar(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException {
		temasModel.gravar(temasDTO);
	}

	/**
	 * M�todo que altera um tema selecionado
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterar(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException {
		temasModel.alterar(temasDTO);
	}

	/**
	 * M�todo que exclui um tema selecionado
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void remover(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException {
		temasModel.remover(temasDTO);
	}

	/**
	 * M�todo que consulta a lista de temas cadastradas no sistema
	 * 
	 * @return List<TemaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<TemaDTO> listarTemas() throws ClassNotFoundException,
			SQLException {
		return temasModel.listarTemas();
	}
}
