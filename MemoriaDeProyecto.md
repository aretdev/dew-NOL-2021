# MEMORIA DEL PROYECTO NOTAS ONLINE
>#### Grupo 3TI11-02
> Integrantes: **Vicente Rodrigo Brisa, Mario Sánchez Rubio, Josep Molina Muñoz, Sergio Martínez Alejandre, Joel Pérez Gómez, Kraasimir Antonov Valev**

## Índice de contenidos:
- **1. Introducción a la estructura del proyecto.**
- **2. Justificación de tecnologías escogidas.**
- **3. Recorrido y guía de uso simplificada.**
- **4. Aspectos relacionados con la implementación.**
 -  **4.1 Identificación e inicio de sesión.**
 - **4.2 Lógica de los servlets.**
 - **4.3 Lógica de los formularios.**
- **5. Problemas y soluciones adoptadas.**

------------

## 1. Introducción a la estructura del proyecto.

Nuestro proyecto se organiza con una estructura similar a la que aparece en la siguiente figura:

![](https://i.imgur.com/HL5PF6l.png)

Se pueden observar unos cuantos componentes diferenciados claramente por su funcionalidad.

En primer lugar tenemos el formulario de inicio de sesión (aparece en color rojo en la figura), por el cual introduciremos nuestras credenciales y accederemos a la aplicación.

Estas credenciales son procesadas por nuestro Filtro LoginControl.java encargado de obtener todos los parámetros de sesión necesarios para la correcta identificación de alumnos y profesores.

El filtro tras todo el proceso de login, realiza una redirección a una página HTML u otra dependiendo del rol de la persona identificada, si nos encontramos ante un 'rolalu' entrará a la página alumnoPrincipal.html, y si nos encontramos ante un 'rolpro' entrará a la página de profesorPrincipal.html. 

Estas páginas interaccionaran con AJAX con nuestros servlets alumnoApi y profesorApi, encargados de realizar peticiones HTTP a CentroEducativo, que devolverá como respuesta objetos JSON que, a su vez, serán recibidos en estas "APIs" y posteriormente serán enviados a las páginas HTML, allí estos serán tratados dentro de sus respectivos scripts en las funciones "success" de nuestras peticiones AJAX.

## 2. Justificación de las tecnologías utilizadas.
Nos decantamos por utilizar las librerías HttpComponents y JSON-Java. 

Tras mirar documentación de todas las librerias propuestas nos quedamos con estas dos puesto que la forma en la que se desarrolla el código con ellas nos parece la más intuitiva y cómoda para nuestra forma de ver tanto las estructuras de las peticiones y respuestas HTTP, como, la estructura de los objetos JSON y su manipulación. Además hacer hincapié en que JSON-Java nos ofrece infinidad de formatos y constructores para la definición y control de estos objetos JSON.

También en cuanto a la forma de trabajar con nuestro proyecto, creemos que el filtrado y la inserción de contenido en nuestras páginas HTML es mas adecuado y sencillo de interpretar utilizando AJAX y no utilizando metodologías más rudimentarias como el continuo patrón de "prints" utilizado en versiones básicas de los primeros servlets que se vieron en la asignatura.

## 3. Recorrido y guía de uso simplificada.
Esta es la página de bienvenida a nuestra aplicación, en la que debemos de rellenar los campos del formulario que se nos abre en pantalla tras dar click a "identificarse". Una vez hecho esto entraremos a la aplicación.
![](https://i.imgur.com/9405W5l.png)

------------

Posteriormente en el caso que entremos como profesor se nos mostrará una pantalla con la imagen correspondiente, el nombre, dni y asignaturas impartidas. 
![](https://i.imgur.com/XgWK2AI.png)

------------

Para entrar a calificar alumnos de estas asignaturas haremos click en alguna de ellas y se nos abrirán una serie de opciones y parametros a considerar, tales como nombre de los alumnos, la media de estos, los aprobados y suspensos etc.
![](https://i.imgur.com/6uFk3CL.png)

------------

Si hacemos click en el botón de calificar nos aparecerá un menu contextual con información más detallada del alumno, ahora aparece su imagen también. Para calificar basta con introducir una nota valida y pulsar calificar. Podremos también desplazarnos entre alumnos con los botones de siguiente y anterior, evitando así salir del menú contextual.
![](https://i.imgur.com/vyYKuwe.png)

------------

Una vez califiquemos a algun alumno comenzarán a aparecer estadisticas en las casillas superiores de la tabla.
![](https://i.imgur.com/xnUQI5q.png)

------------


También se podrá acceder como alumno, en esta vista encontraremos como elemento principal una tabla que muestra la información acerca de las asignaturas en las que esta matriculado el alumno y todos los parámetros que debemos tener en cuenta sobre su información.
![](https://i.imgur.com/4wBnMQB.png)

------------

Si pulsamos sobre el botón de Generar Documento de la barra superior, podemos ver que se nos generará una página nueva en la que aparecerá el certificado asociado a sus calificaciones.
Finalmente si pulsamos en el logo de NOTAS ONLINE, volveremos a la vista web anterior.
![](https://i.imgur.com/trK3tOL.png)

------------


Aquí observamos la vista preliminar de nuestro documento, adaptandose a la perfección a un folio DIN A4.
![](https://i.imgur.com/HW4b9UH.png)

------------


