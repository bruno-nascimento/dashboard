package br.com.bsitecnologia.dashboard.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;

import br.com.bsitecnologia.dashboard.dao.UsuarioDao;
import br.com.bsitecnologia.dashboard.model.Usuario;
import br.com.bsitecnologia.dashboard.resources.qualifiers.UsuarioLogado;

@ConversationScoped
@Named
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1540160644128909016L;

	@Inject
	@New
	private Usuario usuario;
	
	@Inject UsuarioDao usuarioDao;
	
	public String login() {
//		usuario = usuarioDao.authenticateUser(usuario);
//		System.out.println(usuario.getLogin());
//		if(usuario.isLogado()){
//			return "/admin/cargo/cargo?faces-redirect=true";
//		}else{
			return "/login?faces-redirect=true";
//		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@SessionScoped
	@Produces
	@UsuarioLogado
	@Named("usuarioLogado")
	public Usuario getUsuarioLogado(){
		return usuario;
	}
	
}
