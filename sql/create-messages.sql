/* DELETE 'messagesDB' database*/
DROP SCHEMA IF EXISTS usuariosDB;
DROP SCHEMA IF EXISTS productosDB;
/* DELETE USER 'spq' AT LOCAL SERVER*/
DROP USER IF EXISTS 'spq'@'localhost';

/* CREATE 'usuariosDB' DATABASE */
CREATE SCHEMA usuariosDB;
CREATE SCHEMA productosDB;
/* CREATE THE USER 'spq' AT LOCAL SERVER WITH PASSWORD 'spq' */
CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';

GRANT ALL ON usuariosDB.* TO 'spq'@'localhost';