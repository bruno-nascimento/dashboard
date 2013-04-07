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
import br.com.bsitecnologia.dashboard.dao.ClienteDao;
import br.com.bsitecnologia.dashboard.dao.FormularioDao;
import br.com.bsitecnologia.dashboard.dao.StatusDao;
import br.com.bsitecnologia.dashboard.dao.TransicaoStatusDao;
import br.com.bsitecnologia.dashboard.model.Cliente;
import br.com.bsitecnologia.dashboard.model.DominioEnum;
import br.com.bsitecnologia.dashboard.model.Formulario;
import br.com.bsitecnologia.dashboard.model.Status;
import br.com.bsitecnologia.dashboard.model.TransicaoStatus;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class TransicaoStatusBean extends BaseCrudBean<TransicaoStatus> implements Serializable {
	
	private static final long serialVersionUID = 398596340486668576L;

	@Inject private TransicaoStatusDao transicaoStatusDao;
	@Inject @New private TransicaoStatus transicaoStatusForm;
	@Inject private DashboardDataModel<TransicaoStatus> dataModel;
	
	@Inject private ClienteDao clienteDao;

	private List<Cliente> allClientesFromDB;
	private List<SelectItem> clienteList;
	private String clienteIdSelectedItem;
	
	@Inject private StatusDao statusDao;

	private List<Status> allStatusFromDB;
	private List<SelectItem> statusDeList;
	private List<SelectItem> statusParaList;
	private String statusDeIdSelectedItem;
	private String statusParaIdSelectedItem;
	
	@Inject private FormularioDao formularioDao;

	private List<Formulario> allFormulariosFromDB;
	private List<SelectItem> formularioList;
	private String formularioIdSelectedItem;
	
	@PostConstruct
	public void postConstruct(){
		super.init(DominioEnum.TRANSICAO_STATUS);
	}
	
	public void clienteValueChangeListener(ValueChangeEvent event){
		transicaoStatusForm.setCliente(getEntityFromValueChangeEvent(event, allClientesFromDB));
	}
	
	public void formularioValueChangeListener(ValueChangeEvent event){
		transicaoStatusForm.setFormulario(getEntityFromValueChangeEvent(event, allFormulariosFromDB));
	}
	
	public void statusDeValueChangeListener(ValueChangeEvent event){
		transicaoStatusForm.setStatusDe(getStatusFromValueChangeEvent(event));
		statusParaList = fillStatusSelectItemList(transicaoStatusForm.getStatusDe());
	}
	
	public void statusParaValueChangeListener(ValueChangeEvent event){
		transicaoStatusForm.setStatusPara(getStatusFromValueChangeEvent(event));
	}
	
	private Status getStatusFromValueChangeEvent(ValueChangeEvent event){
		return getEntityFromValueChangeEvent(event, allStatusFromDB);
	}
	
	private List<SelectItem> fillStatusSelectItemList(Status skipFromList) {
		List<SelectItem> statusSelectItemList = new ArrayList<SelectItem>();
		for(Status status : allStatusFromDB){
			if(!status.getId().equals(skipFromList.getId()) ){
				statusSelectItemList.add(new SelectItem(status.getId(), status.getNome()));
			}
		}
		return statusSelectItemList;
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/

	@Override
	protected TransicaoStatusDao getDao() {
		return transicaoStatusDao;
	}

	@Override
	protected TransicaoStatus getFormEntity() {
		return transicaoStatusForm;
	}

	@Override
	protected void setFormEntity(TransicaoStatus tipoServico) {
		transicaoStatusForm = tipoServico;		
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[]{BreadcrumbEnum.HOME, BreadcrumbEnum.CARGO};
	}

	@Override
	protected void resetFormEntity() {
		transicaoStatusForm = new TransicaoStatus();
		clienteIdSelectedItem = null;
		statusDeIdSelectedItem = null;
		statusParaIdSelectedItem = null;
		formularioIdSelectedItem = null;
	}
	
	@Override
	protected void postLoad() {
		allClientesFromDB = clienteDao.findAll();
		allStatusFromDB = statusDao.findAll();
		allFormulariosFromDB = formularioDao.findAll();
		clienteList = fillSelectItemList(allClientesFromDB);
		statusDeList = fillSelectItemList(allStatusFromDB);
		formularioList = fillSelectItemList(allFormulariosFromDB);
	}
	
	@Override
	protected void postRowSelect() {
		clienteIdSelectedItem = transicaoStatusForm.getCliente() != null ? transicaoStatusForm.getCliente().getId().toString() : null;
		statusDeIdSelectedItem = transicaoStatusForm.getStatusDe() != null ? transicaoStatusForm.getStatusDe().getId().toString() : null;
		statusParaList = fillStatusSelectItemList(transicaoStatusForm.getStatusDe());
		statusParaIdSelectedItem = transicaoStatusForm.getStatusPara() != null ? transicaoStatusForm.getStatusPara().getId().toString() : null;
		formularioIdSelectedItem = transicaoStatusForm.getFormulario() != null ? transicaoStatusForm.getFormulario().getId().toString() : null;
	}
	
	/* get&set */
	
	public List<SelectItem> getFormularioList() {
		return formularioList;
	}

	public void setFormularioList(List<SelectItem> formularioList) {
		this.formularioList = formularioList;
	}

	public String getFormularioIdSelectedItem() {
		return formularioIdSelectedItem;
	}

	public void setFormularioIdSelectedItem(String formularioIdSelectedItem) {
		this.formularioIdSelectedItem = formularioIdSelectedItem;
	}

	public TransicaoStatus getTransicaoStatusForm() {
		return transicaoStatusForm;
	}

	public void setTransicaoStatusForm(TransicaoStatus transicaoStatusForm) {
		this.transicaoStatusForm = transicaoStatusForm;
	}
	
	public DashboardDataModel<TransicaoStatus> getDataModel() {
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
	
	public String getStatusDeIdSelectedItem() {
		return statusDeIdSelectedItem;
	}

	public void setStatusDeIdSelectedItem(String statusDeIdSelectedItem) {
		this.statusDeIdSelectedItem = statusDeIdSelectedItem;
	}

	public String getStatusParaIdSelectedItem() {
		return statusParaIdSelectedItem;
	}

	public void setStatusParaIdSelectedItem(String statusParaIdSelectedItem) {
		this.statusParaIdSelectedItem = statusParaIdSelectedItem;
	}

	public List<Status> getAllStatusFromDB() {
		return allStatusFromDB;
	}

	public List<SelectItem> getStatusDeList() {
		return statusDeList;
	}

	public List<SelectItem> getStatusParaList() {
		return statusParaList;
	}
	
}