package br.com.bsitecnologia.dashboard.controller.admin;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.New;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;

import br.com.bsitecnologia.dashboard.controller.BaseCrudBean;
import br.com.bsitecnologia.dashboard.controller.datamodel.DashboardDataModel;
import br.com.bsitecnologia.dashboard.controller.template.BreadcrumbEnum;
import br.com.bsitecnologia.dashboard.dao.ProjetoDao;
import br.com.bsitecnologia.dashboard.dao.StatusDao;
import br.com.bsitecnologia.dashboard.model.Projeto;
import br.com.bsitecnologia.dashboard.model.Status;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class ProjetoBean extends BaseCrudBean<Projeto>{

	private static final long serialVersionUID = 1375567838606752462L;
	
	@Inject private StatusDao statusDao;
	private List<Status> allStatusFromDb;
	
	private List<SelectItem> statusList;
	private String statusSelectedItemId;
	
	private List<SelectItem> projetoPaiList;
	private String projetoPaiSelectedItemId;
	
	@Inject private ProjetoDao projetoDao;
	
	@Inject @New private Projeto projetoForm;
	@Inject private DashboardDataModel<Projeto> dataModel;

	@PostConstruct
	public void postConstruct(){
		init();
	}
	
	public void statusValueChangeListener(ValueChangeEvent event){
		projetoForm.setStatus(getEntityFromValueChangeEvent(event, allStatusFromDb));
	}
	
	public void projetoPaiValueChangeListener(ValueChangeEvent event){
		projetoForm.setProjetoPai(getEntityFromValueChangeEvent(event, getList()));
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/
	
	
	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[] {BreadcrumbEnum.HOME, BreadcrumbEnum.PROJETO};
	}

	@Override
	protected ProjetoDao getDao() {
		return projetoDao;
	}

	@Override
	protected Projeto getFormEntity() {
		return projetoForm;
	}

	@Override
	protected void setFormEntity(Projeto projeto) {
		projetoForm = projeto;
	}

	@Override
	protected DashboardDataModel<Projeto> getDataModel() {
		return dataModel;
	}

	@Override
	protected void resetFormEntity() {
		projetoForm = new Projeto();
		projetoPaiSelectedItemId = null;
		statusSelectedItemId = null;
	}

	@Override
	protected void postLoad() {
		allStatusFromDb = statusDao.findAll();
		statusList = fillSelectItemList(allStatusFromDb);
		projetoPaiList = fillSelectItemList(getList());
	}
	
	@Override
	protected void postRowSelect() {
		projetoPaiSelectedItemId = projetoForm.getProjetoPai() != null ? projetoForm.getProjetoPai().getId().toString() : null;
		statusSelectedItemId = projetoForm.getStatus().getId().toString();
	}
	
	/*get&set*/
	
	public List<SelectItem> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<SelectItem> statusList) {
		this.statusList = statusList;
	}

	public String getStatusSelectedItemId() {
		return statusSelectedItemId;
	}

	public void setStatusSelectedItemId(String statusSelectedItemId) {
		this.statusSelectedItemId = statusSelectedItemId;
	}

	public String getProjetoPaiSelectedItemId() {
		return projetoPaiSelectedItemId;
	}

	public void setProjetoPaiSelectedItemId(String projetoPaiSelectedItemId) {
		this.projetoPaiSelectedItemId = projetoPaiSelectedItemId;
	}

	public Projeto getProjetoForm() {
		return projetoForm;
	}

	public void setProjetoForm(Projeto projetoForm) {
		this.projetoForm = projetoForm;
	}

	public void setDataModel(DashboardDataModel<Projeto> dataModel) {
		this.dataModel = dataModel;
	}

	public List<SelectItem> getProjetoPaiList() {
		return projetoPaiList;
	}

	public void setProjetoPaiList(List<SelectItem> projetoPaiList) {
		this.projetoPaiList = projetoPaiList;
	}

}