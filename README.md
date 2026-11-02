# Sistema de Órdenes para Lavandería (PF-08)

**Estudiante:** [Nombre completo]
**Carné:** [Número de carné]

## Descripción del problema
Una lavandería necesita registrar sus tipos de servicio y dar seguimiento a cada orden.
El sistema calcula el costo según el peso de la ropa y muestra las órdenes pendientes de entrega.

## Tecnologías
- Java 17
- Spring Boot (Web, Data JPA, Validation)
- PostgreSQL
- Maven
- Lombok
- HTML, CSS, JavaScript y Bootstrap

## Estructura del sistema
- `controller`: recibe las peticiones HTTP
- `service`: reglas de negocio
- `repository`: acceso a la base de datos
- `entity`: tablas mapeadas con JPA
- `dto`: objetos de entrada y salida de la API
- `exception`: manejo centralizado de errores
- `database`: scripts SQL
- `src/main/resources/static`: interfaz web

## Requisitos
- JDK 17 o superior
- Maven
- PostgreSQL
- IntelliJ IDEA (opcional)

## Configuración de PostgreSQL
1. Crear la base de datos `lavanderia_db`.
2. Ejecutar `database/schema.sql` y luego `database/datos.sql`.
3. Si el usuario o puerto son distintos, editar `src/main/resources/application.properties`.
4. Definir la contraseña como variable de entorno `DB_PASSWORD`
   (en IntelliJ: Run → Edit Configurations → Environment variables).

## Iniciar el backend
1. Abrir el proyecto en IntelliJ.
2. Ejecutar la clase `LavanderiaApplication`.
3. El servidor queda en http://localhost:8080

## Abrir el frontend
Con el backend en ejecución, abrir http://localhost:8080/

## Endpoints

### Tipos de servicio
| Método | Ruta | Descripción |
|---|---|---|
| POST | /api/tipos-servicio | Registrar tipo |
| GET | /api/tipos-servicio | Listar tipos |
| GET | /api/tipos-servicio/{id} | Buscar por id |
| PUT | /api/tipos-servicio/{id} | Modificar |
| DELETE | /api/tipos-servicio/{id} | Desactivar |

### Órdenes
| Método | Ruta | Descripción |
|---|---|---|
| POST | /api/ordenes | Registrar orden |
| GET | /api/ordenes | Listar órdenes |
| GET | /api/ordenes/{id} | Buscar por id |
| PUT | /api/ordenes/{id} | Modificar |
| DELETE | /api/ordenes/{id} | Eliminar |
| PATCH | /api/ordenes/{id}/estado | Cambiar estado |
| GET | /api/ordenes/estado/{estado} | Filtrar por estado |
| GET | /api/ordenes/cliente?cliente=texto | Filtrar por cliente |
| GET | /api/ordenes/listas | Órdenes listas para entrega |
| GET | /api/ordenes/total-dia | Total esperado del día |

## Reglas de negocio
1. El peso y el precio por libra deben ser mayores que cero.
2. El total se calcula multiplicando el peso por la tarifa del servicio.
3. Los estados son RECIBIDA, EN_PROCESO, LISTA y ENTREGADA, y solo se avanza al siguiente.
4. No se puede modificar ni eliminar una orden entregada.
5. No se puede usar un tipo de servicio desactivado.
6. No pueden existir dos tipos de servicio con el mismo nombre.

## Códigos de respuesta
- 200 / 201 / 204: operación correcta
- 400: datos inválidos
- 404: recurso no encontrado
- 409: registro duplicado
- 422: regla de negocio incumplida
- 500: error inesperado