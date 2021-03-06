package br.com.rp.repository;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.com.rp.domain.Agendamento;

@Local
public interface AgendamentoRepository extends Repository<Agendamento> {

	public List<Agendamento> findMovimentacaoRealizadaByContaCorrenteId(Long contaCorrenteId);
	
	public List<Agendamento> findMovimentacaoRealizadaByContaCorrenteIdByDataInicialByDataFinal(Long contaCorrenteId, Date dataInicial, Date dataFinal);
	
	public List<Agendamento> findAgendamentosPendentesByContaCorrenteId(Long contaCorrenteId);

	public List<Agendamento> findAgendamentosPendentesByDataFinal(Date dataFinal);
	
	public List<Agendamento> findAgendamentosPendentesByContaCorrenteIdByDataFinal(Long contaCorrenteId, Date dataFinal);
	
	public List<Agendamento> findAgendamentosRealizadosNaoEnviadosAoBacenByDataFinal(Date dataFinal);

	public List<Agendamento> findAgendamentosRealizadosNaoEnviadosAMatrizByDataFinal(Date dataFinal);
}
