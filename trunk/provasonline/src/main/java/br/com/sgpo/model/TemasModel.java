package br.com.sgpo.model;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.TemasDTO;

/**
 * @author Roseli
 * 
 */
public interface TemasModel {

	public List<TemasDTO> listarTemas(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException;

	public void gravar(TemasDTO temasDTO) throws ClassNotFoundException,
			SQLException;

	public Integer getTotalRegistrosTemas() throws ClassNotFoundException,
			SQLException;

}
