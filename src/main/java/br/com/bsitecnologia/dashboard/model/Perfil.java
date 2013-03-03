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
@Table(name = "Perfil", catalog = "dashboard")
public class Perfil implements Serializable, BaseEntity {
	
	private static final long serialVersionUID = -7169617010076098569L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente")
	private Cliente cliente;
	
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
	private List<TransicaoStatusPerfil> transicaoStatusPerfils = new ArrayList<TransicaoStatusPerfil>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
	private List<PerfilAcaoDominio> perfilAcaoDominios = new ArrayList<PerfilAcaoDominio>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
	private List<Usuario> usuarios = new ArrayList<Usuario>(0);

	public Perfil() {
	}

	public Perfil(String nome) {
		this.nome = nome;
	}

	public Perfil(Cliente cliente, String nome,
			List<TransicaoStatusPerfil> transicaoStatusPerfils,
			List<PerfilAcaoDominio> perfilAcaoDominios, List<Usuario> usuarios) {
		this.cliente = cliente;
		this.nome = nome;
		this.transicaoStatusPerfils = transicaoStatusPerfils;
		this.perfilAcaoDominios = perfilAcaoDominios;
		this.usuarios = usuarios;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public void setTransicaoStatusPerfils(
			List<TransicaoStatusPerfil> transicaoStatusPerfils) {
		this.transicaoStatusPerfils = transicaoStatusPerfils;
	}

	public List<PerfilAcaoDominio> getPerfilAcaoDominios() {
		return this.perfilAcaoDominios;
	}

	public void setPerfilAcaoDominios(List<PerfilAcaoDominio> perfilAcaoDominios) {
		this.perfilAcaoDominios = perfilAcaoDominios;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String getEntityDescription() {
		return nome;
	}

}
