package br.com.bsitecnologia.dashboard.infra.filters.security;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;


public class SecurityPhaseListener implements PhaseListener{

	private static final long serialVersionUID = -1698955036077175071L;

	public SecurityPhaseListener() {
	}

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Security filter test - PHASELISTENER", "just checking... "+currentPage));
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
