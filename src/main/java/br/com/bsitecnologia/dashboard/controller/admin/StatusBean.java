package br.com.bsitecnologia.dashboard.controller.admin;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;

import br.com.bsitecnologia.dashboard.controller.BaseCrudBean;
import br.com.bsitecnologia.dashboard.controller.datamodel.DashboardDataModel;
import br.com.bsitecnologia.dashboard.controller.template.BreadcrumbEnum;
import br.com.bsitecnologia.dashboard.dao.StatusDao;
import br.com.bsitecnologia.dashboard.model.Status;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class StatusBean extends BaseCrudBean<Status>{

	private static final long serialVersionUID = 1375567838606752462L;
	
	@Inject private StatusDao statusDao;
	@Inject @New private Status statusForm;
	@Inject private DashboardDataModel<Status> dataModel;

	@PostConstruct
	public void postConstruct(){
		super.init();
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/
	
	@Override
	protected StatusDao getDao() {
		return statusDao;
	}

	@Override
	protected void setFormEntity(Status status) {
		statusForm = status;
	}

	@Override
	protected Status getFormEntity() {
		return statusForm;
	}
	
	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[] {BreadcrumbEnum.HOME, BreadcrumbEnum.STATUS};
	}
	
	@Override
	protected void resetFormEntity() {
		statusForm = new Status();
	}	

	/*gets&sets*/
	
	public StatusDao getStatusDao() {
		return statusDao;
	}

	public void setStatusDao(StatusDao statusDao) {
		this.statusDao = statusDao;
	}

	public Status getStatusForm() {
		return statusForm;
	}

	public void setStatusForm(Status statusForm) {
		this.statusForm = statusForm;
	}

	public DashboardDataModel<Status> getDataModel() {
		return dataModel;
	}

	public void setDataModel(DashboardDataModel<Status> dataModel) {
		this.dataModel = dataModel;
	}

}
