package com.example.proyectorecargas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BasedatosSqlite extends SQLiteOpenHelper {
    public  BasedatosSqlite (@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

       db.execSQL("create table Historial (email text, descripcion text, valor text, telefono text, fecha text, operadorB text)");
       db.execSQL("create table RegTable (Email text primary key,Usuario text,Telefono int, Clave1 int, ConfirmarClave int, Dinero int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }
}
