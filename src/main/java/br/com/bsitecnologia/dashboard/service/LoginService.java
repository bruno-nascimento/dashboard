package br.com.bsitecnologia.dashboard.service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import br.com.bsitecnologia.dashboard.dao.UsuarioDao;
import br.com.bsitecnologia.dashboard.model.Usuario;
import br.com.bsitecnologia.dashboard.resources.qualifiers.UsuarioLogado;

public class LoginService implements Serializable{
	
	private static final long serialVersionUID = 5609188963373726589L;

	@Inject UsuarioDao usuarioDao;
	
	public Usuario authenticateUser(Usuario usuario) {
		usuario = usuarioDao.authenticateUser(usuario);
		usuario.setLogado(usuarioLoginState(usuario));
		return usuario;
	}
	
	private boolean usuarioLoginState(Usuario usuario){
		return null == usuario || null == usuario.getId() || usuario.getId().equals(0) ? false : true;
	}

}
