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
import br.com.bsitecnologia.dashboard.dao.CampoDao;
import br.com.bsitecnologia.dashboard.dao.FormularioDao;
import br.com.bsitecnologia.dashboard.model.Campo;
import br.com.bsitecnologia.dashboard.model.CampoFormulario;
import br.com.bsitecnologia.dashboard.model.DominioEnum;
import br.com.bsitecnologia.dashboard.model.Formulario;
import br.com.bsitecnologia.dashboard.service.FormularioService;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class FormularioBean extends BaseCrudBean<Formulario> implements Serializable {
	
	private static final long serialVersionUID = -563351669224686839L;
	
	@Inject private FormularioDao formularioDao;
	@Inject @New private Formulario formularioForm;
	@Inject private DashboardDataModel<Formulario> dataModel;
	
	@Inject CampoDao campoDao;
	private List<Campo> allCampoFromDB;
	private List<SelectItem> campoList;
	private String campoIdSelectedItem;
	
	@Inject @New private Campo campo;
	private List<Campo> listaCampo = new ArrayList<Campo>();
	
	@Inject FormularioService formularioService;
	
	@PostConstruct
	public void postConstruct(){
		super.init(DominioEnum.FORMULARIO);
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/

	@Override
	protected void postLoad() {
		allCampoFromDB = campoDao.findAll();
		campoList = fillSelectItemList(allCampoFromDB);
	}
	
	@Override
	protected void postUpdate(){
		formularioService.saveOpcoes(listaCampo, formularioForm);
	}
	
	@Override
	protected void preDelete(){
		formularioService.deleteCampoFormulario(formularioForm);
		formularioForm.setCampoFormularios(null);
	}

	@Override
	protected FormularioDao getDao() {
		return formularioDao;
	}

	@Override
	protected Formulario getFormEntity() {
		return formularioForm;
	}

	@Override
	protected void setFormEntity(Formulario formulario) {
		formularioForm = formulario;		
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[]{BreadcrumbEnum.HOME, BreadcrumbEnum.CARGO};
	}
	
	public void campoValueChangeListener(ValueChangeEvent event){
		campo = getEntityFromValueChangeEvent(event, allCampoFromDB);
	}
	

	@Override
	protected void resetFormEntity() {
		formularioForm = new Formulario();
		campoIdSelectedItem = null;
		campo = new Campo();
		listaCampo = new ArrayList<Campo>();
	}
	
	public void resetCampo(){
		campo = new Campo();
	}
	
	@Override
	protected void postRowSelect() {
		campoIdSelectedItem = null;
		campo = new Campo();
		listaCampo = getAllCamposFromFormulario();
	}
	
	private List<Campo> getAllCamposFromFormulario(){
		List<Campo> resultado = new ArrayList<Campo>();
		Formulario formulario = formularioDao.findById(formularioForm.getId()); 
		if(formulario.getCampoFormularios().size() > 0){ 
			for (CampoFormulario campoFormulario : formulario.getCampoFormularios()) {
				resultado.add(campoFormulario.getCampo());
			}
		}
		return resultado;
	}
	
	/* get&set */
	
	public Formulario getFormularioForm() {
		return formularioForm;
	}

	public void setFormularioForm(Formulario formularioForm) {
		this.formularioForm = formularioForm;
	}
	
	public DashboardDataModel<Formulario> getDataModel() {
		return dataModel;
	}

	public List<SelectItem> getCampoList() {
		return campoList;
	}

	public void setCampoList(List<SelectItem> campoList) {
		this.campoList = campoList;
	}

	public String getCampoIdSelectedItem() {
		return campoIdSelectedItem;
	}

	public void setCampoIdSelectedItem(String campoIdSelectedItem) {
		this.campoIdSelectedItem = campoIdSelectedItem;
	}

	public Campo getCampo() {
		return campo;
	}

	public void setCampo(Campo campo) {
		this.campo = campo;
	}

	public List<Campo> getListaCampo() {
		return listaCampo;
	}

	public void setListaCampo(List<Campo> listaCampo) {
		this.listaCampo = listaCampo;
	}
	
}