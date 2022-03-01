package com.formacionspring.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspring.apirest.entity.Producto;
import com.formacionspring.apirest.service.ProductoService;

@RestController
@RequestMapping("/api")
public class ProductoRestController {

	@Autowired
	private ProductoService servicio;
	
	@GetMapping("/productos")
	public List<Producto> index() {
		return servicio.findAll();
	}
	
	@GetMapping("/productos/{id}")
	public Producto getById(@PathVariable Long id) {
		return servicio.findById(id);
	}
	
	@PostMapping("/productos/nuevo")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto saveProducto(@RequestBody Producto producto) {
		return servicio.save(producto);
	}
	
	@PutMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto updateProducto(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoUpdate = servicio.findById(id);
		
		productoUpdate.setCodigo(producto.getCodigo());
		productoUpdate.setTipo(producto.getTipo());
		productoUpdate.setCantidad(producto.getCantidad());
		productoUpdate.setPrecio(producto.getPrecio());
		productoUpdate.setMarca(producto.getMarca());
		productoUpdate.setFecha_ingreso(producto.getFecha_ingreso());
		productoUpdate.setDescripcion(producto.getDescripcion());
		
		return servicio.save(productoUpdate);
	}
	
	@DeleteMapping("/productos/{id}")
	public Producto deleteProducto(@PathVariable Long id) {
		Producto productoDelete = servicio.findById(id);
		servicio.delete(id);
		return productoDelete;
	}
}



