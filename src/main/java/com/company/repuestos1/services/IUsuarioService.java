package com.company.repuestos1.services;

import org.springframework.http.ResponseEntity;

import com.company.repuestos1.model.Usuario;
import com.company.repuestos1.response.UsuarioResponseRest;


public interface IUsuarioService {
	public ResponseEntity<UsuarioResponseRest> search();
	public ResponseEntity<UsuarioResponseRest> save(Usuario usuario);
	public ResponseEntity<UsuarioResponseRest> searchByEmail(String email);
	public ResponseEntity<UsuarioResponseRest> searchById(Long id);
	public ResponseEntity<UsuarioResponseRest> update(Usuario usuario, Long id);
}
