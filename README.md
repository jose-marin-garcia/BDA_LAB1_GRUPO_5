# BDA_LAB1

Pasos para ejecutar el programa:

1.-Abra pgadmin

2.-Abra una consola en la direccion dentro de Database y ejecute el siguiente comando: "psql -U El usuario con el que instalo postgres -h localhost -p 5432 -f dbCreate.sql"

3.-Ahora en la misma consola ejecute el siguiente comando para hacer el rellenado de datos:"psql -U El usuario con el que instalo postgres -h localhost -d lab1bda -f dump.sql"

psql -U postgres -h localhost -d lab1bda -f dump.sql