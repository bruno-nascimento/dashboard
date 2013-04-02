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
@Table(name = "TransicaoStatus", catalog = "dashboard")
public class TransicaoStatus implements Serializable, BaseEntity {

	private static final long serialVersionUID = -5052286512876413582L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formulario")
	private Formulario formulario;
	
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "de", nullable = false)
	private Status statusDe;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "para", nullable = false)
	private Status statusPara;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transicaoStatus")
	private List<TransicaoStatusPerfil> transicaoStatusPerfils = new ArrayList<TransicaoStatusPerfil>(0);

	
	public TransicaoStatus() {
	}

	public TransicaoStatus(Status statusByDe, Status statusByPara, String nome) {
		this.statusDe = statusByDe;
		this.statusPara = statusByPara;
		this.nome = nome;
	}

	public TransicaoStatus(Status statusByDe, Formulario formulario, Cliente cliente, Status statusByPara, String nome, List<TransicaoStatusPerfil> transicaoStatusPerfils) {
		this.statusDe = statusByDe;
		this.formulario = formulario;
		this.cliente = cliente;
		this.statusPara = statusByPara;
		this.nome = nome;
		this.transicaoStatusPerfils = transicaoStatusPerfils;
	}

	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Status getStatusDe() {
		return this.statusDe;
	}

	public void setStatusDe(Status statusDe) {
		this.statusDe = statusDe;
	}

	public Formulario getFormulario() {
		return this.formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Status getStatusPara() {
		return this.statusPara;
	}

	public void setStatusPara(Status statusPara) {
		this.statusPara = statusPara;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<TransicaoStatusPerfil> getTransicaoStatusPerfils() {
		return this.transicaoStatusPerfils;
	}

	public void setTransicaoStatusPerfils(List<TransicaoStatusPerfil> transicaoStatusPerfils) {
		this.transicaoStatusPerfils = transicaoStatusPerfils;
	}

	@Override
	public String getEntityDescription() {
		return nome;
	}

}