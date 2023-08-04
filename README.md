# DIED-TP

Trabajo practico integrador de la materia Diseño e Implementación de Estructuras de Datos, 1er cuatrimestre 2023.

Autores:
* Flores, José Ignacio
* Meyer, Nahuel
* Pallotti, Francisco

  ## Aclaraciones importanes
  * Actualmente el codigo esta configurado para acceder a una base de datos local. La clase dal.general.Conexion tiene atributos *url*, *username* y *password* donde puede cambiarse la base de datos a la que se accede.
  * El archivo *data.sql* inserta tuplas insertando su ID a mano, pero la BDD fue pensada para que el id se genere automaticamente usando SERIALs (debido a que asumimos que la base de datos no se pobla a mano como en ese caso sino usando el programa). Para evitar problemas de colision entre las IDs insertadas a mano y las generadas por la aplicación (generadas por una secuencia que arranca en '1'), se insertan tuplas con IDs en el orden de 10^5, para minimizar la probabilidad de colisiones.
