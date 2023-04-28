package com.example.proyectorecargas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Tigo extends AppCompatActivity {

    public CardView plan1t, plan2t, plan3t, plan4t;
    String keyname = null;
    String email, operadorM;
    public ImageView retroceder;
    int valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tigo);

        plan1t = findViewById(R.id.plan1t);
        plan2t = findViewById(R.id.plan2t);
        plan3t = findViewById(R.id.plan3t);
        plan4t = findViewById(R.id.plan4t);
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

        } catch (Exception e) {

        }
        plan1t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor = 16000;
                BasedatosSqlite admin = new BasedatosSqlite(Tigo.this, "admin", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor fila = db.rawQuery("select * from RegTable where Usuario=?", new String[]{keyname});
                if (fila.moveToFirst()) {
                    int consulta = fila.getInt(5);
                    if (consulta < valor) {
                        Toast.makeText(Tigo.this, "fondos insuficientes", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Intent intent = new Intent(Tigo.this, Venta.class);

                        String operadorM = "TIGO";
                        String descripcion = "Compra 5 GB recibe GRATIS 5 GB + Minutos y SMS nacional. Incluye: Facebook y WhatsApp";
                        intent.putExtra("Keyprecio", valor);
                        intent.putExtra("keyname", keyname);
                        intent.putExtra("operador", "tigo");
                        intent.putExtra("Keydescripcion", descripcion);
                        intent.putExtra("Keyemail", email);
                        intent.putExtra("Keyoperador", operadorM);
                        startActivity(intent);
                    }
                }
            }
        });

        plan2t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor = 31000;
                BasedatosSqlite admin = new BasedatosSqlite(Tigo.this, "admin", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor fila = db.rawQuery("select * from RegTable where Usuario=?", new String[]{keyname});
                if (fila.moveToFirst()) {
                    int consulta = fila.getInt(5);
                    if (consulta < valor) {
                        Toast.makeText(Tigo.this, "fondos insuficientes", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Intent intent = new Intent(Tigo.this, Venta.class);

                        String operadorM = "TIGO";
                        String descripcion = "Compra 10 GB recibe GRATIS 10 GB + Minutos y SMS nacional. Incluye: Facebook y WhatsApp";
                        intent.putExtra("Keyprecio", valor);
                        intent.putExtra("keyname", keyname);
                        intent.putExtra("operador", "tigo");
                        intent.putExtra("Keydescripcion", descripcion);
                        intent.putExtra("Keyemail", email);
                        intent.putExtra("Keyoperador", operadorM);
                        startActivity(intent);
                    }
                }
            }
        });

        plan3t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor = 5500;
                BasedatosSqlite admin = new BasedatosSqlite(Tigo.this, "admin", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor fila = db.rawQuery("select * from RegTable where Usuario=?", new String[]{keyname});
                if (fila.moveToFirst()) {
                    int consulta = fila.getInt(5);
                    if (consulta < valor) {
                        Toast.makeText(Tigo.this, "fondos insuficientes", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Intent intent = new Intent(Tigo.this, Venta.class);

                        String operadorM = "TIGO";
                        String descripcion = "2 GB + Minutos y SMS nacional";
                        intent.putExtra("Keyprecio", valor);
                        intent.putExtra("keyname", keyname);
                        intent.putExtra("operador", "tigo");
                        intent.putExtra("Keydescripcion", descripcion);
                        intent.putExtra("Keyemail", email);
                        intent.putExtra("Keyoperador", operadorM);
                        startActivity(intent);
                    }
                }
            }
        });

        plan4t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor = 3500;
                BasedatosSqlite admin = new BasedatosSqlite(Tigo.this, "admin", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor fila = db.rawQuery("select * from RegTable where Usuario=?", new String[]{keyname});
                if (fila.moveToFirst()) {
                    int consulta = fila.getInt(5);
                    if (consulta < valor) {
                        Toast.makeText(Tigo.this, "fondos insuficientes", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Intent intent = new Intent(Tigo.this, Venta.class);

                        String operadorM = "TIGO";
                        String descripcion = "Paquete 350 MB + WhatsApp y Facebook ";
                        intent.putExtra("Keyprecio", valor);
                        intent.putExtra("keyname", keyname);
                        intent.putExtra("operador", "tigo");
                        intent.putExtra("Keydescripcion", descripcion);
                        intent.putExtra("Keyemail", email);
                        intent.putExtra("Keyoperador", operadorM);
                        startActivity(intent);
                    }
                }
            }
        });
    }


}