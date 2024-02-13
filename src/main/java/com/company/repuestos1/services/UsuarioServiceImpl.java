package com.company.repuestos1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.repuestos1.dao.IUsuarioDao;
import com.company.repuestos1.model.Usuario;
import com.company.repuestos1.response.UsuarioResponseRest;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	public ResponseEntity<UsuarioResponseRest> search() {
		UsuarioResponseRest response = new UsuarioResponseRest();
		try {
			List<Usuario> usuario = (List<Usuario>) usuarioDao.findAll();
			response.getUsuarioResponse().setUsuario(usuario);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<UsuarioResponseRest> save(Usuario usuario) {
		UsuarioResponseRest response = new UsuarioResponseRest();
		List<Usuario> list = new ArrayList<>();
		try {
			Usuario usuarioSave = usuarioDao.save(usuario);
			if(usuarioSave != null) {
				list.add(usuarioSave);
				response.getUsuarioResponse().setUsuario(list);
				response.setMetadata("Respuesta ok", "00", "Usuario guardado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Usuario no guardado");
				return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al guardar usuario");
			e.getStackTrace();
			return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UsuarioResponseRest> searchByEmail(String email) {
		UsuarioResponseRest response = new UsuarioResponseRest();
		List<Usuario> list = new ArrayList<>();
		List<Usuario> listAux = new ArrayList<>();
		try {
			
			listAux = usuarioDao.findByemailContainingIgnoreCase(email);
			
			if(listAux.size() > 0) {
				
				listAux.stream().forEach((p) -> {
					list.add(p);
				});
				
				response.getUsuarioResponse().setUsuario(list);
				response.setMetadata("Respuesta ok", "00", "Usuario encontrado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Usuario no encontrado");
				return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al buscar por email");
			e.getStackTrace();
			return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UsuarioResponseRest> searchById(Long id) {
		UsuarioResponseRest response = new UsuarioResponseRest();
		List<Usuario> list = new ArrayList<>();
		try {
			Optional<Usuario> usuario = usuarioDao.findById(id);
			
			if(usuario.isPresent()) {
				list.add(usuario.get());
				response.getUsuarioResponse().setUsuario(list);
				response.setMetadata("Respuesta ok", "00", "Usuario encontrado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Usuario no encontrado");
				return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<UsuarioResponseRest> update(Usuario usuario, Long id) {
		UsuarioResponseRest response = new UsuarioResponseRest();
		List<Usuario> list = new ArrayList<>();
		try {
			Optional<Usuario> usuarioSearch = usuarioDao.findById(id);
			if(usuarioSearch.isPresent()) {
				usuarioSearch.get().setNombre(usuario.getNombre());
				usuarioSearch.get().setApellido(usuario.getApellido());
				//usuarioSearch.get().setCc(usuario.getCc());
				usuarioSearch.get().setRol(usuario.getRol());
				usuarioSearch.get().setEmail(usuario.getEmail());
				usuarioSearch.get().setContrasena(usuario.getContrasena());
				
				Usuario usuarioToUpdate = usuarioDao.save(usuarioSearch.get());
				
				if(usuarioToUpdate != null) {
					list.add(usuarioToUpdate);
					response.getUsuarioResponse().setUsuario(list);
					response.setMetadata("Respuesta ok", "00", "Usuario actualizado");
				} else {
					response.setMetadata("Respuesta nook", "-1", "Usuario no actualizado");
					return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}else {
				response.setMetadata("Respuesta nook", "-1", "Usuario no encontrado");
				return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al actualizar usuario");
			e.getStackTrace();
			return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK);
	}

}
