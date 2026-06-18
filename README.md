# 📋 API de Gestión de Tareas

API RESTful para gestión de tareas con autenticación, documentación Swagger y consumo de APIs externas.

## 🚀 Tecnologías

- **Java 17**
- **Spring Boot 3.2**
- **Spring Data JPA** (Hibernate)
- **H2 Database** (en memoria)
- **Spring Validation**
- **Spring Cloud OpenFeign**
- **SpringDoc OpenAPI** (Swagger)
- **Maven**

## 📦 Endpoints principales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/tareas` | Obtener todas las tareas |
| POST | `/tareas` | Crear una nueva tarea |
| GET | `/tareas/{id}` | Obtener tarea por ID |
| PUT | `/tareas/{id}` | Actualizar tarea |
| DELETE | `/tareas/{id}` | Eliminar tarea |
| GET | `/tareas/urgentes` | Obtener tareas urgentes (prioridad ≥ 8) |
| GET | `/tareas/posts-externos` | Consumir API externa de ejemplo |

## 🛠️ Instalación y ejecución

### Requisitos previos
- JDK 17
- IntelliJ IDEA (o cualquier IDE)
- Git

### Pasos

1. Clonar el repositorio:
```bash
git clone https://github.com/dopplerss/tareas-api.git