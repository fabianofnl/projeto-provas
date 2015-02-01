package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.TemaDTO;
import br.com.sgpo.model.TemasModel;
import br.com.sgpo.model.TemasModelImpl;

/**
 * Classe que implementa os métodos de gerenciamento dos Temas
 * 
 * @author Roseli
 * 
 */
public class TemasServiceImpl implements TemasService {

	TemasModel temasModel = new TemasModelImpl();

	@Override
	public List<TemaDTO> listarTemas(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException {
		return temasModel.listarTemas(offSet, recordPerPage);
	}

	/**
	 * Método que cadastra os tema no sistema
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void gravar(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException {
		temasModel.gravar(temasDTO);
	}

	@Override
	public Integer getTotalRegistrosTemas() throws ClassNotFoundException,
			SQLException {
		return temasModel.getTotalRegistrosTemas();
	}

	@Override
	public TemaDTO buscarTemaPorId(Integer temaId)
			throws ClassNotFoundException, SQLException {
		return temasModel.buscarTemaporId(temaId);
	}

	/**
	 * Método que altera um tema selecionado
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void alterar(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException {
		temasModel.alterar(temasDTO);
	}

	/**
	 * Método que exclui um tema selecionado
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void remover(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException {
		temasModel.remover(temasDTO);
	}

	/**
	 * Método que consulta a lista de temas cadastradas no sistema
	 * 
	 * @return List<TemaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<TemaDTO> listarTemas() throws ClassNotFoundException,
			SQLException {
		return temasModel.listarTemas();
	}

}
