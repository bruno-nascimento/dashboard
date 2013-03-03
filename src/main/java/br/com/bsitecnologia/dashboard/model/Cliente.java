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
@Table(name = "Cliente", catalog = "dashboard")
public class Cliente implements Serializable, BaseEntity {
	
	private static final long serialVersionUID = -8753685558904077791L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	// RELACIONAMENTOS 
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<TransicaoStatus> transicaoStatuses = new ArrayList<TransicaoStatus>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<AreaSolicitante> areaSolicitantes = new ArrayList<AreaSolicitante>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Perfil> perfils = new ArrayList<Perfil>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<TransicaoStatusPerfil> transicaoStatusPerfils = new ArrayList<TransicaoStatusPerfil>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<PerfilAcaoDominio> perfilAcaoDominios = new ArrayList<PerfilAcaoDominio>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Criticidade> criticidades = new ArrayList<Criticidade>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Status> statuses = new ArrayList<Status>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Demanda> demandas = new ArrayList<Demanda>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Usuario> usuarios = new ArrayList<Usuario>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<TipoServico> tipoServicos = new ArrayList<TipoServico>(0);

	public Cliente() {
	}

	public Cliente(String nome) {
		this.nome = nome;
	}

	public Cliente(String nome, List<TransicaoStatus> transicaoStatuses,
			List<AreaSolicitante> areaSolicitantes, List<Perfil> perfils,
			List<TransicaoStatusPerfil> transicaoStatusPerfils,
			List<PerfilAcaoDominio> perfilAcaoDominios,
			List<Criticidade> criticidades, List<Status> statuses,
			List<Demanda> demandas, List<Usuario> usuarios,
			List<TipoServico> tipoServicos) {
		this.nome = nome;
		this.transicaoStatuses = transicaoStatuses;
		this.areaSolicitantes = areaSolicitantes;
		this.perfils = perfils;
		this.transicaoStatusPerfils = transicaoStatusPerfils;
		this.perfilAcaoDominios = perfilAcaoDominios;
		this.criticidades = criticidades;
		this.statuses = statuses;
		this.demandas = demandas;
		this.usuarios = usuarios;
		this.tipoServicos = tipoServicos;
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

	public List<AreaSolicitante> getAreaSolicitantes() {
		return this.areaSolicitantes;
	}

	public void setAreaSolicitantes(List<AreaSolicitante> areaSolicitantes) {
		this.areaSolicitantes = areaSolicitantes;
	}

	public List<Perfil> getPerfils() {
		return this.perfils;
	}

	public void setPerfils(List<Perfil> perfils) {
		this.perfils = perfils;
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

	public List<Criticidade> getCriticidades() {
		return this.criticidades;
	}

	public void setCriticidades(List<Criticidade> criticidades) {
		this.criticidades = criticidades;
	}

	public List<Status> getStatuses() {
		return this.statuses;
	}

	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}

	public List<Demanda> getDemandas() {
		return this.demandas;
	}

	public void setDemandas(List<Demanda> demandas) {
		this.demandas = demandas;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<TipoServico> getTipoServicos() {
		return this.tipoServicos;
	}

	public void setTipoServicos(List<TipoServico> tipoServicos) {
		this.tipoServicos = tipoServicos;
	}

	@Override
	public String getEntityDescription() {
		return this.nome;
	}

}
