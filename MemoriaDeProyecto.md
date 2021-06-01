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
Esta es la página de bienvenida a nuestra aplicación, en la que debemos de rellenar los campos del formulario que se nos abre en pantalla tras dar click a "Iniciar Sesión". Una vez hecho esto entraremos a la aplicación.
![](https://i.imgur.com/tb7vKA2.png)

------------
En caso de que ocurra algun fallo en la introducción de nuestras credenciales podremos ver que aparece una pantalla de error que nos volverá a redirigir a nuestra pantalla inicial.

![](https://i.imgur.com/UC7Qnap.png)

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


## 4. Aspectos relacionados con la implementación.
### 4.1 Identificación e inicio de sesión.
En este apartado describiremos los componentes involucrados en el inicio de sesión: el documento HTML de inicio de sesión (index.html) y el filtro asociado a nuestro login (LoginControl.java).

- **Descripción del documento index.html.**
Como en todos los ficheros con esta extensión, hemos optado por dotarlo con una apariencia lo más consistente posible con Bootstrap, pero como este ambito queda fuera de la funcionalidad y operatividad del login no lo trataremos.
![](https://i.imgur.com/tb7vKA2.png)
Como se ha descrito anteriormente contamos con una página centrada en un formulario principal donde introduciremos los datos con los que nos queremos identificar, aqui podemos observar el codigo que consigue esta apariencia.
```html
 <form method="post" action="j_security_check">

      <div class="form-group mb-3">
        <label for="dni">DNI</label>
        <input type="text" name= "j_username" class="form-control" id="dni" aria-describedby="dniHelp" placeholder="Introduce tu DNI">
      </div>

      <div class="form-group">
        <label for="pass">Contraseña</label>
        <input type="password" name= "j_password" class="form-control" id="pass" placeholder="Introduce tu Contraseña">
      </div>
	    
      <div class="container-fluid h-100"> 
          <div class="row mt-4 mb-4">
              <div class="col v-center">
                  <button type="submit" value="Login" class="btn btn-primary d-block mx-auto">Iniciar Sesión</button>
              </div>  
           </div>
          </div>
	</form>
```
En este fragmento del código nos topamos con 3 elementos a destacar.
**1.** El propio **formulario** que enviará los datos introducidos, que realizará la acción establecida como j_security_check tras ser enviado. Esta accion viene predeterminada por el tipo de autenticación FORM que estamos utilizando en la aplicacion y que es visible en el documento web.xml.
**2.** Los **campos del formulario** que recogen datos, tambien deben tener sus propios atributos "name" estandarizados por el metodo de autenticación, estos parametros son: **j_username** para el nombre de usuario y **j_password** para la contraseña.
**3.** Finalmente el botón que acciona el **envío del formulario** que aparece con el tipo **"submit"** para hacer efectiva dicha acción.

Si todo ocurre con éxito, entraremos a la aplicación, en caso contrario y como se describe en el apartado 3, entraremos a una pagina de error (**error.html**) que nos volvera a redireccionar a nuestro login. Esta página como elemento más destacable incluye una funcion javascript que permite la redirección tras 5000ms (5s).
```javascript
$(document).ready(function () {
    window.setTimeout(function () {
        location.href = "../dew-NOL-2021/login.html";
    }, 5000);
```
- **Descripción de la lógica del filtro LoginControl.java**

-  **Contextualización: clase User**.
Para hacer efectiva la separación entre el nivel de datos y la lógica de nuestra aplicación hemos optado por crear una clase asociada a los usuarios de la aplicación, esta clase es muy sencilla ya que unicamente contara con dos atributos: dni y password y dos metodos "get" para obtener los mencionados atributos.
```java
package main;

public class User {

	private String dni ;
	private String pass ;
	
	public User(String dni, String password) {
		this.dni = dni;
		this.pass = password;
	}
	
	public String getDni() {
		return this.dni;
	}
	public String getPassword() {
		return this.pass;
	}
}
```

Basamos nuestra decisión de operar mediante un filtro para optimizar el código y evitar repeticiones en los servlets que vamos a crear posteriormente.

Este filtro LoginControl lleva asociado un campo definido en web.xml denominado url-pattern que nos permite que una petición con cierto formato pase por nuestro filtro.
En este caso el patrón **/*** permite que cualquier petición tenga el formato que tenga pase por este filtro.
Además de este filtro también hemos realizado otro previamente llamado SessionControl, el cual es una aproximación a LoginControl y utiliza la autenticación BASIC en vez de FORM.
```xml
 <filter>
    <display-name>LoginControl</display-name>
    <filter-name>LoginControl</filter-name>
    <filter-class>main.LoginControl</filter-class>
    <init-param>
         <param-name>logPath</param-name>
         <param-value>/home/user/Escritorio/log-NOL-dew.log</param-value>
      </init-param>
	  <filter-mapping>
    <filter-name>LoginControl</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```

Ahora entrando en aspectos más profundos de nuestro código, podemos observar que en el filtro siempre se define una operación doFilter que nos permitirá crear una sesión para un usuario y contraseña concretos además de hacer la petición "login" a CentroEducativo.

En primer lugar procedemos a crear la funcionalidad de logger tal y como se trato en la primera sesión de este proyecto. Crearemos un objeto PrintWriter que nos permita escribir en un fichero, obtendremos la ruta de este fichero haciendo una extracción del parametro logFile que se encuentra en web.xml como se observa en el anterior codigo autocontenido.


En segundo lugar se crea una sesión y se comprueba si existe el identificador de la sesión(key). Si no se ha iniciado sesión entonces se obtiene el nombre de usuario y la contraseña se establece a 123456 al ser única en CentroEducativo.
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
Finalmente comprobamos si el usuario que se ha autenticado es un alumno o es un profesor mediante el uso de los roles, implementados anteriormente en el fichero tomcat-users.xml y web.xml. Tras esta comprobación se redirige al usuario a su ventana correspondiente.
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
