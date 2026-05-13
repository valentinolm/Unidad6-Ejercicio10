# Casos de Prueba - Producto API

## Feature: Gestión de productos

Como usuario del sistema  
Quiero poder gestionar productos mediante una API REST  
Para poder almacenarlos y consultarlos de forma paginada

---

## Escenario 1: Crear un producto correctamente
Dado un producto con nombre "Mouse", precio 1500 y stock 5

Cuando se envia una peticion POST a /productos

Entonces el sistema debe responder con estado 201

Y el producto debe ser guardado correctamente

Y la respuesta debe contener el nombre "Mouse"

## Escenario 2: Listar productos paginados
Dado que existen productos cargados en el sistema

Cuando se envia una peticion GET a /productos?page=0&size=2

Entonces el sistema debe responder con estado 200

Y debe devolver maximo 2 productos

Y la respuesta debe contener el campo content

## Escenario 3: Verificar guardado de producto
Dado un producto valido

Cuando el servicio guarda el producto

Entonces el repositorio debe ser invocado una vez

Y el producto debe ser persistido

## Escenario 4: Verificar listado paginado en servicio
Dado que existen productos en el repositorio

Cuando se solicita la página 0 con tamaño 2

Entonces el servicio debe retornar una página con 2 elementos

Y debe invocar al repositorio con PageRequest correcto

## Escenario 5: Endpoint POST retorna 201
Dado un producto valido en JSON

Cuando se realiza POST /productos

Entonces el sistema responde con status 201 Created

## Escenario 6: Endpoint GET retorna datos correctos
Dado que existen productos cargados

Cuando se realiza GET /productos?page=0&size=2

Entonces el sistema responde con status 200

Y la respuesta contiene productos en formato JSON