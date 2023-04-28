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

public class Movistar extends AppCompatActivity {
    public CardView plan1m, plan2m, plan3m, plan4m;
    String keyname = null;
    String email, operadorM;
    public ImageView retroceder;
    int valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movistar);

        plan1m = findViewById(R.id.plan1m);
        plan2m = findViewById(R.id.plan2m);
        plan3m = findViewById(R.id.plan3m);
        plan4m = findViewById(R.id.plan4m);
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
        plan1m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor = 500000;
                BasedatosSqlite admin = new BasedatosSqlite(Movistar.this, "admin", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor fila = db.rawQuery("select * from RegTable where Usuario=?", new String[]{keyname});
                if (fila.moveToFirst()) {
                    int consulta = fila.getInt(5);
                    if (consulta < valor) {
                        Toast.makeText(Movistar.this, "fondos insuficientes", Toast.LENGTH_SHORT).show();
                        return;
                    } else {

                        Intent intent = new Intent(Movistar.this, Venta.class);

                        String operadorM = "MOVISTAR";
                        String descripcion = "300 Minutos a  todo operado+ 20 Mensaje con Zoom, Skype,Teams ILIMITADO";
                        intent.putExtra("Keyprecio", valor);
                        intent.putExtra("keyname", keyname);
                        intent.putExtra("operador", "movistar");
                        intent.putExtra("Keydescripcion", descripcion);
                        intent.putExtra("Keyemail", email);
                        intent.putExtra("Keyoperador", operadorM);
                        startActivity(intent);
                    }
                }
            }
        });

        plan2m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor = 10000;
                BasedatosSqlite admin = new BasedatosSqlite(Movistar.this, "admin", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor fila = db.rawQuery("select * from RegTable where Usuario=?", new String[]{keyname});
                if (fila.moveToFirst()) {
                    int consulta = fila.getInt(5);
                    if (consulta < valor) {
                        Toast.makeText(Movistar.this, "fondos insuficientes", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Intent intent = new Intent(Movistar.this, Venta.class);

                        String operadorM = "MOVISTAR";
                        String descripcion = "Datos Ilimitados, Minutos y mensajes ilimitados a todo operador";
                        intent.putExtra("Keyprecio", valor);
                        intent.putExtra("keyname", keyname);
                        intent.putExtra("operador", "movistar");
                        intent.putExtra("Keydescripcion", descripcion);
                        intent.putExtra("Keyemail", email);
                        intent.putExtra("Keyoperador", operadorM);
                        startActivity(intent);
                    }
                }
            }
        });

        plan3m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor = 16000;
                BasedatosSqlite admin = new BasedatosSqlite(Movistar.this, "admin", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor fila = db.rawQuery("select * from RegTable where Usuario=?", new String[]{keyname});
                if (fila.moveToFirst()) {
                    int consulta = fila.getInt(5);
                    if (consulta < valor) {
                        Toast.makeText(Movistar.this, "fondos insuficientes", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Intent intent = new Intent(Movistar.this, Venta.class);

                        String operadorM = "MOVISTAR";
                        String descripcion = "Zoom, Skype, Teams Ilimitado";
                        intent.putExtra("Keyprecio", valor);
                        intent.putExtra("keyname", keyname);
                        intent.putExtra("operador", "movistar");
                        intent.putExtra("Keydescripcion", descripcion);
                        intent.putExtra("Keyemail", email);
                        intent.putExtra("Keyoperador", operadorM);
                        startActivity(intent);
                    }
                }
            }
        });

        plan4m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor = 23000;
                BasedatosSqlite admin = new BasedatosSqlite(Movistar.this, "admin", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor fila = db.rawQuery("select * from RegTable where Usuario=?", new String[]{keyname});
                if (fila.moveToFirst()) {
                    int consulta = fila.getInt(5);
                    if (consulta < valor) {
                        Toast.makeText(Movistar.this, "fondos insuficientes", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Intent intent = new Intent(Movistar.this, Venta.class);

                        String operadorM = "MOVISTAR";
                        String descripcion = "Datos Ilimitados, Minutos y Mensajes Ilimitados a todo operador";
                        intent.putExtra("Keyprecio", valor);
                        intent.putExtra("keyname", keyname);
                        intent.putExtra("operador", "movistar");
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