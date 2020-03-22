package com.example.CelisParcial1.DBMODELS;
import android.provider.BaseColumns;

public class DaoModelo implements BaseColumns {
    public static String DATABASE = "ContactosParcial.db";
    public static String TABLA_CONTACTOS = "Contactos";
    public static String COL_IDCONTACT = "_id";
    public static String COL_NAME = "nombre";
    public static String COL_APELLIDO = "apellido";
    public static String COL_TEL = "telefono";
    public static String COL_EMAIL = "email";
    public static String COL_CUMPLE = "cumpleanios";
    public static String COL_GRUPO = "grupo";

    public static final String SQL_CREATE_EVENTOS =
            "CREATE TABLE " + DaoModelo.TABLA_CONTACTOS + " (" +
                    DaoModelo.COL_IDCONTACT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DaoModelo.COL_NAME + " TEXT," +
                    DaoModelo.COL_APELLIDO + " TEXT," +
                    DaoModelo.COL_TEL + " TEXT," +
                    DaoModelo.COL_CUMPLE + " TEXT," +
                    DaoModelo.COL_GRUPO + " TEXT," +
                    DaoModelo.COL_EMAIL + " TEXT)";

    public static final String SQL_DELETE_EVENTOS =
            "DROP TABLE IF EXISTS " + DaoModelo.TABLA_CONTACTOS;

}

