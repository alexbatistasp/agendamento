package br.com.batistacorp.agendamento.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.batistacorp.agendamento.dao.AgendamentoDAO;
import br.com.batistacorp.agendamento.model.Agencia;
import br.com.batistacorp.agendamento.model.Agendamento;

@RestController
@CrossOrigin(origins = "*")
public class AgendamentoController {

	@Autowired
	private AgendamentoDAO dao;

	@PostMapping("/novoagendamento")
	public ResponseEntity<Agendamento> novoAgendamento(@RequestBody Agendamento novo) {
		try {
			dao.save(novo);
			return ResponseEntity.status(201).body(novo);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/agendamentos")
	public ArrayList<Agendamento> buscar(@RequestParam int mode, 
			                             @RequestParam String nome, 
			                             @RequestParam String dataAg, 
			                             @RequestParam int idAgencia){
		Agencia ag;
		ArrayList<Agendamento> lista=null;
		LocalDate data;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		switch(mode) {
		case 0:
			lista = (ArrayList<Agendamento>)dao.findAll();
			break;
		case 1:
			ag = new Agencia();
			ag.setId(idAgencia);
			lista = dao.findAllByAgencia(ag);
			break;
		case 2:
			data = LocalDate.parse(dataAg, formatter); 
			lista = dao.findAllByDataAgendamento(data);
			break;
		case 3:
			ag = new Agencia();
			ag.setId(idAgencia);
			data = LocalDate.parse(dataAg, formatter);
			lista = dao.findAllByDataAgendamentoAndAgencia(data, ag);
			break;
		case 4:
			lista = dao.findAllByNomeClienteContains(nome);
			break;
		case 5:
			ag = new Agencia();
			ag.setId(idAgencia);
			lista = dao.findAllByAgenciaAndNomeClienteContains(ag, nome);
			break;
		case 6:
			data = LocalDate.parse(dataAg, formatter);
			lista = dao.findAllByDataAgendamentoAndNomeClienteContains(data, nome);
			break;
		case 7:
			ag = new Agencia();
			ag.setId(idAgencia);
			data = LocalDate.parse(dataAg, formatter);
			lista = dao.findAllByAgenciaAndDataAgendamentoAndNomeClienteContains(ag, data, nome);
			break;
		}
				
		return lista;
	}

}
