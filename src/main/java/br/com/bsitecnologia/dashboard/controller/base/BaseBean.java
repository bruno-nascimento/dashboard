package br.com.bsitecnologia.dashboard.controller.base;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.bsitecnologia.dashboard.controller.template.BreadcrumbEnum;
import br.com.bsitecnologia.dashboard.model.DominioEnum;
import br.com.bsitecnologia.dashboard.model.Usuario;
import br.com.bsitecnologia.dashboard.resources.qualifiers.UsuarioLogado;
import br.com.bsitecnologia.dashboard.util.BaseEntity;

@SuppressWarnings("unchecked")
public abstract class BaseBean<T extends BaseEntity> implements Serializable{

	private static final long serialVersionUID = 3226781095227842827L;
	private final Class<T> entityClass = (Class<T>)getClass();	
	
	/* Propriedades comuns a todos os beans - variaveis de layout e controle de tela */
	
	private String title = entityClass.getSimpleName().replace("Bean", "");
	private final BreadcrumbEnum[] breadcrumb = setBreadcrumbArray();
	private DominioEnum dominio;
	
	@UsuarioLogado @Inject Usuario usuarioLogado;
	
	/*Metodos a serem implementados pelos beans, operações comuns a todos*/
	
	protected abstract BreadcrumbEnum[] setBreadcrumbArray();
	
	/*Ações comuns a serem herdadas*/
	
	public void init(DominioEnum dominioEnum) {
		dominio = dominioEnum;
	}
	
	public void addMessage(Severity severity, String summary, String detail) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage fm =  new FacesMessage(severity, summary, detail);
        context.addMessage(null, fm);
	}
	
	
	protected void redirect(String page){
		//			redirect("/admin/usuario/usuario");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = facesContext.getApplication().getViewHandler().createView(facesContext, page);
		facesContext.setViewRoot(viewRoot);
		facesContext.renderResponse();
	}
	
	/*get&set*/
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public BreadcrumbEnum[] getBreadcrumb() {
		return breadcrumb;
	}
	
	public DominioEnum getDominio() {
		return dominio;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
