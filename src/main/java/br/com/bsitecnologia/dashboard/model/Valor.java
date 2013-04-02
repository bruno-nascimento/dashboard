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
@Table(name = "Valor", catalog = "dashboard")
public class Valor implements Serializable, BaseEntity {

	private static final long serialVersionUID = -6124386603000074251L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campoFormulario", nullable = false)
	private CampoFormulario campoFormulario;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="opcao")
    private Opcoes opcao;

	@Column(name = "valor")
	private String valor;

	public Valor() {
	}

	public Valor(CampoFormulario campoFormulario) {
		this.campoFormulario = campoFormulario;
	}

	public Valor(CampoFormulario campoFormulario, String valor) {
		this.campoFormulario = campoFormulario;
		this.valor = valor;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CampoFormulario getCampoFormulario() {
		return this.campoFormulario;
	}

	public void setCampoFormulario(CampoFormulario campoFormulario) {
		this.campoFormulario = campoFormulario;
	}
	
	public Opcoes getOpcao() {
		return opcao;
	}

	public void setOpcao(Opcoes opcao) {
		this.opcao = opcao;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String getEntityDescription() {
		return valor;
	}

}
