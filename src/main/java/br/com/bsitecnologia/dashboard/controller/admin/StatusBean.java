package br.com.bsitecnologia.dashboard.controller.admin;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.New;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;

import br.com.bsitecnologia.dashboard.controller.base.BaseCrudBean;
import br.com.bsitecnologia.dashboard.controller.datamodel.DashboardDataModel;
import br.com.bsitecnologia.dashboard.controller.template.BreadcrumbEnum;
import br.com.bsitecnologia.dashboard.dao.ClienteDao;
import br.com.bsitecnologia.dashboard.dao.StatusDao;
import br.com.bsitecnologia.dashboard.model.Cliente;
import br.com.bsitecnologia.dashboard.model.Status;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class StatusBean extends BaseCrudBean<Status> implements Serializable {
	
	private static final long serialVersionUID = -563351669224686839L;
	
	@Inject private StatusDao statusDao;
	@Inject @New private Status statusForm;
	@Inject private DashboardDataModel<Status> dataModel;
	
	@Inject private ClienteDao clienteDao;

	private List<Cliente> allClientesFromDB;
	private List<SelectItem> clienteList;
	private String clienteIdSelectedItem;
	
	@PostConstruct
	public void postConstruct(){
		super.init();
	}
	
	public void clienteValueChangeListener(ValueChangeEvent event){
		statusForm.setCliente(getEntityFromValueChangeEvent(event, allClientesFromDB));
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/

	@Override
	protected StatusDao getDao() {
		return statusDao;
	}

	@Override
	protected Status getFormEntity() {
		return statusForm;
	}

	@Override
	protected void setFormEntity(Status tipoServico) {
		statusForm = tipoServico;		
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[]{BreadcrumbEnum.HOME, BreadcrumbEnum.CARGO};
	}

	@Override
	protected void resetFormEntity() {
		statusForm = new Status();
		clienteIdSelectedItem = null;
	}
	
	@Override
	protected void postLoad() {
		allClientesFromDB = clienteDao.findAll();
		clienteList = fillSelectItemList(allClientesFromDB);
	}
	
	@Override
	protected void postRowSelect() {
		clienteIdSelectedItem = statusForm.getCliente() != null ? statusForm.getCliente().getId().toString() : null;
	}
	
	/* get&set */
	
	public Status getStatusForm() {
		return statusForm;
	}

	public void setStatusForm(Status statusForm) {
		this.statusForm = statusForm;
	}
	
	public DashboardDataModel<Status> getDataModel() {
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
	
}
