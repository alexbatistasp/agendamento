package br.com.batistacorp.agendamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.batistacorp.agendamento.dao.UsuarioDAO;
import br.com.batistacorp.agendamento.model.Usuario;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioDAO dao;
	
	/*  O método logarUsuario vai receber um JSON correspondente ao objeto Usuario
	 *  vindo no corpo da requisicao através do método POST
	 *  Este método POST é o que permite ao FRONT END enviar dados para o BACK END
	 */
//	@PostMapping("/login")
//	public Usuario logarUsuario(@RequestBody Usuario userLogin) {
//		//Usuario retorno = dao.findByEmailAndSenha(userLogin.getEmail(), userLogin.getSenha());
//		Usuario resp = dao.findByEmailOrRacf(userLogin.getEmail(), userLogin.getRacf());
//		
//		//return retorno;
		
	    @CrossOrigin(origins="*")
		@PostMapping("/login")
		public ResponseEntity<Usuario> logarUsuario(@RequestBody Usuario userLogin) {
			Usuario resp = dao.findByEmailOrRacf(userLogin.getEmail(), userLogin.getRacf());
			// encontramos o usuário no banco
			if (resp != null) {
				if (resp.getSenha().equals(userLogin.getSenha())) { // senha confere
					return ResponseEntity.ok(resp);
				}
				else {
					return ResponseEntity.status(401).build(); // se a senha não confere, retorno 401 - Unauthorized
				}
			}
			else {
				return ResponseEntity.notFound().build(); // criamos uma mensagem de resposta 404
			}
			
		}
	}

