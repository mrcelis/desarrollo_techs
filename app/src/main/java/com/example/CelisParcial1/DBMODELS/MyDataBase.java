package com.example.CelisParcial1.DBMODELS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.CelisParcial1.Contact;

public class MyDataBase extends SQLiteOpenHelper {
    public MyDataBase(Context context){
        super(context,DaoModelo.DATABASE,null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(DaoModelo.SQL_CREATE_EVENTOS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DaoModelo.SQL_DELETE_EVENTOS);
        onCreate(db);
    }

    public long insertContacto(SQLiteDatabase db, Contact e){
        ContentValues values = new ContentValues();
        values.put(DaoModelo.COL_NAME, e.getName());
        values.put(DaoModelo.COL_APELLIDO, e.getLastname());
        values.put(DaoModelo.COL_TEL, e.getPhone());
        values.put(DaoModelo.COL_EMAIL, e.getEmail());
        values.put(DaoModelo.COL_CUMPLE, e.getBday());
        values.put(DaoModelo.COL_GRUPO, e.getGroup());

        long newRowId = db.insert(DaoModelo.TABLA_CONTACTOS, null, values);
        return newRowId;
    }

    public Cursor allContactos(SQLiteDatabase db){
        String[] col = {DaoModelo.COL_IDCONTACT, DaoModelo.COL_NAME, DaoModelo.COL_APELLIDO,DaoModelo.COL_TEL,DaoModelo.COL_EMAIL,DaoModelo.COL_CUMPLE,DaoModelo.COL_GRUPO};
        String orden = DaoModelo.COL_NAME + " ASC";

        Cursor cursor = db.query(DaoModelo.TABLA_CONTACTOS,col,null,null,null, null,orden);
        return cursor;
    }

    public void updateContacto(SQLiteDatabase db, Contact e){
        String[] id = {e.getId()};

        ContentValues values = new ContentValues();
        values.put(DaoModelo.COL_NAME, e.getName());
        values.put(DaoModelo.COL_APELLIDO, e.getLastname());
        values.put(DaoModelo.COL_TEL, e.getPhone());
        values.put(DaoModelo.COL_EMAIL, e.getEmail());
        values.put(DaoModelo.COL_CUMPLE, e.getBday());
        values.put(DaoModelo.COL_GRUPO, e.getGroup());

        db.update(DaoModelo.TABLA_CONTACTOS, values, "_id="+ "=?", id);
    }

    public void borrarContacto(SQLiteDatabase db, Contact e){
        String[] id = {e.getId()};
        db.delete(DaoModelo.TABLA_CONTACTOS,DaoModelo.COL_IDCONTACT + "=?", id);
    }

}
