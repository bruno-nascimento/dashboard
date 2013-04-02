package br.com.bsitecnologia.dashboard.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campo", nullable = false)
	private Campo campo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formulario", nullable = false)
	private Formulario formulario;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "campoFormulario")
	private Set<Valor> valores = new HashSet<Valor>(0);

	
	public CampoFormulario() {
	}

	public CampoFormulario(int id, Campo campo, Formulario formulario) {
		this.id = id;
		this.campo = campo;
		this.formulario = formulario;
	}

	public CampoFormulario(int id, Campo campo, Formulario formulario, Set<Valor> valores) {
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

	public Set<Valor> getValores() {
		return this.valores;
	}

	public void setValors(Set<Valor> valores) {
		this.valores = valores;
	}

	@Override
	public String getEntityDescription() {
		return String.format("%s >> %s", formulario.getNome(), campo.getLabel());
	}
	
}