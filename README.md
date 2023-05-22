mvn verify -Pintegration-testsmvn datanucleus:schema-createCOOKMASTER README 
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
    
Para hacer teses de integración:

    mvn verify -Pintegration-tests
    
Para hacer teses de rendimiento:

    mvn verify -Pperformance-tests
    
Para lanzar el servidor ejecutamos el siguiente comando:

    mvn jetty:run

Para ejecutar el cliente usamos el siguiente comando:

    mvn exec:java -Pclient



====================================================================================

Cliente 
Para el correcto uso de la aplicación, como cliente, hay que seguir estos pasos:

1. Iniciamos sesion directamente con (usuario=pedro, contraseña=1234) o creamos una cuenta
2. Ahora nos saldrá para escoger los productos y añadirlos a favoritos o comprarlos.
3. Cuando vas añadiendo los productos, se aumenta la cesta y cuando le das al boton de cesta, ves todos tus productos con la posibilidad de borrarlos de la cesta o pagarlos. Tambien se pueden ver los cupones existentes en el boton cupones.
4. Tras darle al boton pagar debes introducir tu numero de cuenta, tu cupon(opcional) y tras darle a pagar se te descargará un PDF con el ticket.
Documento de Cesta
5. Los usuarios tambien tendrán la opción de visualizar sus historiales de compra.



====================================================================================

Gerente
Para el correcto uso de la aplicación, como gerente, hay que seguir estos pasos:

1. Iniciamos sesion como gerente.
2. Podemos usar las siguientes credenciales : "admin", "admin" (Para iniciar sesión como gerente o trabajador)
3. La posibilidad de registrar un trabajador o gerente y de modificar los ajustes de los productos.
4. En el caso de modificar los ajustes del producto se podrán hacer tres acciones
	4.1 Añadir un producto nuevo a la lista
	4.2 Eliminar un producto si ya no esta disponible en el supermercado.
	4.3 Modificar los datos del producto.
5. En cada ventana se podrá cerrar sesión o volver.

 

