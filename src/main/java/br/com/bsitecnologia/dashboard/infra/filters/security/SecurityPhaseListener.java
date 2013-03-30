package br.com.bsitecnologia.dashboard.infra.filters.security;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.ServletContext;

import br.com.bsitecnologia.dashboard.controller.LoginBean;

public class SecurityPhaseListener implements PhaseListener {

	private static final long serialVersionUID = -1698955036077175071L;


	public void afterPhase(PhaseEvent event) {
		
		
		FacesContext facesContext = event.getFacesContext();
		LoginBean loginBean = (LoginBean)getBeanByName("loginBean", facesContext); 
		String currentPage = facesContext.getViewRoot().getViewId();
		
		boolean isLoginPage = (currentPage.lastIndexOf("login.xhtml") > -1);

		if( !isLoginPage && !loginBean.getUsuarioLogado().isLogado() ) {
			UIViewRoot viewRoot = facesContext.getApplication().getViewHandler().createView(facesContext, "/login");
			facesContext.setViewRoot(viewRoot);
			facesContext.renderResponse();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Conteúdo restrito.", "Para acessar essa página você deve estar autenticado. Informe seu usuário e senha."));
		}
		
	}

	public void beforePhase(PhaseEvent event) {
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
	private BeanManager getBeanManager(FacesContext facesContext) {
        return (BeanManager) ((ServletContext) facesContext.getExternalContext().getContext()).getAttribute("javax.enterprise.inject.spi.BeanManager"); 
    }
	
	private Object getBeanByName(String name, FacesContext facesContext){ 
        BeanManager bm = getBeanManager(facesContext);
        Bean bean = bm.getBeans(name).iterator().next();
        CreationalContext ctx = bm.createCreationalContext(bean); 
        Object o = bm.getReference(bean, bean.getClass(), ctx); 
        return o;
    }

}
