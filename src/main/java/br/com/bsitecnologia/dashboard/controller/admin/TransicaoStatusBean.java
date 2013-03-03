package br.com.bsitecnologia.dashboard.controller.admin;

import java.io.Serializable;
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
import br.com.bsitecnologia.dashboard.dao.ClienteDao;
import br.com.bsitecnologia.dashboard.dao.StatusDao;
import br.com.bsitecnologia.dashboard.dao.TransicaoStatusDao;
import br.com.bsitecnologia.dashboard.model.Cliente;
import br.com.bsitecnologia.dashboard.model.Status;
import br.com.bsitecnologia.dashboard.model.TransicaoStatus;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class TransicaoStatusBean extends BaseCrudBean<TransicaoStatus> implements Serializable {
	
	private static final long serialVersionUID = -563351669224686839L;
	
	@Inject private TransicaoStatusDao transicaoStatusDao;
	@Inject @New private TransicaoStatus transicaoStatusForm;
	@Inject private DashboardDataModel<TransicaoStatus> dataModel;
	
	@Inject private ClienteDao clienteDao;

	private List<Cliente> allClientesFromDB;
	private List<SelectItem> clienteList;
	private String clienteIdSelectedItem;
	
	@Inject private StatusDao statusDao;

	private List<Status> allStatusFromDB;
	private List<SelectItem> statusDeList;
	private List<SelectItem> statusParaList;
	private String statusDeIdSelectedItem;
	private String statusParaIdSelectedItem;
	
	@PostConstruct
	public void postConstruct(){
		super.init();
	}
	
	public void clienteValueChangeListener(ValueChangeEvent event){
		transicaoStatusForm.setCliente(getEntityFromValueChangeEvent(event, allClientesFromDB));
	}
	
	public void statusDeValueChangeListener(ValueChangeEvent event){
		transicaoStatusForm.setStatusDe(getStatusFromValueChangeEvent(event));
		statusParaList = fillStatusSelectItemList(transicaoStatusForm.getStatusDe());
	}
	
	public void statusParaValueChangeListener(ValueChangeEvent event){
		transicaoStatusForm.setStatusPara(getStatusFromValueChangeEvent(event));
	}
	
	private Status getStatusFromValueChangeEvent(ValueChangeEvent event){
		return getEntityFromValueChangeEvent(event, allStatusFromDB);
	}
	
	private List<SelectItem> fillStatusSelectItemList(Status skipFromList) {
		List<SelectItem> statusSelectItemList = new ArrayList<SelectItem>();
		for(Status status : allStatusFromDB){
			if(!status.getId().equals(skipFromList.getId()) ){
				statusSelectItemList.add(new SelectItem(status.getId(), status.getNome()));
			}
		}
		return statusSelectItemList;
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/

	@Override
	protected TransicaoStatusDao getDao() {
		return transicaoStatusDao;
	}

	@Override
	protected TransicaoStatus getFormEntity() {
		return transicaoStatusForm;
	}

	@Override
	protected void setFormEntity(TransicaoStatus tipoServico) {
		transicaoStatusForm = tipoServico;		
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[]{BreadcrumbEnum.HOME, BreadcrumbEnum.CARGO};
	}

	@Override
	protected void resetFormEntity() {
		transicaoStatusForm = new TransicaoStatus();
		clienteIdSelectedItem = null;
		statusDeIdSelectedItem = null;
		statusParaIdSelectedItem = null;
	}
	
	@Override
	protected void postLoad() {
		allClientesFromDB = clienteDao.findAll();
		allStatusFromDB = statusDao.findAll();
		clienteList = fillSelectItemList(allClientesFromDB);
		statusDeList = fillSelectItemList(allStatusFromDB);
	}
	
	@Override
	protected void postRowSelect() {
		clienteIdSelectedItem = transicaoStatusForm.getCliente() != null ? transicaoStatusForm.getCliente().getId().toString() : null;
		statusDeIdSelectedItem = transicaoStatusForm.getStatusDe() != null ? transicaoStatusForm.getStatusDe().getId().toString() : null;
		statusParaIdSelectedItem = transicaoStatusForm.getStatusPara() != null ? transicaoStatusForm.getStatusPara().getId().toString() : null;
	}
	
	/* get&set */
	
	public TransicaoStatus getTransicaoStatusForm() {
		return transicaoStatusForm;
	}

	public void setTransicaoStatusForm(TransicaoStatus transicaoStatusForm) {
		this.transicaoStatusForm = transicaoStatusForm;
	}
	
	public DashboardDataModel<TransicaoStatus> getDataModel() {
		return dataModel;
	}
	
	public String getClienteIdSelectedItem() {
		return clienteIdSelectedItem;
	}

	public void setClienteIdSelectedItem(String clienteIdSelectedItem) {
		this.clienteIdSelectedItem = clienteIdSelectedItem;
	}

	public List<Cliente> getAllClientesFromDB() {
		return allClientesFromDB;
	}

	public List<SelectItem> getClienteList() {
		return clienteList;
	}
	
	public String getStatusDeIdSelectedItem() {
		return statusDeIdSelectedItem;
	}

	public void setStatusDeIdSelectedItem(String statusDeIdSelectedItem) {
		this.statusDeIdSelectedItem = statusDeIdSelectedItem;
	}

	public String getStatusParaIdSelectedItem() {
		return statusParaIdSelectedItem;
	}

	public void setStatusParaIdSelectedItem(String statusParaIdSelectedItem) {
		this.statusParaIdSelectedItem = statusParaIdSelectedItem;
	}

	public List<Status> getAllStatusFromDB() {
		return allStatusFromDB;
	}

	public List<SelectItem> getStatusDeList() {
		return statusDeList;
	}

	public List<SelectItem> getStatusParaList() {
		return statusParaList;
	}
	
}