package com.company.repuestos1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.repuestos1.model.Usuario;
import com.company.repuestos1.response.UsuarioResponseRest;
import com.company.repuestos1.services.IUsuarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v3")
public class UsuarioRestController {
	
	@Autowired
	private IUsuarioService service;
	
	@GetMapping("/usuarios")
	public ResponseEntity<UsuarioResponseRest> searchUsuarios(){
		ResponseEntity<UsuarioResponseRest> response = service.search();
		return response;
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<UsuarioResponseRest> save(@RequestBody Usuario usuario){
		ResponseEntity<UsuarioResponseRest> response = service.save(usuario);
		return response;
	}
	
	@GetMapping("/usuarios/filter/{email}")
	public ResponseEntity<UsuarioResponseRest> searchByEmail(@PathVariable String email){
		ResponseEntity<UsuarioResponseRest> response = service.searchByEmail(email);
		return response;
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioResponseRest> searchById(@PathVariable Long id){
		ResponseEntity<UsuarioResponseRest> response = service.searchById(id);
		return response;
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioResponseRest> update(@RequestBody Usuario usuario, @PathVariable Long id){
		ResponseEntity<UsuarioResponseRest> response = service.update(usuario, id);
		return response;
	}
}
