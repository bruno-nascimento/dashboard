package br.com.bsitecnologia.dashboard.controller.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;

import br.com.bsitecnologia.dashboard.controller.BaseCrudBean;
import br.com.bsitecnologia.dashboard.controller.datamodel.DashboardDataModel;
import br.com.bsitecnologia.dashboard.controller.template.BreadcrumbEnum;
import br.com.bsitecnologia.dashboard.dao.CargoDao;
import br.com.bsitecnologia.dashboard.model.Cargo;
import br.com.bsitecnologia.dashboard.model.Usuario;
import br.com.bsitecnologia.dashboard.resources.qualifiers.UsuarioLogado;

@Named
@ConversationScoped
@SuppressWarnings("unchecked")
public class CargoBean extends BaseCrudBean<Cargo> implements Serializable {
	
	private static final long serialVersionUID = -563351669224686839L;
	
	@Inject @UsuarioLogado Usuario usuario;
	
	@Inject private CargoDao cargoDao;
	@Inject @New private Cargo cargoForm;
	@Inject private DashboardDataModel<Cargo> dataModel;
	
	@PostConstruct
	public void postConstruct(){
		super.init();
	}
	
	/*BASE BEAN ABSTRACT METHODS IMPLEMENTATION*/

	@Override
	protected CargoDao getDao() {
		return cargoDao;
	}

	@Override
	protected Cargo getFormEntity() {
		return cargoForm;
	}

	@Override
	protected void setFormEntity(Cargo cargo) {
		cargoForm = cargo;		
	}

	@Override
	protected BreadcrumbEnum[] setBreadcrumbArray() {
		return new BreadcrumbEnum[]{BreadcrumbEnum.HOME, BreadcrumbEnum.CARGO};
	}

	@Override
	protected void resetFormEntity() {
		cargoForm = new Cargo();
	}
	
	/* get&set */
	
	public Cargo getCargoForm() {
		return cargoForm;
	}

	public void setCargoForm(Cargo cargoForm) {
		this.cargoForm = cargoForm;
	}
	
	public DashboardDataModel<Cargo> getDataModel() {
		return dataModel;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
