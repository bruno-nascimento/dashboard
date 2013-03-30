package br.com.bsitecnologia.dashboard.infra.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.solder.exception.control.CaughtException;
import org.jboss.solder.exception.control.Handles;
import org.jboss.solder.exception.control.HandlesExceptions;

import br.com.bsitecnologia.dashboard.infra.exceptions.customexceptions.UsuarioLogadoNotAuthorizedException;

@HandlesExceptions
public class ExceptionHandler {
	
	public void catchConstraintViolationException(@Handles CaughtException<ConstraintViolationException> evt){
		addMessage("Tentativa de criar um registro já existente.", evt.getException().getLocalizedMessage());
		evt.abort();
	}
	
	public void catchNoResultException(@Handles CaughtException<NoResultException> evt){
		addMessage("Usuário ou senha inválidos.", evt.getException().getLocalizedMessage());
		evt.abort();
	}
	
	public void catchUsuarioLogadoNotAuthorizedException(@Handles CaughtException<UsuarioLogadoNotAuthorizedException> evt){
		addMessage(FacesMessage.SEVERITY_WARN, "ACESSO NEGADO!", evt.getException().getLocalizedMessage());
		evt.abort();
	}
	
	public void catchException(@Handles CaughtException<Exception> evt){
		addMessage("Ops! Huston, we have a problem.", evt.getException().getLocalizedMessage());
		evt.abort();
	}
	
	private void addMessage(String summary, String detail){
		FacesMessage fm =  new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	private void addMessage(Severity severity, String summary, String detail){
		FacesMessage fm =  new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}

}
