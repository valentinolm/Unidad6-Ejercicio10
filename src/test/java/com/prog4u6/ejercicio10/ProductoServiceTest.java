package com.prog4u6.ejercicio10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void guardarProductoTest() {
        ProductoModel producto = new ProductoModel("Mouse", 1500, 5);

        when(productoRepository.save(producto)).thenReturn(producto);

        ProductoModel resultado = productoService.guardar(producto);

        assertNotNull(resultado);
        assertEquals("Mouse", resultado.getNombre());
        verify(productoRepository).save(producto);
    }

    @Test
    void listarProductosTest() {
        List<ProductoModel> lista = List.of(
                new ProductoModel("Mouse", 1500, 5),
                new ProductoModel("Teclado", 3000, 3)
        );

        when(productoRepository.findAll(PageRequest.of(0, 2)))
                .thenReturn(new PageImpl<>(lista));

        var resultado = productoService.listar(0, 2);

        assertEquals(2, resultado.getContent().size());
        verify(productoRepository).findAll(PageRequest.of(0, 2));
    }
}