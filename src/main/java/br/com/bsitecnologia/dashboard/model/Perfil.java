package br.com.bsitecnologia.dashboard.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Perfil", catalog = "dashboard")
public class Perfil implements java.io.Serializable {

	private static final long serialVersionUID = -1503176661628732282L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@Column(name = "nome", length = 45)
	private String nome;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
	private List<Usuario> usuarios = new ArrayList<Usuario>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
	private List<PerfilFuncionalidade> perfilFuncionalidades = new ArrayList<PerfilFuncionalidade>(0);

	public Perfil() {
	}

	public Perfil(String nome, List<Usuario> usuarios, List<PerfilFuncionalidade> perfilFuncionalidades) {
		this.nome = nome;
		this.usuarios = usuarios;
		this.perfilFuncionalidades = perfilFuncionalidades;
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

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<PerfilFuncionalidade> getPerfilFuncionalidades() {
		return this.perfilFuncionalidades;
	}

	public void setPerfilFuncionalidades(List<PerfilFuncionalidade> perfilFuncionalidades) {
		this.perfilFuncionalidades = perfilFuncionalidades;
	}

}
