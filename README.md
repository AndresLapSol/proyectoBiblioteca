# 📚 Proyecto Biblioteca

¡Bienvenido al **Proyecto Biblioteca**! 🎉  
Una aplicación Java que utiliza **Hibernate** para gestionar una base de datos **MySQL** y simular un sistema de préstamos de libros.

---

## 🎯 Objetivos
- Gestionar el catálogo de libros 📖  
- Registrar usuarios y préstamos 🧑‍🤝‍🧑  
- Mostrar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)  
- Aprender a configurar y usar Hibernate con MySQL

---

## ✨ Características
- 📘 **CRUD de Libros**: agrega, lista, edita y elimina libros  
- 👤 **CRUD de Usuarios**: administra usuarios de la biblioteca  
- 🔄 **Gestión de Préstamos**: registra cuándo un usuario toma o devuelve un libro  
- 🔧 **Configuración Hibernate** con XML y anotaciones  
- 🗂️ **Estructura modular** y organizada

---

## 🚀 Tecnologías y Herramientas
- **Java 8+**  
- **Hibernate ORM 5.x**  
- **MySQL Server 5.7+**  
- **Maven**  
- **Log4j** para logging  
- IDE recomendado: IntelliJ IDEA o Eclipse

---

## 🔧 Prerrequisitos
1. JDK 8 o superior instalado  
2. MySQL instalado y en ejecución  
3. Maven instalado  
4. (Opcional) IDE como IntelliJ IDEA o Eclipse  

---

## 🛠️ Instalación y Configuración

### 1. Clonar el repositorio
```bash
git clone https://github.com/AndresLapSol/proyectoBiblioteca.git
cd proyectoBiblioteca
```

### 2. Configurar la base de datos
- Crear una base de datos en MySQL, por ejemplo: `biblioteca_db`  
- Editar `src/main/resources/hibernate.cfg.xml` con tus credenciales:
```xml
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/biblioteca_db</property>
<property name="hibernate.connection.username">TU_USUARIO</property>
<property name="hibernate.connection.password">TU_CONTRASEÑA</property>
```

### 3. Compilar el proyecto
```bash
mvn clean install
```

### 4. Ejecutar la aplicación
```bash
mvn exec:java -Dexec.mainClass="org.example.MainApp"
```
- Verás en consola las operaciones de gestión de la biblioteca 📋

---

## 📂 Estructura del Proyecto
```
proyectoBiblioteca/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── entity/      # Clases de entidad (Libro, Usuario, Prestamo)
│   │   │   ├── dao/         # Objetos de acceso a datos
│   │   │   └── util/        # Utilidades (HibernateUtil)
│   │   └── resources/
│   │       ├── hibernate.cfg.xml
│   │       └── log4j.properties
│   └── test/
│       └── java/org/example/ # Tests unitarios
├── catalogo.dat             # Datos de ejemplo del catálogo
├── pom.xml
└── LICENSE
```

---

## 🤝 Contribuciones
¡Todas las contribuciones son bienvenidas!  
1. Haz un **fork** del repositorio  
2. Crea una **branch** para tu feature (`git checkout -b feature/nombre`)  
3. Realiza tus cambios y **commitea** (`git commit -m "Agrega nueva funcionalidad"`)  
4. Haz **push** a tu branch y abre un **Pull Request** 📩

---

## ⚖️ Licencia
Este proyecto está bajo la licencia **CC0-1.0**. Consulta el archivo `LICENSE` para más detalles.

---

## 📫 Contacto
Desarrollado por **Andrés LapSol**  
Si tienes dudas o sugerencias, escríbeme a: `andreslapsol@gmail.com`  
