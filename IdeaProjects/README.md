## 🌟 Descripción del Proyecto

**Happy Feet Veterinaria** es un sistema completo de gestión para clínicas veterinarias desarrollado en Java con arquitectura MVC. El sistema permite administrar todas las operaciones de una veterinaria de manera eficiente y organizada.

## 🏗️ Arquitectura del Proyecto
src/main/java/org/example/
├── 📁 controller/ # Controladores de la aplicación
├── 📁 model/entities/ # Entidades del dominio
├── 📁 repository/ # Acceso a datos (DAO Pattern)
├── 📁 service/ # Lógica de negocio
├── 📁 view/ # Interfaz de usuario
├── 🔧 DatabaseConnection.java
└── 🚀 Main.java

text

## 📋 Módulos del Sistema

### 🧑‍💼 Gestión de Dueños
- Registro y actualización de dueños de mascotas
- Búsqueda y listado de dueños
- Gestión completa de información personal

### 🐕 Gestión de Mascotas 
- Registro de mascotas con datos completos
- Asociación con dueños
- Historial de mascotas

### 📅 Gestión de Citas
- Programación de citas médicas
- Consulta de citas por mascota
- Cancelación y confirmación de citas

### 🏥 Gestión de Historial Médico
- Registro de historiales médicos
- Seguimiento de tratamientos
- Consulta de historiales por mascota

### 🧾 Gestión de Facturas
- Generación de facturas
- Cálculo automático de totales
- Consulta y listado de facturas

### 🛒 Gestión de Items de Factura
- Detalle de servicios y productos en facturas
- Gestión de items individuales

### 📦 Gestión de Inventario
- Control de stock de productos
- Alertas de productos bajos en stock
- Actualización de inventario

## 🛠️ Tecnologías Utilizadas

- **Java 17** - Lenguaje de programación
- **Maven** - Gestión de dependencias
- **MySQL** - Base de datos (configurable)
- **JDBC** - Conexión a base de datos
- **Patrón MVC** - Arquitectura del sistema
- **DAO Pattern** - Acceso a datos

## 📥 Instalación y Configuración

### Prerrequisitos
- Java JDK 17 o superior
- Maven 3.8 o superior
- MySQL 8.0 o superior

### Pasos de instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/happy-feet-veterinaria.git
   cd happy-feet-veterinaria
Configurar la base de datos

sql
-- Ejecutar los scripts SQL en /database/
-- schema.sql y data.sql
Configurar conexión a BD

java
// Editar DatabaseConnection.java
private static final String URL = "jdbc:mysql://localhost:3306/veterinaria";
private static final String USER = "tu_usuario";
private static final String PASSWORD = "tu_password";
Compilar el proyecto

bash
mvn clean compile
Ejecutar la aplicación

bash
mvn exec:java -Dexec.mainClass="org.example.Main"
🚀 Uso del Sistema
Ejecución Directa
bash
java -cp target/classes org.example.Main
Menú Principal
text
✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨
🏥           SISTEMA VETERINARIA HAPPY FEET           🏥
✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨
👥  1. Gestión de Dueños
🐶  2. Gestión de Mascotas
📅  3. Gestión de Citas
🏥  4. Gestión de Historial Médico
🧾  5. Gestión de Facturas
🛒  6. Gestión de Items de Factura
📦  7. Gestión de Inventario
🚪  8. Salir del Sistema
────────────────────────────────────────────
🎯 Seleccione una opción: 
👥 Roles de Usuario
El sistema está diseñado para:

Recepcionistas - Gestión de citas y dueños

Veterinarios - Historial médico y consultas

Administradores - Facturas e inventario

🔧 Desarrollo
Compilar y Ejecutar Tests
bash
mvn clean test
Generar JAR Ejecutable
bash
mvn clean package
📊 Características Destacadas
✅ Interfaz amigable con emojis y diseño atractivo

✅ Validación de datos en todas las entradas

✅ Manejo de errores robusto

✅ Arquitectura escalable MVC

✅ Código mantenible y documentado

✅ Base de datos relacional optimizada

## se añadio los archivos exporthistorialmedico 