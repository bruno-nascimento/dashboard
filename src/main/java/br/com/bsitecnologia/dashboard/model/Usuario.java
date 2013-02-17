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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import br.com.bsitecnologia.dashboard.util.BaseEntity;

@Entity
@Table(name = "Usuario", catalog = "dashboard", uniqueConstraints = @UniqueConstraint(columnNames = "login"))
public class Usuario implements Serializable, BaseEntity {

	private static final long serialVersionUID = 4408109929394314711L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perfil", nullable = false)
	private Perfil perfil;
	@Column(name = "login", unique = true, nullable = false, length = 100)
	private String login;
	@Column(name = "senha", nullable = false, length = 86)
	private String senha;
	@Column(name = "isColaborador", nullable = false)
	private boolean isColaborador;
	
	@Transient
	private boolean logado;

	public Usuario() {
	}

	public Usuario(Perfil perfil, String login, String senha, boolean isColaborador) {
		this.perfil = perfil;
		this.login = login;
		this.senha = senha;
		this.isColaborador = isColaborador;
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

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isColaborador() {
		return isColaborador;
	}

	public void setColaborador(boolean isColaborador) {
		this.isColaborador = isColaborador;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	@Override
	public String getEntityDescription() {
		return login;
	}

}
