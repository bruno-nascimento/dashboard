package br.com.bsitecnologia.dashboard.controller.admin;

import java.util.ArrayList;
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
import br.com.bsitecnologia.dashboard.dao.EquipeDao;
import br.com.bsitecnologia.dashboard.model.Equipe;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class EquipeBean extends BaseCrudBean<Equipe>{

	private static final long serialVersionUID = 1375567838606752462L;
	
	@Inject private EquipeDao equipeDao;
	@Inject @New private Equipe equipeForm;
	@Inject private DashboardDataModel<Equipe> dataModel;
	private List<SelectItem> comboEquipePai = new ArrayList<SelectItem>(0);
	private String comboEquipePaiSelectedItem;

	@PostConstruct
	public void postConstruct(){
		super.init();
	}
	
	private void fillComboEquipePai() {
		for(Equipe equipe : getList()){
			comboEquipePai.add(new SelectItem(equipe.getId(), equipe.getNome()));
		}
	}
	
	public void equipePaiValueChangeListener(ValueChangeEvent event){
		equipeForm.setEquipePai(getEntityFromValueChangeEvent(event, getList()));
	}
	
	public void resetComboEquipePaiSelectedItem(){
		comboEquipePaiSelectedItem = equipeForm.getEquipePai() != null ? equipeForm.getEquipePai().getId().toString() : null;
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/
	
	@Override
	protected EquipeDao getDao() {
		return equipeDao;
	}

	@Override
	protected void setFormEntity(Equipe equipe) {
		equipeForm = equipe;
	}

	@Override
	protected void postRowSelect() {
		resetComboEquipePaiSelectedItem();
	}
	
	@Override
	protected Equipe getFormEntity() {
		return equipeForm;
	}
	
	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[] {BreadcrumbEnum.HOME, BreadcrumbEnum.EQUIPE};
	}
	
	@Override
	protected void postLoad() {
		fillComboEquipePai();
	}
	
	@Override
	protected void resetFormEntity() {
		equipeForm = new Equipe();
		comboEquipePaiSelectedItem = null;
	}
	
	/*gets&sets*/
	
	public Equipe getEquipeForm() {
		return equipeForm;
	}

	public void setEquipeForm(Equipe equipeForm) {
		this.equipeForm = equipeForm;
	}

	public DashboardDataModel<Equipe> getDataModel() {
		return dataModel;
	}

	public void setDataModel(DashboardDataModel<Equipe> dataModel) {
		this.dataModel = dataModel;
	}

	public List<SelectItem> getComboEquipePai() {
		return comboEquipePai;
	}

	public String getComboEquipePaiSelectedItem() {
		return comboEquipePaiSelectedItem;
	}

	public void setComboEquipePaiSelectedItem(String comboEquipePaiSelectedItem) {
		this.comboEquipePaiSelectedItem = comboEquipePaiSelectedItem;
	}

}
