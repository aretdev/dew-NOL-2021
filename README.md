# Acta de las reuniones 1 y 2 del grupo de DEW 3TI11
Asistentes: Mario, Joel, Josep, Vicente y Sergio

------------
###### Esta acta contiene las dos primeras reuniones: la del 26 de abril y la del 29 de abril

####  1.	Dinámica de trabajo
Hemos inicializado un GitHub (git) como herramienta colaborativa para el código, un Discord como herramienta para la comunicación dentro de reuniones y un grupo de Whatsapp para las comunicaciones fuera de las reuniones. Los archivos externos al proyecto se guardarán en un OneDrive colaborativo en el que todos podremos acceder y editar como iguales. 
El horario tentativo para las reuniones será de 12:30 a 14:00 los jueves y de 10:00 a 12:00 los lunes. Todos podemos cumplir este horario excepto Krasimir, porqué trabaja. Estamos buscando huecos para que todos podamos reunirnos a la vez, pero Krasimir tiene muy poco tiempo libre por lo que parece, aunque ha dicho que podría después de las 19:30.

#### 2.	Trabajo realizado
En la primera reunión, todos los asistentes hemos expuesto los temas que habían sido asignados con antelación. Antes de la reunión se había asignado a Josep como secretario que realizara las actas correspondientes a cada una de las reuniones a partir de esta. Hemos hecho un proyecto de prueba y lo hemos publicado para comprobar si todo funcionaba correctamente. También hemos configurado el git todos juntos en la máquina de cada uno de los asistentes.
En la segunda sesión, nos hemos dedicado a crear un proyecto de prueba para familiarizarnos con el proyecto. Hemos usado la versión de Tomcat 2.5 y la versión de Eclipse 2019-03. Nos hemos familiarizado con la conexión del servidor mediante un logger básico y un hola mundo.
#### Resumen de la actividad "Requerimientos Básicos":
Se trataron los principales conflictos en los equipos, como juzgar a los demás, superioridad, ignorar o ser deshonesto con el grupo y se remarcó la importancia de la pronta resolución de problemas.

Además se recalcó la importancia que tiene la libertad de expresión y el respeto mutuo por parte de todos los miembros. Por otro lado, los objetivos se establecieron claramente, de manera precisa y atendiendo a las capacidades de cada uno. En cuanto a la resolución de problemas se eligió como favorito el método SWOT.

Por último tratamos el brainstorming, que sera utilizado cuando sea necesario afrontar un problema que tenga diferentes soluciones y todas sean válidas.

En general, los comentarios al respecto fueron de aprobación y no hubo disconformidades en general. Se advirtió la importancia de la puntualidad y se acordó un horario flexible para poder trabajar entre todos.

Mario : Agrupación 1. 
Vicente : Agrupación 1.
Joel: Agrupación 2.
Krasimir: Agrupación 2.
Sergio : Agrupación 3.
Josep: Agrupación 3.

#### 3.	Problemas acontecidos y soluciones propuestas
Esta reunión ha concluido sin problemas. Hay buen ambiente entre todos los asistentes, todos nos comprometemos a un horario estable. El único problema ha sido la falta de asistencia de Krasimir, pero intentaremos reunirnos con él lo más pronto posible y reajustar el horario.
Sin embargo, posteriormente se trató el tema con Krasimir y quedó conforme con lo expuesto en la reunión.

# Acta de la reunión 3 del grupo de DEW 3TI11
Asistentes: Mario, Joel, Krasimir, Vicente y Sergio

------------
###### Esta acta contiene la tercera reunión: 3 de mayo.

####  1.	Dinámica de trabajo
Se repartieron las tareas en 2 grupos de trabajo claramente diferenciados para agilizar el trabajo.

Mario, Krasimir y Joel trabajaron los aspectos relacionados con la orden curl así como la redacción de la explicación del funcionamiento de las órdenes y su posterior salida en el bash.

Sergio y Vicente se encargaron de la parte de código referida especialmente en modificaciones en formularios y pruebas del proyecto.

#### 2.	Trabajo realizado
Se hizo un breve comentario sobre el funcionamiento de la orden curl a todos los miembros del grupo y se trabajó con el script tarea1.sh mencionado en la actividad de tratamiento de dicha orden.

Asimismo se modificaron algunos aspectos de los formularios, como la introducción de campos de texto para usuario y contraseña, además, se comprobó el correcto funcionamiento del proyecto en las máquinas de portal-ng.
#### 3.	Problemas acontecidos y soluciones propuestas
No sucedió ningún problema y la sesión se llevó a cabo de manera satisfactoria.

------------
# Actividades asociadas al hito 1 (excluyendo actas).

### Utilización de Formularios.

El siguiente fragmento de código html nos permite dirigirnos a cada versión de los formularios que interactúan con los servlets.
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Indice de Loggers</h1>
<a href="login.html">Enlace al Logger version 0</a>
<a href="login1.html">Enlace al Logger version 1</a>
<a href="login2.html">Enlace al Logger version 2</a>
</body>
</html>
```
A continuación procedemos a explicar el funcionamiento de los formularios html utilizados.

El siguiente formulario corresponde al formulario base con el que interactuaremos. En este formulario en su campo action especifica la ejecución del servlet *logger0*,  y el campo method el uso del método ** GET**.
Veremos tres tipos de inputs, el primero del tipo *text* de nombre user en el que se introducirá el nombre de usuario. El siguiente de tipo *password* con nombre pass en el que se introducirá la contraseña. Y finalmente un botón submit para el envío del formulario.
Para el metodo **POST** utilizaremos el mismo formato que se ha descrito previamente en el metodo **GET**
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login</title>
</head>
<body>
<h1>Prueba de logger</h1>

<h2>Envio por get</h2>
<form action="logger0" method="get">
	<input type="text" name="user" placeholder="Nombre de Usuario"><br>
	<input type="password" name="pass" placeholder="Contraseña"><br>
	<input type="submit">
</form>

<h2>Envio por post</h2>
<form action="logger0" method="post">
	<input type="text" name="user" placeholder="Nombre de Usuario"><br>
	<input type="password" name="pass" placeholder="Contraseña"><br>
	<input type="submit">
</form>
</body>
</html>
```
>Nota: Los formularios utilizados para el logger 1 y el logger 2 es cambiar el campo action de los formularios por logger 1 y logger 2 respectivamente.

### Otra información relevante

Por último, vamos a detallar brevemente los servlets.

El servlet que corresponde al logger1 tiene dos métodos que son el doGet y el doPost. Si nos centramos en el doGet vemos claramente que en la primera línea se especifica una ubicación. Esta será la ruta de los logs que se generen para nuestro login1. Al utilizar la clase File debemos tratar las futuras excepciones que se puedan generar de forma dinámica.

```java
File file1 = new File("/home/user/Escritorio/log-NOL-dew.log");
```

Por otra parte, en el servlet logger2 la ruta se especifica desde el fichero web.xml y el código cambiaría significativamente.

```java
File file1 = new File(getServletContext().getInitParameter("logPath"));
PrintWriter pw2 = new PrintWriter(new FileOutputStream(new File(getServletContext().getInitParameter("logPath")),true));
```
Una vez especificamos el parámetro xml, al cual hemos denotado logPath, nos dirigimos al archivo web.xml y añadimos las siguientes líneas. Con esto especificamos cual será la ruta para el log. 
```xml
<context-param>
        <param-name>logPath</param-name>
        <param-value>/home/user/Escritorio/log-NOL-dew.log</param-value>
    </context-param>
```

Y la salida de ambos servlets la mostramos con la siguiente línea. Está línea se compone de la fecha de la solicitud, el usuario y contraseña que hace la solicitud, la IP desde que se hace la solicitud, el nombre del servlet, el URI de respuesta y el método GET.

```java
pw2.println(LocalDateTime.now().toString() + " " + request.getQueryString() + " " + usuario + " "  + request.getRemoteAddr() + " " + getServletName() + " " + request.getRequestURI() + " " + request.getMethod());
```

### Explicación ordenes utilizadas con curl:
Para probar el funcionamiento de la orden curl, hemos definido un script llamado tarea1.sh en el cual se incluyen numerosas ordenes de obtención, modificación y borrado de elementos de CentroEducativo.
En primer lugar, definimos dos variables de entorno en nuestro fichero de pruebas.
- La variable de entorno **CentroEducativo** se utiliza para la automatización de las órdenes para evitar repetir la cadena asignada a esta en cada comando.
- La variable de entorno **key** también se utiliza para evitar la repetición de nuestra clave de sesión cada vez que se escriba una orden curl en el script.
Aclarar también que en este caso accedemos como administradores para la posterior visualización, modificación y borrado de diferentes elementos de CentroEducativo.

```bash
echo "Identificacion como administrador"
CentroEducativo="http://dew-masanru6-2021.dsic.cloud:9090/CentroEducativo"
key=$(curl -s --data '{"dni":"111111111","password":"654321"}' -X POST -H "content-type: application/json" ${CentroEducativo}/login -H "accept: */*" -c cookies -b cookies)
```
Las dos primeras ordenes que forman parte del script sirven para obtener los json correspondientes a la lista de profesores y la lista de asignaturas.



Como podemos observar ambas utilizan el método GET para ofrecernos la salida adecuada. Además utilizan las variables de entorno ***CentroEducativo*** y ***key***   para completar la URL correcta. Además se añade un campo accept: application/json para procesar en el cliente únicamente el formato json. Finalmente añadimos "*-b cookies -c cookies*" para alojar ahi nuestro fichero de cookies.
```bash
echo "Ver los profesores existentes"
curl -X GET "${CentroEducativo}/profesores?key=$key" -H "accept: application/json" -b cookies -c cookies
echo "Ver las asignaturas"
curl -X GET "${CentroEducativo}/asignaturas?key=$key" -H "accept: application/json" -b cookies -c cookies
```
Esta es la salida de la primera orden que visualiza los profesores existentes.
```json
[{"dni":"23456733H","nombre":"Ramón","apellidos":"Garcia"},{"dni":"10293756L","nombre":"Pedro","apellidos":"Valderas"},{"dni":"06374291A","nombre":"Manoli","apellidos":"ALbert"},{"dni":"65748923M","nombre":"Joan","apellidos":"Fons"}
```
Esta es la salida de la orden que visualiza las asignaturas.
```json
{"acronimo":"DEW","nombre":"Desarrollo Web","curso":3,"cuatrimestre":"B","creditos":4.5},{"acronimo":"IAP","nombre":"Integración de Aplicaciones","curso":4,"cuatrimestre":"A","creditos":4.5},{"acronimo":"DCU","nombre":"Desarrollo Centrado en el Usuario","curso":4,"cuatrimestre":"A","creditos":4.5}
```
El procedimiento que seguir con las siguientes ordenes es similar. La función que buscamos ahora es añadir datos. Por ello en la orden curl incluiremos --data y el json que queramos introducir en CentroEducativo, en este caso añadimos la asignatura MAT y al profesor Nico Sánchez Jiménez.  Además se cambia el método HTTP a utilizar y que nos explica la API swagger, por el método POST.

```bash
echo "Añadir asignatura"
curl -s --data '{"acronimo":"MAT","nombre":"Matematicas De Informatica","curso":4,"cuatrimestre":"A","creditos":4.5}' -X POST -H "content-type: application/json" "${CentroEducativo}/asignaturas?key=$key" -H "accept:*/*" -b cookies -c cookies
echo "Añadir profesor"
curl -s --data '{"apellidos": "Sánchez Jimenez", "dni":"8988988X", "nombre":"Nico", "password":"1234"}' -X POST -H "content-type: application/json" "${CentroEducativo}/profesores?key=$key" -H "accept:*/*" -b cookies -c cookies
```
Esta es la salida de la orden de visualización de asignaturas expuesta anteriormente, tras haber añadido el comando para crear una nueva asignatura llamada MAT. La orden de creación simplemente nos devuelve un **OK** para confirmar que todo ha ido bien.
```json
[{"acronimo":"DEW","nombre":"Desarrollo Web","curso":3,"cuatrimestre":"B","creditos":4.5},{"acronimo":"IAP","nombre":"Integración de Aplicaciones","curso":4,"cuatrimestre":"A","creditos":4.5},{"acronimo":"DCU","nombre":"Desarrollo Centrado en el Usuario","curso":4,"cuatrimestre":"A","creditos":4.5},{"acronimo":"MAT","nombre":"Matematicas De Informatica","curso":4,"cuatrimestre":"A","creditos":4.5}
```
Esta es la salida que nos ofrece la orden de ver profesores tras ejecutar el comando para añadir al profesor Nico, de la misma manera que antes nos devolverá un **OK** para decir que todo ha ido bien.
```json
{"dni":"23456733H","nombre":"Ramón","apellidos":"Garcia"},{"dni":"10293756L","nombre":"Pedro","apellidos":"Valderas"},{"dni":"06374291A","nombre":"Manoli","apellidos":"ALbert"},{"dni":"65748923M","nombre":"Joan","apellidos":"Fons"},{"dni":"8988988X","nombre":"Nico","apellidos":"Sánchez Jimenez"}
```
Para realizar el borrado de una asignatura por acrónimo debemos de modificar el URL, de tal forma que nos quede con la estructura /asignatura/acrónimo. En nuestro caso queremos eliminar la asignatura MAT creada anteriormente, y para ello utilizamos el método HTTP DELETE y el acronimo MAT junto a la URL.
Tratamos a parte y vemos su salida fuera del fichero para observar los cambios.
```bash
echo "Borrar MAT"
curl -X DELETE -H "content-type: application/json" "${CentroEducativo}/asignaturas/MAT?key=$key" -H "accept: */*" -b cookies -c cookies
echo "Ver las asignaturas despues de ser borrada MAT"
curl -X GET  "${CentroEducativo}/asignaturas?key=$key" -H "accept: application/json" -b cookies -c cookies
```
La salida que añade la orden de borrar es un **OK** y posterior a ello ejecutamos de nuevo la orden de ver asignaturas para comprobar el cambio realizado. Se observa que la asignatura MAT ya no aparece.
```json
{"acronimo":"DEW","nombre":"Desarrollo Web","curso":3,"cuatrimestre":"B","creditos":4.5},{"acronimo":"IAP","nombre":"Integración de Aplicaciones","curso":4,"cuatrimestre":"A","creditos":4.5},{"acronimo":"DCU","nombre":"Desarrollo Centrado en el Usuario","curso":4,"cuatrimestre":"A","creditos":4.5}
```
> Nota: Si ejecutamos el script tarea1.sh veremos que en su resultado no aparece el conjunto de asignaturas tras haber añadido MAT, simplemente al reconocer que hay una orden de borrado, la salida tras crear la asignatura y después de eliminarla será la misma.
