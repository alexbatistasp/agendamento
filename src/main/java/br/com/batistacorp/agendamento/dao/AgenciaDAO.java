package br.com.batistacorp.agendamento.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.batistacorp.agendamento.model.Agencia;

public interface AgenciaDAO extends CrudRepository<Agencia, Integer> {

	public ArrayList<Agencia> findAllByOrderByNome();
	public ArrayList<Agencia> findAllByOrderByIdDesc();
}
