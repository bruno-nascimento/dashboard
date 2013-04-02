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

import br.com.bsitecnologia.dashboard.util.BaseEntity;

@Entity
@Table(name = "Opcoes", catalog = "dashboard")
public class Opcoes implements Serializable, BaseEntity {
	
	private static final long serialVersionUID = -8486858203298760429L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campo", nullable = false)
	private Campo campo;
	
	@Column(name = "nome", nullable = false, length = 50)
	private String nome;
	
	@Column(name = "valor", nullable = false, length = 50)
	private String valor;

	
	public Opcoes() {
	}

	public Opcoes(Campo campo, String nome, String valor) {
		this.campo = campo;
		this.nome = nome;
		this.valor = valor;
	}

	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Campo getCampo() {
		return this.campo;
	}

	public void setCampo(Campo campo) {
		this.campo = campo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String getEntityDescription() {
		return nome;
	}

}
