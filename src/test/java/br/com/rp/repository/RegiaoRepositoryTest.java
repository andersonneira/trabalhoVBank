/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rp.repository;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Regiao;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author anderson
 */
public class RegiaoRepositoryTest extends AbstractTest {

    @EJB
    private RegiaoRepository repository;

    @Test(expected = EJBTransactionRolledbackException.class)
    public void deveNaoInserirPorFaltaDeCaracteresDoNome() {
        Regiao sp = new Regiao();
        sp.setNome("1");
        sp.setCepFinal("86800-005");
        sp.setCepFinal("86800-006");
        repository.save(sp);
    }

    @Test
    public void deveInserir() {
        Regiao sp = new Regiao();
        sp.setNome("Norte");
        sp.setCepFinal("86800-005");
        sp.setCepInicial("86800-006");
        sp = repository.save(sp);
        Assert.assertNotNull(sp.getId());
    }
    
    @Test(expected = EJBTransactionRolledbackException.class)
    public void naoDeveInserirPorFormatoCepIncialInvalido() {
        Regiao sp = new Regiao();
        sp.setNome("Norte");
        sp.setCepInicial("868.0.0005");
        sp.setCepFinal("86800-005");
        sp = repository.save(sp);
        Assert.assertNotNull(sp.getId());
    }
    
    @Test(expected = EJBTransactionRolledbackException.class)
    public void naoDeveInserirPorFormatoCepFinalInvalido() {
        Regiao sp = new Regiao();
        sp.setNome("Norte");
        sp.setCepInicial("86800-005");
        sp.setCepFinal("868.0.0005");
        sp = repository.save(sp);
        Assert.assertNotNull(sp.getId());
    }
    
    @Test(expected = EJBTransactionRolledbackException.class)
    public void naoDeveInserirPorFaltaDeCepInicial() {
        Regiao sp = new Regiao();
        sp.setNome("Norte");
        sp.setCepFinal("86800-005");
        sp = repository.save(sp);
        Assert.assertNotNull(sp.getId());
    }
    
    @Test(expected = EJBTransactionRolledbackException.class)
    public void naoDeveInserirPorFaltaDeCepFinal() {
        Regiao sp = new Regiao();
        sp.setNome("Norte");
        sp.setCepInicial("86800-005");
        sp = repository.save(sp);
        Assert.assertNotNull(sp.getId());
    }
    
}
