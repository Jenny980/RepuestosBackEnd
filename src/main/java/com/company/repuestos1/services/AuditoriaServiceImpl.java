package com.company.repuestos1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.repuestos1.dao.IAuditoriaDao;
import com.company.repuestos1.model.Auditoria;
import com.company.repuestos1.response.AuditoriaResponseRest;

@Service
public class AuditoriaServiceImpl implements IAuditoriaService{

	@Autowired
	private IAuditoriaDao auditoriaDao;

	@Override
	public ResponseEntity<AuditoriaResponseRest> search() {
		AuditoriaResponseRest response = new AuditoriaResponseRest();
		try {
			List<Auditoria> auditoria = (List<Auditoria>) auditoriaDao.findAll();
			response.getAuditoriaResponse().setAuditoria(auditoria);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<AuditoriaResponseRest> save(Auditoria auditoria) {
		AuditoriaResponseRest response = new AuditoriaResponseRest();
		List<Auditoria> list = new ArrayList<>();
		try {
			Auditoria proveedorSave = auditoriaDao.save(auditoria);
			if(proveedorSave != null) {
				list.add(proveedorSave);
				response.getAuditoriaResponse().setAuditoria(list);
				response.setMetadata("Respuesta ok", "00", "Auditoria guardado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Proveedor no guardado");
				return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al guardar Auditoria");
			e.getStackTrace();
			return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AuditoriaResponseRest> searchById(Long id) {
		AuditoriaResponseRest response = new AuditoriaResponseRest();
		List<Auditoria> list = new ArrayList<>();
		try {
			Optional<Auditoria> usuario = auditoriaDao.findById(id);
			
			if(usuario.isPresent()) {
				list.add(usuario.get());
				response.getAuditoriaResponse().setAuditoria(list);
				response.setMetadata("Respuesta ok", "00", "Auditoria encontrado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Auditoria no encontrado");
				return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<AuditoriaResponseRest> update(Auditoria auditoria, Long id) {
		AuditoriaResponseRest response = new AuditoriaResponseRest();
		List<Auditoria> list = new ArrayList<>();
		try {
			Optional<Auditoria> auditoriaSearch = auditoriaDao.findById(id);
			if(auditoriaSearch.isPresent()) {
				auditoriaSearch.get().setAccion(auditoria.getAccion());
				auditoriaSearch.get().setUsuario(auditoria.getUsuario());
				auditoriaSearch.get().setIdUsuario(auditoria.getIdUsuario());
				
				Auditoria usuarioToUpdate = auditoriaDao.save(auditoriaSearch.get());
				
				if(usuarioToUpdate != null) {
					list.add(usuarioToUpdate);
					response.getAuditoriaResponse().setAuditoria(list);
					response.setMetadata("Respuesta ok", "00", "Auditoria actualizado");
				} else {
					response.setMetadata("Respuesta nook", "-1", "Auditoria no actualizado");
					return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}else {
				response.setMetadata("Respuesta nook", "-1", "Auditoria no encontrado");
				return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al actualizar Auditoria");
			e.getStackTrace();
			return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<AuditoriaResponseRest> searchByIdUsuario(Long id) {
		AuditoriaResponseRest response = new AuditoriaResponseRest();
		List<Auditoria> list = new ArrayList<>();
		List<Auditoria> listAux = new ArrayList<>();
		try {
			
			listAux = auditoriaDao.findAllByidUsuario(id);
			
			if(listAux.size() > 0) {
				
				listAux.stream().forEach((p) -> {
					list.add(p);
				});
				
				response.getAuditoriaResponse().setAuditoria(listAux);
				response.setMetadata("Respuesta ok", "00", "Auditoria encontrado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Auditoria no encontrado");
				return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al buscar por id usuario");
			e.getStackTrace();
			return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<AuditoriaResponseRest> searchByUsuario(String usuario) {
		AuditoriaResponseRest response = new AuditoriaResponseRest();
		List<Auditoria> list = new ArrayList<>();
		List<Auditoria> listAux = new ArrayList<>();
		try {
			
			listAux = auditoriaDao.findByusuarioContainingIgnoreCase(usuario);
			
			if(listAux.size() > 0) {
				
				listAux.stream().forEach((p) -> {
					list.add(p);
				});
				
				response.getAuditoriaResponse().setAuditoria(listAux);
				response.setMetadata("Respuesta ok", "00", "Auditoria encontrado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Auditoria no encontrado");
				return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al buscar por nombre");
			e.getStackTrace();
			return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<AuditoriaResponseRest>(response, HttpStatus.OK);
	}

}


