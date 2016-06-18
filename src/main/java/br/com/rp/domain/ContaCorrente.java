/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rp.domain;

import br.com.rp.enums.StatusConta;
import br.com.rp.enums.TipoConta;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author anderson
 */
@Entity
@Table(name = "conta_corrente")
public class ContaCorrente extends BaseEntity {

    @Column(name = "numero", length = 10, nullable = false)
    @Size(min = 3, max = 10)
    private String numero;

    @Column(name = "digito_verificador", length = 10, nullable = false)
    @Size(min = 1, max = 1)
    private String digitoVerificador;

    @Column(name = "limite", nullable = false)
    private BigDecimal limite;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_conta", nullable = false)
    private StatusConta statusConta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conta", nullable = false)
    private TipoConta tipoConta;

    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;

    public ContaCorrente() {
        gerarConta();
    }

    /**
     * Metodo que buscao saldo da conta ( contabiliza todas as trasações).
     *
     * @return saldo da conta
     */
    public BigDecimal getSaldo() {
        return BigDecimal.ZERO;
    }

    /**
     * Quando uma conta for iniciada esse metodo irá gerar o número e o digito
     * verificador da conta.
     */
    private void gerarConta() {
        Long time = System.currentTimeMillis();
        this.digitoVerificador = "" + Math.log10(time);
    }

    public String getNumero() {
        return numero;
    }

    public String getDigitoVerificador() {
        return digitoVerificador;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public StatusConta getStatusConta() {
        return statusConta;
    }

    public void setStatusConta(StatusConta statusConta) {
        this.statusConta = statusConta;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}