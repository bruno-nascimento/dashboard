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
@Table(name = "TipoDado", catalog = "dashboard")
public class TipoDado implements Serializable, BaseEntity {

	private static final long serialVersionUID = -5540582353597193759L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "nome", nullable = false, length = 45)
	private String nome;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoDado")
	private Set<TipoInputTipoDado> tipoInputTipoDados = new HashSet<TipoInputTipoDado>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoDado")
	private Set<Campo> campos = new HashSet<Campo>(0);

	
	public TipoDado() {
	}

	public TipoDado(String nome) {
		this.nome = nome;
	}

	public TipoDado(String nome, Set<TipoInputTipoDado> tipoInputTipoDados,	Set<Campo> campos) {
		this.nome = nome;
		this.tipoInputTipoDados = tipoInputTipoDados;
		this.campos = campos;
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

	public Set<TipoInputTipoDado> getTipoInputTipoDados() {
		return this.tipoInputTipoDados;
	}

	public void setTipoInputTipoDados(Set<TipoInputTipoDado> tipoInputTipoDados) {
		this.tipoInputTipoDados = tipoInputTipoDados;
	}

	public Set<Campo> getCampos() {
		return this.campos;
	}

	public void setCampos(Set<Campo> campos) {
		this.campos = campos;
	}

	@Override
	public String getEntityDescription() {
		return nome;
	}

}
