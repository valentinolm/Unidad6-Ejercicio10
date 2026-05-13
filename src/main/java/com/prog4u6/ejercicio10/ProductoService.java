package com.prog4u6.ejercicio10;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ProductoModel guardar(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    public Page<ProductoModel> listar(int page, int size) {
        return productoRepository.findAll(PageRequest.of(page, size));
    }
}