package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {

    private final ProductoService productosService;

    @Autowired
    public ProductosController(ProductoService productosService) {
        this.productosService = productosService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        return new ResponseEntity<>(productosService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> getProductosByQuery(
            @RequestParam String nombre) {
        return new ResponseEntity<>(
                productosService.findByNombre(nombre), HttpStatus.OK);
    }

    @GetMapping("/cabecera")
    public ResponseEntity<String> getAgentInfo(
            @RequestHeader("User-Agent") String userAgent) {
        return new ResponseEntity<>(
                "Información del cliente (User-Agent): " + userAgent,
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(
            @PathVariable String id) {
        Producto producto = productosService.findById(id);

        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(
            @RequestBody Producto producto) {
        return new ResponseEntity<>(
                productosService.save(producto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(
            @PathVariable String id,
            @RequestBody Producto producto) {
        Producto existente = productosService.findById(id);

        if (existente != null) {
            producto.setId(id);
            return new ResponseEntity<>(
                    productosService.update(producto), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Producto> patchUpdateProducto(
            @PathVariable String id,
            @RequestBody Map<String, Object> updates) {
        Producto producto = productosService.patch(id, updates);

        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(
            @PathVariable String id) {
        if (productosService.findById(id) != null) {
            productosService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}