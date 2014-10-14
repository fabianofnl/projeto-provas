package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.TemaDTO;

/**
 * @author Roseli
 * 
 */
public interface TemasService {

	public List<TemaDTO> listarTemas(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException;

	public void gravar(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException;

	public Integer getTotalRegistrosTemas() throws ClassNotFoundException,
			SQLException;

	public TemaDTO buscarTemaPorId(Integer temaId)
			throws ClassNotFoundException, SQLException;

	public void alterar(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException;

	public void remover(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException;

	public List<TemaDTO> listarTemas() throws ClassNotFoundException,
			SQLException;

}
