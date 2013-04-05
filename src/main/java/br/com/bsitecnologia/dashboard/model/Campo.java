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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((campoFormularios == null) ? 0 : campoFormularios.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + (obrigatorio ? 1231 : 1237);
		result = prime * result + ((opcoes == null) ? 0 : opcoes.hashCode());
		result = prime * result + tamanho;
		result = prime * result
				+ ((tipoDado == null) ? 0 : tipoDado.hashCode());
		result = prime * result
				+ ((tipoInput == null) ? 0 : tipoInput.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!getClass().isAssignableFrom(obj.getClass()))
			return false;
		Campo other = (Campo) obj;
		if (getCampoFormularios() == null) {
			if (other.getCampoFormularios() != null)
				return false;
		} else if (!getCampoFormularios().equals(other.getCampoFormularios()))
			return false;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		if (getLabel() == null) {
			if (other.getLabel() != null)
				return false;
		} else if (!getLabel().equals(other.getLabel()))
			return false;
		if (getObrigatorio() != other.getObrigatorio())
			return false;
		if (getOpcoes() == null) {
			if (other.getOpcoes() != null)
				return false;
		} else if (!getOpcoes().equals(other.getOpcoes()))
			return false;
		if (getTamanho() != other.getTamanho())
			return false;
		if (getTipoDado() == null) {
			if (other.getTipoDado() != null)
				return false;
		} else if (!getTipoDado().equals(other.getTipoDado()))
			return false;
		if (getTipoInput() == null) {
			if (other.getTipoInput() != null)
				return false;
		} else if (!getTipoInput().equals(other.getTipoInput()))
			return false;
		return true;
	}

}
