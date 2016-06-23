package br.com.rp.repository;

import javax.ejb.EJB;
import org.junit.Assert;
import org.junit.Test;
import br.com.rp.AbstractTest;
import br.com.rp.domain.Funcionario;

public class FuncionarioRepositoryTest extends AbstractTest{
	
	@EJB
    private FuncionarioRepository repository;
	
	@Test
    public void deveInserirUmFuncionario() {
//        Funcionario fo = new Funcionario("Marcos","marcos123","009.794.029-13");
//        fo = repository.save(fo);
//        Assert.assertNotNull(fo.getId());
    }

}
