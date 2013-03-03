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

import br.com.bsitecnologia.dashboard.controller.BaseCrudBean;
import br.com.bsitecnologia.dashboard.controller.datamodel.DashboardDataModel;
import br.com.bsitecnologia.dashboard.controller.template.BreadcrumbEnum;
import br.com.bsitecnologia.dashboard.dao.ClienteDao;
import br.com.bsitecnologia.dashboard.dao.CriticidadeDao;
import br.com.bsitecnologia.dashboard.model.Cliente;
import br.com.bsitecnologia.dashboard.model.Criticidade;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class CriticidadeBean extends BaseCrudBean<Criticidade> implements Serializable {
	
	private static final long serialVersionUID = -563351669224686839L;
	
	@Inject private CriticidadeDao criticidadeDao;
	@Inject @New private Criticidade criticidadeForm;
	@Inject private DashboardDataModel<Criticidade> dataModel;
	
	@Inject private ClienteDao clienteDao;

	private List<Cliente> allClientesFromDB;
	private List<SelectItem> clienteList;
	private String clienteIdSelectedItem;
	
	@PostConstruct
	public void postConstruct(){
		super.init();
	}
	
	public void clienteValueChangeListener(ValueChangeEvent event){
		criticidadeForm.setCliente(getEntityFromValueChangeEvent(event, allClientesFromDB));
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/

	@Override
	protected CriticidadeDao getDao() {
		return criticidadeDao;
	}

	@Override
	protected Criticidade getFormEntity() {
		return criticidadeForm;
	}

	@Override
	protected void setFormEntity(Criticidade tipoServico) {
		criticidadeForm = tipoServico;		
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[]{BreadcrumbEnum.HOME, BreadcrumbEnum.CARGO};
	}

	@Override
	protected void resetFormEntity() {
		criticidadeForm = new Criticidade();
		clienteIdSelectedItem = null;
	}
	
	@Override
	protected void postLoad() {
		allClientesFromDB = clienteDao.findAll();
		clienteList = fillSelectItemList(allClientesFromDB);
	}
	
	@Override
	protected void postRowSelect() {
		clienteIdSelectedItem = criticidadeForm.getCliente() != null ? criticidadeForm.getCliente().getId().toString() : null;
	}
	
	/* get&set */
	
	public Criticidade getCriticidadeForm() {
		return criticidadeForm;
	}

	public void setCriticidadeForm(Criticidade criticidadeForm) {
		this.criticidadeForm = criticidadeForm;
	}
	
	public DashboardDataModel<Criticidade> getDataModel() {
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
