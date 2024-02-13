package com.company.repuestos1.response;

import java.util.List;
import com.company.repuestos1.model.Producto;
import lombok.Data;

@Data
public class ProductoResponse {
	private List<Producto> producto;
}
