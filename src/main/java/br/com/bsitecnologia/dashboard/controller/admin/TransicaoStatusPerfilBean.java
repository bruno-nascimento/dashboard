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
import br.com.bsitecnologia.dashboard.dao.PerfilDao;
import br.com.bsitecnologia.dashboard.dao.TransicaoStatusDao;
import br.com.bsitecnologia.dashboard.dao.TransicaoStatusPerfilDao;
import br.com.bsitecnologia.dashboard.model.Cliente;
import br.com.bsitecnologia.dashboard.model.Perfil;
import br.com.bsitecnologia.dashboard.model.TransicaoStatus;
import br.com.bsitecnologia.dashboard.model.TransicaoStatusPerfil;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class TransicaoStatusPerfilBean extends BaseCrudBean<TransicaoStatusPerfil> implements Serializable {
	
	private static final long serialVersionUID = -563351669224686839L;
	
	@Inject private TransicaoStatusPerfilDao transicaoStatusPerfilDao;
	@Inject @New private TransicaoStatusPerfil transicaoStatusPerfilForm;
	@Inject private DashboardDataModel<TransicaoStatusPerfil> dataModel;
	
	@Inject private ClienteDao clienteDao;

	private List<Cliente> allClientesFromDB;
	private List<SelectItem> clienteList;
	private String clienteIdSelectedItem;
	
	@Inject private TransicaoStatusDao transicaoStatusDao;

	private List<TransicaoStatus> allTransicaoStatusFromDB;
	private List<SelectItem> transicaoStatusList;
	private String transicaoStatusIdSelectedItem;
	
	@Inject private PerfilDao perfilDao;

	private List<Perfil> allPerfilFromDB;
	private List<SelectItem> perfilList;
	private String perfilIdSelectedItem;
	
	
    
	
	@PostConstruct
	public void postConstruct(){
		super.init();
	}
	
	public void clienteValueChangeListener(ValueChangeEvent event){
		transicaoStatusPerfilForm.setCliente(getEntityFromValueChangeEvent(event, allClientesFromDB));
	}
	
	public void perfilValueChangeListener(ValueChangeEvent event){
		transicaoStatusPerfilForm.setPerfil(getEntityFromValueChangeEvent(event, allPerfilFromDB));
	}
	
	public void transicaoStatusValueChangeListener(ValueChangeEvent event){
		transicaoStatusPerfilForm.setTransicaoStatus(getEntityFromValueChangeEvent(event, allTransicaoStatusFromDB));
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/

	@Override
	protected TransicaoStatusPerfilDao getDao() {
		return transicaoStatusPerfilDao;
	}

	@Override
	protected TransicaoStatusPerfil getFormEntity() {
		return transicaoStatusPerfilForm;
	}

	@Override
	protected void setFormEntity(TransicaoStatusPerfil tipoServico) {
		transicaoStatusPerfilForm = tipoServico;		
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[]{BreadcrumbEnum.HOME, BreadcrumbEnum.CARGO};
	}

	@Override
	protected void resetFormEntity() {
		transicaoStatusPerfilForm = new TransicaoStatusPerfil();
		clienteIdSelectedItem = null;
		transicaoStatusIdSelectedItem = null;
		perfilIdSelectedItem = null;
	}
	
	@Override
	protected void postLoad() {
		allClientesFromDB = clienteDao.findAll();
		clienteList = fillSelectItemList(allClientesFromDB);
		
		allTransicaoStatusFromDB = transicaoStatusDao.findAll();
		transicaoStatusList = fillSelectItemList(allTransicaoStatusFromDB);
		
		allPerfilFromDB = perfilDao.findAll();
		perfilList = fillSelectItemList(allPerfilFromDB);
	}
	
	@Override
	protected void postRowSelect() {
		clienteIdSelectedItem = transicaoStatusPerfilForm.getCliente() != null ? transicaoStatusPerfilForm.getCliente().getId().toString() : null;
		perfilIdSelectedItem = transicaoStatusPerfilForm.getPerfil() != null ? transicaoStatusPerfilForm.getPerfil().getId().toString() : null;
		transicaoStatusIdSelectedItem = transicaoStatusPerfilForm.getTransicaoStatus() != null ? transicaoStatusPerfilForm.getTransicaoStatus().getId().toString() : null;
	}
	
	/* get&set */
	
	public TransicaoStatusPerfil getTransicaoStatusPerfilForm() {
		return transicaoStatusPerfilForm;
	}

	public void setTransicaoStatusPerfilForm(TransicaoStatusPerfil transicaoStatusPerfilForm) {
		this.transicaoStatusPerfilForm = transicaoStatusPerfilForm;
	}
	
	public DashboardDataModel<TransicaoStatusPerfil> getDataModel() {
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

	public String getTransicaoStatusIdSelectedItem() {
		return transicaoStatusIdSelectedItem;
	}

	public void setTransicaoStatusIdSelectedItem(String transicaoStatusIdSelectedItem) {
		this.transicaoStatusIdSelectedItem = transicaoStatusIdSelectedItem;
	}

	public String getPerfilIdSelectedItem() {
		return perfilIdSelectedItem;
	}

	public void setPerfilIdSelectedItem(String perfilIdSelectedItem) {
		this.perfilIdSelectedItem = perfilIdSelectedItem;
	}

	public List<TransicaoStatus> getAllTransicaoStatusFromDB() {
		return allTransicaoStatusFromDB;
	}

	public List<SelectItem> getTransicaoStatusList() {
		return transicaoStatusList;
	}

	public List<Perfil> getAllPerfilFromDB() {
		return allPerfilFromDB;
	}

	public List<SelectItem> getPerfilList() {
		return perfilList;
	}
	
}