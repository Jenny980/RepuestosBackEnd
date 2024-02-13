package com.company.repuestos1.response;

import java.util.List;

import com.company.repuestos1.model.Usuario;

import lombok.Data;

@Data
public class UsuarioResponse {
	private List<Usuario> usuario;
}
