###Utilizacion de Formularios.
El siguiente fragmento de codigo html nos permite dirigirnos a cada version de los formularios que interactuan con los servlets.
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

El siguiente formulario corresponde  al formulario base con el que interactuaremos. En este formulario en su campo action especifica la ejecución del servlet *logger0*,  y el campo method el uso del metodo ** GET**.
Veremos tres tipos de inputs, el primero del tipo *text* de nombre user en el que se introducirá el nombre de usuario. El siguiente de tipo *password* con nombre pass en el que se introducira la contraseña. Y finalmente un boton submit para el envio del formulario.
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


### Explicación ordenes utilizadas con curl:
Para probar el funcionamiento de la orden curl, hemos definido un script llamado tarea1.sh en el cual se incluyen numerosas ordenes de obtencion, modificacion y borrado de elementos de CentroEducativo.
En primer lugar, definimos dos variables de entorno en nuestro fichero de pruebas.
- La variable de entorno **CentroEducativo** se utiliza para la automatización de las ordenes para evitar repetir la cadena asignada a esta en cada comando.
- La variable de entorno **key** tambien se utiliza para evitar la repetición de nuestra clave de sesión cada vez que se escriba una orden curl en el script.
Aclarar también que en este caso accedemos como administradores para la posterior visualizacion, modificación y borrado de diferentes elementos de CentroEducativo.

```bash
echo "Identificacion como administrador"
CentroEducativo="http://dew-masanru6-2021.dsic.cloud:9090/CentroEducativo"
key=$(curl -s --data '{"dni":"111111111","password":"654321"}' -X POST -H "content-type: application/json" ${CentroEducativo}/login -H "accept: */*" -c cookies -b cookies)
```
Las dos primeras ordenes que forman parte del script sirven para obtener los json correspondientes a la lista de profesores y la lista de asignaturas.



Como podemos observar ambas utilizan el método GET para ofrecernos la salida adecuada. Ademas utilizan las variables de entorno ***CentroEducativo*** y ***key***   para completar la URL correcta. Ademas se añade un campo accept: application/json para procesar en el cliente unicamente el formato json. Finalmente añadimos "*-b cookies -c cookies*" para alojar ahi nuestro fichero de cookies.
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
El procedimiento a seguir con las siguientes ordenes es similar. La funcion que buscamos ahora es añadir datos. Por ello en la orden curl incluiremos --data y el json que queramos introducir en CentroEducativo, en este caso añadimos la asignatura MAT y al profesor Nico Sánchez Jiménez.  Ademas se cambia el método HTTP a utilizar y que nos explica la API swagger, por el método POST.

```bash
echo "Añadir asignatura"
curl -s --data '{"acronimo":"MAT","nombre":"Matematicas De Informatica","curso":4,"cuatrimestre":"A","creditos":4.5}' -X POST -H "content-type: application/json" "${CentroEducativo}/asignaturas?key=$key" -H "accept:*/*" -b cookies -c cookies
echo "Añadir profesor"
curl -s --data '{"apellidos": "Sánchez Jimenez", "dni":"8988988X", "nombre":"Nico", "password":"1234"}' -X POST -H "content-type: application/json" "${CentroEducativo}/profesores?key=$key" -H "accept:*/*" -b cookies -c cookies
```
Esta es la salida de la orden de visualizacion de asignaturas expuesta anteriormente, tras haber añadido el comando para crear una nueva asignatura llamada MAT. La orden de creacion simplemente nos devuelve un **OK** para confirmar que todo ha ido bien.
```json
[{"acronimo":"DEW","nombre":"Desarrollo Web","curso":3,"cuatrimestre":"B","creditos":4.5},{"acronimo":"IAP","nombre":"Integración de Aplicaciones","curso":4,"cuatrimestre":"A","creditos":4.5},{"acronimo":"DCU","nombre":"Desarrollo Centrado en el Usuario","curso":4,"cuatrimestre":"A","creditos":4.5},{"acronimo":"MAT","nombre":"Matematicas De Informatica","curso":4,"cuatrimestre":"A","creditos":4.5}
```
Esta es la salida que nos ofrece la orden de ver profesores tras ejecutar el comando para añadir al profesor Nico, de la misma manera que antes nos devolverá un **OK** para decir que todo ha ido bien.
```json
{"dni":"23456733H","nombre":"Ramón","apellidos":"Garcia"},{"dni":"10293756L","nombre":"Pedro","apellidos":"Valderas"},{"dni":"06374291A","nombre":"Manoli","apellidos":"ALbert"},{"dni":"65748923M","nombre":"Joan","apellidos":"Fons"},{"dni":"8988988X","nombre":"Nico","apellidos":"Sánchez Jimenez"}
```
Para realizar el borrado de una asignatura por acronimo debemos de modificar el URL, de tal forma que nos quede con la estructura /asignatura/acronimo. En nuestro caso queremos eliminar la asignatura MAT creada anteriormente, y para ello utilizamos el metodo HTTP DELETE y el acronimo MAT junto a la URL.
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
> Nota: Si ejecutamos el script tarea1.sh veremos que en su resultado no aparece el conjunto de asignaturas tras haber añadido MAT, simplemente al reconocer que hay una orden de borrado, la salida tras crear la asignatura y despues de eliminarla sera la misma.
