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
import br.com.bsitecnologia.dashboard.dao.AtorExternoDao;
import br.com.bsitecnologia.dashboard.dao.ColaboradorDao;
import br.com.bsitecnologia.dashboard.dao.ImpedimentoDao;
import br.com.bsitecnologia.dashboard.dao.ProjetoDao;
import br.com.bsitecnologia.dashboard.dao.RiscoDao;
import br.com.bsitecnologia.dashboard.model.AtorExterno;
import br.com.bsitecnologia.dashboard.model.Colaborador;
import br.com.bsitecnologia.dashboard.model.Impedimento;
import br.com.bsitecnologia.dashboard.model.Projeto;
import br.com.bsitecnologia.dashboard.model.Risco;

@SuppressWarnings("unchecked")
@Named
@ConversationScoped
public class ImpedimentoBean extends BaseCrudBean<Impedimento>{

	private static final long serialVersionUID = 1375567838606752462L;
	
	@Inject private ProjetoDao projetoDao;
	private List<Projeto> allProjetosFromDb;
	private List<SelectItem> projetoSelectItemList = new ArrayList<SelectItem>();
	private String selectedProjetoItem;
	
	@Inject private ColaboradorDao colaboradorDao;
	private List<Colaborador> allColaboradorFromDb;
	private List<SelectItem> colaboradorSelectItemList = new ArrayList<SelectItem>();
	private String selectedColaboradorAutorItem;
	private String selectedColaboradorAtribuidoItem;
	
	@Inject private RiscoDao riscoDao; 
	private List<Risco> allRiscoFromDb;
	private List<SelectItem> riscoSelectItemList = new ArrayList<SelectItem>();
	private String selectedRiscoItem;
	
	@Inject private AtorExternoDao atorExternoDao;
	private List<AtorExterno> allAtorExternoFromDb;
	private List<SelectItem> atorExternoItemList = new ArrayList<SelectItem>();
	private String selectedAtorExternoItem;
	
	@Inject @New private Impedimento impedimentoForm;
	@Inject private DashboardDataModel<Impedimento> dataModel;
	@Inject private ImpedimentoDao impedimentoDao;

	@PostConstruct
	public void postConstruct(){
		init();
	}
	
	public void colaboradorAtribuidoValueChangeListener(ValueChangeEvent event){
		impedimentoForm.setColaboradorAtribuido(getEntityFromValueChangeEvent(event, allColaboradorFromDb));
	}
	
	public void colaboradorAutorValueChangeListener(ValueChangeEvent event){
		impedimentoForm.setColaboradorAutor(getEntityFromValueChangeEvent(event, allColaboradorFromDb));
	}

	public void projetoValueChangeListener(ValueChangeEvent event){
		impedimentoForm.setProjeto(getEntityFromValueChangeEvent(event, allProjetosFromDb));
	}
	
	public void riscoValueChangeListener(ValueChangeEvent event){
		impedimentoForm.setRisco(getEntityFromValueChangeEvent(event, allRiscoFromDb));
	}
	
	public void atorExternoValueChangeListener(ValueChangeEvent event){
		impedimentoForm.setAtorExterno(getEntityFromValueChangeEvent(event, allAtorExternoFromDb));
	}

	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/
	
	@Override
	protected ImpedimentoDao getDao() {
		return impedimentoDao;
	}

	@Override
	protected Impedimento getFormEntity() {
		return impedimentoForm;
	}

	@Override
	protected void setFormEntity(Impedimento impedimento) {
		impedimentoForm = impedimento;
	}

	@Override
	protected DashboardDataModel<Impedimento> getDataModel() {
		return dataModel;
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[]{BreadcrumbEnum.HOME, BreadcrumbEnum.IMPEDIMENTO};
	}

	@Override
	protected void resetFormEntity() {
		impedimentoForm = new Impedimento();
		selectedAtorExternoItem = null;
		selectedColaboradorAtribuidoItem = null;
		selectedColaboradorAutorItem = null;
		selectedProjetoItem = null;
		selectedRiscoItem = null;
	}

	@Override
	protected void postLoad() {
		allAtorExternoFromDb = atorExternoDao.findAll();
		atorExternoItemList = fillSelectItemList(allAtorExternoFromDb);
		allColaboradorFromDb = colaboradorDao.findAll();
		colaboradorSelectItemList = fillSelectItemList(allColaboradorFromDb);
		allProjetosFromDb = projetoDao.findAll();
		projetoSelectItemList = fillSelectItemList(allProjetosFromDb);
		allRiscoFromDb = riscoDao.findAll();
		riscoSelectItemList = fillSelectItemList(allRiscoFromDb);
	}
	
	@Override
	protected void postRowSelect() {
		selectedAtorExternoItem = impedimentoForm.getAtorExterno().getId().toString();
		selectedColaboradorAtribuidoItem = impedimentoForm.getColaboradorAtribuido().getId().toString();
		selectedColaboradorAutorItem = impedimentoForm.getColaboradorAtribuido().getId().toString();
		selectedProjetoItem = impedimentoForm.getProjeto().getId().toString();
		selectedRiscoItem = impedimentoForm.getRisco().getId().toString();
	}
	
	
	/*get&set*/

	public String getSelectedProjetoItem() {
		return selectedProjetoItem;
	}

	public void setSelectedProjetoItem(String selectedProjetoItem) {
		this.selectedProjetoItem = selectedProjetoItem;
	}

	public String getSelectedColaboradorAutorItem() {
		return selectedColaboradorAutorItem;
	}

	public void setSelectedColaboradorAutorItem(String selectedColaboradorAutorItem) {
		this.selectedColaboradorAutorItem = selectedColaboradorAutorItem;
	}

	public String getSelectedColaboradorAtribuidoItem() {
		return selectedColaboradorAtribuidoItem;
	}

	public void setSelectedColaboradorAtribuidoItem(String selectedColaboradorAtribuidoItem) {
		this.selectedColaboradorAtribuidoItem = selectedColaboradorAtribuidoItem;
	}

	public String getSelectedRiscoItem() {
		return selectedRiscoItem;
	}

	public void setSelectedRiscoItem(String selectedRiscoItem) {
		this.selectedRiscoItem = selectedRiscoItem;
	}

	public String getSelectedAtorExternoItem() {
		return selectedAtorExternoItem;
	}

	public void setSelectedAtorExternoItem(String selectedAtorExternoItem) {
		this.selectedAtorExternoItem = selectedAtorExternoItem;
	}

	public Impedimento getImpedimentoForm() {
		return impedimentoForm;
	}

	public void setImpedimentoForm(Impedimento impedimentoForm) {
		this.impedimentoForm = impedimentoForm;
	}

	public List<SelectItem> getProjetoSelectItemList() {
		return projetoSelectItemList;
	}

	public List<SelectItem> getColaboradorSelectItemList() {
		return colaboradorSelectItemList;
	}

	public List<SelectItem> getRiscoSelectItemList() {
		return riscoSelectItemList;
	}

	public List<SelectItem> getAtorExternoItemList() {
		return atorExternoItemList;
	}

	public void setDataModel(DashboardDataModel<Impedimento> dataModel) {
		this.dataModel = dataModel;
	}
	
}