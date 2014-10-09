package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.TemasDTO;

/**
 * @author Roseli
 * 
 */
public interface TemasService {

	public List<TemasDTO> listarTemas(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException;

	public void gravar(TemasDTO temasDTO) throws ClassNotFoundException,
			SQLException;

	public Integer getTotalRegistrosTemas() throws ClassNotFoundException,
			SQLException;

	public TemasDTO buscarTemaPorId(Integer temaId)
			throws ClassNotFoundException, SQLException;

	public void alterar(TemasDTO temasDTO) throws ClassNotFoundException,
			SQLException;

	public void remover(TemasDTO temasDTO) throws ClassNotFoundException,
			SQLException;

	public List<TemasDTO> listarTemas() throws ClassNotFoundException,
			SQLException;

}
