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
@Table(name = "TransicaoStatusPerfil", catalog = "dashboard", uniqueConstraints = @UniqueConstraint(columnNames = {"transicaoStatus", "perfil" }))
public class TransicaoStatusPerfil implements Serializable, BaseEntity {

	private static final long serialVersionUID = -6375907435464495672L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perfil", nullable = false)
	private Perfil perfil;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transicaoStatus", nullable = false)
	private TransicaoStatus transicaoStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente")
	private Cliente cliente;

	public TransicaoStatusPerfil() {
	}

	public TransicaoStatusPerfil(Perfil perfil, TransicaoStatus transicaoStatus) {
		this.perfil = perfil;
		this.transicaoStatus = transicaoStatus;
	}

	public TransicaoStatusPerfil(Perfil perfil,
			TransicaoStatus transicaoStatus, Cliente cliente) {
		this.perfil = perfil;
		this.transicaoStatus = transicaoStatus;
		this.cliente = cliente;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public TransicaoStatus getTransicaoStatus() {
		return this.transicaoStatus;
	}

	public void setTransicaoStatus(TransicaoStatus transicaoStatus) {
		this.transicaoStatus = transicaoStatus;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String getEntityDescription() {
		return String.format("%s >> %s", perfil.getNome(), transicaoStatus.getNome());
	}

}
