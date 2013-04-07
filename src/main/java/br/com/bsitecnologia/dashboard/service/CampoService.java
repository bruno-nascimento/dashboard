package br.com.bsitecnologia.dashboard.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.bsitecnologia.dashboard.dao.OpcoesDao;
import br.com.bsitecnologia.dashboard.model.Campo;
import br.com.bsitecnologia.dashboard.model.Opcoes;

public class CampoService implements Serializable{

	private static final long serialVersionUID = 1110908260885376782L;
	
	@Inject OpcoesDao opcoesDao;
	
	public void saveOpcoes(List<Opcoes> listaOpcoes, Campo campo){
		deleteOpcoes(campo);
		for (Opcoes opcao : listaOpcoes) {
			opcao.setCampo(campo);
			opcoesDao.save(opcao);
		}
	}
	
	public void deleteOpcoes(Campo campo){
		opcoesDao.deleteByCampo(campo);
	}

	public List<Opcoes> getOpcoesByCampo(Campo campo) {
		return opcoesDao.getOpcoesByCampo(campo);
	}

}
