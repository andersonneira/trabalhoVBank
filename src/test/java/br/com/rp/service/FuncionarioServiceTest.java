package br.com.rp.service;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;
import br.com.rp.AbstractTest;
import br.com.rp.domain.Funcionario;
import br.com.rp.services.FuncionarioService;

public class FuncionarioServiceTest extends AbstractTest {
	@EJB
	private FuncionarioService service;

	@Test
	@UsingDataSet("db/funcionario.xml")
	public void deveRetornar2Funcionarios() {
		List<Funcionario> funcionarios = service.getAllFuncionarios();
		Assert.assertEquals(2, funcionarios.size());
	}
	@Test
	@UsingDataSet("db/funcionario.xml")
	public void deveRetornarUmFuncionarioPeloId() {
		Long id = new Long(1);
		Funcionario funcionario = service.getFuncionarioById(id);
		String marcos = new String("marcos");
		Assert.assertEquals(marcos, funcionario.getNome());
	}
	
	
}
