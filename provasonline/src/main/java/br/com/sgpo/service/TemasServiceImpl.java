package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.TemasDTO;
import br.com.sgpo.model.TemasModel;
import br.com.sgpo.model.TemasModelImpl;

/**
 * @author Roseli
 * 
 */
public class TemasServiceImpl implements TemasService {

	TemasModel temasModel = new TemasModelImpl();

	@Override
	public List<TemasDTO> listarTemas(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException {
		return temasModel.listarTemas(offSet, recordPerPage);
	}

	@Override
	public void gravar(TemasDTO temasDTO) throws ClassNotFoundException,
			SQLException {
		temasModel.gravar(temasDTO);
	}

	@Override
	public Integer getTotalRegistrosTemas() throws ClassNotFoundException,
			SQLException {
		return temasModel.getTotalRegistrosTemas();
	}

}
