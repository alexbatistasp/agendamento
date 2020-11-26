package br.com.batistacorp.agendamento.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.batistacorp.agendamento.model.Agendamento;

public interface AgendamentoDAO extends CrudRepository<Agendamento, Integer> {

}
