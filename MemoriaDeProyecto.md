# MEMORIA DEL PROYECTO NOTAS ONLINE
>#### Grupo 3TI11-02
> Integrantes: **Vicente Rodrigo Brisa, Mario Sánchez Rubio, Josep Molina Muñoz, Sergio Martínez Alejandre, Joel Pérez Gómez, Kraasimir Antonov Valev**

## Índice de contenidos:
- **1. Introducción a la estructura del proyecto.**
- **2. Justificación de tecnologías escogidas.**
- **3. Recorrido y guía de uso simplificada.**
- **4. Aspectos relacionados con la implementación.**
 -  **4.1 Identificación e inicio de sesión. (falta ver identificacion en otro pc y otra credencial)**
 - **4.2 Lógica de los servlets.**
 - **4.2.1 Explicacion servlet alumnoApi.java**
 - **4.2.2 Explicacion servlet profesorApi.java**
 - **4.3 Lógica de los formularios.**
 - **4.3.1 Explicacion de alumnoPrincipal.html (Josep)**
 - **4.3.2 Explicacion de profesorPrincipal.html (Vicente)**
- **5. Problemas y soluciones adoptadas. Testeo (Sergio)**
- **6. Actas de las reuniones y funcionamiento general del grupo (Mario)**

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
Esta clase será posteriormente utilizada en nuestro filtro LoginControl.java para poder hacer efectiva la mencionada separación.

------------

Continuando con la explicación de nuestro filtro comenzamos con la decisión que nos llevó a definirlo en la manera en la que se detallará. Basamos nuestra decisión de operar mediante un filtro para optimizar el código y evitar repeticiones en cuanto a obtención de parámetros involucrados con la sesión, en los servlets que vamos a crear posteriormente.

Este filtro LoginControl lleva asociado un campo definido en web.xml denominado url-pattern que nos permite que una petición con cierto formato pase por nuestro filtro.
En este caso el patrón **/* ** permite que cualquier petición tenga el formato que tenga pase por este filtro.

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

Ahora entrando en aspectos más profundos de nuestro código, debemos prestar especial atención en lo que ocurre en la funcion **init** y en los **atributos globales** que se definen dentro de nuestro filtro.

En primer lugar comenzando con los atributos globales crearemos 2: un archivo logFile, del que se hablará después y una Tabla Hash que contiene pares de valores String y Usuario.

```java
	File logFile;
	HashMap<String, User> usuarios = null;
```
En cuanto al método init, en el es donde precisamente reside nuestra separación logica-datos.
Como se muestra en el código hemos instanciado la Tabla Hash y le hemos comenzado a asignar valores para introducir a los usuarios que participaran en la aplicación. Ahora podremos acceder a nuestra aplicación utilizando el nickname que le asignamos en la Tabla Hash.
Tambien se realiza la creación del fichero dedicado al log de la aplicación, cuyo contenido será explicado posteriormente.

```java
public void init(FilterConfig fConfig) throws ServletException {
usuarios = new HashMap<String, User>();
		
		//Profesores
		usuarios.put("rgarcia", new User("23456733H","123456"));
		usuarios.put("peval", new User("10293756L","123456"));
		usuarios.put("manal", new User("06374291A","123456"));
		usuarios.put("jofon", new User("65748923M","123456"));
		
		//Alumnos
		usuarios.put("pegarsan", new User("12345678W","123456"));
		usuarios.put("marfergo", new User("23456387R","123456"));
		usuarios.put("miherllo", new User("34567891F","123456"));
		usuarios.put("laubentor", new User("93847525G","123456"));
		usuarios.put("minalpe", new User("37264096W","123456"));
		
		
		logFile = new File(fConfig.getInitParameter("logPath"));
		try {
			logFile.createNewFile();
		}catch(Exception e) {
			System.out.println("No se pudo crear el fichero");
		}
		
	}
	
```

En segundo lugar procedemos a crear la funcionalidad de logger tal y como se trato en la primera sesión de este proyecto. Crearemos un objeto **PrintWriter** que nos permita escribir en un fichero (logFile) que se encuentra inicialmente creado en el método init() como se menciona anteriormente.

```java
/*Logger de peticiones*/
 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		PrintWriter pw2 = new PrintWriter(new FileOutputStream(logFile,true));
		HttpServletRequest req = (HttpServletRequest) request;
		pw2.println(LocalDateTime.now().toString() + " " + req.getQueryString() + " " + req.getRemoteUser() + " "  + request.getRemoteAddr() + " " + req.getServerName() + " " + req.getRequestURI() + " " + req.getMethod());
		pw2.close();
		
```

En tercer lugar se crea una sesión y se comprueba si existe el identificador de la sesión(key). Si no se ha iniciado sesión entonces se obtiene el nombre de usuario mediante el método **getRemoteUser() **y la posterior operacion consultora de la TablaHash construida anteriormente y lo mismo ocurre con la contraseña. Ahora el nexo de la clase User con el filtro, se hace efectivo.
```java
	HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);
	        if(session.getAttribute("key") == null) {
	        	String userTomcat = req.getRemoteUser();
	            String user = usuarios.get(userTomcat).getDni();
	            String pass = usuarios.get(userTomcat).getPassword();;
```

Después se crea un **objeto JSON** para introducir las credenciales del usuario que ha iniciado sesión.

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

**1.** La propia **petición** asociada a la **URL** correspondiente utilizando el constructor de la clase **HttpPost** incluida en la libreria HttpComponents.

**2.** La cabecera** CONTENT_TYPE** en la que se especifica que el contenido estará en formato JSON.

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

También comprobamos si el usuario que se ha autenticado es un alumno o es un profesor mediante el uso de los roles, implementados anteriormente en el fichero tomcat-users.xml y web.xml. Tras esta comprobación se redirige al usuario a su ventana correspondiente.

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

Por ultimo trabajamos ciertos aspectos de seguridad en cierto punto, o más bien de pequeñas fallas o bugs en nuestra aplicación. El siguiente fragmento con el que concluye nuestro filtro de identificación LoginControl realiza comprobaciones relativas a las URL.

Ya que por ejemplo se podría introducir la URL del alumno (/alumnoPrincipal.html), estando en la pantalla del profesor y viceversa, realizando así un acceso incoherente a una página que nos generará numerosos errores, por no estar identificados como tal. 

Forzaremos que este comportamiento no ocurra haciendo una redirección a la misma página en la que nos encontramos para no poder salir de ahí en caso de que la URL sea modificada "maliciosamente".

```java
   if(req.getRequestURI().equals(req.getContextPath() + "/index.html") || 
	        		req.getRequestURI().equals("/dew-NOL-2021/") ) {
	        	if(req.isUserInRole("rolalu")) {
                	res.sendRedirect(req.getContextPath() + "/alumnoPrincipal.html");
                	return;
                }
                else if(req.isUserInRole("rolpro")) {
                	res.sendRedirect(req.getContextPath() + "/profesorPrincipal.html");
                	return;
                }
	        }else if( (req.isUserInRole("rolalu") && req.getRequestURI().equals(req.getContextPath() + "/profesorPrincipal.html")) ||
	        		(req.isUserInRole("rolpro") && req.getRequestURI().equals(req.getContextPath() + "/alumnoPrincipal.html")) ) {
	        	if(req.isUserInRole("rolalu")) {
                	res.sendRedirect(req.getContextPath() + "/alumnoPrincipal.html");
                	return;
                }
                else if(req.isUserInRole("rolpro")) {
                	res.sendRedirect(req.getContextPath() + "/profesorPrincipal.html");
                	return;
                }
	        }else {
	        	chain.doFilter(request, response);
	        }
	     }
        
	}
```
## 4.2 Lógica de los servlets

### 4.2.1 Explicación servlet alumnoApi.java
En este apartado vamos a centrarnos en explicar el funcionamiento del servlet encargado de comunicarse con CentroEducativo cuando nuestro rol es el de Alumno. Este servlet realiza peticiones POST, pero en el método doPost() hemos redireccionado todas estas peticiones al método doGET() del servlet. El objetivo de esta redirección es que todas las peticiones que hacemos a CentroEducativo nos devuelven la información específica según el argumento que le pasamos por la URL. Vamos a explicar cómo se crea la petición paso por paso.

En primer lugar, empezaremos creando la petición y los datos mínimos requeridos que necesitamos son el usuario, la clave y las cookies. Cada usuario se identifica por su DNI por lo que vamos a obtener de CentroEducativo el DNI del alumno. A continuación, necesitamos una clave la cual nos la proporciona también CentroEducativo y ya por último creamos las cookies como una lista de cookies. 

En segundo lugar, vamos a comprobar de que el alumno que hace la petición tiene el rol adecuado. Para ello vamos a hacer uso del método isUserInRole() y le especificamos que compruebe si el alumno tiene el ro rolalu. Si el alumno tiene ese rol tendrá los privilegios suficientes para obtener:
1)	Las asignaturas en las que está matriculado
2)	Su DNI
3)	El avatar de su usuario
4)	Los detalles de cada asignatura 
5)	El profesor que imparte dicha asignatura

En tercer lugar, cada una de estas cinco peticiones tiene su propia estructura y devuelve la información exacta que necesitamos. Cuando el parametro es avatar tiene una implementación mas detallada que se explica en el punto 4.2.2. También, cabe mencionar que no son peticiones secuenciales ya que se crean según el uso que el alumno este haciendo de la página web por lo que debemos separarlas respectivamente de la siguiente forma:

```java
String nombreMaquina = "masanru6";
if(param.equals("asignaturas")) {
	    		httpGet = new HttpGet("http://dew-"+nombreMaquina+"-2021.dsic.cloud:9090/CentroEducativo/alumnos/"+dni+"/asignaturas?key="+key);
	    		
    		} else if(param.equals("dni")) {
	    		httpGet = new HttpGet("http://dew-"+nombreMaquina+"-2021.dsic.cloud:9090/CentroEducativo/alumnos/"+dni+"?key="+key);
	    		
    		} else if(param.equals("avatar")) {
    			
    			ServletContext context = getServletContext();
    			String pathToAvatar = context.getRealPath("/WEB-INF/img");
    			
    			response.setContentType("text/plain");
    			response.setCharacterEncoding("UTF-8");
    			BufferedReader origen = new BufferedReader(new FileReader(pathToAvatar+"/"+dni+".pngb64"));
    			response.setContentType("text/plain");
    			
    			PrintWriter out = response.getWriter();
    			out.print("{\"dni\": \""+dni+"\", \"img\": \""); 
    			String linea = origen.readLine(); out.print(linea); 
    			while ((linea = origen.readLine()) != null) {out.print("\n"+linea);}
    			out.print("\"}");
    			out.close(); origen.close();
    			
    		}
    		else if(param.equals("detallesasig")) {
    			String acronimo = request.getParameter("acron");
	    		httpGet = new HttpGet("http://dew-"+nombreMaquina+"-2021.dsic.cloud:9090/CentroEducativo/asignaturas/"+acronimo+"?key="+key);
    		}else if(param.equals("profsasig")) {
    			String acronimo = request.getParameter("acron");
	    		httpGet = new HttpGet("http://dew-"+nombreMaquina+"-2021.dsic.cloud:9090/CentroEducativo/asignaturas/"+acronimo+"/profesores?key="+key);

}
```
En cuarto lugar, dependiendo de la petición que estemos haciendo en ese momento nuestro servlet comprobará si tenemos acceso a esos datos o no. Si no tenemos acceso, nos devolverá un error 401 – No tienes permitido realizar esta acción!. Esta comprobación la hemos implementado para evitar que un alumno pueda obtener los datos de otro alumno creando de forma manual la petición.
 
Por último, si todo ha concluido con éxito y la petición ha obtenido un código 200 tendremos los datos que necesitamos en local ya que CentroEducativo nos los habría proporcionado. Con esto podremos empezar a crear la página web del alumno con toda su respectiva información.


### 4.2.2 Explicacion servlet profesorApi.java
A continuación, se procede a explicar el funcionamiento del servlet encargado de intermediar con CentroEducativo cuando iniciemos sesión como profesor. Este servlet admite peticiones tanto por GET como por POST (ya que POST llama a GET), en las líneas de código mostradas a continuación podemos observar el inicio del metodo GET. En el declaramos un string *"nombreMaquina"* que utilizaremos para poder cambiar con facilidad la maquina en la que vamos a ejecutar la aplicación, ahorrándonos así tener que buscar en distintas líneas de código. Posteriormente, obtendrá los datos del profesor que lo llamó con el fin de ir construyendo la petición a CentroEducativo. Para ello se utilizara la sesión obtenida con el comando *request.getSession(False)* extrayendo de ella los atributos *dni* y *key* asi como la cookie.

```Java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Cambiar nombreMaquina a tu maquina con CentroEducativo
		 * */
		String nombreMaquina = "virodbri";
		/*
		 * Empezamos a preparar la peticion
		 * 
		 * */
		HttpSession ses = request.getSession(false);
    	BasicCookieStore cookieStore = new BasicCookieStore();
    	CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    	
    	List<Cookie> cookies = (List<Cookie>) ses.getAttribute("cookie");
    	String dni = ses.getAttribute("dni").toString();
    	String key = ses.getAttribute("key").toString();
    	HttpGet httpGet = null;
```

De la misma manera que en el servlet del alumno una vez obtenido los datos del usuario, comprobaremos que realmente tiene permisos para realizar las peticiones correspondientes, esto se realizará con la siguiente línea:
```java
/* Solo aquellos con rolpro pueden realizar esta operacion
    	 * */
    	if(request.isUserInRole("rolpro")) {
```

Si el usuario no es del rol correspondiente se devolverá un error 401 y se mostrara el mensaje que se ve en el siguiente fragmento de código
```java
}else {
    		response.setStatus(401);
    		response.getWriter().append("No tienes permitido realizar esta accion!");
    		return;
    	}
    	
```

Una vez nos aseguramos que el usuario es del rol correspondiente comenzamos a realizar las distintas peticiones a CentroEducativo. Como podemos observar en el siguiente fragmento de código lo haremos mediante el uso del parámetro*"opcion"* que obtendremos por POST, dependiendo de qué valor le asignemos a este parámetro, profesorApi sabrá que petición exacta debe realizar.
Dependiendo del valor de dicho parámetro podemos observar la petición para obtener el dni del profesor, las asignaturas, los alumnos de estas entre otras. Veremos otras peticiones que merecen una mención especial más adelante.
```java
String param = request.getParameter("opcion");
            response.setContentType("application/json");
            
    		if(param.equals("profasig")) {
	    		httpGet = new HttpGet("http://dew-"+nombreMaquina+"-2021.dsic.cloud:9090/CentroEducativo/profesores/"+dni+"/asignaturas?key="+key);
    		} else if(param.equals("asigalum")) {
    			String acronimo = request.getParameter("acronimo");
	    		httpGet = new HttpGet("http://dew-"+nombreMaquina+"-2021.dsic.cloud:9090/CentroEducativo/asignaturas/"+acronimo+"/alumnos?key="+key);
    		}else if(param.equals("getalumno")) {
    			String dnialumno = request.getParameter("dnialumno");
	    		httpGet = new HttpGet("http://dew-"+nombreMaquina+"-2021.dsic.cloud:9090/CentroEducativo/alumnos/"+dnialumno+"?key="+key);
    		}else if(param.equals("dni")) {
	    		httpGet = new HttpGet("http://dew-"+nombreMaquina+"-2021.dsic.cloud:9090/CentroEducativo/profesores/"+dni+"?key="+key);
    		}
```

A continuación, procedemos a explicar el funcionamiento de las peticiones que son distintas o más complejas que las anteriores. La siguiente petición corresponde a la de mostrar el avatar correspondiente al usuario. Para ello, en primer lugar, se obtiene la ruta donde están ubicadas las imágenes de los usuarios. Una vez obtenida la imagen que corresponde al avatar del usuario mediante la utilización de su dni se mostrara por pantalla con un *PrintWriter*.
```java
else if(param.equals("avatar")) {
    			
    			String dniparam = request.getParameter("dniavatar");
    			ServletContext context = getServletContext();
    			String pathToAvatar = context.getRealPath("/WEB-INF/img");
    			
    			response.setContentType("text/plain");
    			response.setCharacterEncoding("UTF-8");
    			BufferedReader origen = new BufferedReader(new FileReader(pathToAvatar+"/"+dniparam+".pngb64"));
    			response.setContentType("text/plain");
    			
    			PrintWriter out = response.getWriter();
    			out.print("{\"dni\": \""+dniparam+"\", \"img\": \""); 
    			String linea = origen.readLine(); out.print(linea); 
    			while ((linea = origen.readLine()) != null) {out.print("\n"+linea);}
    			out.print("\"}");
    			out.close(); origen.close();
    			return;
````

La siguiente petición que merece un tratamiento especial en su explicación es la correspondiente a asignar la nota a los alumnos de las asignaturas. Como podemos observar en el siguiente fragmento de código una vez obtenido el parámetro nota solo serán aceptados números en el rango del 0 al 10. Una vez comprobado que la nota es válida, mediante el dni y el acrónimo de la asignatura tendremos la ruta para realizar el *HttpPut* y con ello realizar el cambio de nota. También tendremos que añadir las cookies. Una vez hecho esto procedemos a devolver el contenido de respuesta de la petición put al html correspondiente.
```java
else if(param.equals("setnota")) {
    			Float nota = Float.parseFloat(request.getParameter("nota"));
    			if(nota >= 0 && nota <= 10 ) {
	    			String dnialum = request.getParameter("dnialumno");
	    			String acron = request.getParameter("acron");
	    			HttpPut httpPut = new HttpPut("http://dew-"+nombreMaquina+"-2021.dsic.cloud:9090/CentroEducativo/alumnos/"+dnialum+"/asignaturas/"+acron+"?key="+key);
	    			StringEntity notaChanged = new StringEntity(nota.toString());
	    			httpPut.setEntity(notaChanged);
	    			httpPut.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
	    	        cookieStore.addCookie(cookies.get(0));
	    	        
	    	        CloseableHttpResponse response1 = httpclient.execute(httpPut);	
	    	        String content = "-1";
	    	        HttpEntity entity1 = response1.getEntity();
	    	        
	    	        if(response1.getCode() == 200) {
	    	            try {
	    	            	content = EntityUtils.toString(entity1);
	    	            }catch (ParseException e) {System.out.println("Error entity");}
	    	            EntityUtils.consume(entity1);
	    	            response1.close();
	    	            response.setContentType("text/plain");
	    	    		response.getWriter().append(content);
	    	        }else {
	    	    		response.getWriter().append("No tienes permitido realizar esta accion!");
	    	        }
    			}else {
    				response.getWriter().append("La nota se ha podido actualizar");
    				response.setStatus(500);
    			}
    		}
```

Para finalizar este subapartado, procedemos a ejecutar la petición, sin olvidarnos de añadir las cookies. Una vez la petición se realice y CentroEducativo nos responda correctamente deberemos colocar su respuesta como el cuerpo de respuesta de profesorApi y ya habremos terminado. Esto se hara como se muestra en el siguiente fragmento de código.
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
```

## 4.3. Lógica de los formularios.

#### 4.3.1 Explicación de alumnoPrincipal.html 

Ahora vamos a explicar en que consiste la página en la que accede el alumno una vez ha iniciado sesión.
Esta página, al igual que las demás, está maquetada con Bootstrap; y los elementos como la barra de navegación superior (navBarAlum)

```<nav class="navbar navbar-expand-lg navbar-dark bg-dark" id="navBarAlum">```

o el pie de página (footerImp)

```<footer id="footerImp" style="visibility:hidden; position: fixed; left: 0; bottom: 0;width: 100%;  text-align: center;">```

se cargan al principio, ya que son estáticos y su función no cambia dependiendo del alumno. 
La página también contiene ítems dinámicos, que son los que mayor relevancia aportan para el usuario. Una vez cargados estos elementos estáticos, se cargarán los elementos dinámicos, que son los que cambian en función del alumno que se conecta. Para hacer el trabajo más fácil a la hora de actualizar el contenido, existen una serie de variables globales al principio de la etiqueta ```<script>``` que nos permiten introducir con más facilidad estos datos.

```js
var nombre ="";
var nota = 0;
var dni = "";
var modoDocumento = false;
```

Además, tenemos una serie de constantes al inicio de la ejecución de esta función que permite almacenar la URL y separar sus parametros. Esto nos servirá para más adelante.

```js
$(document).ready(function(){
		const queryString = window.location.search;
		const urlParams = new URLSearchParams(queryString);
		const param = urlParams.get('asignatura')
		// Peticiones AJAX

```
Básicamente existen 3 elementos centrales: la tarjeta del alumno, en la que se muestra el nombre, el avatar y el DNI; la tabla de asignaturas, en la que se muestran los nombres, los creditos y el cuatrimestre de cada asignatura que tiene el alumno; y los detalles de cada asignatura, cuya información está dentro de una ventana que se obtiene al hacer click dentro del nombre de una asignatura de la tabla.
Para obtener estos datos, la página efectua sendas peticiones AJAX, que procederemos a explicar a continuación.

###### Peticiones AJAX para la tarjeta del alumno

```js
//Petición del DNI
$.ajax({
	            url: '/dew-NOL-2021/alumnoApi',
	            type: 'POST',
	            dataType: 'json',
	            async: true,
	            data: 'opcion=dni',
	            success: function(data){
	            	nombre = data.nombre + " " + data.apellidos;
	            	dni = data.dni;
	                $("#insertar-dni").append(data.nombre + " " + data.apellidos )
	            },
	            error: function(){
					alert("Error en la petición de los datos del alumno")
	
	            }
	        })
```

Esta petición se encarga de, siempre que tenga éxito, obtener el valor del DNI del servlet AlumnoApi, construir el nombre y apellidos e introducirlo todo en la tarjeta. Al principio de la petición, accede al servlet alumnoApi, mediante POST, y le comunicará que desea obtener el DNI mediante «opción=dni». De esta petición se obtendrá unos datos tipo JSON, que se tratarán como se especifica en el apartado «success»: los datos se introducirán en las variables «nombre» y «dni», y posteriormente se concatenará una cadena con el nombre y apellidos del alumno en la etiqueta html con id «#insertar-dni». En caso de error, saltará un cuadro de alerta que comunicará el error.

```js
//Petición de la imagen del avatar
$.getJSON("/dew-NOL-2021/alumnoApi?opcion=avatar")
			.done(function(response){
			$("#esto").text(response.dni);
			$("#aqui").attr("src", "data:image/png;base64,"+response.img);
			})
			.fail(function(jqxhr, textStatus, error ) {
			 var err = jqxhr.response.replace(",", "\n"); // Pequeños ajustes
				alert("Error en la petición de la imagen del alumno :" + error)
			});
```

Esta petición se encarga de, siempre que tenga éxito, obtener el JSON de la imágen del avatar en formato base64. Se usa la petición getJSON, pidiendo la imágen y, al recibir el JSON, extrayendo el string del apartado data e introduciendolo en una etiqueta llamada «#aquí». El navegador interpreta automáticamente esta cadena como una imágen, de forma que ya no hacen falta tratamientos posteriores. En caso de error, saltará un cuadro de alerta que comunicará el error.

###### Petición AJAX de la tabla de asignaturas

```js
if(param == null){
			$("#mainAsigContent").hide()
		
			$.ajax({
				url: '/dew-NOL-2021/alumnoApi',
				type: 'POST',
				dataType: 'json',
				async: true,
				data: 'opcion=asignaturas',
				success: function(data){
	
					$.each(data, function (index, val) {
						var notaFormatted = (val.nota == "") ? "No calificado" : val.nota;
						console.log(data);
						var cuerpoAsig = document.getElementById("cuerpoAsig")
						var tr = document.createElement('tr')
						tr.id = val.asignatura
						var tdNombre = document.createElement('td')
						var enlaceAsig = document.createElement('a')
						var tdCurso = document.createElement('td')
						var tdCreditos = document.createElement('td')
						var tdCuatri = document.createElement('td')
						var tdNota = document.createElement('td')
						
						$.ajax({
				            url: '/dew-NOL-2021/alumnoApi',
				            type: 'POST',
				            dataType: 'json',
				            async: true,
				            data: 'opcion=detallesasig&acron='+val.asignatura,
				            success: function(data){
				            	tdCreditos.innerHTML = data.creditos
				            	tdCurso.innerHTML = data.curso
								tdCuatri.innerHTML = data.cuatrimestre
				            },
				            error: function(){
								alert("Error en la petición de detalles de las asignaturas")
	
				            }
				        })
				        
						enlaceAsig.innerHTML = val.asignatura
						enlaceAsig.setAttribute('href', "alumnoPrincipal.html?asignatura="+val.asignatura)
						tdNombre.appendChild(enlaceAsig)
						tdNota.innerHTML = notaFormatted
						
						tr.appendChild(tdNombre)
						tr.appendChild(tdCurso)
						tr.appendChild(tdCreditos)
						tr.appendChild(tdCuatri)
						tr.appendChild(tdNota)
						
						cuerpoAsig.appendChild(tr)
	
					    
					  });
					
				},
				error: function(){
					alert("Error en la petición de las asignaturas")
				}
				});		
```
Esta petición AJAX hace uso de las constantes de la URL que hay al principio de la función «$(document).ready». En el caso de que la URL no contenga ningún parámetro, es decir, que los parámetros sean «null», significará que la tabla debe aparecer. Lo primero que se ejecuta es un trozo de código que oculta los detalles de la asignatura, en caso de que estuvieran presentes o no. 

Luego se ejecuta la petición AJAX como tal, que en esencia, construirá la tabla de las asignaturas del alumno, con sus datos básicos y la nota del alumno.
Concretamente lo que hace es pedir un dato JSON a «alumnoApi», pasandole la opción asignaturas. Si tiene éxito, recorrerá cada uno de los datos del JSON (índice y valor), y a partir de estos datos irá maquetando la tabla, creando cada una de las etiquetas e introduciendo los datos donde debe.

Esta petición AJAX es particular, ya que tiene otra petición AJAX dentro de sí, la cual extraerá los datos de las asignaturas de manera similar a los demás JSON; salvo por el hecho de que en lugar de pasar una sola opción al servlet, pasa tanto la opción de asignaturas, como un dato acron que contiene el nombre de la asignatura con el objetivo de extraer sus datos concretos. Si tiene éxito se introducirán en el HTML los datos concretos de la asignatura, así como se acabará de maquetar la tabla y se introducirá un enlace en cada uno de los nombres para poder acceder a los detalles.

Si falla cualquiera de las peticiones JSON, saltará un cuadro de alerta que comunicará el error.

###### Petición AJAX de los detalles de las asignaturas
```js
else {
		
		$("#genDoc").hide()
		$("#mainAlumContent").hide()
		$.ajax({
			url: '/dew-NOL-2021/alumnoApi',
			type: 'POST',
			dataType: 'json',
			async: true,
			data: 'opcion=asignaturas',
			success: function(data){

				$.each(data, function (index, val) {
					if(val.asignatura == param){
						var notaFormatted = (val.nota == "") ? "No calificado" : val.nota;
						$.ajax({
				            url: '/dew-NOL-2021/alumnoApi',
				            type: 'POST',
				            dataType: 'json',
				            async: true,
				            data: 'opcion=detallesasig&acron='+val.asignatura,
				            success: function(data){
				            	$("#notaAsig").html("Nota: "+notaFormatted)
				            	$("#cuatriAsig").html("Cuatrimestre: "+data.cuatrimestre)
				            	$("#cursoAsig").html("Curso: "+data.curso)
				            	$("#credAsig").html("Créditos: "+data.creditos)
				            	
				            	var indexName = "("+data.acronimo+") " + data.nombre
								$("#nombreAsigIndex").html(indexName)
								
								$.ajax({
									url: '/dew-NOL-2021/alumnoApi',
									type: 'POST',
									dataType: 'json',
									async: true,
									data: 'opcion=profsasig&acron='+val.asignatura,
									success: function(data){

										$.each(data, function (indexpro, valpro) {
											$("#profeAsig").append("<li>"+valpro.nombre+" "+valpro.apellidos+"</li>")
										    
										});
										
									},
									error: function(){
										alert("Error en la petición de las asignaturas")
									}
								});	

				            },
				            error: function(){
								alert("Error en la petición de detalles de las asignaturas")
	
				            }
				        })
					}
				    
				  });
				
			},
			error: function(){
				alert("Error en la petición de las asignaturas")
			}
			});	
```
Esta petición en esencia, trata de construir el cuadro de los detalles de las asignaturas, con su nota, curso, profesor, cuatrimestre, créditos y descripción.
En el caso que se haya pinchado en un nombre de asignatura, se introducirán parámetros a la URL, de forma que se ejecutará este trozo de código, ocultando a la tabla de las asignaturas y creando este cuadro de información.

En la petición AJAX pedirá al servlet el json de las asignaturas, y si tiene éxito, recorrerá los datos de este JSON, comprobando si la asignatura del JSON coincide con la asignatura que se requieren los datos, y en caso afirmativo se introducirá la nota y se hará otra petición AJAX. Esta segunda petición enviará un segundo dato tipo acron con el nombre de «val.asignatura», para extraer los demás datos de la asignatura: cuatrimestre, curso, etc. Por último se anida una tercera petición AJAX que pide el JSON de los profesores, y recorre el JSON para extraer el dato de los profesores e introducirlo dentro del cuadro de la información detallada. 
Como siempre, en caso de fallo, salta una mensaje de error en la petición.

###### Generación de documento imprimible.

Esta página también dispone de una opción para la creación de un documento imprimible, que se encuentra en el botón ```id="genDoc"```. Este botón invoca la función «generarDocumento()».

```js
function generarDocumento(){
		document.getElementById('navBarAlum').style.visibility  = "hidden";
		$("#parrafoImprimible").html("DEW-Centro Educativo certifica que D/Dª "+nombre+" con DNI "+dni+" matriculado/a en el curso 2020/2021, ha obtenido las calificaciones que se muestran en la siguiente tabla.")
		const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
		var fechaHoy = new Date().toLocaleDateString('es-ES', options)
		$("#fechaImprimible").html("En Valencia, a " + fechaHoy)
		$('body,html').css({
			"height":"297mm",
			"width":"210mm"
		})
		document.getElementById('footerImp').style.visibility  = "visible";
		alert("¡Para volver a la vista web, pulsa el logo de NOTAS ONLINE!")

	}
```
Esta función estructura la página de forma que este en un formato apto para la impresión o para guardarlo como documento más comodamente. Justo al acceder, salta un cuadro emergente para avisar de que se puede acceder de vuelta a la página pulsando el logo.


## 6. Actas de reuniones y funcionamiento general del grupo.
En este apartado vamos a realizar una recopilación de las reuniones hasta la fecha y como se ha ido abordando el trabajo en todas ellas.
Describiremos las reuniones normalmente agrupandolas en sucesivos dias en los que se realizase la misma actividad o el mismo avance en el desarrollo.
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
# Acta de la reunión 4 y 5 del grupo DEW 3TI11-02.

> ##### Asistentes: Vicente, Joel, Mario, Josep, Krasimir y Sergio.

------------
###### Esta acta contiene las reuniones 4 y 5: la del 10 de mayo y la del 12 de mayo.
#### 1. Dinámica de trabajo.
Se establece claramente un plan de trabajo detallado a seguir para la actividad 2, con 4 pasos fundamentales para poder organizar el trabajo e ir desarrollando progresivamente la actividad en grupo.
En la actividad 3 se procede de la misma manera generando un plan compartido a todos los miembros del grupo detallando las actividades a desarrollar.

#### 2. Trabajo realizado.
Se termina en su totalidad la actividad número 2 y se comienza con los primeros detalles de la actividad 3.
En primer lugar se realizó el consenso para la elección de bibliotecas. Nos decantamos por utilizar HttpComponents y JSON-Java. 
Tras mirar documentación de todas las librerias propuestas nos quedamos con estas dos puesto que la forma en la que se desarrolla el código con ellas nos parece la más intuitiva y cómoda para nuestra forma de ver tanto las estructuras de las peticiones y respuestas HTTP, como, la estructura de los objetos JSON y su manipulación. Además hacer hincapié en que JSON-Java nos ofrece infinidad de formatos y constructores para la definición y control de estos objetos JSON.
Una vez descargadas e incorporadas al proyecto las librerías,  se procede con la actividad de inicio TheCatApi completando con éxito la operación GET y mostrandose correctamente las imágenes en la web.
Después se comenzó a indagar en los pasos 2 y 3 de nuestro plan que incluyen, la creación de roles en tomcat-users.xml, la modificación del web.xml para la incorporación de las auth-contraint y se desarrolló el filtro SessionControl siguiendo el seudocódigo ofrecido en el manual *(Trabajo en grupo NOL)*, que nos indica como transitar y comunicar la capa lógica con la capa de datos mediante el uso de una sesión, un objeto json con las variables a utilizar en la operación de login, y la propia petición POST de login a CentroEducativo.

Tras, ello se crea un formulario base (login3.html) para comenzar con la autenticación de los usuarios y también se crea un nuevo filtro LoginControl que selecciona los datos dni y pass de manera diferente al anterior Servlet "SessionControl". Se modifica también el Servlet HolaMundo para poder obtener la respuesta en formato JSON a una posible consulta a CentroEducativo.
> NOTA: La actividad de testeo se realizó desde la máquina de porta-ng de Vicente:
> **dew-virodbri-2020.dsic.cloud**

#### 3. Problemas acontecidos y soluciones propuestas
Ningún problema ocurrido en el transcurso de las sesiones, y se ofrece una posible solución ante problemas de compatibilidad de horario debido a la creciente carga de trabajo que la realización del proyecto supone y la compaginación con otras asignaturas.
La solución propuesta fue realizar una delegación de trabajo con previa explicación en un horario fuera del habitual al miembro del grupo que no pueda asistir a alguna reunión en particular.
Todos los miembros aceptaron la propuesta y se introdujeron nuevas jornadas de trabajo para intensificar la productividad.

# Acta de la reunión 6,7,8 del grupo DEW 3TI11-02.

> #### Asistentes: Vicente, Joel, Mario y Sergio.

------------
###### Esta acta contiene las reuniones 6,7 y 8: la del 14 de mayo, 16 de mayo y 17 de mayo.

#### 1. Dinámica de trabajo.
Se continua con la dinámica propuesta en las sesiones anteriores. Seguir con el plan de ejecución propuesto.
#### 2. Trabajo realizado.
Se comienza al desarrollo de las piezas desarrolladas en los requisitos de la tarea, se modifican ciertos ficheros para hacer una condensación de la información y hacer el proceso más simple. Comenzamos con el estudio individualizado de todas los integrantes del equipo de jQuery y Ajax para poder trabajar "ágilmente". Posteriormente se colabora en la construcción de las APIs y el diseño de las páginas HTML con Bootstrap. 
Se profundiza en el concepto de interconexión entre servlets y html ya que lo consideramos muy importante para el completo entendimiento del proyecto. 
Realizamos un trabajo paralelo desarrollando la parte de servlets en grupo y la parte de Ajax/jQuery también en grupo.

> NOTA: La actividad de testeo se realizó desde la máquina de porta-ng de Vicente:
> **dew-virodbri-2020.dsic.cloud**

#### 3. Problemas acontecidos y soluciones propuestas
Destacamos la falta de asistencia de Krasimir durante todas las reuniones, y la falta de asistencia de Josep a las 2 primeras. 
Las dos primeras reuniones fue donde más trabajo se realizó y se explicó a los miembros del grupo que no asistieron que debían estudiar y ponerse al día interpretando el código y probando las diferentes partes del proyecto. 
Observamos compromiso por parte de Josep, se verá su evolución y se comentará en las siguientes actas.

# Acta de la reunión 9, 10 y 11 del grupo DEW 3TI11-02.

> #### Asistentes: Joel, Mario, Vicente, Sergio, Krasimir y Josep.

###### Esta acta contiene las reuniones 6,7 y 8: la del 19 de mayo, 21 de mayo y 22 de mayo.

#### 1. Dinámica de trabajo.
Se detalla un nuevo plan de trabajo para completar las funcionalidades que faltaban por implementar. Procedemos a separar e identificar diferentes partes de la aplicación a implementar. Separamos el trabajo y trabajamos en las dudas generales del grupo.

#### 2. Trabajo realizado.
Se comienza a realizar el aspecto más definitivo en cuanto al estilo de Bootstrap de la aplicación. Se intenta consensuar un diseño uniforme, y se realiza con exito. Definimos unas plantillas uniformes para las paginas de alumno y profesor en local y posteriormente las implementamos dentro de la maquina virtual de linux.
Se sigue trabajando con AJAX y jQuery para seguir desarrollando las peticiones necesarias a los servlets, en este caso comenzamos a profundizar en mas detalles con la decodificación de las imagenes en base64 y se comienza tambien a realizar la implementación de la evaluación del profesor a los diferentes alumnos con la interfaz agil AJAX y la creacion de un "json Global" para acceder a los datos localmente como se indica en la documentación.
#### 3. Problemas acontecidos y soluciones propuestas
Algun problema de concepto entre los miembros del grupo debido a la novedad de los contenidos, se soluciona con busqueda e investigación en páginas oficiales.
Tras la solución de dichos problemas de concepto y la explicación a los miembros del grupo, las reuniones avanzan a un buen ritmo.

# Acta de la reunión 12, 13 y 14 del grupo DEW 3TI11-02.

> #### Asistentes: Vicente, Joel, Mario, Sergio, Krasimir y Josep.

###### Esta acta contiene las reuniones 6,7 y 8: la del 24 de mayo, 26 de mayo y 29 de mayo.

#### 1. Dinámica de trabajo.
Se prosigue con el plan de trabajo y se comienzan a perfilar los ultimos flecos de la aplicación.

#### 2. Trabajo realizado.
Se termina el aspecto del diseño con Bootstrap. Se realiza la implementación del diseño CSS para el documento imprimible, tras ciertas dudas sobre como abordarlo y la consulta al profesor, se realizan funciones que modifican la pagina del alumno para obtener dicho imprimible.

Se termina tambien la implementación de la funcionalidad de calificacion de profesor y se crea una nueva estructura dentro de las pestañas de las asignaturas de los profesores. Esta medida es consensuada por los miembros del grupo ya que estos parametros adicionales de media de los alumnos, aprobados, suspensos etc... dotan a la aplicación de una mayor completitud en su contenido.

Se realiza una reunion especial el dia 26 para acordar el miembro que se presentara al examen y en la reunion del dia 29 se cohesiona toda la información relativa a nuestro proyecto mediante una exposicion "estilo clase magistral" de los contenidos realizados en el proyecto para evitar dudas futuras.

Se tratan también aspectos de cara a la entrega final que deberiamos incluir en las actas y se comienza a distribuir el trabajo.
#### 3. Problemas acontecidos y soluciones propuestas
De nuevo durante las reuniones ocurre algun problema de concepto por parte de algunos componentes que son rapidamente solucionados mediante la explicación de fragmentos de codigo de nuestro proyecto.
El resto avanza adecuadamente.
# Acta de la reunión 15 del grupo DEW 3TI11-02.

> #### Asistentes: Vicente, Joel, Mario, Sergio, Krasimir y Josep.

###### Esta acta contiene la reunión del dia: 4 de junio.
#### 1. Dinámica de trabajo.
Se expone a los miembros del grupo soluciones ante posibles problemas tras la correccion del hito dos.

#### 2. Trabajo realizado.

Se solucionan los aspectos relacionados a la correccion del hito y se implementa una nueva página relacionada a detalles de la asignatura.
Se comenta tambien la necesidad de incluir algun apartado extra en las actas finales, como podria ser un apartado de pruebas de seguridad y un apartado que explique el posible acceso de un alumno con credenciales diferentes y que habria que hacer para lograrlo.

#### 3. Problemas acontecidos y soluciones propuestas
No hay problemas evidentes puesto que estamos en la etapa de casi finalizacion del proyecto. Los miembros tienen los conceptos claros.
