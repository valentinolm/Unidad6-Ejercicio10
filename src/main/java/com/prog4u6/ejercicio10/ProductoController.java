package com.prog4u6.ejercicio10;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoModel> guardarProducto(@RequestBody ProductoModel productoModel) {
        return ResponseEntity.status(201).body(productoService.guardar(productoModel));
    }

    @GetMapping
    public Page<ProductoModel> obtenerProductos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {
        return productoService.listar(page, size);
    }
}