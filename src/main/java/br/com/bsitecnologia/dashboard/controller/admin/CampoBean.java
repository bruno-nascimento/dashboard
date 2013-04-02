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
import br.com.bsitecnologia.dashboard.dao.TipoInputDao;
import br.com.bsitecnologia.dashboard.model.Campo;
import br.com.bsitecnologia.dashboard.model.DominioEnum;
import br.com.bsitecnologia.dashboard.model.TipoDado;
import br.com.bsitecnologia.dashboard.model.TipoInput;
import br.com.bsitecnologia.dashboard.model.TipoInputTipoDado;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class CampoBean extends BaseCrudBean<Campo> implements Serializable {
	
	private static final long serialVersionUID = -563351669224686839L;
	
	@Inject private CampoDao campoDao;
	@Inject @New private Campo campoForm;
	@Inject private DashboardDataModel<Campo> dataModel;
	
	@Inject TipoInputDao tipoInputDao;
	private List<TipoInput> allTipoInputFromDB;
	private List<SelectItem> tipoInputList;
	private String tipoInputIdSelectedItem;
	
	private List<SelectItem> tipoDadoList;
	private String tipoDadoIdSelectedItem;
	private List<TipoDado> allTipoDadoFromTipoInput;
	
	@PostConstruct
	public void postConstruct(){
		super.init(DominioEnum.CAMPO);
		setTitle("Campo");
		
	}
	
	public void tipoInputValueChangeListener(ValueChangeEvent event){
		campoForm.setTipoInput(getEntityFromValueChangeEvent(event, allTipoInputFromDB));
		setTipoDadoComboOptions();
	}
	
	public void tipoDadoValueChangeListener(ValueChangeEvent event){
		campoForm.setTipoDado(getEntityFromValueChangeEvent(event, allTipoDadoFromTipoInput));
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/

	@Override
	protected void postLoad() {
		allTipoInputFromDB = tipoInputDao.findAll();
		tipoInputList = fillSelectItemList(allTipoInputFromDB);
	}

	@Override
	protected CampoDao getDao() {
		return campoDao;
	}

	@Override
	protected Campo getFormEntity() {
		return campoForm;
	}

	@Override
	protected void setFormEntity(Campo campo) {
		campoForm = campo;		
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[]{BreadcrumbEnum.HOME, BreadcrumbEnum.CARGO};
	}

	@Override
	protected void resetFormEntity() {
		campoForm = new Campo();
		tipoInputIdSelectedItem = null;
		tipoDadoIdSelectedItem = null;
	}
	
	@Override
	protected void postRowSelect() {
		tipoInputIdSelectedItem = campoForm.getTipoInput() != null ? campoForm.getTipoInput().getId().toString() : null;
		setTipoDadoComboOptions();
		tipoDadoIdSelectedItem = campoForm.getTipoDado() != null ? campoForm.getTipoDado().getId().toString() : null;
	}
	
	private void setTipoDadoComboOptions(){
		List<TipoInputTipoDado> tipoInputTipoDadoList = campoForm.getTipoInput().getTipoInputTipoDados();
		List<TipoDado> tipoInputList = new ArrayList<TipoDado>();
		for (TipoInputTipoDado tipoInputTipoDado : tipoInputTipoDadoList) {
			tipoInputList.add(tipoInputTipoDado.getTipoDado()); 
		}
		allTipoDadoFromTipoInput = tipoInputList;
		tipoDadoList = fillSelectItemList(tipoInputList);
	}
	
	/* get&set */
	
	public Campo getCampoForm() {
		return campoForm;
	}

	public void setCampoForm(Campo campoForm) {
		this.campoForm = campoForm;
	}
	
	public DashboardDataModel<Campo> getDataModel() {
		return dataModel;
	}
	
	public List<SelectItem> getTipoInputList() {
		return tipoInputList;
	}

	public void setTipoInputList(List<SelectItem> tipoInputList) {
		this.tipoInputList = tipoInputList;
	}

	public String getTipoInputIdSelectedItem() {
		return tipoInputIdSelectedItem;
	}

	public void setTipoInputIdSelectedItem(String tipoInputIdSelectedItem) {
		this.tipoInputIdSelectedItem = tipoInputIdSelectedItem;
	}

	public List<SelectItem> getTipoDadoList() {
		return tipoDadoList;
	}

	public void setTipoDadoList(List<SelectItem> tipoDadoList) {
		this.tipoDadoList = tipoDadoList;
	}

	public String getTipoDadoIdSelectedItem() {
		return tipoDadoIdSelectedItem;
	}

	public void setTipoDadoIdSelectedItem(String tipoDadoIdSelectedItem) {
		this.tipoDadoIdSelectedItem = tipoDadoIdSelectedItem;
	}
	
}
