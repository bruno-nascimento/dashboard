package br.com.bsitecnologia.dashboard.controller.admin;

import java.util.ArrayList;
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
import br.com.bsitecnologia.dashboard.dao.StatusDao;
import br.com.bsitecnologia.dashboard.dao.TransicaoStatusDao;
import br.com.bsitecnologia.dashboard.model.Status;
import br.com.bsitecnologia.dashboard.model.TransicaoStatus;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class TransicaoStatusBean extends BaseCrudBean<TransicaoStatus>{

	private static final long serialVersionUID = 1375567838606752462L;
	
	@Inject private StatusDao statusDao;
	private List<Status> allStatusFromDb;
	
	private List<SelectItem> statusFromList;
	private List<SelectItem> statusToList;
	private String statusFromSelectedItemId;
	private String statusToSelectedItemId;
	
	@Inject private TransicaoStatusDao transicaoTransicaoStatusDao;
	@Inject @New private TransicaoStatus transicaoStatusForm;
	@Inject private DashboardDataModel<TransicaoStatus> dataModel;

	@PostConstruct
	public void postConstruct(){
		super.init();
		setTitle(BreadcrumbEnum.TRANSICAO_STATUS.getName());
	}
	
	public void statusFromValueChangeListener(ValueChangeEvent event){
		transicaoStatusForm.setStatusFrom(getStatusFromValueChangeEvent(event));
		statusToList = fillstatusSelectItemList(transicaoStatusForm.getStatusFrom());
	}
	
	public void statusToValueChangeListener(ValueChangeEvent event){
		transicaoStatusForm.setStatusTo(getStatusFromValueChangeEvent(event));
	}
	
	private List<SelectItem> fillstatusSelectItemList(Status skipFromList) {
		List<SelectItem> statusSelectItemList = new ArrayList<SelectItem>();
		for(Status status : allStatusFromDb){
			if(skipFromList == null || !status.getId().equals(skipFromList.getId()) ){
				statusSelectItemList.add(new SelectItem(status.getId(), status.getNome()));
			}
		}
		return statusSelectItemList;
	}
	
	private Status getStatusFromValueChangeEvent(ValueChangeEvent event){
		return getEntityFromValueChangeEvent(event, allStatusFromDb);
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/
	
	@Override
	protected TransicaoStatusDao getDao() {
		return transicaoTransicaoStatusDao;
	}

	@Override
	protected void setFormEntity(TransicaoStatus status) {
		transicaoStatusForm = status;
	}

	@Override
	protected TransicaoStatus getFormEntity() {
		return transicaoStatusForm;
	}
	
	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[] {BreadcrumbEnum.HOME, BreadcrumbEnum.TRANSICAO_STATUS};
	}
	
	@Override
	protected void resetFormEntity() {
		transicaoStatusForm = new TransicaoStatus();
		statusFromSelectedItemId = null;
		statusToSelectedItemId = null;
	}
	
	@Override
	protected void postLoad(){
		allStatusFromDb = statusDao.findAll();
		statusFromList = fillSelectItemList(allStatusFromDb);
	}
	
	@Override
	protected void postRowSelect() {
		statusFromSelectedItemId = transicaoStatusForm.getStatusFrom().getId().toString();
		statusToList = fillstatusSelectItemList(transicaoStatusForm.getStatusFrom());
		statusToSelectedItemId = transicaoStatusForm.getStatusTo().getId().toString();
	}

	/*gets&sets*/
	
	public TransicaoStatusDao getTransicaoStatusDao() {
		return transicaoTransicaoStatusDao;
	}

	public void setTransicaoStatusDao(TransicaoStatusDao transicaoTransicaoStatusDao) {
		this.transicaoTransicaoStatusDao = transicaoTransicaoStatusDao;
	}

	public TransicaoStatus getTransicaoStatusForm() {
		return transicaoStatusForm;
	}

	public void setTransicaoStatusForm(TransicaoStatus transicaoStatusForm) {
		this.transicaoStatusForm = transicaoStatusForm;
	}

	public DashboardDataModel<TransicaoStatus> getDataModel() {
		return dataModel;
	}

	public void setDataModel(DashboardDataModel<TransicaoStatus> dataModel) {
		this.dataModel = dataModel;
	}
	
	public List<SelectItem> getStatusFromList() {
		return statusFromList;
	}

	public void setStatusFromList(List<SelectItem> statusFromList) {
		this.statusFromList = statusFromList;
	}

	public List<SelectItem> getStatusToList() {
		return statusToList;
	}

	public void setStatusToList(List<SelectItem> statusToList) {
		this.statusToList = statusToList;
	}

	public String getStatusFromSelectedItemId() {
		return statusFromSelectedItemId;
	}

	public void setStatusFromSelectedItemId(String statusFromSelectedItemId) {
		this.statusFromSelectedItemId = statusFromSelectedItemId;
	}

	public String getStatusToSelectedItemId() {
		return statusToSelectedItemId;
	}

	public void setStatusToSelectedItemId(String statusToSelectedItemId) {
		this.statusToSelectedItemId = statusToSelectedItemId;
	}

}