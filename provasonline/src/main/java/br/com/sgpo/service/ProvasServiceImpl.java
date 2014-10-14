package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.TemaDTO;
import br.com.sgpo.model.ProvasModel;
import br.com.sgpo.model.ProvasModelImpl;

public class ProvasServiceImpl implements ProvasService {

	private ProvasModel provasModel = new ProvasModelImpl();

	@Override
	public List<ProvaDTO> listarProvas(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException {

		List<ProvaDTO> listaProvas = provasModel.listarProvas(offSet,
				recordPerPage);

		for (ProvaDTO provaDTO : listaProvas) {
			provaDTO.setListaTemas(provasModel.listarTemasPorProva(provaDTO
					.getProvaId()));
			provaDTO.setQuantidadeTemas(provaDTO.getListaTemas().size());

			Integer totalQuestoes = 0;
			for (TemaDTO temaDTO : provaDTO.getListaTemas()) {
				temaDTO.setListaQuestoes(provasModel.listarQuestoesPorTemas(
						temaDTO.getTemaId(), provaDTO.getProvaId()));
				totalQuestoes += temaDTO.getListaQuestoes().size();
			}
			provaDTO.setQuantidadeQuestoes(totalQuestoes);

		}
		return listaProvas;
	}

	@Override
	public void gravar(ProvaDTO provaDTO) throws ClassNotFoundException,
			SQLException {
		provasModel.gravar(provaDTO);
	}

	@Override
	public Integer getTotalRegistrosProvas() throws ClassNotFoundException,
			SQLException {
		return provasModel.getTotalRegistrosProvas();
	}
}
