package br.com.bsitecnologia.dashboard.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;

import br.com.bsitecnologia.dashboard.model.Usuario;
import br.com.bsitecnologia.dashboard.resources.qualifiers.UsuarioLogado;
import br.com.bsitecnologia.dashboard.service.LoginService;

@ConversationScoped
@Named
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1540160644128909016L;

	@Inject @New private Usuario usuario;
	
	@Inject LoginService loginService;
	
	public String login() {
		usuario = loginService.authenticateUser(usuario);
		return redirectUsuario(usuario);
	}
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}
	
	@SessionScoped
	@Produces
	@UsuarioLogado
	@Named("usuarioLogado")
	public Usuario getUsuarioLogado(){
		return usuario;
	}
	
	private String redirectUsuario(Usuario usuario){
		if(usuario.isLogado()){
			return "/dashboard/demandas?faces-redirect=true";
		}
		return "/login?faces-redirect=true";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
