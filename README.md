# VetSystem
Sistema de Gestión para Clínica Veterinaria

**Proyecto Final — Programación Orientada a Objetos**
Universidad Tecnológica del Perú — 2026
Autor: Adrian Rodrigo Diestra Bazan | U22330682

---

## Descripción
VetSystem es un sistema de gestión desarrollado en Java con interfaz gráfica JavaFX y base de datos MySQL, orientado a clínicas veterinarias pequeñas y medianas que buscan digitalizar sus procesos administrativos y clínicos.

---

## Tecnologías utilizadas
- Java JDK 25.0.2
- JavaFX 21
- MySQL 8.0.46
- Apache NetBeans
- Maven

---

## Requisitos previos
Antes de ejecutar el proyecto asegúrate de tener instalado:
- Java JDK 25 o superior
- Apache NetBeans
- MySQL Server 8.0
- MySQL Workbench (opcional, para visualizar la base de datos)

---

## Instrucciones de ejecución

### Paso 1 — Iniciar MySQL
Abre CMD como administrador y ejecuta:
net start MySQL80

### Paso 2 — Crear la base de datos
1. Abre MySQL Workbench
2. Conéctate con usuario root
3. Abre el archivo `vetsystem.sql` que está en la raíz del proyecto
4. Ejecuta el script completo con Ctrl + Shift + Enter
5. Esto crea la base de datos, las tablas y los datos de prueba

### Paso 3 — Configurar la conexión
Abre el archivo `src/main/java/com/vetsystem/dao/Conexion.java` y verifica que la contraseña coincida con la de tu instalación de MySQL:
```java
private static final String PASSWORD = "tu_password";
```

### Paso 4 — Ejecutar el proyecto
1. Abre NetBeans
2. Abre el proyecto VetSystem
3. Clic derecho sobre el proyecto → Run Maven → Goals
4. Escribe `javafx:run` y clic en OK

### Paso 5 — Iniciar sesión
Usa estas credenciales de prueba:

| Usuario | Contraseña | Rol |
|---|---|---|
| admin | 1234 | Administrador |
| drjuan | 1234 | Veterinario |
| recep01 | 1234 | Recepcionista |

---

## Estructura del proyecto
VetSystem/
├── src/main/java/com/vetsystem/
│ ├── App.java
│ ├── model/ → Clases del dominio (Paciente, Dueno, etc.)
│ ├── dao/ → Acceso a base de datos (JDBC + patrón DAO)
│ ├── controller/ → Lógica de cada pantalla
│ └── view/ → Archivos FXML (JavaFX)
├── src/main/resources/
│ └── com/vetsystem/view/ → Pantallas FXML
├── vetsystem.sql → Script de base de datos
├── pom.xml → Dependencias Maven
└── README.md

---

## Funcionalidades
- Login con roles (Admin, Veterinario, Recepcionista)
- Gestión de pacientes (mascotas) con filtro por especie
- Gestión de dueños con búsqueda por nombre
- Registro de consultas médicas
- Control de inventario de medicamentos
- Uso de Iterator, ListIterator y Lambda

---

## Para Detener MySQL si se requiere con el CMD:
net stop MySQL80
