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
import br.com.bsitecnologia.dashboard.dao.AreaSolicitanteDao;
import br.com.bsitecnologia.dashboard.model.Cliente;
import br.com.bsitecnologia.dashboard.model.AreaSolicitante;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class AreaSolicitanteBean extends BaseCrudBean<AreaSolicitante> implements Serializable {
	
	private static final long serialVersionUID = -563351669224686839L;
	
	@Inject private AreaSolicitanteDao areaSolicitanteDao;
	@Inject @New private AreaSolicitante areaSolicitanteForm;
	@Inject private DashboardDataModel<AreaSolicitante> dataModel;
	
	@Inject private ClienteDao clienteDao;

	private List<Cliente> allClientesFromDB;
	private List<SelectItem> clienteList;
	private String clienteIdSelectedItem;
	
	@PostConstruct
	public void postConstruct(){
		super.init();
	}
	
	public void clienteValueChangeListener(ValueChangeEvent event){
		areaSolicitanteForm.setCliente(getEntityFromValueChangeEvent(event, allClientesFromDB));
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/

	@Override
	protected AreaSolicitanteDao getDao() {
		return areaSolicitanteDao;
	}

	@Override
	protected AreaSolicitante getFormEntity() {
		return areaSolicitanteForm;
	}

	@Override
	protected void setFormEntity(AreaSolicitante tipoServico) {
		areaSolicitanteForm = tipoServico;		
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[]{BreadcrumbEnum.HOME, BreadcrumbEnum.CARGO};
	}

	@Override
	protected void resetFormEntity() {
		areaSolicitanteForm = new AreaSolicitante();
		clienteIdSelectedItem = null;
	}
	
	@Override
	protected void postLoad() {
		allClientesFromDB = clienteDao.findAll();
		clienteList = fillSelectItemList(allClientesFromDB);
	}
	
	@Override
	protected void postRowSelect() {
		clienteIdSelectedItem = areaSolicitanteForm.getCliente() != null ? areaSolicitanteForm.getCliente().getId().toString() : null;
	}
	
	/* get&set */
	
	public AreaSolicitante getAreaSolicitanteForm() {
		return areaSolicitanteForm;
	}

	public void setAreaSolicitanteForm(AreaSolicitante areaSolicitanteForm) {
		this.areaSolicitanteForm = areaSolicitanteForm;
	}
	
	public DashboardDataModel<AreaSolicitante> getDataModel() {
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
