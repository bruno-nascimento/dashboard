package br.com.bsitecnologia.dashboard.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bsitecnologia.dashboard.model.Usuario;
import br.com.bsitecnologia.dashboard.resources.qualifiers.UsuarioLogado;
import br.com.bsitecnologia.dashboard.service.LoginService;

@SessionScoped
@Named
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1540160644128909016L;

	@Inject @New private Usuario usuarioLogado;
	
	@Inject LoginService loginService;
	
	public String login() {
		usuarioLogado = loginService.authenticateUser(usuarioLogado);
		return redirectUsuario();
	}
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}
	
	@Produces
	@UsuarioLogado
	@Named("usuarioLogado")
	public Usuario getUsuarioLogado(){
		return usuarioLogado;
	}
	
	private String redirectUsuario(){
		if(getUsuarioLogado().isLogado()){
			return "/admin/cliente/cliente?faces-redirect=true";
		}
		return "/login?faces-redirect=true";
	}
	
	public Usuario getUsuario() {
		return usuarioLogado;
	}

	public void setUsuario(Usuario usuario) {
		this.usuarioLogado = usuario;
	}
	
}
