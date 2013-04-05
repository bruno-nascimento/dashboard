package br.com.bsitecnologia.dashboard.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "CampoFormulario", catalog = "dashboard")
public class CampoFormulario implements Serializable, BaseEntity {

	private static final long serialVersionUID = -1783422574109262929L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campo", nullable = false)
	private Campo campo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formulario", nullable = false)
	private Formulario formulario;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "campoFormulario")
	private List<Valor> valores = new ArrayList<Valor>(0);

	
	public CampoFormulario() {
	}

	public CampoFormulario(int id, Campo campo, Formulario formulario) {
		this.id = id;
		this.campo = campo;
		this.formulario = formulario;
	}

	public CampoFormulario(int id, Campo campo, Formulario formulario, List<Valor> valores) {
		this.id = id;
		this.campo = campo;
		this.formulario = formulario;
		this.valores = valores;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Campo getCampo() {
		return this.campo;
	}

	public void setCampo(Campo campo) {
		this.campo = campo;
	}

	public Formulario getFormulario() {
		return this.formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	public List<Valor> getValores() {
		return this.valores;
	}

	public void setValors(List<Valor> valores) {
		this.valores = valores;
	}

	@Override
	public String getEntityDescription() {
		return String.format("%s >> %s", formulario.getNome(), campo.getLabel());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
		result = prime * result
				+ ((formulario == null) ? 0 : formulario.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((valores == null) ? 0 : valores.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CampoFormulario other = (CampoFormulario) obj;
		if (campo == null) {
			if (other.campo != null)
				return false;
		} else if (!campo.equals(other.campo))
			return false;
		if (formulario == null) {
			if (other.formulario != null)
				return false;
		} else if (!formulario.equals(other.formulario))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (valores == null) {
			if (other.valores != null)
				return false;
		} else if (!valores.equals(other.valores))
			return false;
		return true;
	}
	
	
	
}