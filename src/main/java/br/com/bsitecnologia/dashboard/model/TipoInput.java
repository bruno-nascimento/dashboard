package br.com.bsitecnologia.dashboard.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.bsitecnologia.dashboard.util.BaseEntity;

@Entity
@Table(name = "TipoInput", catalog = "dashboard")
public class TipoInput implements Serializable, BaseEntity {

	private static final long serialVersionUID = -8759837005395280694L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "nome", nullable = false, length = 45)
	private String nome;
	
	@Basic
	@Column(name = "opcoes", columnDefinition = "BIT", length = 1, nullable = false)
	private boolean opcoes;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoInput")
	private List<Campo> campos = new ArrayList<Campo>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoInput")
	private List<TipoInputTipoDado> tipoInputTipoDados = new ArrayList<TipoInputTipoDado>(0);

	
	public TipoInput() {
	}

	public TipoInput(String nome, boolean opcoes) {
		this.nome = nome;
		this.opcoes = opcoes;
	}

	public TipoInput(String nome, boolean opcoes, List<Campo> campos, List<TipoInputTipoDado> tipoInputTipoDados) {
		this.nome = nome;
		this.opcoes = opcoes;
		this.campos = campos;
		this.tipoInputTipoDados = tipoInputTipoDados;
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

	public boolean isOpcoes() {
		return this.opcoes;
	}

	public void setOpcoes(boolean opcoes) {
		this.opcoes = opcoes;
	}

	public List<Campo> getCampos() {
		return this.campos;
	}

	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}

	public List<TipoInputTipoDado> getTipoInputTipoDados() {
		return this.tipoInputTipoDados;
	}

	public void setTipoInputTipoDados(List<TipoInputTipoDado> tipoInputTipoDados) {
		this.tipoInputTipoDados = tipoInputTipoDados;
	}

	@Override
	public String getEntityDescription() {
		return nome;
	}

}
