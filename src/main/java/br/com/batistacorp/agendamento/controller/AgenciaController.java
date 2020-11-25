package br.com.batistacorp.agendamento.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.batistacorp.agendamento.dao.AgenciaDAO;
import br.com.batistacorp.agendamento.model.Agencia;

@RestController
public class AgenciaController {
	
	@Autowired
	private AgenciaDAO dao;
	@GetMapping("/agencias")
	public ArrayList<Agencia> reperarTodas(){
		ArrayList<Agencia> lista; //declarei a lista
		//lista = (ArrayList<Agencia>)dao.findAll(); //Recupera registros do banco
		//lista = (ArrayList<Agencia>)dao.findAllByOrderByNome();
		lista = (ArrayList<Agencia>)dao.findAllByOrderByIdDesc();
		return lista;
		
	}

}
