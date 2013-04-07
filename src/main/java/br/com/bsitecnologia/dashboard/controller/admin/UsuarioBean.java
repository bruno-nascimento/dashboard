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

import br.com.bsitecnologia.dashboard.controller.base.BaseCrudBean;
import br.com.bsitecnologia.dashboard.controller.datamodel.DashboardDataModel;
import br.com.bsitecnologia.dashboard.controller.template.BreadcrumbEnum;
import br.com.bsitecnologia.dashboard.dao.ClienteDao;
import br.com.bsitecnologia.dashboard.dao.PerfilDao;
import br.com.bsitecnologia.dashboard.dao.UsuarioDao;
import br.com.bsitecnologia.dashboard.model.Cliente;
import br.com.bsitecnologia.dashboard.model.DominioEnum;
import br.com.bsitecnologia.dashboard.model.Perfil;
import br.com.bsitecnologia.dashboard.model.Telefone;
import br.com.bsitecnologia.dashboard.model.Usuario;
import br.com.bsitecnologia.dashboard.service.UsuarioService;

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
	
	@Inject @New private Telefone telefone;
	private List<Telefone> listaTelefones = new ArrayList<Telefone>();
	
	@Inject UsuarioService usuarioService;
	
	@PostConstruct
	public void postConstruct(){
		super.init(DominioEnum.USUARIO);
	}
	
	public void clienteValueChangeListener(ValueChangeEvent event){
		usuarioForm.setCliente(getEntityFromValueChangeEvent(event, allClientesFromDB));
	}
	
	public void perfilValueChangeListener(ValueChangeEvent event){
		usuarioForm.setPerfil(getEntityFromValueChangeEvent(event, allPerfisFromDB));
	}
	
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/
	
	@Override
	protected void postUpdate(){
		usuarioService.saveTelefones(listaTelefones, usuarioForm);
	}
	
	@Override
	protected void preDelete(){
		usuarioService.deleteTelefones(usuarioForm);
		usuarioForm.setTelefones(null);
	}

	@Override
	protected UsuarioDao getDao() {
		return usuarioDao;
	}

	@Override
	protected Usuario getFormEntity() {
		return usuarioForm;
	}

	@Override
	protected void setFormEntity(Usuario usuarioForm) {
		this.usuarioForm = usuarioForm;		
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
		telefone = new Telefone();
		listaTelefones = new ArrayList<Telefone>();
	}
	
	public void resetTelefone(){
		telefone = new Telefone();
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
		listaTelefones = usuarioForm.getTelefones();
	}
	
	/* get&set */
	
	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public List<Telefone> getListaTelefones() {
		return listaTelefones;
	}

	public void setListaTelefones(List<Telefone> listaTelefones) {
		this.listaTelefones = listaTelefones;
	}

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
