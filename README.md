# 📚 IronLibrary – Sistema de Gestión de Biblioteca en Java

Este proyecto es una aplicación de consola desarrollada en Java que permite gestionar una biblioteca académica. A través de un menú interactivo, los usuarios pueden registrar libros y autores, emitir préstamos a estudiantes, buscar libros por distintos criterios y consultar información sobre préstamos activos.

## 🧩 Estructura del Proyecto

El sistema se basa en cuatro clases principales:

- **Book**: Representa un libro con atributos como `isbn`, `title`, `category` y `quantity`.
- **Author**: Representa un autor con atributos como `authorId`, `name`, `email` y una relación uno a uno con un `Book`.
- **Student**: Representa a un estudiante con atributos como `usn` (número único de estudiante) y `name`.
- **Issue**: Representa un préstamo de libro con atributos como `issueId`, `issueDate`, `returnDate` y relaciones uno a uno con un `Student` y un `Book`.

## 🖥️ Funcionalidades

- Agregar libros con sus autores.
- Buscar libros por título, categoría o autor.
- Listar todos los libros junto a su autor.
- Emitir libros a estudiantes (gestión de préstamos).
- Consultar los libros prestados a un estudiante por su USN.
- Validación de entradas y control de errores.
- Persistencia de datos con base de datos SQL.

## ⚙️ Requisitos Previos

- **Java**
- **Maven**
- **MySQL** (u otro sistema de gestión de base de datos SQL compatible)
- **IDE recomendado**: IntelliJ IDEA

## 🧪 Configuración de Base de Datos

**1**. Crea una base de datos en MySQL llamada `ironlibrary`.

   ```sql
   CREATE DATABASE ironlibrary;
   ```


**2.Configura tu conexión en el archivo src/main/resources/application.properties (si usas Spring) o directamente en el 
código JDBC:**


``` properties

jdbc.url=jdbc:mysql://localhost:3306/ironlibrary
jdbc.user=tu_usuario
jdbc.password=tu_contraseña
```
**🚀 Ejecución del Proyecto**
Clonar el repositorio:

```bash

git clone https://github.com/franciscofarrando/homework-java-ironlibrary.git
cd homework-java-ironlibrary
Compilar y ejecutar (usando Maven):
```


```bash
mvn clean install
mvn exec:java
Asegúrate de tener en el pom.xml configurado el plugin exec-maven-plugin con el mainClass correcto.
```
## ✅  Pruebas 
**Este proyecto contiene pruebas unitarias para métodos relevantes. Para ejecutarlas:**


```bash
    mvn test
```


## 👨🏻‍💻 Autores
- **Francisco  Farrando** - [franciscofarrando]
- **Javier  Moneo** - [AsterixKo]
- **Juan Jose Franco** - [DevJerryX]