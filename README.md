# HSqlServer
Minimalist HSqlDB server for TomEE.

This project allow you to manage one or more database inside a TomEE server.

Requires TomEE v8 and Java v11.

## How to configure?
Edit file src/main/resources/META-INF/apache-deltaspike.properties
- application.port is the port number to connect on. 
- application.databases is a list of databases to expose to other webapps. Format is: &lt;name&gt;:&lt;path&gt;;
