# Novatech ERP - Nuestro Proyecto Final

Este es el resultado de un trabajo enfocado en modernizar lo que normalmente vemos en sistemas de escritorio. No es solo un POS basico; la idea fue llevar la experiencia de un ERP real a Java Swing, conectandolo con una base de datos en la nube y cuidando detalles de rendimiento que usualmente se pasan por alto.

## Desarrollo del Sistema

### 1. Conectividad en la Nube (Supabase + PostgreSQL)

A diferencia de usar un localhost, aqui todo corre en Supabase. Lo interesante no es solo que los datos esten en la nube, sino como los gestionamos:

- Optimizacion de Recursos: Implemente un gestor que corta la conexion si no hay actividad por 60 segundos. Esto se hizo para no saturar el servidor innecesariamente. En cuanto necesitas consultar algo, se reconecta en milisegundos sin que el usuario note nada.

### 2. Interfaz Dark Mode y Responsive

Buscamos alejarnos de las interfaces clasicas de los sistemas de escritorio antiguos.

- El panel de ventas tiene una rejilla dinamica. Si estiras la ventana, los productos se reacomodan solos. Si la achicas, se apilan verticalmente evitando el scroll horizontal.
- Todo el diseño sigue una linea oscura, limpia y sin decoraciones innecesarias, buscando un look profesional de software corporativo moderno.

### 3. Seguridad por Roles (RBAC)

El sistema cambia su interfaz y permisos segun la autenticacion:

- ADMIN: Tiene el control total del sistema, incluyendo gestion de personal y usuarios.
- VENDEDOR: Enfocado en el Punto de Venta (POS) y gestion de clientes.
- ALMACENERO: Solo tiene acceso a stock, categorias y proveedores.
- GERENTE: Se enfoca en la visualizacion de reportes y estadisticas de negocio.

## Stack Tecnologico

- Java 25: El motor principal del sistema.
- Maven: Gestion de dependencias y ciclo de vida del proyecto.
- JDBC + PostgreSQL: Protocolo de comunicacion con Supabase.
- Swing: Interfaz grafica moderna mediante el uso de FlatLaf.

## Ejecucion del Proyecto

1. Clonar el repositorio.
2. Verificar la instalacion de JDK 25 y Maven.
3. El proyecto utiliza maven para su gestion. Compilar con:
   mvn clean install
4. Para lanzar la aplicacion:
   mvn exec:java -Dexec.mainClass="com.novatech.proyectofinal.ProyectoFinal"

---

Proyecto desarrollado con un enfoque en la modernizacion de aplicaciones de escritorio en Java.
