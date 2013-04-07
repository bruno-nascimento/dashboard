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
	private List<TransicaoStatus> transicaoStatuses = new ArrayList<TransicaoStatus>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formulario")
	private List<CampoFormulario> campoFormularios = new ArrayList<CampoFormulario>(0);

	
	public Formulario() {
	}

	public Formulario(String nome) {
		this.nome = nome;
	}
	
	public Formulario(String nome, List<TransicaoStatus> transicaoStatuses,List<CampoFormulario> campoFormularios) {
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

	public List<TransicaoStatus> getTransicaoStatuses() {
		return this.transicaoStatuses;
	}

	public void setTransicaoStatuses(List<TransicaoStatus> transicaoStatuses) {
		this.transicaoStatuses = transicaoStatuses;
	}

	public List<CampoFormulario> getCampoFormularios() {
		return this.campoFormularios;
	}

	public void setCampoFormularios(List<CampoFormulario> campoFormularios) {
		this.campoFormularios = campoFormularios;
	}

	@Override
	public String getEntityDescription() {
		return nome;
	}

}
