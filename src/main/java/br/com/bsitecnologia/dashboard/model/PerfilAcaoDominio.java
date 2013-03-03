package br.com.bsitecnologia.dashboard.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.bsitecnologia.dashboard.util.BaseEntity;

@Entity
@Table(name = "PerfilAcaoDominio", catalog = "dashboard", uniqueConstraints = @UniqueConstraint(columnNames = {"perfil", "dominio", "acao" }))
public class PerfilAcaoDominio implements Serializable, BaseEntity {
	
	private static final long serialVersionUID = 691518827722925587L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acao", nullable = false)
	private Acao acao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perfil", nullable = false)
	private Perfil perfil;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dominio", nullable = false)
	private Dominio dominio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente")
	private Cliente cliente;

	public PerfilAcaoDominio() {
	}

	public PerfilAcaoDominio(Acao acao, Perfil perfil, Dominio dominio) {
		this.acao = acao;
		this.perfil = perfil;
		this.dominio = dominio;
	}

	public PerfilAcaoDominio(Acao acao, Perfil perfil, Dominio dominio,
			Cliente cliente) {
		this.acao = acao;
		this.perfil = perfil;
		this.dominio = dominio;
		this.cliente = cliente;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Acao getAcao() {
		return this.acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Dominio getDominio() {
		return this.dominio;
	}

	public void setDominio(Dominio dominio) {
		this.dominio = dominio;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String getEntityDescription() {
		return String.format("%s >> %s >> %s", perfil.getNome(), dominio.getNome(), acao.getNome());
	}

}
