package com.formacionspring.apirest.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
		String nombreImageAnterior = productoDelete.getImagen();
		
		//Si el producto tiene una imagen, se elimina antes de eliminar el producto
		if (nombreImageAnterior != null && nombreImageAnterior.length() > 0) {
			Path rutaArchivoAnterior = Paths.get("uploads").resolve(nombreImageAnterior).toAbsolutePath();
			File archivoImagenAnterior = rutaArchivoAnterior.toFile();
			if (archivoImagenAnterior.exists() && archivoImagenAnterior.canRead()) {
				archivoImagenAnterior.delete();
			}
		}
		
		servicio.delete(id);
		
		return productoDelete;
	}
	
	@PostMapping("producto/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String,Object> response = new HashMap<>();
		
		Producto producto = servicio.findById(id);
		
		if (!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
				
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			} 
			
			String nombreImageAnterior = producto.getImagen();
			
			if (nombreImageAnterior != null && nombreImageAnterior.length() > 0) {
				Path rutaArchivoAnterior = Paths.get("uploads").resolve(nombreImageAnterior).toAbsolutePath();
				File archivoImagenAnterior = rutaArchivoAnterior.toFile();
				if (archivoImagenAnterior.exists() && archivoImagenAnterior.canRead()) {
					archivoImagenAnterior.delete();
				}
			}
			
			producto.setImagen(nombreArchivo);
			servicio.save(producto);
			
			response.put("mensaje", "Se ha subido correctamente la imagen: " + nombreArchivo);
			response.put("producto", producto);
			
		}
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/uploads/imagen/{nombreImagen:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable String nombreImagen) {
		Path rutaImagen = Paths.get("uploads").resolve(nombreImagen).toAbsolutePath();
		
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaImagen.toUri());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error: No se puede cargar la imagen " + nombreImagen);
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename:\" " + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
}



