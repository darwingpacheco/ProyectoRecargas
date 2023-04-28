package com.example.proyectorecargas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Claro extends AppCompatActivity  {
    public CardView plan1, plan2, plan3, plan4;
    String keyname = null;
    String email, operadorM;
    int valor;
    public ImageView retroceder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claro);


        plan1 = findViewById(R.id.plan1);
        plan2 = findViewById(R.id.plan2);
        plan3 = findViewById(R.id.plan3);
        plan4 = findViewById(R.id.plan4);
        retroceder = findViewById(R.id.retroceder);


        retroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        try {
            Bundle bundle = getIntent().getExtras();
            keyname = bundle.getString("keyname");
            email = bundle.getString("Keyemail");
            operadorM = bundle.getString("Keyoperador");

        }catch (Exception e){

        }


        plan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor = 4000;
                BasedatosSqlite admin = new BasedatosSqlite(Claro.this, "admin", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor fila = db.rawQuery("select * from RegTable where Usuario=?", new String[]{keyname});
                if (fila.moveToFirst()) {
                    int consulta = fila.getInt(5);
                    if (consulta < valor) {
                        Toast.makeText(Claro.this, "fondos insuficientes", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Intent intent = new Intent(Claro.this, Venta.class);

                        String operadorM = "CLARO";
                        String descripcion = "100 Minutos a todo destino + 1Gb de navegacion + Whatsapp e Instagram ilimitado";
                        intent.putExtra("Keyprecio", valor);
                        intent.putExtra("keyname", keyname);
                        intent.putExtra("Keydescripcion", descripcion);
                        intent.putExtra("operador", "claro");
                        intent.putExtra("Keyemail", email);
                        intent.putExtra("Keyoperador", operadorM);
                        startActivity(intent);
                    }
                }
            }
        });

        plan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor = 7000;
                BasedatosSqlite admin = new BasedatosSqlite(Claro.this, "admin", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor fila = db.rawQuery("select * from RegTable where Usuario=?", new String[]{keyname});
                if (fila.moveToFirst()) {
                    int consulta = fila.getInt(5);
                    if (consulta < valor) {
                        Toast.makeText(Claro.this, "fondos insuficientes", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Intent intent = new Intent(Claro.this, Venta.class);

                        String operadorM = "CLARO";
                        String descripcion = "300 Minutos a todo destino + 5Gb de navegación + Whatsapp e Instagram ilimitado";
                        intent.putExtra("Keyprecio", valor);
                        intent.putExtra("keyname", keyname);
                        intent.putExtra("Keydescripcion", descripcion);
                        intent.putExtra("operador", "claro");
                        intent.putExtra("Keyemail", email);
                        intent.putExtra("Keyoperador", operadorM);
                        startActivity(intent);
                    }
                }
            }
        });

        plan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor = 16000;
                BasedatosSqlite admin = new BasedatosSqlite(Claro.this, "admin", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor fila = db.rawQuery("select * from RegTable where Usuario=?", new String[]{keyname});
                if (fila.moveToFirst()) {
                    int consulta = fila.getInt(5);
                    if (consulta < valor) {
                        Toast.makeText(Claro.this, "fondos insuficientes", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Intent intent = new Intent(Claro.this, Venta.class);

                        String operadorM = "CLARO";
                        String descripcion = "Minutos y mensajes ilimitados a todo destino + 10Gb de navegación + Whatsapp e Instagram ilimitado";
                        intent.putExtra("Keyprecio", valor);
                        intent.putExtra("keyname", keyname);
                        intent.putExtra("Keydescripcion", descripcion);
                        intent.putExtra("operador", "claro");
                        intent.putExtra("Keyemail", email);
                        intent.putExtra("Keyoperador", operadorM);
                        startActivity(intent);
                    }
                }
            }
        });

        plan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor = 28000;
                BasedatosSqlite admin = new BasedatosSqlite(Claro.this, "admin", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor fila = db.rawQuery("select * from RegTable where Usuario=?", new String[]{keyname});
                if (fila.moveToFirst()) {
                    int consulta = fila.getInt(5);
                    if (consulta < valor) {
                        Toast.makeText(Claro.this, "fondos insuficientes", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Intent intent = new Intent(Claro.this, Venta.class);

                        String operadorM = "CLARO";
                        String descripcion = "Minutos y mensajes ilimitados a todo destino + 30Gb de navegación + Whatsapp e Instagram ilimitado";
                        intent.putExtra("Keyprecio", valor);
                        intent.putExtra("keyname", keyname);
                        intent.putExtra("Keydescripcion", descripcion);
                        intent.putExtra("operador", "claro");
                        intent.putExtra("Keyemail", email);
                        intent.putExtra("Keyoperador", operadorM);
                        startActivity(intent);
                    }
                }
            }
        });
    }


}