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
import br.com.bsitecnologia.dashboard.dao.UsuarioDao;
import br.com.bsitecnologia.dashboard.model.Cliente;
import br.com.bsitecnologia.dashboard.model.Perfil;
import br.com.bsitecnologia.dashboard.model.Usuario;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class UsuarioBean extends BaseCrudBean<Usuario> implements Serializable {
	
	private static final long serialVersionUID = -563351669224686839L;
	
	@Inject private UsuarioDao usuarioDao;
	@Inject @New private Usuario usuarioForm;
	@Inject private DashboardDataModel<Usuario> dataModel;
	
	@Inject private ClienteDao clienteDao;

	private List<Cliente> allClientesFromDB;
	private List<SelectItem> clienteList;
	private String clienteIdSelectedItem;
	
	@Inject private PerfilDao perfilDao;

	private List<Perfil> allPerfisFromDB;
	private List<SelectItem> perfilList;
	private String perfilIdSelectedItem;
	
	@PostConstruct
	public void postConstruct(){
		super.init();
	}
	
	public void clienteValueChangeListener(ValueChangeEvent event){
		usuarioForm.setCliente(getEntityFromValueChangeEvent(event, allClientesFromDB));
	}
	
	public void perfilValueChangeListener(ValueChangeEvent event){
		usuarioForm.setPerfil(getEntityFromValueChangeEvent(event, allPerfisFromDB));
	}
	
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/

	@Override
	protected UsuarioDao getDao() {
		return usuarioDao;
	}

	@Override
	protected Usuario getFormEntity() {
		return usuarioForm;
	}

	@Override
	protected void setFormEntity(Usuario tipoServico) {
		usuarioForm = tipoServico;		
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[]{BreadcrumbEnum.HOME, BreadcrumbEnum.CARGO};
	}

	@Override
	protected void resetFormEntity() {
		usuarioForm = new Usuario();
		clienteIdSelectedItem = null;
		perfilIdSelectedItem = null;
	}
	
	@Override
	protected void postLoad() {
		allClientesFromDB = clienteDao.findAll();
		allPerfisFromDB = perfilDao.findAll();
		
		clienteList = fillSelectItemList(allClientesFromDB);
		perfilList = fillSelectItemList(allPerfisFromDB);
	}
	
	@Override
	protected void postRowSelect() {
		clienteIdSelectedItem = usuarioForm.getCliente() != null ? usuarioForm.getCliente().getId().toString() : null;
		perfilIdSelectedItem = usuarioForm.getPerfil() != null ? usuarioForm.getPerfil().getId().toString() : null;
	}
	
	/* get&set */
	
	public Usuario getUsuarioForm() {
		return usuarioForm;
	}

	public void setUsuarioForm(Usuario usuarioForm) {
		this.usuarioForm = usuarioForm;
	}
	
	public DashboardDataModel<Usuario> getDataModel() {
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

	public List<SelectItem> getPerfilList() {
		return perfilList;
	}

	public void setPerfilList(List<SelectItem> perfilList) {
		this.perfilList = perfilList;
	}

	public String getPerfilIdSelectedItem() {
		return perfilIdSelectedItem;
	}

	public void setPerfilIdSelectedItem(String perfilIdSelectedItem) {
		this.perfilIdSelectedItem = perfilIdSelectedItem;
	}
	
	
	
}
