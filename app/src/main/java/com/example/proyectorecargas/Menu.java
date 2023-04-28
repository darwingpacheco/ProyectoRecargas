package com.example.proyectorecargas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Menu extends AppCompatActivity {
    public TextView nombre, tv2;
    public String usuariomenu;
    public String email;
    public ImageView btnhistorial;
    public Context ctx= this;
    String dia;
    String diaActual;
    CardView claroname,movistarname, tigoname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guia);



//        BasedatosSqlite admin = new BasedatosSqlite(Menu.this, "admin", null, 1);
//        SQLiteDatabase db = admin.getWritableDatabase();
//        Cursor fila = db.rawQuery("select * from Historial where email=?", new String[]{email});



             nombre = findViewById(R.id.nombre);
             tv2 = findViewById(R.id.tv2);
             btnhistorial = findViewById(R.id.btnhistorial);
             claroname = findViewById(R.id.claroname);
             movistarname = findViewById(R.id.movistarname);
             tigoname = findViewById(R.id.tigoname);




        try {

            Bundle bundle = getIntent().getExtras();
            String dato = bundle.getString("Keynombre");
            usuariomenu = dato;
            int dinero = bundle.getInt("Keydinero");
            nombre.setText(dato);
           tv2.setText(String.valueOf(dinero));
           email= bundle.getString("Keyemail");
        } catch (Exception e){
            e.printStackTrace();
        }


        btnhistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Historialmain.class);
                intent.putExtra("keyname", usuariomenu);
                
                intent.putExtra("Keyemail", email);
                startActivity(intent);

            }
        });

        claroname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Claro.class);
                intent.putExtra("keyname", usuariomenu);
                intent.putExtra("Keyemail", email);
                startActivity(intent);

            }
        });
        movistarname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Movistar.class);
                intent.putExtra("keyname", usuariomenu);
                intent.putExtra("Keyemail", email);
                startActivity(intent);
            }
        });
        tigoname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Tigo.class);
                intent.putExtra("keyname", usuariomenu);
                intent.putExtra("Keyemail", email);
                startActivity(intent);
            }
        });

    }

    public void actualizarsaldo() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = df.format(calendar.getTime());
        String[] partes = fecha.split("-");
         dia = partes[2];

        BasedatosSqlite admin = new BasedatosSqlite(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT fecha FROM Historial WHERE email = ?", new String[]{email});
        if (cursor == null) {
            return;

        }

        String fechaActual = "";
        diaActual = "";
        if (cursor.moveToLast()) {//seleccionar de arriba hacia abajo
            fechaActual = cursor.getString(0);
            String[] partesActual = fechaActual.split("-");
            diaActual = partesActual[0];

            if (diaActual == null || diaActual.isEmpty()) {
                return;
            }
        }

        if (!dia.equals(diaActual)) {

            ContentValues newSaldo = new ContentValues();
            newSaldo.put("Dinero", 1000000);
            int saldoActualizado = db.update("RegTable", newSaldo, "email=?", new String[]{email});
            if (saldoActualizado == 1) {
                System.out.println("Actualizacion de saldo Exitosa");

            }
            Cursor saldo = db.rawQuery("select Dinero from RegTable where email=?", new String[]{email});
            if (saldo.moveToFirst()) {
                tv2.setText( saldo.getString(0));
                db.close();
            }


        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarsaldo();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
