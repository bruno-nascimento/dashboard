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
import br.com.bsitecnologia.dashboard.dao.TipoServicoDao;
import br.com.bsitecnologia.dashboard.model.Cliente;
import br.com.bsitecnologia.dashboard.model.TipoServico;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class TipoServicoBean extends BaseCrudBean<TipoServico> implements Serializable {
	
	private static final long serialVersionUID = -563351669224686839L;
	
	@Inject private TipoServicoDao tipoServicoDao;
	@Inject @New private TipoServico tipoServicoForm;
	@Inject private DashboardDataModel<TipoServico> dataModel;
	
	@Inject private ClienteDao clienteDao;

	private List<Cliente> allClientesFromDB;
	private List<SelectItem> clienteList;
	private String clienteIdSelectedItem;
	
	@PostConstruct
	public void postConstruct(){
		super.init();
	}
	
	public void clienteValueChangeListener(ValueChangeEvent event){
		tipoServicoForm.setCliente(getEntityFromValueChangeEvent(event, allClientesFromDB));
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/

	@Override
	protected TipoServicoDao getDao() {
		return tipoServicoDao;
	}

	@Override
	protected TipoServico getFormEntity() {
		return tipoServicoForm;
	}

	@Override
	protected void setFormEntity(TipoServico tipoServico) {
		tipoServicoForm = tipoServico;		
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[]{BreadcrumbEnum.HOME, BreadcrumbEnum.CARGO};
	}

	@Override
	protected void resetFormEntity() {
		tipoServicoForm = new TipoServico();
		clienteIdSelectedItem = null;
	}
	
	@Override
	protected void postLoad() {
		allClientesFromDB = clienteDao.findAll();
		clienteList = fillSelectItemList(allClientesFromDB);
	}
	
	@Override
	protected void postRowSelect() {
		clienteIdSelectedItem = tipoServicoForm.getCliente() != null ? tipoServicoForm.getCliente().getId().toString() : null;
	}
	
	/* get&set */
	
	public TipoServico getTipoServicoForm() {
		return tipoServicoForm;
	}

	public void setTipoServicoForm(TipoServico tipoServicoForm) {
		this.tipoServicoForm = tipoServicoForm;
	}
	
	public DashboardDataModel<TipoServico> getDataModel() {
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
