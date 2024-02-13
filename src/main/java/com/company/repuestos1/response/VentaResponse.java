package com.company.repuestos1.response;

import java.util.List;

import com.company.repuestos1.model.Venta;

import lombok.Data;

@Data
public class VentaResponse {
	private List<Venta> venta;
}
