/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rp.repository;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cliente;
import br.com.rp.domain.ContaCorrente;
import br.com.rp.domain.Regiao;
import br.com.rp.enums.StatusConta;
import br.com.rp.enums.TipoConta;
import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author anderson
 */
public class ContaCorrenteRespositoryTest extends AbstractTest {

    private static final String _NOME_VALIDO = "Teste";
    private static final String _EMAIL_VALIDO = "teste@gmail.com";
    private static final String _CEP_VALIDO = "86800-005";
    private static final String _DOCUMENTO_VALIDO = "000.000.000-0";

    @EJB
    private ContaCorrenteRepository repository;
    @EJB
    private ClienteRepository clienteRepository;
    @EJB
    private RegiaoRepository regiaoRepository;

    

    @Test(expected = EJBTransactionRolledbackException.class)
    public void deveNaoInserirPorFaltaDeLimite() {
        ContaCorrente c = new ContaCorrente();
        c.setCliente(criarClienteCorreto());
        c.setStatusConta(StatusConta.APROVADA);
        c.setTipoConta(TipoConta.POUPANCA);
        c = repository.save(c);
        Assert.assertNotNull(c.getDigitoVerificador());
    }

    @Test(expected = EJBTransactionRolledbackException.class)
    public void deveNaoInserirPorFaltaDeStatusConta() {
        ContaCorrente c = new ContaCorrente();
        c.setCliente(criarClienteCorreto());
        c.setLimite(BigDecimal.TEN);
        c.setTipoConta(TipoConta.POUPANCA);
        c = repository.save(c);
        Assert.assertNotNull(c.getDigitoVerificador());
    }

    @Test(expected = EJBTransactionRolledbackException.class)
    public void deveNaoInserirPorFaltaDeTipoConta() {
        ContaCorrente c = new ContaCorrente();
        c.setCliente(criarClienteCorreto());
        c.setLimite(BigDecimal.TEN);
        c.setStatusConta(StatusConta.APROVADA);
        c = repository.save(c);
        Assert.assertNotNull(c.getDigitoVerificador());
    }
    
    @Test(expected = EJBTransactionRolledbackException.class)
    public void deveNaoInserirPorFaltaDeCliente() {
        ContaCorrente c = new ContaCorrente();
        c.setTipoConta(TipoConta.POUPANCA);
        c.setLimite(BigDecimal.TEN);
        c.setStatusConta(StatusConta.APROVADA);
        c = repository.save(c);
        Assert.assertNotNull(c.getDigitoVerificador());
    }

    private Cliente criarClienteCorreto() {
        Regiao regiao = new Regiao();
        regiao.setNome(_NOME_VALIDO);
        regiao = regiaoRepository.save(regiao);

        Cliente cliente = new Cliente();
        cliente.setNome(_NOME_VALIDO);
        cliente.setEmail(_EMAIL_VALIDO);
        cliente.setCep(_CEP_VALIDO);
        cliente.setDocumento(_DOCUMENTO_VALIDO);
        cliente.setRegiao(regiao);

        return clienteRepository.save(cliente);
    }

}
