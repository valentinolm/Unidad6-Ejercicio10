package com.prog4u6.ejercicio10;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    public final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @PostMapping
    public ResponseEntity<ProductoModel> guardarProducto(@RequestBody ProductoModel productoModel) {
        ProductoModel producto = productoRepository.save(productoModel);
        return ResponseEntity.ok().body(producto);
    }

    @GetMapping
    public Iterable<ProductoModel> obtenerProductos() {
        return productoRepository.findAll();
    }
}
