package br.com.sgpo.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.QuestaoDTO;
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

	@Override
	public ProvaDTO buscarProvaPorId(Integer provaId)
			throws ClassNotFoundException, SQLException {
		return provasModel.buscarProvaPorId(provaId);
	}

	@Override
	public void removerQuestao(QuestaoDTO questaoDTO, ProvaDTO provaDTO)
			throws ClassNotFoundException, SQLException {
		provasModel.removerQuestao(questaoDTO, provaDTO);
	}

	@Override
	public void associarProvaQuestoes(Integer provaId, Integer[] questoesId)
			throws ClassNotFoundException, SQLException {
		provasModel.associaProvaQuestoes(provaId, questoesId);
	}

	@Override
	public void remover(ProvaDTO provaDTO) throws ClassNotFoundException,
			SQLException {
		provasModel.remover(provaDTO);
	}

	@Override
	public List<ApostilaDTO> listarApostilas(Integer offSet,
			Integer recordPerPage) throws ClassNotFoundException, SQLException {

		List<ApostilaDTO> listaApostilas = provasModel.listarApostilas(offSet,
				recordPerPage);

		for (ApostilaDTO apostilaDTO : listaApostilas) {
			apostilaDTO.setListaProvas(provasModel
					.listarProvasPorApostilas(apostilaDTO.getApostilaId()));
		}
		return listaApostilas;
	}

	@Override
	public Integer getTotalRegistrosApostilas() throws ClassNotFoundException,
			SQLException {
		return provasModel.getTotalRegistrosApostilas();
	}

	@Override
	public void gravarApostila(ApostilaDTO apostilaDTO)
			throws ClassNotFoundException, SQLException {
		provasModel.gravaApostila(apostilaDTO);
	}

	@Override
	public ApostilaDTO buscarApostilaPorId(Integer apostilaId)
			throws ClassNotFoundException, SQLException {
		return provasModel.buscarApostilaPorId(apostilaId);
	}

	@Override
	public void removerApostila(ApostilaDTO apostilaDTO)
			throws ClassNotFoundException, SQLException, IOException {

		String fileNameFull = apostilaDTO.getServerPath()
				+ apostilaDTO.getHashName() + "_" + apostilaDTO.getNome();
		File file = new File(fileNameFull);
		if (file.delete()) {
			provasModel.removerApostila(apostilaDTO);
		} else {
			throw new IOException(
					"Arquivo nao foi apagado, verificar a existencia do mesmo");
		}
	}

	@Override
	public List<ProvaDTO> listarProvas(Integer apostilaId)
			throws ClassNotFoundException, SQLException {
		return provasModel.listarProvas(apostilaId);
	}

	@Override
	public void associarApostilaProvas(Integer apostilaId,
			Integer[] provasIdInteger) throws ClassNotFoundException,
			SQLException {
		provasModel.associarApostilaProvas(apostilaId, provasIdInteger);

	}

	@Override
	public void removerApostilaProva(ApostilaDTO apostilaDTO, ProvaDTO provaDTO)
			throws ClassNotFoundException, SQLException {
		provasModel.removerApostilaProva(apostilaDTO, provaDTO);
	}
}
