package com.company.repuestos1.response;

import java.util.List;

import com.company.repuestos1.model.Auditoria;

import lombok.Data;

@Data
public class AuditoriaResponse {
	private List<Auditoria> auditoria;
}
