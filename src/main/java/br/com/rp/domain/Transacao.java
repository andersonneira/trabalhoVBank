package br.com.rp.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transacao")
public class Transacao extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "valor", nullable=false)
	private BigDecimal valor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "envioBacen")
	private Date envioBacen;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "envioMatriz")
	private Date envioMatriz;
	
	@Column(name = "agenciaDestino", nullable=false)
	private String agenciaDestino;
	
	@Column(name = "agenciaDestinoDigitoVerificador", nullable=false)
	private String agenciaDestinoDigitoVerificador;
	
	@Column(name = "numeroContaDestino", nullable=false)
	private String numeroContaDestino;
	
	@Column(name = "numeroContaDestinoDigitoVerificador", nullable=false)
	private String numeroContaDestinoDigitoVerificador;
	
	@OneToOne
	@JoinColumn(name="deposito_cheque")
	private DepositoCheque depositoCheque;

	@OneToOne
	@JoinColumn(name="pagamento")
	private Pagamento pagamento;

	public Transacao() {
		super();
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getEnvioBacen() {
		return envioBacen;
	}

	public void setEnvioBacen(Date envioBacen) {
		this.envioBacen = envioBacen;
	}

	public Date getEnvioMatriz() {
		return envioMatriz;
	}

	public void setEnvioMatriz(Date envioMatriz) {
		this.envioMatriz = envioMatriz;
	}

	public String getAgenciaDestino() {
		return agenciaDestino;
	}

	public void setAgenciaDestino(String agenciaDestino) {
		this.agenciaDestino = agenciaDestino;
	}

	public String getAgenciaDestinoDigitoVerificador() {
		return agenciaDestinoDigitoVerificador;
	}

	public void setAgenciaDestinoDigitoVerificador(String agenciaDestinoDigitoVerificador) {
		this.agenciaDestinoDigitoVerificador = agenciaDestinoDigitoVerificador;
	}

	public String getNumeroContaDestino() {
		return numeroContaDestino;
	}

	public void setNumeroContaDestino(String numeroContaDestino) {
		this.numeroContaDestino = numeroContaDestino;
	}

	public String getNumeroContaDestinoDigitoVerificador() {
		return numeroContaDestinoDigitoVerificador;
	}

	public void setNumeroContaDestinoDigitoVerificador(String numeroContaDestinoDigitoVerificador) {
		this.numeroContaDestinoDigitoVerificador = numeroContaDestinoDigitoVerificador;
	}
	
	public DepositoCheque getDepositoCheque() {
		return depositoCheque;
	}

	public void setDepositoCheque(DepositoCheque depositoCheque) {
		this.depositoCheque = depositoCheque;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	@Override
	public String toString() {
		return "Transacao [valor=" + valor + ", envioBacen=" + envioBacen + ", envioMatriz=" + envioMatriz
				+ ", agenciaDestino=" + agenciaDestino + ", agenciaDestinoDigitoVerificador="
				+ agenciaDestinoDigitoVerificador + ", numeroContaDestino=" + numeroContaDestino
				+ ", numeroContaDestinoDigitoVerificador=" + numeroContaDestinoDigitoVerificador + ", depositoCheque="
				+ depositoCheque + ", pagamento=" + pagamento + "]";
	}
}
