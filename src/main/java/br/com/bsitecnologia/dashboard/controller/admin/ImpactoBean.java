package br.com.bsitecnologia.dashboard.controller.admin;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;

import br.com.bsitecnologia.dashboard.controller.BaseCrudBean;
import br.com.bsitecnologia.dashboard.controller.datamodel.DashboardDataModel;
import br.com.bsitecnologia.dashboard.controller.template.BreadcrumbEnum;
import br.com.bsitecnologia.dashboard.dao.ImpactoDao;
import br.com.bsitecnologia.dashboard.model.Impacto;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class ImpactoBean extends BaseCrudBean<Impacto>{

	private static final long serialVersionUID = 1375567838606752462L;
	
	@Inject private ImpactoDao impactoDao;
	@Inject @New private Impacto impactoForm;
	@Inject private DashboardDataModel<Impacto> dataModel;

	@PostConstruct
	public void postConstruct(){
		super.init();
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/
	
	@Override
	protected ImpactoDao getDao() {
		return impactoDao;
	}

	@Override
	protected void setFormEntity(Impacto impacto) {
		impactoForm = impacto;
	}

	@Override
	protected Impacto getFormEntity() {
		return impactoForm;
	}
	
	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[] {BreadcrumbEnum.HOME, BreadcrumbEnum.IMPACTO};
	}
	
	@Override
	protected void resetFormEntity() {
		impactoForm = new Impacto();
	}	

	/*gets&sets*/
	
	public ImpactoDao getImpactoDao() {
		return impactoDao;
	}

	public void setImpactoDao(ImpactoDao impactoDao) {
		this.impactoDao = impactoDao;
	}

	public Impacto getImpactoForm() {
		return impactoForm;
	}

	public void setImpactoForm(Impacto impactoForm) {
		this.impactoForm = impactoForm;
	}

	public DashboardDataModel<Impacto> getDataModel() {
		return dataModel;
	}

	public void setDataModel(DashboardDataModel<Impacto> dataModel) {
		this.dataModel = dataModel;
	}

}
