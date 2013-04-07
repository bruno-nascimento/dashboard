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
import javax.persistence.Transient;

import br.com.bsitecnologia.dashboard.util.BaseEntity;

@Entity
@Table(name = "Usuario", catalog = "dashboard")
public class Usuario implements Serializable, BaseEntity {
	
	private static final long serialVersionUID = 114469773949877471L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perfil", nullable = false)
	private Perfil perfil;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente")
	private Cliente cliente;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "senha", nullable = false, length = 86)
	private String senha;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
	private List<Telefone> telefones = new ArrayList<Telefone>(0);
	
	@Transient
	private boolean logado;


	public Usuario() {
	}

	public Usuario(Perfil perfil, String email, String senha, String nome) {
		this.perfil = perfil;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
	}

	public Usuario(Perfil perfil, Cliente cliente, String email, String senha,
			String nome, List<Telefone> telefones) {
		this.perfil = perfil;
		this.cliente = cliente;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.telefones = telefones;
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

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Telefone> getTelefones() {
		return this.telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	@Override
	public String getEntityDescription() {
		return nome;
	}

}
