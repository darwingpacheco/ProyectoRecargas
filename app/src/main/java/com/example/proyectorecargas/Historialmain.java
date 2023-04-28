package com.example.proyectorecargas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Historialmain extends AppCompatActivity {
    List<DatosVentaBD> elements;

    public String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historialmain);

        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("Keyemail");

        init();

    }
    public void init(){
        elements = new ArrayList<>();

        BasedatosSqlite admin = new BasedatosSqlite(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Historial WHERE email = ?"+"order by fecha desc", new String[]{email});

        while (cursor.moveToNext()){
            String email = cursor.getString(0);
            String descripcion = cursor.getString(1);
            String valor = cursor.getString(2);
            String telefono = cursor.getString(3);
            String fecha = cursor.getString(4);
            String operador = cursor.getString(5);

            DatosVentaBD datos = new DatosVentaBD(email, descripcion, valor, telefono, fecha, operador);
            elements.add(datos);
        }


        listAdapter lista = new listAdapter(elements, this);
        RecyclerView recycler = findViewById(R.id.listRecyclerView);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(lista);
    }

}