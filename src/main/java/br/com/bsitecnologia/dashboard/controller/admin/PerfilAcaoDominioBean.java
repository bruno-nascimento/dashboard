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
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.com.bsitecnologia.dashboard.controller.base.BaseCrudBean;
import br.com.bsitecnologia.dashboard.controller.datamodel.DashboardDataModel;
import br.com.bsitecnologia.dashboard.controller.template.BreadcrumbEnum;
import br.com.bsitecnologia.dashboard.dao.AcaoDao;
import br.com.bsitecnologia.dashboard.dao.ClienteDao;
import br.com.bsitecnologia.dashboard.dao.DominioDao;
import br.com.bsitecnologia.dashboard.dao.PerfilAcaoDominioDao;
import br.com.bsitecnologia.dashboard.dao.PerfilDao;
import br.com.bsitecnologia.dashboard.model.Acao;
import br.com.bsitecnologia.dashboard.model.Cliente;
import br.com.bsitecnologia.dashboard.model.Dominio;
import br.com.bsitecnologia.dashboard.model.Perfil;
import br.com.bsitecnologia.dashboard.model.PerfilAcaoDominio;
import br.com.bsitecnologia.dashboard.service.PerfilAcaoDominioService;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class PerfilAcaoDominioBean extends BaseCrudBean<PerfilAcaoDominio> implements Serializable {
	
	private static final long serialVersionUID = -563351669224686839L;
	
	@Inject private PerfilAcaoDominioDao perfilAcaoDominioDao;
	@Inject @New private PerfilAcaoDominio perfilAcaoDominioForm;
	@Inject private DashboardDataModel<PerfilAcaoDominio> dataModel;
	
	@Inject private ClienteDao clienteDao;

	private List<Cliente> allClientesFromDB;
	private List<SelectItem> clienteList;
	private String clienteIdSelectedItem;
	
	@Inject private PerfilDao perfilDao;

	private List<Perfil> allPerfisFromDB;
	private List<SelectItem> perfilList;
	private String perfilIdSelectedItem;
	
	@Inject private AcaoDao acaoDao;

	private List<Acao> allAcaoFromDB;
	
	@Inject private DominioDao dominioDao;

	private List<Dominio> allDominioFromDB;
	
	@Inject PerfilAcaoDominioService perfilAcaoDominioService;
	
	private TreeNode root;  
    private TreeNode[] selectedNodes;

	private List<PerfilAcaoDominio> selecionados;
	
	@PostConstruct
	public void postConstruct(){
		super.init();
		root = new DefaultTreeNode("Root", null);
		perfilAcaoDominioService.buildAcaoDominioTree(root, allDominioFromDB, allAcaoFromDB);
	}
	
	public void clienteValueChangeListener(ValueChangeEvent event){
		perfilAcaoDominioForm.setCliente(getEntityFromValueChangeEvent(event, allClientesFromDB));
	}
	
	public void perfilValueChangeListener(ValueChangeEvent event){
		perfilAcaoDominioForm.setPerfil(getEntityFromValueChangeEvent(event, allPerfisFromDB));
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/

	@Override
	protected PerfilAcaoDominioDao getDao() {
		return perfilAcaoDominioDao;
	}

	@Override
	protected PerfilAcaoDominio getFormEntity() {
		return perfilAcaoDominioForm;
	}

	@Override
	protected void setFormEntity(PerfilAcaoDominio perfilAcaoDominio) {
		perfilAcaoDominioForm = perfilAcaoDominio;		
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[]{BreadcrumbEnum.HOME, BreadcrumbEnum.CARGO};
	}

	@Override
	protected void resetFormEntity() {
		perfilAcaoDominioForm = new PerfilAcaoDominio();
		clienteIdSelectedItem = null;
		perfilIdSelectedItem = null;
		selectedNodes = null;
		selecionados = null;
		perfilAcaoDominioService.resetSelected(root);
	}
	
	@Override
	protected void postLoad() {
		allClientesFromDB = clienteDao.findAll();
		allPerfisFromDB = perfilDao.findAll();
		allAcaoFromDB = acaoDao.findAll();
		allDominioFromDB = dominioDao.findAll();
		
		clienteList = fillSelectItemList(allClientesFromDB);
		perfilList = fillSelectItemList(allPerfisFromDB);
	}
	
	@Override
	public void save() {
		Cliente cliente = clienteIdSelectedItem == null || clienteIdSelectedItem.equals("") ? null : getEntityById(Integer.valueOf(clienteIdSelectedItem), allClientesFromDB);
		Perfil perfil = perfilIdSelectedItem == null || perfilIdSelectedItem.equals("") ? null : getEntityById(Integer.valueOf(perfilIdSelectedItem), allPerfisFromDB);
		TreeNode[] selectedNodesTemp = selectedNodes;
		List<PerfilAcaoDominio> selecionadosTemp = selecionados;
		if(selecionadosTemp != null && selecionadosTemp.size() > 0){
			this.delete(false);
		}
		for(PerfilAcaoDominio perfilAcaoDominio : perfilAcaoDominioService.prepareToSave(selectedNodesTemp, cliente, perfil)){
			setFormEntity(perfilAcaoDominio);
			super.save();
		}
	}
	
	public void delete(){
		delete(true);
	}
	
	@Override
	public void delete(boolean showDeleteMessages) {
		for (PerfilAcaoDominio pad : selecionados) {
			setFormEntity(pad);
			super.delete(showDeleteMessages);
		}
	}

	@Override
	protected void postRowSelect() {
		perfilAcaoDominioService.resetSelected(root);
		selecionados = null;
		clienteIdSelectedItem = perfilAcaoDominioForm.getCliente() != null ? perfilAcaoDominioForm.getCliente().getId().toString() : null;
		perfilIdSelectedItem = perfilAcaoDominioForm.getPerfil() != null ? perfilAcaoDominioForm.getPerfil().getId().toString() : null;
		selecionados = perfilAcaoDominioService.buildSelectedAcaoDominioTreeAndReturnSelected(perfilAcaoDominioForm, root);
	}
	
	/* get&set */
	
	public PerfilAcaoDominio getPerfilAcaoDominioForm() {
		return perfilAcaoDominioForm;
	}

	public void setPerfilAcaoDominioForm(PerfilAcaoDominio perfilAcaoDominioForm) {
		this.perfilAcaoDominioForm = perfilAcaoDominioForm;
	}
	
	public DashboardDataModel<PerfilAcaoDominio> getDataModel() {
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
	
	public TreeNode getRoot() {
		return root;
	}
	
	public TreeNode[] getSelectedNodes() {  
        return selectedNodes;  
    }  
  
    public void setSelectedNodes(TreeNode[] selectedNodes) {  
        this.selectedNodes = selectedNodes;  
    }
    
	public void setClienteList(List<SelectItem> clienteList) {
		this.clienteList = clienteList;
	}
	
}
