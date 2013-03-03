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
@Table(name = "Acao", catalog = "dashboard")
public class Acao implements Serializable, BaseEntity {

	private static final long serialVersionUID = -2347695387604154656L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "acao")
	private List<PerfilAcaoDominio> perfilAcaoDominios = new ArrayList<PerfilAcaoDominio>(0);

	public Acao() {
	}

	public Acao(String nome) {
		this.nome = nome;
	}

	public Acao(String nome, List<PerfilAcaoDominio> perfilAcaoDominios) {
		this.nome = nome;
		this.perfilAcaoDominios = perfilAcaoDominios;
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

	public List<PerfilAcaoDominio> getPerfilAcaoDominios() {
		return this.perfilAcaoDominios;
	}

	public void setPerfilAcaoDominios(List<PerfilAcaoDominio> perfilAcaoDominios) {
		this.perfilAcaoDominios = perfilAcaoDominios;
	}

	@Override
	public String getEntityDescription() {
		return nome;
	}

}
