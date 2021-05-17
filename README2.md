# Acta de la reunión 4 y 5 del grupo DEW 3TI11-02.

> ##### Asistentes: Vicente, Joel, Mario, Josep, Krasimir y Sergio.

------------
###### Esta acta contiene las reuniones 4 y 5: la del 10 de mayo y la del 12 de mayo.
#### 1. Dinámica de trabajo.
Se establece claramente un plan de trabajo detallado a seguir para la actividad 2, con 4 pasos fundamentales para poder organizar el trabajo e ir desarrollando progresivamente la actividad en grupo.
En la actividad 3 se procede de la misma manera generando un plan compartido a todos los miembros del grupo detallando las actividades a desarrollar.

#### 2. Trabajo realizado.

Se termina en su totalidad la actividad numero 2 y se comienza con los primeros detalles de la actividad 3.
En primer lugar se realizó el consenso para la eleccion de bibliotecas. Nos decantamos por utilizar HttpComponents y JSON-Java. 

Tras mirar documentación de todas las librerias propuestas nos quedamos con estas dos puesto que la forma en la que se desarrolla el codigo con ellas nos parece la mas intuitiva y cómoda para nuestra forma de ver tanto las estructuras de las peticiones y respuestas HTTP, como, la estructura de los objetos JSON y su manipulacion. Además hacer especial incapié en que JSON-Java nos ofrece infinidad de formatos y constructores para la definicion y control de estos objetos JSON.

Una vez descargadas e incorporadas al proyecto las librerias,  se procede con la actividad de inicio TheCatApi completando con éxito la operación GET y mostrandose correctamente las imagenes en la web.

Después se comienzó a indagar en los pasos 2 y 3 de nuestro plan que incluyen, la creacion de roles en tomcat-users.xml, la modificación del web.xml para la incorporación de las auth-contraint y se desarrolló el filtro SessionControl siguiendo el pseudocodigo ofrecido en el manual *(Trabajo en grupo NOL)*, que nos indica como transitar y comunicar la capa lógica con la capa de datos mediante el uso de una sesión, un objeto json con las variables a utilizar en la operacion de login, y la propia peticion POST de login a CentroEducativo.

Tras, ello se crea un formulario base (login3.html) para comenzar con la autenticación de los usuarios y tambien se crea un nuevo filtro LoginControl que selecciona los datos dni y pass de manera diferente al anterior Servlet "SessionControl". Se modifica tambien el Servlet HolaMundo para poder obtener la respuesta en formato JSON a una posible consulta a CentroEducativo.

> NOTA: La actividad de testeo se realizó desde la maquina de porta-ng de Vicente:
> **dew-virodbri-2020.dsic.cloud**

#### 3. Problemas acontecidos y soluciones propuestas
Ningún problema ocurrido en el transcurso de las sesiones, y se ofrece una posible solucion ante problemas de compatibilidad de horario debido a la creciente carga de trabajo que la realizacion del proyecto supone y la compaginacion con otras asignaturas.

La solución propuesta fue realizar una delegación de trabajo con previa explicación en un horario fuera del habitual al miembro del grupo que no pueda asistir a alguna reunión en particular.
Todos los miembros aceptaron la propuesta y se introdujeron nuevas jornadas de trabajo para intensificar la productividad.


# Acta de la reunión 6,7,8 del grupo DEW 3TI11-02.

> ##### Asistentes: Vicente, Joel, Mario y Sergio.
------------
###### Esta acta contiene las reuniones 6,7 y 8: la del 14 de mayo, 16 de mayo y 17 de mayo.

#### 1. Dinámica de trabajo.
Se continua con la dinamica propuesta en las sesiones anteriores. Seguir con el plan de ejecución propuesto, en estas reuniones especificamente, se trabajó
#### 2. Trabajo realizado.

Se comienza al desarrollo de las piezas desarrolladas en los requisitos de la tarea, se modifican ciertos ficheros para hacer una condensación de la información y hacer el proceso mas simple. Comenzamos con el estudio individualizado de todas los integrantes del equipo de jQuery y Ajax para poder trabajar "agilmente". Posteriormente se colabora en la construcción de las APIs y el diseño de las páginas HTML con Bootstrap. 

Se profundiza en el concepto de interconexion entre servlets y html ya que lo consideramos muy importante para el completo entendimiento del proyecto. 

Realizamos un trabajo paralelo desarrollando la parte de Servlets en grupo y la parte de Ajax/jQuery también en grupo.

> NOTA: La actividad de testeo se realizó desde la maquina de porta-ng de Vicente:
> **dew-virodbri-2020.dsic.cloud**

#### 3. Problemas acontecidos y soluciones propuestas
Destacamos la falta de asistencia de Krasimir durante todas las reuniones, y la falta de asistencia de Josep a las 2 primeras. 
Las dos primeras reuniones fue donde mas trabajo se realizo y se explicó a los miembros del grupo que no asistieron que debían estudiar y ponerse al día interpretando el código y probando las diferentes partes del proyecto. 
Observamos compromiso por parte de Josep, se verá su evolución y se comentará en las siguientes actas.

### Login
A continuación, procedemos a explicar el funcionamiento del login que utilizaremos para acceder a nuestra aplicación de notas online.

Para la implementación del login, hemos condensado los distintos login que utilizamos en el hito anterior para hacer pruebas de funcionamiento en un solo login. De esta manera podremos usarlo más facilmente y de una manera que se ajusta más a lo apropiado para este hito.

A pesar de no ser necesario para la consecucion de este hito, hemos decidido darle un diseño simple pero más vistoso utilizando Bootstrap. Las siguientes lineas de código serán necesarias para la utilización de ajax y de bootstrap.
``` html
<head>
	<title>Notas Online - Iniciar Sesión</title>
	<meta charset="UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <script src="https://kit.fontawesome.com/57989d593d.js" crossorigin="anonymous"></script>

    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" rel="stylesheet">

    <script crossorigin="anonymous" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

    <script crossorigin="anonymous" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
```
En este apartado de la memoria  no se entrará en detalles del funcionamiento de bootstrap.  En el codigo que podemos ver a continuación creamos una cabecera de la pagina donde se da la bienvenida y se da a elegir con dos botones entre hacer login como alumno o como profesor.
```html
<body>
	<header>
  <div class="p-5 text-center bg-dark text-light" style="background: red;" >
    <h1 class="mb-3">Bienvenid@ a Notas Online</h1>
    <h4 class="mb-3">Grupo 02</h4>
    <div class="btn-group" role="group" aria-label="Basic example">
  <button type="button" class="btn btn-light" data-toggle="modal" data-target="#exampleModalCenter">Alumno</button>
  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">Profesor</button>
	</div>
  </div>
</header>
```
Al pulsar en uno de estos botones se abrira una ventana modal en la que se nos pedira un DNI y una contraseña, al introducirlos podemos pulsar en un botón  de iniciar sesión y hacer un submit para confirmar si podemos logearnos o no es un usuario valido. Podemos ver el codigo de esta funcionalidad a continuación. Un aspecto interesante a explicar del siguiente fragmento de codigo es la utilizacion de *j_security_check* en el action del post asi como  *j_username  y  j_password* en el parametro nombre de sus respectivos inputs. Estos son los nombres estandarizados en la especificacion de servlets Java, de esta manera los servidores de la aplicación puede autentificar los contenedores independientemente de la aplicacion implementada. 
```html
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Iniciar Sesión</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body container">
		  <form method="post" action="j_security_check">
		  <div class="form-group">
		    <label for="dni">DNI</label>
		    <input type="text" name= "j_username" class="form-control" id="dni" aria-describedby="dniHelp" placeholder="Introduce tu DNI">
		  </div>
		  <div class="form-group">
		    <label for="pass">Contraseña</label>
		    <input type="password" name= "j_password" class="form-control" id="pass" placeholder="Introduce tu Contraseña">
		  </div>
		  <div class="container-fluid h-100"> 
        <div class="row w-100 mt-4 mb-4">
            <div class="col v-center">
                <button type="submit" value="Login" class="btn btn-primary d-block mx-auto">Iniciar Sesión</button>
            </div>  
        </div>
		</form>
		</div>
      </div>
    </div>
  </div>
</div>
```
El aspecto de el login sin haber abierto la ventana modal sera como se muestra en la siguiente figura.

![](http://personales.alumno.upv.es/virodbri/img/capturas/inicio-sinlogin.png)
> Figura 1.  

Y de la siguiente manera es como se visualizara con la ventana del login abierta.

![](http://personales.alumno.upv.es/virodbri/img/capturas/inicio-login.png)
> Figura 2.  

Como podemos ver en las imagenes, tambien hemos añadido un pie de pagina en el que hemos añadido nuestros nombres. Esto se ha hecho con el siguiente fragmento de codigo en el que creamos una lista dentro de un *footer* .

Si nos identificamos  de manera correcta como alumno accederemos a una pagina con la apariencia que vemos en la siguiente fiugra. En esta se da la bienvenida al usuario que se ha autentificado y un boton con el que accedemos a la consulta de asignaturas. Al pulsarlo se puede ver la lista que vemos más abajo en la figura. 

![](http://personales.alumno.upv.es/virodbri/img/capturas/alumno.png)
> Figura 3.  

De la misma manera, cuando nos identificamos de manera correcta como profesor accedemos a la página que vemos a contuniación. En esta también se nos da la bienvenida y se muestra una lista con las asignaturas y los alumnos de cada asignatura.

![](http://personales.alumno.upv.es/virodbri/img/capturas/profesor1.png)
> Figura 4.  

El metodo de autentificación utilizado es FORM en vez de BASIC como utilizamos anteriormente.
```xml
<login-config>
    <auth-method>FORM</auth-method>
    <form-login-config>
      <form-login-page>/login.html</form-login-page>
      <form-error-page>/error.html</form-error-page>
    </form-login-config>
  </login-config>
```
# Proceso de login mediante el filtro LoginControl.

Basamos nuestra decisión de operar mediante un filtro para optimizar el código y evitar repeticiones en los servlets que vamos a crear posteriormente.

Este filtro LoginControl lleva asociado un campo definido en web.xml denominado url-pattern que nos permite que una petición con cierto formato pase por nuestro filtro.
En este caso el patrón **/*** permite que cualquier petición tenga el formato que tenga pase por este filtro.
Además de este filtro también hemos realizado otro previamente llamado SessionControl, el cual es una aproximación a LoginControl y utiliza la autenticación BASIC en vez de FORM.
```xml
  <filter>
    <display-name>LoginControl</display-name>
    <filter-name>LoginControl</filter-name>
    <filter-class>main.LoginControl</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>LoginControl</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```
Ahora entrando en aspectos más profundos de nuestro código, podemos observar que en el filtro siempre se define una operación doFilter que nos permitirá crear una sesión para un usuario y contraseña concretos además de hacer la petición "login" a CentroEducativo.

En primer lugar se crea una sesión y se comprueba si existe el identificador de la sesión(key). Si no se ha iniciado sesión entonces se obtiene el nombre de usuario y la contraseña se establece a 123456 al ser única en CentroEducativo.
```java
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(true);
        if(session.getAttribute("key") == null) {
	            String user = req.getRemoteUser();
	            String pass = "123456";
```
Después se crea un objeto JSON para introducir las credenciales del usuario que ha iniciado sesión.
```java
        JSONObject cred = new JSONObject();
	        	cred.put("dni", user);
	        	cred.put("password", pass);
	            StringEntity entity = new StringEntity(cred.toString());
```
A continuación se define una BasicCookieStore, que nos permite almacenar las cookies entre diferentes peticiones.
```java
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();

```
Para crear la petición POST de login necesitamos estos 2 componentes:
1. La propia petición asociada a la URL correspondiente utilizando el constructor de la clase HttpPost incluida en la libreria HttpComponents.
2. La cabecera CONTENT_TYPE en la que se especifica que el contenido estará en formato JSON.
```java
		 HttpPost httpPost = new HttpPost("http://dew-virodbri-2021.dsic.cloud:9090/CentroEducativo/login/");
		 httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
```

Ahora mediante el uso de HttpEntity obtenemos el cuerpo de la respuesta que nos ofrece CentroEducativo. Este cuerpo de respuesta únicamente contendrá la clave de sesión(keyRes) que posteriormente será asignada a la variable de sesión key, junto al resto de variables de sesión (user y pass).


```java
		httpPost.setEntity(entity);
	                CloseableHttpResponse response1 = httpclient.execute(httpPost);
	                String keyRes = "-1";
	                    HttpEntity entity1 = response1.getEntity();
	                    try {
							keyRes = EntityUtils.toString(entity1);	
	                    }catch (ParseException e) {
						}
	                    EntityUtils.consume(entity1);
	                    response1.close();
```
Se comprobará para realizar esta asignación de variables de sesión si el código de respuesta es un 200 OK y también que la clave sea correcta. Se asignan a la sesión los parámetros dni, password, key y cookie.
```java
	                if(response1.getCode() == 200 && !keyRes.equals("-1")){
		            	session.setAttribute("dni", user);
			            session.setAttribute("password", pass);
			            session.setAttribute("key", keyRes);
			            session.setAttribute("cookie", cookieStore.getCookies());
	                }
```
Finalmente comprobamos si el usuario que se ha autenticado es un alumno o es un profesor mediante el uso de los roles, implementados anteriormente en  el fichero tomcat-users.xml y web.xml. Tras esta comprobación se redirige al usuario a su ventana correspondiente.
```java
	                if(req.isUserInRole("rolalu")) {
	                	req.getRequestDispatcher("/alumnoPrincipal.html").include(request, response);
	                	return;
	                }
	                else if(req.isUserInRole("rolpro")) {
	                	req.getRequestDispatcher("/profesorPrincipal.html").include(request, response);
	                	return;
	                }
	        }
			chain.doFilter(request, response);
	}
```

# Construcción y envío de las peticiones a CentroEducativo
------------
Para poder comunicarse con centro educativo, necesitamos que un servlet haga de "intermediario" con el mismo , ya que realmente el cliente no tiene acceso directo con la base de datos CentroEducativo.

Ante esta situación este servlet tendrá la capacidad de obtener la respuesta del mismo y devuelva al cliente la información que debe mostrar en el navegador.

En nuestro caso, hemos decidido llamar a este "servlet intermediario" alumnoApi. Este servlet admite peticiones tanto por el método GET y POST y empezará por obtener los datos del usuarió que lo llamó con el fin de ir construyendo la petición a CentroEducativo, estos datos son su dni, su key y su cookie.
```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreMaquina = "virodbri";
		HttpSession ses = request.getSession(false);
    	BasicCookieStore cookieStore = new BasicCookieStore();
    	CloseableHttpClient httpclient = 	 HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    	List<Cookie> cookies = (List<Cookie>) ses.getAttribute("cookie");
    	String dni = ses.getAttribute("dni").toString();
    	String key = ses.getAttribute("key").toString();
    	HttpGet httpGet = null;
```
------------

Una vez obtenido los datos del usuario, debemos comprobar que realmente tiene persmisos para realizar las peticiones correspondientes, esto lo comprobaremos con la siguiente linea:
```java
if(request.isUserInRole("rolalu")) {
```
------------

Ya seguros de que el usuario es del rol correspondiente, pasaremos a realizar la petición a CentroEducativo. Este servlet está creado para que pueda admitir varias peticiones a varios datos de CentroEducativo, estas peticiones se diferencian mediante un parámetro por POST el cual es *opcion*, dependiendo de que valor le asignemos a este parámetro alumnoApi sabrá que petición exacta debe realizar.
```java
String param = request.getParameter("opcion");
            response.setContentType("application/json");
    		if(param.equals("asignaturas")) {
	    		httpGet = new HttpGet("http://dew-"+nombreMaquina+"-2021.dsic.cloud:9090/CentroEducativo/alumnos/"+dni+"/asignaturas?key="+key);
    		} else if(param.equals("dni")) {
	    		httpGet = new HttpGet("http://dew-"+nombreMaquina+"-2021.dsic.cloud:9090/CentroEducativo/alumnos/"+dni+"?key="+key);
    		}
    	}else {
```
------------

Podría darse el caso , de que el usuario intentase realizar una petición "fuera de lugar", una petición que no le corresponde a el mismo saber, debido a esto, debemos gestionar el caso de error.
```java
response.setStatus(401);
response.getWriter().append("No tienes permitido realizar esta accion!");
return;
```
------------

Comprobados todos los apartados anteriores, debemos ahora ejecutar la petición, sin olvidarnos de añadir las cookies (La librería HttpComponents tiene una forma un poco "diferente" de gestionar las cookies). Una vez la petición se realice y CentroEducativo nos responda correctamente, simplemente deberemos colocar su respuesta como el cuerpo de respuesta de alumnoApi y ya habremos terminado.
```java
if(httpGet != null) {
	    	httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
	        cookieStore.addCookie(cookies.get(0));
	        CloseableHttpResponse response1 = httpclient.execute(httpGet);
	        String content = "-1";
	        HttpEntity entity1 = response1.getEntity();
	        if(response1.getCode() == 200) {
	            try {
	            	content = EntityUtils.toString(entity1);
	            }catch (ParseException e) {System.out.println("Error entity");}
	            EntityUtils.consume(entity1);
	            response1.close();
	    		response.getWriter().append(content);
	        }else {
	    		response.getWriter().append("No tienes permitido realizar esta accion!");
	        }
    	}
	}
```
------------

También esta la implementación de profesorApi, en esencia es exactamente lo mismo que alumnoApi pero con peticiones diferentes a CentroEducativo y con las comprobaciones correspondientes para asegurar que la petición es realizada por un usuario con el rol profesor.

# Interpretación de respuestas de CentroEducativo y retorno de las páginas HTML.
En primer lugar debemos destacar que la interacción entre nuestras hojas HTML y los resultados de las peticiones realizadas por los Servlets construidos se realiza utilizando jQuery y AJAX.

En este hito se han implementado tanto las primeras funcionalidades del "escenario de la alumna" como el "escenario del profesor".

Por ejemplo, analizando en profundidad el escenario de la alumna se realizarán principalmente dos peticiones AJAX a nuestro Servlet "alumnoApi.java" que será el encargado de devolvernos el contenido deseado para su posterior tratamiento.

A continuación se detalla el funcionamiento de la petición que nos ofrece las asignaturas matriculadas y la nota de estas, para un alumno especificado en concreto.

------------


En primer lugar comenzamos a construir la petición AJAX con la función `$(document).ready()`, esta nos permite que la peticion se ejecute una vez se ha cargado toda la página.
Posteriormente dentro de la petición se incluyen campos como la ruta del servlet que hace las peticiones (url de "alumnoApi"), el tipo de petición, que será del tipo POST para poder enviar parámetros al Servlet y que este discrimine las peticiones segun ese parametro. Otros campos pueden ser el tipo de contenido de la respuesta (json) y el propio parametro a enviar al servlet codificado como `opcion=asignaturas`.
```javascript
$(document).ready(function(){
		$.ajax({
			url: '/dew-NOL-2021/alumnoApi',
			type: 'POST',
			dataType: 'json',
			async: true,
			data: 'opcion=asignaturas',
```
Ahora en el parametro **success** se define la funcion que sera ejecutada si nuestra petición se ha realizado con éxito. En este caso, "data" será el contenido de respuesta de la petición que se realiza por medio del Servlet y que aquí procesamos. Con la funcion `$.each` recorremos el JSON data asociado e insertamos en nuestra hoja HTML los valores de asignatura y nota del alumno para dicha asignatura. Si ese alumno no tiene nota se pondrá como "No calificado".
```javascript
			success: function(data){
				$.each(data, function (index, val) {
					var notaFormatted = (val.nota == "") ? "No calificado" : val.nota;
					$("#insertar-asignaturas ul").append('<li class="list-group-item">' + val.asignatura + "		Nota: "+ notaFormatted + '</li>');
				  });
			},
```
Por último el campo error que ejecutara la función asociada cuando algo no funcione segun lo esperado y el codigo devuelto por la petición a CentroEducativo difiera de 200. Se insertará un simple "ERROR EN AJAX". 
```javascript
			error: function(){
				$("#insertar-asignaturas").append("<p>ERROR EN AJAX</p>");
			}
			});
		
```

------------

La diferencia principal entre esta y la anterior radica en el campo data, ahora no le enviamos `opcion=asignatura` enviamos `opcion=dni` para que nuestro Servlet "alumnoApi" diferencie entre las diferentes peticiones POST que se le hacen dentro de este fichero HTML asociado al alumno.
El procesamiento del fichero JSON de respuesta es parecido, añadiendo a nuestro documento el nombre y los apellidos en un encabezado H2.

```javascript
$.ajax({
            url: '/dew-NOL-2021/alumnoApi',
            type: 'POST',
            dataType: 'json',
            async: true,
            data: 'opcion=dni',
            success: function(data){
                $("#insertar-dni").append("<h2> Bienvenido " + data.nombre + " " + data.apellidos + "</h2>")
            },
            error: function(){
                $("#insertar-dni").append("<p>ERROR EN AJAX</p>");
            }
        })
```
De manera similar, se crean sucesivas peticiones AJAX utilizando el mismo procedimiento para insertar diferentes campos en nuestras páginas HTML. Por ejemplo en el "escenario de profesor" se realiza de manera similar la petición que ofrece lista de asignaturas que imparte ese profesor y lista de alumnos matriculados.
