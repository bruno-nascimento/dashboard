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
import br.com.bsitecnologia.dashboard.dao.PerfilDao;
import br.com.bsitecnologia.dashboard.model.Cliente;
import br.com.bsitecnologia.dashboard.model.Perfil;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class PerfilBean extends BaseCrudBean<Perfil> implements Serializable {
	
	private static final long serialVersionUID = -563351669224686839L;
	
	@Inject private PerfilDao perfilDao;
	@Inject @New private Perfil perfilForm;
	@Inject private DashboardDataModel<Perfil> dataModel;
	
	@Inject private ClienteDao clienteDao;

	private List<Cliente> allClientesFromDB;
	private List<SelectItem> clienteList;
	private String clienteIdSelectedItem;
	
	@PostConstruct
	public void postConstruct(){
		super.init();
	}
	
	public void clienteValueChangeListener(ValueChangeEvent event){
		perfilForm.setCliente(getEntityFromValueChangeEvent(event, allClientesFromDB));
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/

	@Override
	protected PerfilDao getDao() {
		return perfilDao;
	}

	@Override
	protected Perfil getFormEntity() {
		return perfilForm;
	}

	@Override
	protected void setFormEntity(Perfil tipoServico) {
		perfilForm = tipoServico;		
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[]{BreadcrumbEnum.HOME, BreadcrumbEnum.CARGO};
	}

	@Override
	protected void resetFormEntity() {
		perfilForm = new Perfil();
		clienteIdSelectedItem = null;
	}
	
	@Override
	protected void postLoad() {
		allClientesFromDB = clienteDao.findAll();
		clienteList = fillSelectItemList(allClientesFromDB);
	}
	
	@Override
	protected void postRowSelect() {
		clienteIdSelectedItem = perfilForm.getCliente() != null ? perfilForm.getCliente().getId().toString() : null;
	}
	
	/* get&set */
	
	public Perfil getPerfilForm() {
		return perfilForm;
	}

	public void setPerfilForm(Perfil perfilForm) {
		this.perfilForm = perfilForm;
	}
	
	public DashboardDataModel<Perfil> getDataModel() {
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
