package br.com.batistacorp.agendamento.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.batistacorp.agendamento.model.Agencia;
import br.com.batistacorp.agendamento.model.Agendamento;

public interface AgendamentoDAO extends CrudRepository<Agendamento, Integer> {
	
	public ArrayList<Agendamento> findAllByDataAgendamento(LocalDate data);
	public ArrayList<Agendamento> findAllByNomeClienteContains(String nome);
	public ArrayList<Agendamento> findAllByAgencia(Agencia agencia);
	public ArrayList<Agendamento> findAllByDataAgendamentoAndAgencia(LocalDate data, Agencia agencia); 
	public ArrayList<Agendamento> findAllByDataAgendamentoAndNomeClienteContains(LocalDate data, String cliente);
    public ArrayList<Agendamento> findAllByAgenciaAndNomeClienteContains(Agencia agencia, String cliente);
    public ArrayList<Agendamento> findAllByAgenciaAndDataAgendamentoAndNomeClienteContains(Agencia agencia, LocalDate data, String cliente);

}
