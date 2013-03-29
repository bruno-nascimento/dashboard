package br.com.bsitecnologia.dashboard.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.bsitecnologia.dashboard.dao.UsuarioDao;
import br.com.bsitecnologia.dashboard.model.Usuario;

public class LoginService implements Serializable{
	
	private static final long serialVersionUID = 5609188963373726589L;

	@Inject UsuarioDao usuarioDao;
	
	public Usuario authenticateUser(Usuario usuario) {
		usuario = usuarioDao.authenticateUser(usuario);
//		usuario.getPerfil();
//		usuario.getPerfil().getPerfilAcaoDominios().get(0).getDominio().getNome();
		usuario.setLogado(usuarioLoginState(usuario));
		return usuario;
	}
	
	private boolean usuarioLoginState(Usuario usuario){
		return null == usuario || null == usuario.getId() || usuario.getId().equals(0) ? false : true;
	}

}
