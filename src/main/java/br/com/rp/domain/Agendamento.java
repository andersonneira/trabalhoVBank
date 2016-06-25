package br.com.rp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.rp.enums.EstadoAgendamento;

@Entity
@Table(name = "agendamento")
public class Agendamento extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dataInclusao", nullable=false)
	private Date dataInclusao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dataRealizacao", nullable=false)
	private Date dataRealizacao;
	
	@Column(name="estado", nullable=false)
	@Enumerated(EnumType.STRING)
	private EstadoAgendamento estado;
	
	@OneToOne(orphanRemoval = true)
	@JoinColumn(name="transacao", nullable=false)
//	@PrimaryKeyJoinColumn
	private Transacao transacao;
	
	@ManyToOne
	@JoinColumn(name = "conta", nullable=false)
	private ContaCorrente conta;

	public Agendamento() {
		super();
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public EstadoAgendamento getEstado() {
		return estado;
	}

	public void setEstado(EstadoAgendamento estado) {
		this.estado = estado;
	}

	public Transacao getTransacao() {
		return transacao;
	}

	public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}
	
	public ContaCorrente getConta() {
		return conta;
	}

	public void setConta(ContaCorrente conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		return "Agendamento [dataInclusao=" + dataInclusao + ", dataRealizacao=" + dataRealizacao + ", estado=" + estado
				+ ", transacao=" + transacao + ", conta=" + conta + "]";
	}
}
