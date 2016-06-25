package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.DepositoCheque;
import br.com.rp.repository.DepositoChequeRepository;

@Stateless
public class DepositoChequeRepositoryImpl extends AbstractRepositoryImpl<DepositoCheque> implements DepositoChequeRepository {

	public DepositoChequeRepositoryImpl() {
		super(DepositoCheque.class);
	}

	
}
