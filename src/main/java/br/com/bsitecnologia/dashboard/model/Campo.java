package br.com.bsitecnologia.dashboard.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.bsitecnologia.dashboard.util.BaseEntity;

@Entity
@Table(name = "Campo", catalog = "dashboard")
public class Campo implements Serializable, BaseEntity{

	private static final long serialVersionUID = 8685296900997227291L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoInput", nullable = false)
	private TipoInput tipoInput;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoDado", nullable = false)
	private TipoDado tipoDado;
	
	@Column(name = "label", nullable = false, length = 200)
	private String label;
	
	@Column(name = "tamanho", nullable = false)
	private int tamanho;
	
	@Basic
	@Column(name = "obrigatorio", columnDefinition = "BIT", length = 1, nullable = false)
	private boolean obrigatorio;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "campo")
	private List<Opcoes> opcoes = new ArrayList<Opcoes>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "campo")
	private List<CampoFormulario> campoFormularios = new ArrayList<CampoFormulario>(0);

	
	public Campo() {
	}

	public Campo(TipoInput tipoInput, TipoDado tipoDado, String label, int tamanho) {
		this.tipoInput = tipoInput;
		this.tipoDado = tipoDado;
		this.label = label;
		this.tamanho = tamanho;
	}

	public Campo(TipoInput tipoInput, TipoDado tipoDado, String label, int tamanho, Boolean obrigatorio, List<Opcoes> opcoeses, List<CampoFormulario> campoFormularios) {
		this.tipoInput = tipoInput;
		this.tipoDado = tipoDado;
		this.label = label;
		this.tamanho = tamanho;
		this.obrigatorio = obrigatorio;
		this.opcoes = opcoeses;
		this.campoFormularios = campoFormularios;
	}

	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoInput getTipoInput() {
		return this.tipoInput;
	}

	public void setTipoInput(TipoInput tipoInput) {
		this.tipoInput = tipoInput;
	}

	public TipoDado getTipoDado() {
		return this.tipoDado;
	}

	public void setTipoDado(TipoDado tipoDado) {
		this.tipoDado = tipoDado;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getTamanho() {
		return this.tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public boolean getObrigatorio() {
		return this.obrigatorio;
	}

	public void setObrigatorio(boolean obrigatorio) {
		this.obrigatorio = obrigatorio;
	}

	public List<Opcoes> getOpcoes() {
		return this.opcoes;
	}

	public void setOpcoes(List<Opcoes> opcoes) {
		this.opcoes = opcoes;
	}

	public List<CampoFormulario> getCampoFormularios() {
		return this.campoFormularios;
	}

	public void setCampoFormularios(List<CampoFormulario> campoFormularios) {
		this.campoFormularios = campoFormularios;
	}

	@Override
	public String getEntityDescription() {
		return label;
	}

}
