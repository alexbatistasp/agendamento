package br.com.batistacorp.agendamento.controller;

import java.time.LocalDate;
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
	public ArrayList<Agendamento> buscar(@RequestParam int mode, @RequestParam String nome,
			@RequestParam LocalDate data, @RequestParam int idAgencia) {

		ArrayList<Agendamento> lista = null;
		switch (mode) {
		case 0:
			lista = (ArrayList<Agendamento>) dao.findAll();
			break;
		case 1:
		case 2:
			lista = dao.findAllByDataAgendamento(data);
			break;
		case 3:
		case 4:
			lista = dao.findAllByNomeClienteContains(nome);
			break;
		case 5:
		case 6:
		case 7:
		}

		return lista;
	}

}
