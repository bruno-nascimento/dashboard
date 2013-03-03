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

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class PerfilAcaoDominioBean extends BaseCrudBean<PerfilAcaoDominio> implements Serializable {
	
	private static final long serialVersionUID = -563351669224686839L;
	
	@Inject private PerfilAcaoDominioDao perfilAcaoDominio;
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
	private List<SelectItem> acaoList;
	private String acaoIdSelectedItem;
	
	@Inject private DominioDao dominioDao;

	private List<Dominio> allDominioFromDB;
	private List<SelectItem> dominioList;
	private String dominioIdSelectedItem;
	
	@PostConstruct
	public void postConstruct(){
		super.init();
	}
	
	public void clienteValueChangeListener(ValueChangeEvent event){
		perfilAcaoDominioForm.setCliente(getEntityFromValueChangeEvent(event, allClientesFromDB));
	}
	
	public void perfilValueChangeListener(ValueChangeEvent event){
		perfilAcaoDominioForm.setPerfil(getEntityFromValueChangeEvent(event, allPerfisFromDB));
	}
	
	public void acaoValueChangeListener(ValueChangeEvent event){
		perfilAcaoDominioForm.setAcao(getEntityFromValueChangeEvent(event, allAcaoFromDB));
	}
	
	public void dominioValueChangeListener(ValueChangeEvent event){
		perfilAcaoDominioForm.setDominio(getEntityFromValueChangeEvent(event, allDominioFromDB));
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/

	@Override
	protected PerfilAcaoDominioDao getDao() {
		return perfilAcaoDominio;
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
		acaoIdSelectedItem = null;
		dominioIdSelectedItem = null;
	}
	
	@Override
	protected void postLoad() {
		allClientesFromDB = clienteDao.findAll();
		allPerfisFromDB = perfilDao.findAll();
		allAcaoFromDB = acaoDao.findAll();
		allDominioFromDB = dominioDao.findAll();
		
		clienteList = fillSelectItemList(allClientesFromDB);
		perfilList = fillSelectItemList(allPerfisFromDB);
		acaoList = fillSelectItemList(allAcaoFromDB);
		dominioList = fillSelectItemList(allDominioFromDB);
	}
	
	public List<SelectItem> getAcaoList() {
		return acaoList;
	}

	public void setAcaoList(List<SelectItem> acaoList) {
		this.acaoList = acaoList;
	}

	public String getAcaoIdSelectedItem() {
		return acaoIdSelectedItem;
	}

	public void setAcaoIdSelectedItem(String acaoIdSelectedItem) {
		this.acaoIdSelectedItem = acaoIdSelectedItem;
	}

	public List<SelectItem> getDominioList() {
		return dominioList;
	}

	public void setDominioList(List<SelectItem> dominioList) {
		this.dominioList = dominioList;
	}

	public String getDominioIdSelectedItem() {
		return dominioIdSelectedItem;
	}

	public void setDominioIdSelectedItem(String dominioIdSelectedItem) {
		this.dominioIdSelectedItem = dominioIdSelectedItem;
	}

	public void setClienteList(List<SelectItem> clienteList) {
		this.clienteList = clienteList;
	}

	@Override
	protected void postRowSelect() {
		clienteIdSelectedItem = perfilAcaoDominioForm.getCliente() != null ? perfilAcaoDominioForm.getCliente().getId().toString() : null;
		perfilIdSelectedItem = perfilAcaoDominioForm.getPerfil() != null ? perfilAcaoDominioForm.getPerfil().getId().toString() : null;
		acaoIdSelectedItem = perfilAcaoDominioForm.getAcao() != null ? perfilAcaoDominioForm.getAcao().getId().toString() : null;
		dominioIdSelectedItem = perfilAcaoDominioForm.getDominio() != null ? perfilAcaoDominioForm.getDominio().getId().toString() : null;
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
	
	
	
}
