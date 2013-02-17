package br.com.bsitecnologia.dashboard.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.New;
import javax.faces.application.FacesMessage;
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
import br.com.bsitecnologia.dashboard.dao.UsuarioDao;
import br.com.bsitecnologia.dashboard.infra.cripto.Sha512Crypt;
import br.com.bsitecnologia.dashboard.model.AtorExterno;
import br.com.bsitecnologia.dashboard.model.Colaborador;
import br.com.bsitecnologia.dashboard.model.Usuario;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class UsuarioBean extends BaseCrudBean<Usuario>{
	
	private static final long serialVersionUID = -4925905139833098305L;

	@Inject @New private Usuario usuarioForm;
	@Inject private UsuarioDao  usuarioDao;
	@Inject private DashboardDataModel<Usuario> dataModel;
	
	private String confirmacaoSenha;
	private boolean resetarSenha;
	
	@Inject	private ColaboradorDao colaboradorDao;
	private List<Colaborador> allColaboradorFromDb;
	private List<SelectItem> colaboradorSelectItemList = new ArrayList<SelectItem>();
	private String selectedColaboradorItem;
	
	@Inject private AtorExternoDao atorExternoDao;
	private List<AtorExterno> allAtorExternoFromDb;
	private List<SelectItem> atorExternoSelectItemList = new ArrayList<SelectItem>();
	private String selectedAtorExternoItem;
	

	@PostConstruct
	public void postConstruct(){
		super.init();
//		usuarioForm.setUsuarioTipoColaborador(true);
	}
	
	public void verificaSenhas(){
		if(!confereIgualdadeSenha()){
			msgSenhaNaoConfere(FacesMessage.SEVERITY_WARN);
			return;
		}
	}
	
	public void msgSenhaNaoConfere(FacesMessage.Severity severity){
		addMessage(severity, "Usuário: senha", "A senha e a confirmação de senha são diferentes.");
	}
	
	private boolean confereIgualdadeSenha(){
		if(usuarioForm.getSenha() != null && confirmacaoSenha.equals(usuarioForm.getSenha())){
			return true;
		}
		return false;
	}
	
	public void renderCombos(ValueChangeEvent event){
		if(null != event.getNewValue()){
//			usuarioForm.setUsuarioTipoColaborador(Boolean.parseBoolean(event.getNewValue().toString()));
		}
	}
	
	public void colaboradorValueChangeListener(ValueChangeEvent event){
		if(null != event.getNewValue()){
			//usuarioForm.setColaborador(getEntityFromValueChangeEvent(event, allColaboradorFromDb));
			//usuarioForm.getColaborador().setUsuario(usuarioForm);
		}
	}
	
	public void atorExternoValueChangeListener(ValueChangeEvent event){
		if(null != event.getNewValue()){
			//usuarioForm.setAtorExterno(getEntityFromValueChangeEvent(event, allAtorExternoFromDb));
			//usuarioForm.getAtorExterno().setUsuario(usuarioForm);
		}
	}
	
	public void resetarSenha(){
		resetarSenha = true;
		usuarioForm.setSenha(null);
		addMessage(FacesMessage.SEVERITY_INFO, "Usuário: senha", "Informe uma nova senha para o usuário");
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/

	@Override
	protected UsuarioDao getDao() {
		return usuarioDao;
	}

	@Override
	protected Usuario getFormEntity() {
		return usuarioForm;
	}

	@Override
	protected void setFormEntity(Usuario usuario) {
		usuarioForm = usuario;
	}

	@Override
	protected DashboardDataModel<Usuario> getDataModel() {
		return dataModel;
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[] {BreadcrumbEnum.HOME, BreadcrumbEnum.USUARIO};
	}

	@Override
	protected void resetFormEntity() {
		usuarioForm = new Usuario();
		selectedColaboradorItem = null;
		selectedAtorExternoItem = null;
		resetarSenha = false;
	}
	
	@Override
	protected void postLoad() {
		allColaboradorFromDb = colaboradorDao.findAll();
		allAtorExternoFromDb = atorExternoDao.findAll();
		atorExternoSelectItemList = fillSelectItemList(allAtorExternoFromDb);
		colaboradorSelectItemList = fillSelectItemList(allColaboradorFromDb);
	}
	
	@Override
	protected void postRowSelect() {
//		if (usuarioForm.isUsuarioTipoColaborador()) {
//			selectedColaboradorItem = usuarioForm.getColaborador().getId().toString();
//		}else{
//			selectedAtorExternoItem = usuarioForm.getAtorExterno().getId().toString();
//		}
		resetarSenha = false;
	}
	
	@Override
	protected boolean persistValidation() {
		if(confereIgualdadeSenha()){
			return true;
		}
		msgSenhaNaoConfere(FacesMessage.SEVERITY_ERROR);
		return false;
	}
	
	@Override
	protected boolean updateValidation() {
		if(resetarSenha && !confereIgualdadeSenha()){
			msgSenhaNaoConfere(FacesMessage.SEVERITY_ERROR);
			return false;
		}
		return true;
	}
	
	@Override
	protected void prePersist() {
		usuarioForm.setSenha(Sha512Crypt.Sha512_crypt(usuarioForm.getSenha()));
	}
	
	@Override
	protected void preUpdate() {
		if(resetarSenha){
			usuarioForm.setSenha(Sha512Crypt.Sha512_crypt(usuarioForm.getSenha()));
		}
	}
	
	/*get&set*/
	
	public List<SelectItem> getColaboradorSelectItemList() {
		return colaboradorSelectItemList;
	}

	public void setColaboradorSelectItemList(
			List<SelectItem> colaboradorSelectItemList) {
		this.colaboradorSelectItemList = colaboradorSelectItemList;
	}

	public String getSelectedColaboradorItem() {
		return selectedColaboradorItem;
	}

	public void setSelectedColaboradorItem(String selectedColaboradorItem) {
		this.selectedColaboradorItem = selectedColaboradorItem;
	}

	public List<SelectItem> getAtorExternoSelectItemList() {
		return atorExternoSelectItemList;
	}

	public void setAtorExternoSelectItemList(List<SelectItem> atorExternoSelectItemList) {
		this.atorExternoSelectItemList = atorExternoSelectItemList;
	}

	public String getSelectedAtorExternoItem() {
		return selectedAtorExternoItem;
	}

	public void setSelectedAtorExternoItem(String selectedAtorExternoItem) {
		this.selectedAtorExternoItem = selectedAtorExternoItem;
	}

	public Usuario getUsuarioForm() {
		return usuarioForm;
	}

	public void setUsuarioForm(Usuario usuarioForm) {
		this.usuarioForm = usuarioForm;
	}
	
	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
	
	public boolean isResetarSenha() {
		return resetarSenha;
	}

	public void setResetarSenha(boolean resetarSenha) {
		this.resetarSenha = resetarSenha;
	}
}