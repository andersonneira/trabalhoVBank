package br.com.rp.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Welton Cyriaco
 *
 */
public class ContaCorrenteMovimentacaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long contaCorrenteId;
	
	private Date dataRealizacao;
	
	private BigDecimal valor;

	public ContaCorrenteMovimentacaoDTO(Long contaCorrenteId, Date dataRealizacao, BigDecimal valor) {
		super();
		this.contaCorrenteId = contaCorrenteId;
		this.dataRealizacao = dataRealizacao;
		this.valor = valor;
	}

	public Long getContaCorrenteId() {
		return contaCorrenteId;
	}

	public void setContaCorrenteId(Long contaCorrenteId) {
		this.contaCorrenteId = contaCorrenteId;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "ContaCorrenteMovimentacaoDTO [contaCorrenteId=" + contaCorrenteId + ", dataRealizacao=" + dataRealizacao
				+ ", valor=" + valor + "]";
	}
}
