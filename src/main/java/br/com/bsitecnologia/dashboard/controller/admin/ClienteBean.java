package br.com.bsitecnologia.dashboard.controller.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;

import br.com.bsitecnologia.dashboard.controller.BaseCrudBean;
import br.com.bsitecnologia.dashboard.controller.datamodel.DashboardDataModel;
import br.com.bsitecnologia.dashboard.controller.template.BreadcrumbEnum;
import br.com.bsitecnologia.dashboard.dao.ClienteDao;
import br.com.bsitecnologia.dashboard.model.Cliente;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class ClienteBean extends BaseCrudBean<Cliente> implements Serializable {
	
	private static final long serialVersionUID = -563351669224686839L;
	
	@Inject private ClienteDao clienteDao;
	@Inject @New private Cliente clienteForm;
	@Inject private DashboardDataModel<Cliente> dataModel;
	
	@PostConstruct
	public void postConstruct(){
		super.init();
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/

	@Override
	protected ClienteDao getDao() {
		return clienteDao;
	}

	@Override
	protected Cliente getFormEntity() {
		return clienteForm;
	}

	@Override
	protected void setFormEntity(Cliente cargo) {
		clienteForm = cargo;		
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[]{BreadcrumbEnum.HOME, BreadcrumbEnum.CARGO};
	}

	@Override
	protected void resetFormEntity() {
		clienteForm = new Cliente();
	}
	
	/* get&set */
	
	public Cliente getClienteForm() {
		return clienteForm;
	}

	public void setClienteForm(Cliente clienteForm) {
		this.clienteForm = clienteForm;
	}
	
	public DashboardDataModel<Cliente> getDataModel() {
		return dataModel;
	}
	
}
