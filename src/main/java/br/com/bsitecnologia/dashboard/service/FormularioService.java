package br.com.bsitecnologia.dashboard.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.bsitecnologia.dashboard.dao.CampoFormularioDao;
import br.com.bsitecnologia.dashboard.model.Campo;
import br.com.bsitecnologia.dashboard.model.CampoFormulario;
import br.com.bsitecnologia.dashboard.model.Formulario;

public class FormularioService implements Serializable{

	private static final long serialVersionUID = 1110908260885376782L;
	
	@Inject CampoFormularioDao campoFormularioDao;
	
	public void saveOpcoes(List<Campo> listaCampo, Formulario formulario){
		deleteCampoFormulario(formulario);
		for (Campo campo : listaCampo) {
			CampoFormulario campoFormulario = new CampoFormulario();
			campoFormulario.setCampo(campo);
			campoFormulario.setFormulario(formulario);
			
			campoFormularioDao.save(campoFormulario);
		}
	}
	
	public void deleteCampoFormulario(Formulario formulario){
		campoFormularioDao.deleteByFormulario(formulario);
	}

}
