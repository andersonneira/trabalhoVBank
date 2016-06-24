package br.com.rp.repository.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import br.com.rp.domain.Funcionario;
import br.com.rp.repository.FuncionarioRepository;

@Stateless
public class FuncionarioRepositoryImpl extends AbstractRepositoryImpl<Funcionario> implements FuncionarioRepository {
	public FuncionarioRepositoryImpl(){
		super(Funcionario.class);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Funcionario save(Funcionario object) {
		return super.save(object);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Funcionario findById(Long Id) {
		return super.findById(Id);
	}
}
