package com.example.proyectorecargas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2;

    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);



    }
    public void registrol(View v) {
        Intent intent = new Intent(this, registrousuarios.class);
        startActivity(intent);
        finish();

    }
    public void iniciarsesion (View v){
        BasedatosSqlite admin = new BasedatosSqlite(this,"admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String correo= et1.getText().toString();
        String contraseña=et2.getText().toString();


        try {

            Cursor fila= db.rawQuery("SELECT * FROM RegTable WHERE Email = ? and Clave1 = ?", new String[]{correo,contraseña});

            if (fila.moveToFirst()) {
                String emailUsuario = fila.getString(0);
                String passUsuario = fila.getString(3);
                String nombreUsuario = fila.getString(1);
                int dineroUsuario = fila.getInt(5);


                if (correo.equals(emailUsuario) && contraseña.equals(passUsuario)) {
                    Intent intent = new Intent(MainActivity.this, Menu.class);
                    intent.putExtra("Keynombre", nombreUsuario);
                    intent.putExtra("Keydinero", dineroUsuario);
                    intent.putExtra("Keydescripcion", dineroUsuario);
                    intent.putExtra("Keyemail", emailUsuario);
                  et1.setText("");
                  et2.setText("");
                    startActivity(intent);
                }
            } else {
                Toast toast = Toast.makeText(this, "**DATOS INCORRECTOS, POR FAVOR VERIFIQUE**", Toast.LENGTH_SHORT);
                toast.show();
            }
        } catch (Exception e){
            Toast toast = Toast.makeText(this,"ERROR"+ e.getMessage(),Toast.LENGTH_LONG);
            toast.show();
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación de acción");
        builder.setMessage("¿Estás seguro de que quieres salir de la aplicacion?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finishAffinity();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

}