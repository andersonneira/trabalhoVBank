/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rp.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cliente;
import br.com.rp.domain.ContaCorrente;
import br.com.rp.domain.Regiao;
import br.com.rp.enums.StatusConta;
import br.com.rp.enums.TipoConta;

/**
 *
 * @author anderson
 */
//@CleanupUsingScript(value="script deleçao", phase=TestExecutionPhase.AFTER)
public class ContaCorrenteRespositoryTest extends AbstractTest {

    private static final String _NOME_VALIDO = "Teste";
    private static final String _EMAIL_VALIDO = "teste@gmail.com";
    private static final String _CEP_VALIDO = "86800-005";

    @EJB
    private ContaCorrenteRepository repository;
    @EJB
    private ClienteRepository clienteRepository;
    @EJB
    private RegiaoRepository regiaoRepository;

    private static final BigDecimal _LIMITE_CONTA_VALIDO = new BigDecimal(300);
	private static final StatusConta _STATUS_CONTA_VALIDO = StatusConta.APROVADA;
	private static final TipoConta _TIPO_CONTA_VALIDO = TipoConta.CORRENTE;
	
	@Test
	public void deveListarContaPorClienteId() {
		Regiao regiao = new Regiao();
		regiao.setNome(_NOME_VALIDO);
		regiao.setCepFinal("86800-005");
		regiao.setCepInicial("86800-005");
		regiao = regiaoRepository.save(regiao);

		Cliente cliente = new Cliente();
		cliente.setNome(_NOME_VALIDO);
		cliente.setEmail(_EMAIL_VALIDO);
		cliente.setCep(_CEP_VALIDO);
		cliente.setDocumento(new GerarCPF().geraCPF());
		cliente.setRegiao(regiao);

		clienteRepository.save(cliente);

		ContaCorrente c = new ContaCorrente();
		c.setLimite(_LIMITE_CONTA_VALIDO);
		c.setStatusConta(_STATUS_CONTA_VALIDO);
		c.setTipoConta(_TIPO_CONTA_VALIDO);
		c.setCliente(cliente);
		repository.save(c);
		Assert.assertNotNull(c.getDigitoVerificador());

		List<ContaCorrente> cc = repository.findByClienteId(c.getCliente().getId());
		Assert.assertNotEquals(0, cc.size());
	}
	
	@Test
	public void deveListarContaPorDocumentoCliente() {
		Regiao regiao = new Regiao();
		regiao.setNome(_NOME_VALIDO);
		regiao.setCepFinal("86800-005");
		regiao.setCepInicial("86800-005");
		regiao = regiaoRepository.save(regiao);

		Cliente cliente = new Cliente();
		cliente.setNome(_NOME_VALIDO);
		cliente.setEmail(_EMAIL_VALIDO);
		cliente.setCep(_CEP_VALIDO);
		cliente.setDocumento(new GerarCPF().geraCPF());
		cliente.setRegiao(regiao);

		clienteRepository.save(cliente);

		ContaCorrente c = new ContaCorrente();
		c.setLimite(_LIMITE_CONTA_VALIDO);
		c.setStatusConta(_STATUS_CONTA_VALIDO);
		c.setTipoConta(_TIPO_CONTA_VALIDO);
		c.setCliente(cliente);
		repository.save(c);
		Assert.assertNotNull(c.getDigitoVerificador());

		List<ContaCorrente> cc = repository.findByClienteDocumento(c.getCliente().getDocumento());
		Assert.assertNotEquals(0, cc.size());
	}
	
    @Test
    public void deveListarContaPorNumeroPorDigitoVerificador() {
		Regiao regiao = new Regiao();
		regiao.setNome(_NOME_VALIDO);
		regiao.setCepFinal("86800-005");
		regiao.setCepInicial("86800-005");
		regiao = regiaoRepository.save(regiao);

		Cliente cliente = new Cliente();
		cliente.setNome(_NOME_VALIDO);
		cliente.setEmail(_EMAIL_VALIDO);
		cliente.setCep(_CEP_VALIDO);
		cliente.setDocumento(new GerarCPF().geraCPF());
		cliente.setRegiao(regiao);

		clienteRepository.save(cliente);

		ContaCorrente c = new ContaCorrente();
		c.setLimite(_LIMITE_CONTA_VALIDO);
		c.setStatusConta(_STATUS_CONTA_VALIDO);
		c.setTipoConta(_TIPO_CONTA_VALIDO);
		c.setCliente(cliente);
		repository.save(c);
		Assert.assertNotNull(c.getDigitoVerificador());

		ContaCorrente cc = repository.findByContaByDigitoVerificador(c.getNumero(), c.getDigitoVerificador());
		Assert.assertEquals(c.getId(), cc.getId());
    }

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
        regiao.setCepFinal("86800-005");
		regiao.setCepInicial("86800-005");
        regiao = regiaoRepository.save(regiao);

        Cliente cliente = new Cliente();
        cliente.setNome(_NOME_VALIDO);
        cliente.setEmail(_EMAIL_VALIDO);
        cliente.setCep(_CEP_VALIDO);
        cliente.setDocumento(new GerarCPF().geraCPF());
        cliente.setRegiao(regiao);

        return clienteRepository.save(cliente);
    }

}
