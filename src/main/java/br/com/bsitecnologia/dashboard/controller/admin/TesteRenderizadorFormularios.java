package br.com.bsitecnologia.dashboard.controller.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;

import br.com.bsitecnologia.dashboard.dao.FormularioDao;
import br.com.bsitecnologia.dashboard.model.CampoFormulario;
import br.com.bsitecnologia.dashboard.model.Formulario;
import br.com.bsitecnologia.dashboard.model.Opcoes;
import br.com.bsitecnologia.dashboard.model.Valor;
import br.com.bsitecnologia.dashboard.service.CampoService;

@Named
@ConversationScoped
public class TesteRenderizadorFormularios implements Serializable{

	private static final long serialVersionUID = 5202451693226294430L;
	
	@Inject 
	private FormularioDao formularioDao;
	
	private Formulario formulario;
	
	private List<Valor> listaValores;
	
	@Inject
	private CampoService campoService;
	
	@PostConstruct
	public void postConstruct(){
		formulario = formularioDao.findAll().get(0);
		initValorList();
	}
	
	public void salvar(){
		for (Valor valor : listaValores) {
			System.out.println("###################### "+valor.getValor());
		}
	}
	
	private void initValorList(){
		listaValores = new ArrayList<Valor>();
		for (CampoFormulario campoFormulario : formulario.getCampoFormularios()) {
			Valor valor = new Valor(campoFormulario);
			List<Opcoes> opcoes = campoService.getOpcoesByCampo(campoFormulario.getCampo());
			valor.getCampoFormulario().getCampo().setOpcoes(opcoes);
			listaValores.add(valor);
		}
	}
	
	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}
	
	public List<Valor> getListaValores() {
		return listaValores;
	}

	public void setListaValores(List<Valor> listaValores) {
		this.listaValores = listaValores;
	}

}
