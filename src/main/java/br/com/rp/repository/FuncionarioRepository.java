package br.com.rp.repository;

import javax.ejb.Local;
import br.com.rp.domain.Funcionario;

@Local
public interface FuncionarioRepository extends Repository<Funcionario> {

}
