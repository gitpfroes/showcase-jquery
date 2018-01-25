# showcase-jquery
A couple simple codes with jquery.

First, we will have a simple project demonstrating the basic of using JSON to populate a JQuery Datatable.

This project was created with:

- Eclipse IDE -  Oxygen.2 Release (4.7.2)
- Java 1.8
- Tomcat 8
- JQuery 3.3.1.min
- DataTables 1.10.16 min
- Maven (I'm using the one that comes with Eclipse)

To create a Maven Project, you can do that either on Eclipse, or via command line. Here are some good tutorials for both cases:

Eclipse: http://crunchify.com/how-to-create-dynamic-web-project-using-maven-in-eclipse/
Command line: https://www.mkyong.com/maven/how-to-create-a-web-application-project-with-maven/

It's possible that your project will have a compile error, regarding the HttpServlet class, so, add it in the Maven dependencies (pom.xml file):

		<!-- Servlet API -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>3.1.0</version>
			<scope>provided</scope>
		</dependency>

With that ready, let's create a simple some classes. The first one is our entity, I called it User, and put it in the package br.com.caprica.showcase.pojo. It has three attributes:

  - int id;
	- String name;
	- String job;

Next, let's create our Servlet. Again, you can do that in to ways: by creating a Java Class, or using the Eclipse shortcut to create a Servlet. The difference is just that if you create a java class, you will have to do the servlet configuration manually; with the Servlet shortcut, Eclipse does that for you.

The servlet is named ListUsersServlet, but you can choose whatever name you want. The important part is the mapping. The Servlet 3.0 specification gave us the option of annotating our servlets, instead of mapping all of them in the web.xml. I'm using way:

@WebServlet("/listusers")
public class ListUsersServlet extends HttpServlet {
  ...
}

The same thing with the XML configuration would look like this (in the web.xml file):

  <servlet>
  	<servlet-name>ListUsers</servlet-name>
  	<display-name>ListUsers</display-name>
  	<description></description>
  	<servlet-class>br.com.caprica.showcase.servlets.ListUsersServlet</servlet-class>
  </servlet>
  <servlet-mapping>
  	<servlet-name>ListUsers</servlet-name>
  	<url-pattern>/listusers</url-pattern>
  </servlet-mapping>
  
So, now, on our Servlet, let's implement the doGet method (which responds to HTTP GET Requests). I created a UserDAO to simulate a database layer. It has a listUsers method, that returns a List object, if a mock list of User objects. This is the method I'm gonna call on the Servlet:

  List<User> users = new UserDAO().listUsers();
  
Next, we have to convert that List object to a JSON string output. There are different ways to do that in Java, depending on which frameworks you are using. I choose to use the GSON Google library. To add the dependency on Maven, again, in the pom.xml:

		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
			<version>2.8.2</version>
		</dependency>
    
To convert our object:

		Gson gson = new Gson();
		String json = gson.toJson(users);
		System.out.println(json);

The System.out.println there is just to print in the console how the output looks like. It will be like this:

  [{"id":1,"name":"Laura Roslin","job":"President of the Twelve Colonies"},{"id":2,"name":"William Adama ","job":"Admiral"},{"id":3,"name":"Leland Adama","job":"Viper Pilot"},{"id":4,"name":"Kara Thrace","job":"Viper Pilot"},{"id":5,"name":"Gaius Baltar","job":"Computer Scientist "},{"id":6,"name":"Number Six","job":"Cylon"},{"id":7,"name":"Saul Tigh","job":"Executive Officer"},{"id":8,"name":"Sharon Valerii","job":"Lieutenant Junior Grade"},{"id":9,"name":"Karl C. Agathon","job":"Electronic Countermeasures Officer"},{"id":10,"name":"Galen Tyrol","job":"Senior Chief Petty Officer"},{"id":11,"name":"Felix Gaeta","job":"Lieutenant Junior Grade"},{"id":12,"name":"Anastasia Dualla","job":"Lieutenant Junior Grade"},{"id":13,"name":"Samuel T. Anders","job":"Pyramid Player"},{"id":14,"name":"Tom Zarek","job":"Prisioner"},{"id":15,"name":"Helena Cain","job":"Rear Admiral"},{"id":16,"name":"Sherman Cottle","job":"Chief Medical Officer"}]
  
  (If you are not familiar with JSON, here is a great tutorial: https://www.w3schools.com/js/js_json_intro.asp )

The last part in the Servlet is to print the JSON string to the response:

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
    
Now, we have to create a web page to list the JSON data in the user's browser. I intend to use this project also to show some bootstrap basics in the future, so let's already create our page with it. Go to the bootstrap site, and get the basic template:

https://getbootstrap.com/docs/3.3/getting-started/#template

Create a index.html page, and copy the HTML in that link. Important note: remove the jquery declaration at the end, otherwise we will get some jquery errors.

I didn't download the css or javascript used in this project, I used the CDN (Content Delivery Network) version, just to make things simple. In a professional project, it's recommended to always download it. Here is how we are gonna have it for now:

	<!-- Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- JQuery and Datatable -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
  
Next, we are gonna create the javascript function to get the data from our JSON, and load it in an HTML table:

	<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
	    $('#example').DataTable( {
	        "ajax": {
	            "url": "listusers",
	            "dataSrc": "",
	            "type": "GET"
	        },
	            
	        "columns": [
	        	{ "data": "id" },
	            { "data": "name" },
	            { "data": "job" }
	        ]
		});
	});
	</script>
  
Breaking that code:

 - $(document).ready is what tells you the page is ready for javascript manipulation. function() {...} is the code we wanna execute when that hapens. More here: https://learn.jquery.com/using-jquery-core/document-ready/
 - '#example' is the ID we are gonna attribute to our html table.
 - DataTable is the function to create our DataTable object.
    -> the "ajax" option is where we define the URL we are gonna call. In our case, is our servlet, so it's "listusers". The dataSrc option is to tell where our data array is in the JSON string. We are not using any identifier in it, so it's blank. Next, "type": "GET", just to ensure we are making an HTTP GET request. More about it here: https://datatables.net/manual/ajax
- next, we configurate our columns. The names you will put there are the ones in the JSON data array. For example, one object in our array looks like this:

{"id":1,"name":"Laura Roslin","job":"President of the Twelve Colonies"}

So, our columns are "id", "name" and "job".

Now, the only thing left is to make the declaration of the table:

	<div id="container">
		<div class="table-responsive">
			<table class="table" id="example">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Occupation</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

So, once the page is ready, jquery will call our doGet method, load the response in a DataTable object, and add it to a table with the id "example". 

Deploy the project on a Tomcat server, here is a tutorial to aid you, in case you need:http://crunchify.com/step-by-step-guide-to-setup-and-install-apache-tomcat-server-in-eclipse-development-environment-ide/

(Just a note: there is a trick part in configuring a Tomcat server on Eclipse: by default, it deploys your projects on an internal folder, called wtpwebapps. Personally, I don't like that, I prefer to have it on the Tomcat webapp folder. If you prefer this way too, after you created your server and before you do anything with it, follow the steps here: https://www.mkyong.com/eclipse/where-is-eclipse-deploy-web-application-tomcat/ )

Now, that would be all. Start your Tomcat, and open the URL of your project, in my case:

http://localhost:8080/showcase/

Because I defined in the pom.xml that my build name would be "showcase":

	<build>
		<finalName>showcase</finalName>
	</build>
