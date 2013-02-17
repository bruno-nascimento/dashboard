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
import br.com.bsitecnologia.dashboard.dao.ImpactoDao;
import br.com.bsitecnologia.dashboard.dao.RiscoDao;
import br.com.bsitecnologia.dashboard.model.Impacto;
import br.com.bsitecnologia.dashboard.model.ProbabilidadeRisco;
import br.com.bsitecnologia.dashboard.model.Risco;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class RiscoBean extends BaseCrudBean<Risco>{

	private static final long serialVersionUID = 1375567838606752462L;
	
	@Inject @New private Risco riscoForm;
	@Inject private DashboardDataModel<Risco> dataModel;
	@Inject private RiscoDao riscoDao;

	@Inject private ImpactoDao impactoDao;
	private List<Impacto> allImpactoFromDb;
	private List<SelectItem> impactoSelectItemList = new ArrayList<SelectItem>(0);
	private String impactoSelectedItem;
	
	private String probabilidadeRiscoSelectedItem;
	private List<SelectItem> probabilidadeRiscoList = new ArrayList<SelectItem>(0);
	
	public void impactoToValueChangeListener(ValueChangeEvent event){
		riscoForm.setImpacto(getEntityFromValueChangeEvent(event, allImpactoFromDb));
	}
	
	public void probabilidadeRiscoValueChangeListener(ValueChangeEvent event){
		riscoForm.setProbabilidade( ProbabilidadeRisco.getProbabilidadeRiscoByProbabilidade(Integer.valueOf((String)event.getNewValue())).getProbabilidade() );
	}

	private void fillProbabilidadeRiscoList(){
		for (ProbabilidadeRisco probabilidadeRisco : ProbabilidadeRisco.values()) {
			probabilidadeRiscoList.add(new SelectItem(probabilidadeRisco.getProbabilidade(), probabilidadeRisco.getNome()));
		}
	}

	@PostConstruct
	public void postConstruct(){
		super.init();
	}

	@Override
	protected RiscoDao getDao() {
		return riscoDao;
	}

	@Override
	protected Risco getFormEntity() {
		return riscoForm;
	}

	@Override
	protected void setFormEntity(Risco risco) {
		riscoForm = risco;
	}

	@Override
	protected DashboardDataModel<Risco> getDataModel() {
		return dataModel;
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[]{BreadcrumbEnum.HOME, BreadcrumbEnum.RISCO};
	}

	@Override
	protected void resetFormEntity() {
		riscoForm = new Risco();
		impactoSelectedItem = null;
		probabilidadeRiscoSelectedItem = null;
	}

	@Override
	protected void postLoad() {
		allImpactoFromDb = impactoDao.findAll();
		impactoSelectItemList = fillSelectItemList(allImpactoFromDb);
		fillProbabilidadeRiscoList();
	}
	
	@Override
	protected void postRowSelect() {
		impactoSelectedItem = riscoForm.getImpacto().getId().toString();
		probabilidadeRiscoSelectedItem = ProbabilidadeRisco.getProbabilidadeRiscoByProbabilidade(riscoForm.getProbabilidade()).getProbabilidade().toString();
	}
	
	/*get&set*/
	
	public Risco getRiscoForm() {
		return riscoForm;
	}

	public void setRiscoForm(Risco riscoForm) {
		this.riscoForm = riscoForm;
	}

	public List<SelectItem> getImpactoSelectItemList() {
		return impactoSelectItemList;
	}

	public void setImpactoSelectItemList(List<SelectItem> impactoSelectItemList) {
		this.impactoSelectItemList = impactoSelectItemList;
	}

	public String getImpactoSelectedItem() {
		return impactoSelectedItem;
	}

	public void setImpactoSelectedItem(String impactoSelectedItem) {
		this.impactoSelectedItem = impactoSelectedItem;
	}

	public void setDataModel(DashboardDataModel<Risco> dataModel) {
		this.dataModel = dataModel;
	}
	
	public String getProbabilidadeRiscoSelectedItem() {
		return probabilidadeRiscoSelectedItem;
	}

	public void setProbabilidadeRiscoSelectedItem(String probabilidadeRiscoSelectedItem) {
		this.probabilidadeRiscoSelectedItem = probabilidadeRiscoSelectedItem;
	}

	public List<SelectItem> getProbabilidadeRiscoList() {
		return probabilidadeRiscoList;
	}

	public void setProbabilidadeRiscoList(List<SelectItem> probabilidadeRiscoList) {
		this.probabilidadeRiscoList = probabilidadeRiscoList;
	}
	
	public String getProbabilidadeRisco(Integer probabilidade){
		return ProbabilidadeRisco.getProbabilidadeRiscoByProbabilidade(probabilidade).getNome();
	}

}
