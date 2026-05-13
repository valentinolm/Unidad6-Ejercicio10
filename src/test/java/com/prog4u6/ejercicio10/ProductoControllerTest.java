package com.prog4u6.ejercicio10;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void guardarProductoTest() throws Exception {
        ProductoModel producto = new ProductoModel("Mouse", 1500, 5);

        when(productoService.guardar(org.mockito.ArgumentMatchers.any()))
                .thenReturn(producto);

        mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Mouse"))
                .andExpect(jsonPath("$.precio").value(1500.0));
    }

    @Test
    void listarProductosTest() throws Exception {
        List<ProductoModel> lista = List.of(
                new ProductoModel("Mouse", 1500, 5),
                new ProductoModel("Teclado", 3000, 3)
        );

        when(productoService.listar(0, 2))
                .thenReturn(new PageImpl<>(lista));

        mockMvc.perform(get("/productos?page=0&size=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombre").value("Mouse"))
                .andExpect(jsonPath("$.content[1].nombre").value("Teclado"));
    }
}