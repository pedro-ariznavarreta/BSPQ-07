mvn datanucleus:schema-createCOOKMASTER README 
============================

Este proyecto es una aplicacion para la gestoría de un supermercado. En el que hay varios roles los cuales pueden acceder a la aplicación. Existe el rol de cliente el cual tiene acceso a una cuenta y a los productos, donde puede comprar y añadir al carrito varios productos. Por otra parte tenemos las clases de trabajador y gerente los cuales tienen permisos de administrador para añadir productos y añadir mas trabajadores o gerentes.

Para compilar el proyecto ejecutamos el siguiente comando:

    mvn clean compile
      

Para probar los test unitarios introduzca este comando:

    mvn test

Para encender la base de datos ejecutamos el siguiente comando:

    mysql –uroot -p < sql/create-messages.sql

Para enlazar la aplicación a la base de datos ejecutamos el siguiente comando:

    mvn datanucleus:schema-create
      
Para añadir datos de ejemplo ejecutamos el siguiente comando:

    mvn exec:java -P datos
    
Integration tests can be launched using the following command. An embedded Grizzly HTTP server will be launched to perform real calls to the REST API and to the MySQL database.

  	mvn verify -Pintegration-tests

Performance tests can be launched using the following command. In this example, these tests are the same integration tests but executed multiple times to calculate some statistics

  	mvn verify -Pperformance-tests


Para lanzar el servidor ejecutamos el siguiente comando:

    mvn jetty:run

Para ejecutar el cliente usamos el siguiente comando:

    mvn exec:java -Pclient



====================================================================================

Cliente
Para el correcto uso de la aplición hay que seguir estos pasos:

1. Iniciamos sesion o creamos una cuenta
2. Ahora nos saldrá para escoger los productos y añadirlos a favoritos o comprarlos.
3.

====================================================================================

Gerente o Trabajador
1. Iniciamos sesion como gerente o trabajador.
2. Podemos usar las siguientes credenciales : "admin", "admin" (Para iniciar sesión como gerente o trabajador) 

