package com.aplicacion.app01sqlite.transacciones;

public class Transacciones
{
    /* tablas */
    public static final String tablapersonas = "personas";
    /* Campos */
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";
    /* tablas - CREATE , DROP */
    public static final String CreateTablePersonas = "CREATE TABLE personas( id INTEGER PRIMARY KEY AUTOINCREMENT, nombres TEXT, apellidos TEXT, edad INTEGER," +
                                                     "correo TEXT, direccion TEXT)";

    public static final String DropTablePersonas = "DROP TABLE IF EXISTS personas";

    /* Creacion del nombre de la base de datos */
    public static final String NameDataBase = "DBCurso";
}
