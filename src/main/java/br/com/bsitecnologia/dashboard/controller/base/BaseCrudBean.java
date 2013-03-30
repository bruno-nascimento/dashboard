package br.com.bsitecnologia.dashboard.controller.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import br.com.bsitecnologia.dashboard.controller.datamodel.DashboardDataModel;
import br.com.bsitecnologia.dashboard.dao.base.GenericJpaRepository;
import br.com.bsitecnologia.dashboard.model.AcaoEnum;
import br.com.bsitecnologia.dashboard.model.DominioEnum;
import br.com.bsitecnologia.dashboard.resources.qualifiers.ControleAcesso;
import br.com.bsitecnologia.dashboard.util.BaseEntity;

@SuppressWarnings("unchecked")
public abstract class BaseCrudBean<T extends BaseEntity> extends BaseBean<T> implements Serializable{

	private static final long serialVersionUID = 3226781095227842827L;
	
	private List<T> list;
	
	/*Metodos a serem implementados pelos beans, operações comuns a todos*/
	
	protected abstract <X extends GenericJpaRepository<T, Serializable>> X getDao();
	protected abstract T getFormEntity();
	protected abstract void setFormEntity(T t);
	protected abstract DashboardDataModel<T> getDataModel();
	protected abstract void resetFormEntity();
	
	/*Metodos que podem ser sobreescritos quando necessario*/
	
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
	
	public void init(DominioEnum dominioEnum) {
		super.init(dominioEnum);
		loadList();
	}
	
	protected void loadList(){
		list = findAll();
		getDataModel().setList(list);
		postLoad();
	}
	
	protected List<T> findAll(){
		return getDao().findAll();
	}
	
	public void save(){
		if(getFormEntity().getId() == null || getFormEntity().getId() == 0){
			salvar();
		}else{
			editar();
		}
	}
	
	@ControleAcesso(acao=AcaoEnum.INSERIR)
	public void salvar(){
		persistir();
	}
	
	@ControleAcesso(acao=AcaoEnum.EDITAR)
	public void editar(){
		persistir();
	}
	
	private final void persistir(){
		if(executeValidations()){
			executePreInterceptors();
			setFormEntity(getDao().save(getFormEntity()));
			loadList();
			addMessage(FacesMessage.SEVERITY_INFO, String.format("%s: %s", super.getTitle(), getFormEntityFromDBList().getEntityDescription()), String.format("%s salvo(a) com sucesso.", super.getTitle()));
			executePostInterceptors();
			resetFormEntity();
		}
	}
	
	private T getFormEntityFromDBList(){
		for(T t : list){
			if(t.getId().equals(getFormEntity().getId())){
				return t;
			}
		}
		return null;
	}
	
	private void executePostInterceptors() {
		if(persistMethodIsUpdate()){
			postUpdate();
		}else{
			postPersist();
		}
		
	}
	
	private void executePreInterceptors() {
		if(persistMethodIsUpdate()){
			preUpdate();
		}else{
			prePersist();
		}
	}
	
	private boolean executeValidations() {
		if(persistMethodIsUpdate()){
			return updateValidation();
		}else{
			return persistValidation();
		}
	}
	
	@ControleAcesso(acao=AcaoEnum.EXCLUIR)
	public void delete(){
		delete(true);
	}
	
	@ControleAcesso(acao=AcaoEnum.EXCLUIR)
	public void delete(boolean showMessage){
		if(deleteValidation()){
			preDelete();
			getDao().delete(getFormEntity());
			if(showMessage){
				addMessage(FacesMessage.SEVERITY_INFO,  String.format("%s: %s", super.getTitle(), getFormEntityFromDBList().getEntityDescription()), String.format("%s deletado(a) com sucesso.", super.getTitle()));
			}
			loadList();
			postDelete();
			resetFormEntity();
		}
	}
	
	public void cancel(){
		resetFormEntity();
	}
	
	public void onRowSelect(SelectEvent event) {
        setFormEntity((T) event.getObject());
        postRowSelect();
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
	
	/*get&set*/
	
	public boolean persistMethodIsUpdate(){
		return null != getFormEntity() && getFormEntity().getId() != null || getFormEntity().getId() != 0;
	}
	
	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
