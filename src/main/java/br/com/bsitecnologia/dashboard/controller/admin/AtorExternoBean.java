package br.com.bsitecnologia.dashboard.controller.admin;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;

import br.com.bsitecnologia.dashboard.controller.BaseCrudBean;
import br.com.bsitecnologia.dashboard.controller.datamodel.DashboardDataModel;
import br.com.bsitecnologia.dashboard.controller.template.BreadcrumbEnum;
import br.com.bsitecnologia.dashboard.dao.AtorExternoDao;
import br.com.bsitecnologia.dashboard.model.AtorExterno;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class AtorExternoBean extends BaseCrudBean<AtorExterno>{

	private static final long serialVersionUID = 1375567838606752462L;
	
	@Inject private AtorExternoDao atorExternoDao;
	@Inject @New private AtorExterno atorExternoForm;
	@Inject private DashboardDataModel<AtorExterno> dataModel;

	@PostConstruct
	public void postConstruct(){
		super.init();
		super.setTitle(BreadcrumbEnum.ATOR_EXTERNO.getName());
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/
	
	@Override
	protected AtorExternoDao getDao() {
		return atorExternoDao;
	}

	@Override
	protected void setFormEntity(AtorExterno atorExterno) {
		atorExternoForm = atorExterno;
	}

	@Override
	protected AtorExterno getFormEntity() {
		return atorExternoForm;
	}
	
	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[] {BreadcrumbEnum.HOME, BreadcrumbEnum.ATOR_EXTERNO};
	}
	
	@Override
	protected void resetFormEntity() {
		atorExternoForm = new AtorExterno();
	}

	/*gets&sets*/
	
	public AtorExternoDao getAtorExternoDao() {
		return atorExternoDao;
	}

	public void setAtorExternoDao(AtorExternoDao statusDao) {
		this.atorExternoDao = statusDao;
	}

	public AtorExterno getAtorExternoForm() {
		return atorExternoForm;
	}

	public void setAtorExternoForm(AtorExterno atorExternoForm) {
		this.atorExternoForm = atorExternoForm;
	}

	public DashboardDataModel<AtorExterno> getDataModel() {
		return dataModel;
	}

	public void setDataModel(DashboardDataModel<AtorExterno> dataModel) {
		this.dataModel = dataModel;
	}

}
