/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rp.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author anderson
 */
public class ContaCorrenteTeste {

    private ContaCorrente contaCorrente;

    @Before
    public void inciarTeste() {
        contaCorrente = new ContaCorrente();
    }

    @Test
    public void deveTerUmDigitoVerificador() {
        Assert.assertEquals(1, contaCorrente.getDigitoVerificador().length());
    }

    @Test
    public void deveTer5DigitosNoNumeroDaConta() {
        Assert.assertEquals(5, contaCorrente.getNumero().length());
    }

}
