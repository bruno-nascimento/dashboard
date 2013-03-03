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
	
	private static final long serialVersionUID = 9220102061465948511L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "de", nullable = false)
	private Status statusDe;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "para", nullable = false)
	private Status statusPara;
	
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transicaoStatus")
	private List<TransicaoStatusPerfil> transicaoStatusPerfils = new ArrayList<TransicaoStatusPerfil>(0);

	public TransicaoStatus() {
	}

	public TransicaoStatus(Status statusDe, Status statusPara, String nome) {
		this.statusDe = statusDe;
		this.statusPara = statusPara;
		this.nome = nome;
	}

	public TransicaoStatus(Status statusDe, Cliente cliente,
			Status statusPara, String nome,
			List<TransicaoStatusPerfil> transicaoStatusPerfils) {
		this.statusDe = statusDe;
		this.cliente = cliente;
		this.statusPara = statusPara;
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
