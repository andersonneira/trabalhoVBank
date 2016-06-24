package br.com.rp.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="configuracao")
public class Configuracao extends BaseEntity {
	
	@Temporal(TemporalType.TIME)
	@Column(name = "hora_abertura_operacao",nullable=false)
	private Date horaAberturaOperacao;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "hora_fechamento_operacao",nullable=false)
	private Date horaFechamentoOperacao;
	
	@Column(name = "limite_padrao",nullable=false)
	private BigDecimal limitePadrao;

	public Date getHoraAberturaOperacao() {
		return horaAberturaOperacao;
	}

	public void setHoraAberturaOperacao(Date horaAberturaOperacao) {
		this.horaAberturaOperacao = horaAberturaOperacao;
	}

	public Date getHoraFechamentoOperacao() {
		return horaFechamentoOperacao;
	}

	public void setHoraFechamentoOperacao(Date horaFechamentoOperacao) {
		this.horaFechamentoOperacao = horaFechamentoOperacao;
	}

	public BigDecimal getLimitePadrao() {
		return limitePadrao;
	}

	public void setLimitePadrao(BigDecimal limitePadrao) {
		this.limitePadrao = limitePadrao;
	}
	
	
	
	
}
