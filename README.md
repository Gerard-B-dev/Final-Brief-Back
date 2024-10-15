# Proyecto Final Final Brief Front - Back

# Alumno: Gerard Borràs Alonso
# Plataforma de Gestión de Productos y Tareas

## Descripción del Proyecto

Este proyecto es una **aplicación web** que permite a los usuarios **registrarse**, **iniciar sesión** y gestionar tanto **productos** como **tareas** de manera eficiente. La aplicación está desarrollada utilizando **Vue.js** para el frontend y **Java Spring Boot** para el backend, proporcionando una arquitectura moderna y escalable.

### Características Principales

1. **Autenticación de Usuarios**:
    - Registro de nuevos usuarios.
    - Inicio de sesión con validación mediante **JWT** (JSON Web Tokens) para sesiones seguras.

2. **Gestión de Productos**:
    - Crear, leer, actualizar y eliminar productos.
    - Los productos incluyen información detallada como título, descripción, imagen y precio.

3. **Gestión de Tareas**:
    - Crear, leer, actualizar y eliminar tareas.
    - Las tareas pueden marcarse como completadas o modificarse en cualquier momento.

4. **Protección de Rutas**:
    - Solo los usuarios autenticados pueden acceder a las secciones de gestión de productos y tareas.

---

## Tecnologías Utilizadas

### Frontend
- **Vue.js**: Framework progresivo para la construcción de interfaces de usuario.
- **Vue Router**: Para el manejo de rutas y navegación entre vistas.
- **Pinia**: Para la gestión global del estado de la aplicación.
- **Axios**: Cliente HTTP utilizado para la comunicación con el backend.
- **Vite**: Herramienta de construcción rápida para proyectos Vue.js.

### Backend
- **Java Spring Boot**: Framework para el desarrollo de aplicaciones backend.
- **Spring Security**: Para la autenticación y autorización.
- **JWT (JSON Web Tokens)**: Para la autenticación basada en tokens.
- **MySQL**: Base de datos relacional utilizada para almacenar los usuarios, productos y tareas.
- **Hibernate (JPA)**: Para la gestión de la base de datos y la interacción con las entidades.

---

## Instalación y Configuración

### Prerrequisitos

1. **Node.js** y **npm** instalados para el frontend.
2. **JDK 11+** y **Maven** instalados para el backend.
3. **MySQL** instalado y corriendo en `localhost:3306`.

---

## Uso de la Aplicación

1. **Registro**: El usuario puede registrarse con su correo electrónico y contraseña desde la página `/register`.
2. **Inicio de Sesión**: Después del registro, el usuario puede iniciar sesión en `/login`. Al autenticarse correctamente, recibirá un token JWT para acceder a las demás funcionalidades.
3. **Gestión de Productos**: Una vez autenticado, el usuario puede añadir, editar o eliminar productos desde el **Dashboard**.
4. **Gestión de Tareas**: De igual manera, el usuario puede gestionar sus tareas en el **Dashboard**, añadiendo, modificando o eliminando tareas según sea necesario.

---

## Consideraciones de Seguridad

- **Autenticación mediante JWT**: Se asegura que solo usuarios autenticados tengan acceso a rutas protegidas.
- **Encriptación de contraseñas**: Las contraseñas de los usuarios se almacenan en la base de datos utilizando **BCrypt**.
- **Políticas de CORS**: Se ha configurado el backend para permitir solicitudes solo desde orígenes autorizados.

---

# Enunciado base de IronHack

Para finalizar el proyecto final  debes haber realizado tu proyecto parcial en Java Spring. No importa si el proyecto de Java es el mismo que usarás aquí, pero debes tenerlo hecho.
Introducción
Este documento describirá los requisitos de su próximo proyecto. Creará una aplicación de tareas sencilla con  Vue.js  que permita a los usuarios crear una cuenta, registrar tareas, editarlas y marcarlas como completadas. La aplicación estará vinculada a una base de datos, donde se almacenarán todos los datos de los usuarios y las tareas. La base de datos que utilizaremos para este proyecto es  Supabase . También utilizaremos  Vite  como herramienta de compilación para compilar nuestro código y proporcionar un servidor de desarrollo mientras trabajamos.

En las siguientes lecciones encontrará notas introductorias sobre bases de datos.

Necesitarás usar las habilidades CSS que aprendiste anteriormente en el curso para diseñar tu aplicación. Puedes elegir cualquier biblioteca de UI con la que trabajar, como  Bootstrap ,  Vue Element Plus ,  Vuetify , o escribir tus propios estilos para obtener puntos extra.

Objetivos de aprendizaje
Al finalizar este proyecto podrás:

Utilice Vue.js para crear una aplicación
Conectar una aplicación Vue.js a Supabase  (Base de datos como servicio)
Requisitos del proyecto
Tiene un repositorio en GitHub
Comprometa su trabajo al menos una vez al día durante el tiempo de desarrollo del proyecto.
Su aplicación debe visualizarse en un navegador
Su aplicación debe implementarse en línea mediante un servicio en la nube como Netlify
Apégate a  los principios KISS  (Keep It Simple, Stupid) y  DRY  (Don't Repeat Yourself).
Su aplicación debe tener estilo CSS
Su aplicación debe estar completamente integrada con una base de datos Supabase utilizando todas las operaciones CRUD
Debes usar Pinia de Vue para implementar completamente el manejo de estado
Debes manejar cualquier operación asincrónica usando Promesas o Async/await
Debe documentar las características, la configuración y las especificaciones técnicas de la aplicación dentro de un archivo README
Entregables
La funcionalidad debe incluir:

- Autenticación (registrarse, iniciar sesión, cerrar sesión)
- Una interfaz que muestra la lista de tareas personales del usuario.
- Una capacidad para agregar nuevas tareas
- Una capacidad para editar tareas existentes
- Una capacidad para marcar tareas como completadas/incompletas
- Una capacidad para eliminar tareas existentes
- Una tienda y una base de datos desde las que su aplicación puede recuperar datos


Vue.js
Módulos necesarios:
Enrutador Vue

Necesitarás configurar una ruta para  Dashboard y Auth
Pinia  (para nuestra tienda)

Necesitará configurar dos tiendas de usuarios:  user.js y task.js

1. Tareas

- Crear una pantalla de registro/inicio de sesión que se mostrará cuando el usuario no haya iniciado sesión
- Asegúrese de que el formulario de registro solicite una dirección de correo electrónico, una contraseña y una confirmación de contraseña al usuario.
- Asegúrese de que el formulario de inicio de sesión solicite un correo electrónico y una contraseña al usuario.
- Crear una página donde, después de iniciar sesión, se le muestre al usuario el contenido principal de la aplicación.
- La aplicación debe recuperar todas las tareas de la lista de tareas del usuario que inició sesión almacenada en Supabase