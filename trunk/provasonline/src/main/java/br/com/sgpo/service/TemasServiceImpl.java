package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.TemaDTO;
import br.com.sgpo.model.TemasModel;
import br.com.sgpo.model.TemasModelImpl;

/**
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

	@Override
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

	@Override
	public void alterar(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException {
		temasModel.alterar(temasDTO);
	}

	@Override
	public void remover(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException {
		temasModel.remover(temasDTO);
	}

	@Override
	public List<TemaDTO> listarTemas() throws ClassNotFoundException,
			SQLException {
		return temasModel.listarTemas();
	}

}
