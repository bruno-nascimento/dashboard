package br.com.bsitecnologia.dashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PerfilFuncionalidade", catalog = "dashboard")
public class PerfilFuncionalidade implements java.io.Serializable {

	private static final long serialVersionUID = 5538427132612989517L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perfil", nullable = false)
	private Perfil perfil;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "funcionalidade", nullable = false)
	private Funcionalidade funcionalidade;
	@Column(name = "visualizar")
	private boolean visualizar;
	@Column(name = "inserir")
	private boolean inserir;
	@Column(name = "editar")
	private boolean editar;
	@Column(name = "excluir")
	private boolean excluir;

	public PerfilFuncionalidade() {
	}

	public PerfilFuncionalidade(Perfil perfil, Funcionalidade funcionalidade) {
		this.perfil = perfil;
		this.funcionalidade = funcionalidade;
	}

	public PerfilFuncionalidade(Perfil perfil, Funcionalidade funcionalidade, Boolean visualizar, Boolean inserir, Boolean editar, Boolean excluir) {
		this.perfil = perfil;
		this.funcionalidade = funcionalidade;
		this.visualizar = visualizar;
		this.inserir = inserir;
		this.editar = editar;
		this.excluir = excluir;
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

	public Funcionalidade getFuncionalidade() {
		return this.funcionalidade;
	}

	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public boolean getVisualizar() {
		return this.visualizar;
	}

	public void setVisualizar(Boolean visualizar) {
		this.visualizar = visualizar;
	}

	public boolean getInserir() {
		return this.inserir;
	}

	public void setInserir(Boolean inserir) {
		this.inserir = inserir;
	}

	public boolean getEditar() {
		return this.editar;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	public boolean getExcluir() {
		return this.excluir;
	}

	public void setExcluir(Boolean excluir) {
		this.excluir = excluir;
	}

}
