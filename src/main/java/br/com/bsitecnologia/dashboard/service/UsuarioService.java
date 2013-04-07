package br.com.bsitecnologia.dashboard.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.bsitecnologia.dashboard.dao.TelefoneDao;
import br.com.bsitecnologia.dashboard.model.Telefone;
import br.com.bsitecnologia.dashboard.model.Usuario;

public class UsuarioService implements Serializable{
	
	private static final long serialVersionUID = -4547841571365952505L;

	@Inject
	TelefoneDao telefoneDao;
	
	public void saveTelefones(List<Telefone> listaTelefones, Usuario usuario){
		deleteTelefones(usuario);
		for (Telefone telefone : listaTelefones) {
			telefone.setUsuario(usuario);
			telefoneDao.save(telefone);
		}
	}
	
	public void deleteTelefones(Usuario usuario){
		telefoneDao.deleteByUsuario(usuario);
	}

}
