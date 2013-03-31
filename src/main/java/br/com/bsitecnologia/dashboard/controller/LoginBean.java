package br.com.bsitecnologia.dashboard.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bsitecnologia.dashboard.model.AcaoEnum;
import br.com.bsitecnologia.dashboard.model.DominioEnum;
import br.com.bsitecnologia.dashboard.model.PerfilAcaoDominio;
import br.com.bsitecnologia.dashboard.model.Usuario;
import br.com.bsitecnologia.dashboard.resources.qualifiers.UsuarioLogado;
import br.com.bsitecnologia.dashboard.service.LoginService;

@SessionScoped
@Named
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1540160644128909016L;

	@Inject @New private Usuario usuarioLogado;
	
	@Inject LoginService loginService;
	
	private DominioEnum dominio;
	private AcaoEnum acaoEnum;
	
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
			return "/admin/home?faces-redirect=true";
		}
		return "/login?faces-redirect=true";
	}
	
	public boolean hasAccessToDominio(DominioEnum dominio){
		for(PerfilAcaoDominio pad : usuarioLogado.getPerfil().getPerfilAcaoDominios()){
			if(pad.getDominio().getId().equals(dominio.getId())){
				return true;
			}
		}
		return false;
	}
	
	public Usuario getUsuario() {
		return usuarioLogado;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuarioLogado = usuario;
	}
	
	public DominioEnum getDominio() {
		return dominio;
	}
	
	public void setDominio(DominioEnum dominio) {
		this.dominio = dominio;
	}
	
	public AcaoEnum getAcaoEnum() {
		return acaoEnum;
	}
	
	public void setAcaoEnum(AcaoEnum acaoEnum) {
		this.acaoEnum = acaoEnum;
	}
	
}
