/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rp.repository;

import br.com.rp.AbstractTest;
import br.com.rp.domain.ContaCorrente;

import javax.ejb.EJB;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author anderson
 */
public class ContaCorrenteRespositoryTest extends AbstractTest {

    @EJB
    private ContaCorrenteRepository repository;

    @Test
    public void deveNaoInserirPorFaltaDeCaracteresDoNome() {
        ContaCorrente c = new ContaCorrente();
        Assert.assertNotNull(c.getDigitoVerificador());
    }

}
