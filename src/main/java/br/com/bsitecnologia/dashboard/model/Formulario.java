package br.com.bsitecnologia.dashboard.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.bsitecnologia.dashboard.util.BaseEntity;

@Entity
@Table(name = "Formulario", catalog = "dashboard")
public class Formulario implements Serializable, BaseEntity {

	private static final long serialVersionUID = 9186340627879105413L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formulario")
	private Set<TransicaoStatus> transicaoStatuses = new HashSet<TransicaoStatus>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formulario")
	private Set<CampoFormulario> campoFormularios = new HashSet<CampoFormulario>(0);

	
	public Formulario() {
	}

	public Formulario(String nome) {
		this.nome = nome;
	}
	
	public Formulario(String nome, Set<TransicaoStatus> transicaoStatuses,Set<CampoFormulario> campoFormularios) {
		this.nome = nome;
		this.transicaoStatuses = transicaoStatuses;
		this.campoFormularios = campoFormularios;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<TransicaoStatus> getTransicaoStatuses() {
		return this.transicaoStatuses;
	}

	public void setTransicaoStatuses(Set<TransicaoStatus> transicaoStatuses) {
		this.transicaoStatuses = transicaoStatuses;
	}

	public Set<CampoFormulario> getCampoFormularios() {
		return this.campoFormularios;
	}

	public void setCampoFormularios(Set<CampoFormulario> campoFormularios) {
		this.campoFormularios = campoFormularios;
	}

	@Override
	public String getEntityDescription() {
		return nome;
	}

}
