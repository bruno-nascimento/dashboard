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
@Table(name = "TipoInputTipoDado", catalog = "dashboard")
public class TipoInputTipoDado implements Serializable, BaseEntity {

	private static final long serialVersionUID = 8675255596648759529L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoInput", nullable = false)
	private TipoInput tipoInput;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoDado", nullable = false)
	private TipoDado tipoDado;

	
	public TipoInputTipoDado() {
	}

	public TipoInputTipoDado(TipoInput tipoInput, TipoDado tipoDado) {
		this.tipoInput = tipoInput;
		this.tipoDado = tipoDado;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoInput getTipoInput() {
		return this.tipoInput;
	}

	public void setTipoInput(TipoInput tipoInput) {
		this.tipoInput = tipoInput;
	}

	public TipoDado getTipoDado() {
		return this.tipoDado;
	}

	public void setTipoDado(TipoDado tipoDado) {
		this.tipoDado = tipoDado;
	}

	@Override
	public String getEntityDescription() {
		return String.format("%s >> %s", tipoInput.getNome(), tipoDado.getNome());
	}

}
