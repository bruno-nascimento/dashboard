package br.com.bsitecnologia.dashboard.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;

import br.com.bsitecnologia.dashboard.controller.datamodel.DashboardDataModel;
import br.com.bsitecnologia.dashboard.controller.template.BreadcrumbEnum;
import br.com.bsitecnologia.dashboard.controller.template.Buttons;
import br.com.bsitecnologia.dashboard.dao.base.GenericJpaRepository;
import br.com.bsitecnologia.dashboard.model.Usuario;
import br.com.bsitecnologia.dashboard.resources.qualifiers.ControleAcesso;
import br.com.bsitecnologia.dashboard.resources.qualifiers.UsuarioLogado;
import br.com.bsitecnologia.dashboard.util.Acao;
import br.com.bsitecnologia.dashboard.util.BaseEntity;

@SuppressWarnings("unchecked")
public abstract class BaseCrudBean<T extends BaseEntity> implements Serializable{

	private static final long serialVersionUID = 1906091377786784028L;
	private final Class<T> entityClass = (Class<T>)getClass();	
	
	
	/* Propriedades comuns a todos os beans - variaveis de layout e controle de tela */
	
	private String title = entityClass.getSimpleName().replace("Bean", "");
	private final BreadcrumbEnum[] breadcrumb = setBreadcrumbArray();
	private String saveButtonLabel = Buttons.SAVE.getLabel();
	private boolean showDeleteButton = false;
	private List<T> list;
	@Inject @UsuarioLogado Usuario usuario;

	
	/*Metodos a serem implementados pelos beans, operações comuns a todos*/
	
	protected abstract <X extends GenericJpaRepository<T, Serializable>> X getDao();
	protected abstract T getFormEntity();
	protected abstract void setFormEntity(T t);
	protected abstract DashboardDataModel<T> getDataModel();
	protected abstract BreadcrumbEnum[] setBreadcrumbArray();
	protected abstract void resetFormEntity();
	
	
	/*Metodos a serem sobreescritos quando necessario*/
	
	protected void postRowSelect(){}
	protected void postLoad(){}
	protected void prePersist(){}
	protected void postPersist(){}
	protected void preUpdate(){}
	protected void postUpdate(){}
	protected void preDelete(){}
	protected void postDelete(){}
	protected boolean persistValidation(){return true;}
	protected boolean updateValidation(){return true;}
	protected boolean deleteValidation(){return true;}
	
	
	/*Ações comuns a serem herdadas*/
	
	public void init() {
		loadList();
	}
	
	protected void loadList(){
		list = getDao().findAll();
		getDataModel().setList(list);
		postLoad();
	}
	
	@ControleAcesso(acao=Acao.INSERIR)
	public void save(){
		if(executeValidations()){
			executePreInterceptors();
			setFormEntity(getDao().save(getFormEntity()));
			loadList();
			addMessage(FacesMessage.SEVERITY_INFO, String.format("%s: %s", title, getFormEntity().getEntityDescription()), String.format("%s salvo(a) com sucesso.", title));
			saveButtonLabel = Buttons.SAVE.getLabel();
			showDeleteButton = false;
			executePostInterceptors();
			resetFormEntity();
		}
	}
	
	private void executePostInterceptors() {
		if(showDeleteButton){
			postUpdate();
		}else{
			postPersist();
		}
		
	}
	
	private void executePreInterceptors() {
		if(showDeleteButton){
			preUpdate();
		}else{
			prePersist();
		}
	}
	
	private boolean executeValidations() {
		if(showDeleteButton){
			return updateValidation();
		}else{
			return persistValidation();
		}
	}
	
	public void delete(){
		if(deleteValidation()){
			preDelete();
			getDao().delete(getFormEntity());
			loadList();
			addMessage(FacesMessage.SEVERITY_INFO,  String.format("%s: %s", title, getFormEntity().getEntityDescription()), String.format("%s deletado(a) com sucesso.", title));
			saveButtonLabel = Buttons.SAVE.getLabel();
			showDeleteButton = false;
			postDelete();
			resetFormEntity();
		}
	}
	
	public void cancel(){
		showDeleteButton = false;
		saveButtonLabel = Buttons.SAVE.getLabel();
		resetFormEntity();
	}
	
	public void onRowSelect(SelectEvent event) {
		saveButtonLabel = Buttons.EDIT.getLabel();
		showDeleteButton = true;
        setFormEntity((T) event.getObject());
        postRowSelect();
    }
	
	public void addMessage(Severity severity, String summary, String detail) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage fm =  new FacesMessage(severity, summary, detail);
        context.addMessage(null, fm);
	}
	
	protected <A extends BaseEntity> A getEntityFromValueChangeEvent(ValueChangeEvent event, List<A> list){
		if(event.getNewValue() != null){
			for (A a : list) {
				if(Integer.valueOf(event.getNewValue().toString()).equals(a.getId())){
					return a;
				}
			}
		}
		return null;
	}
	
	protected <A extends BaseEntity> A getEntityById(Integer id, List<A> list){
		for (A a : list) {
			if(id.equals( a.getId() ) ){
				return a;
			}
		}
		return null;
	}
	
	
	protected List<SelectItem> fillSelectItemList(List<? extends BaseEntity> list) {
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		for(BaseEntity t: list){
			selectItemList.add(new SelectItem(t.getId(), t.getEntityDescription()));
		}
		return selectItemList;
	}
	
	protected void redirect(String page){
		//			redirect("/admin/usuario/usuario");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = facesContext.getApplication().getViewHandler().createView(facesContext, page);
		facesContext.setViewRoot(viewRoot);
		facesContext.renderResponse();
	}
	
	/*get&set*/
	
	public String getSaveButtonLabel() {
		return saveButtonLabel;
	}
	
	public void setSaveButtonLabel(String saveButtonLabel) {
		this.saveButtonLabel = saveButtonLabel;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public boolean isShowDeleteButton() {
		return showDeleteButton;
	}

	public void setShowDeleteButton(boolean showDeleteButton) {
		this.showDeleteButton = showDeleteButton;
	}

	public BreadcrumbEnum[] getBreadcrumb() {
		return breadcrumb;
	}
	
	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
