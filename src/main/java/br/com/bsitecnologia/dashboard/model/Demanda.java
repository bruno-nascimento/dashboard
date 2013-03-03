package br.com.bsitecnologia.dashboard.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.bsitecnologia.dashboard.util.BaseEntity;

@Entity
@Table(name = "Demanda", catalog = "dashboard")
public class Demanda implements Serializable, BaseEntity {
	
	private static final long serialVersionUID = 2590227996063531348L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", nullable = false)
	private Status status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoServico", nullable = false)
	private TipoServico tipoServico;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "criticidade", nullable = false)
	private Criticidade criticidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente", nullable = false)
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "areaSolicitante", nullable = false)
	private AreaSolicitante areaSolicitante;
	
	@Column(name = "identificadorCliente", nullable = false, length = 20)
	private String identificadorCliente;
	
	@Column(name = "identificadorInterno", length = 20)
	private String identificadorInterno;
	
	@Column(name = "projeto", nullable = false, length = 100)
	private String projeto;
	
	@Column(name = "titulo", nullable = false)
	private String titulo;
	
	@Column(name = "descricao", nullable = false, length = 2000)
	private String descricao;
	
	@Column(name = "valorOrcado", precision = 9)
	private BigDecimal valorOrcado;
	
	@Column(name = "prazoEstimadoHoras")
	private Integer prazoEstimadoHoras;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "inicio", length = 10)
	private Date inicio;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fim", length = 10)
	private Date fim;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "aceite", length = 10)
	private Date aceite;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "previsaoConclusao", length = 10)
	private Date previsaoConclusao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "conclusao", length = 10)
	private Date conclusao;

	
	public Demanda() {
	}

	public Demanda(Status status, TipoServico tipoServico,
			Criticidade criticidade, Cliente cliente,
			AreaSolicitante areaSolicitante, String identificadorCliente,
			String projeto, String titulo, String descricao) {
		this.status = status;
		this.tipoServico = tipoServico;
		this.criticidade = criticidade;
		this.cliente = cliente;
		this.areaSolicitante = areaSolicitante;
		this.identificadorCliente = identificadorCliente;
		this.projeto = projeto;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Demanda(Status status, TipoServico tipoServico,
			Criticidade criticidade, Cliente cliente,
			AreaSolicitante areaSolicitante, String identificadorCliente,
			String identificadorInterno, String projeto, String titulo,
			String descricao, BigDecimal valorOrcado,
			Integer prazoEstimadoHoras, Date inicio, Date fim, Date aceite,
			Date previsaoConclusao, Date conclusao) {
		this.status = status;
		this.tipoServico = tipoServico;
		this.criticidade = criticidade;
		this.cliente = cliente;
		this.areaSolicitante = areaSolicitante;
		this.identificadorCliente = identificadorCliente;
		this.identificadorInterno = identificadorInterno;
		this.projeto = projeto;
		this.titulo = titulo;
		this.descricao = descricao;
		this.valorOrcado = valorOrcado;
		this.prazoEstimadoHoras = prazoEstimadoHoras;
		this.inicio = inicio;
		this.fim = fim;
		this.aceite = aceite;
		this.previsaoConclusao = previsaoConclusao;
		this.conclusao = conclusao;
	}

	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TipoServico getTipoServico() {
		return this.tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Criticidade getCriticidade() {
		return this.criticidade;
	}

	public void setCriticidade(Criticidade criticidade) {
		this.criticidade = criticidade;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public AreaSolicitante getAreaSolicitante() {
		return this.areaSolicitante;
	}

	public void setAreaSolicitante(AreaSolicitante areaSolicitante) {
		this.areaSolicitante = areaSolicitante;
	}

	public String getIdentificadorCliente() {
		return this.identificadorCliente;
	}

	public void setIdentificadorCliente(String identificadorCliente) {
		this.identificadorCliente = identificadorCliente;
	}

	public String getIdentificadorInterno() {
		return this.identificadorInterno;
	}

	public void setIdentificadorInterno(String identificadorInterno) {
		this.identificadorInterno = identificadorInterno;
	}

	public String getProjeto() {
		return this.projeto;
	}

	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValorOrcado() {
		return this.valorOrcado;
	}

	public void setValorOrcado(BigDecimal valorOrcado) {
		this.valorOrcado = valorOrcado;
	}

	public Integer getPrazoEstimadoHoras() {
		return this.prazoEstimadoHoras;
	}

	public void setPrazoEstimadoHoras(Integer prazoEstimadoHoras) {
		this.prazoEstimadoHoras = prazoEstimadoHoras;
	}

	public Date getInicio() {
		return this.inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return this.fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public Date getAceite() {
		return this.aceite;
	}

	public void setAceite(Date aceite) {
		this.aceite = aceite;
	}

	public Date getPrevisaoConclusao() {
		return this.previsaoConclusao;
	}

	public void setPrevisaoConclusao(Date previsaoConclusao) {
		this.previsaoConclusao = previsaoConclusao;
	}

	public Date getConclusao() {
		return this.conclusao;
	}

	public void setConclusao(Date conclusao) {
		this.conclusao = conclusao;
	}

	@Override
	public String getEntityDescription() {
		return titulo;
	}

}
