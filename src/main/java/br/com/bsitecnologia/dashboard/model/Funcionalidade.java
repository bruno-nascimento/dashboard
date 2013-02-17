package br.com.bsitecnologia.dashboard.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Funcionalidade", catalog = "dashboard")
public class Funcionalidade implements java.io.Serializable {

	private static final long serialVersionUID = 7392034940190428687L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@Column(name = "nome", length = 45)
	private String nome;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionalidade")
	private Set<PerfilFuncionalidade> perfilFuncionalidades = new HashSet<PerfilFuncionalidade>(0);
	
	public Funcionalidade() {
	}

	public Funcionalidade(String nome, Set<PerfilFuncionalidade> perfilFuncionalidades) {
		this.nome = nome;
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

	public Set<PerfilFuncionalidade> getPerfilFuncionalidades() {
		return this.perfilFuncionalidades;
	}

	public void setPerfilFuncionalidades(Set<PerfilFuncionalidade> perfilFuncionalidades) {
		this.perfilFuncionalidades = perfilFuncionalidades;
	}

}
