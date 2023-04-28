package com.example.proyectorecargas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registrousuarios extends AppCompatActivity {
    EditText nom,email,numero,contraseña,confirmar_contraseña;
    Button reg;

    private Cursor file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrousuarios);

        nom=findViewById(R.id.nom);
        email=findViewById(R.id.email);
        numero=findViewById(R.id.numero);
        contraseña=findViewById(R.id.contraseña1);
        confirmar_contraseña=findViewById(R.id.contraseña2);
        reg=findViewById(R.id.button);


        contraseña.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    String input = contraseña.getText().toString().trim();
                    if (!input.toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@.,-_;'/:#$%^&+=!])(?=\\S+$).{8,}$")) {
                        contraseña.setError("La contraseña debe contener:\n* Una letra mayúscula.\n * Un número. \n * Un carácter especial.\n * Longitud minima: 8.");
                        reg.setEnabled(false);
                    } else {
                        contraseña.setError(null);
                        reg.setEnabled(true);
                    }
                }
            }
        });


        contraseña.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro();
            }
        });
    }


    public void registro() {
        BasedatosSqlite admin = new BasedatosSqlite(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String usuarior = nom.getText().toString();
        String emailr = email.getText().toString();
        String numeror = numero.getText().toString();
        String clave = contraseña.getText().toString();
        String confircontraseña = confirmar_contraseña.getText().toString();
        int dinerr = 1000000;

        ContentValues values = new ContentValues();

      Cursor file= db.rawQuery("SELECT * FROM RegTable WHERE Email = ? OR Telefono = ?", new String[]{emailr,numeror});
        if(file.getCount()>0){
            Toast.makeText(this, "Este Email o este numero de Telefono ya esta registrado", Toast.LENGTH_SHORT).show();
            return;
        }

         else if(usuarior.isEmpty() && emailr.isEmpty() && numeror.isEmpty() && clave.isEmpty() && confircontraseña.isEmpty()){
          Toast.makeText(this, "**DILIGENCIAR TODOS LOS CAMPOS**", Toast.LENGTH_SHORT).show();
      }

        else if (usuarior.isEmpty()) {

            Toast.makeText(this, "!!POR FAVOR LLENE CAMPO **Nombre**", Toast.LENGTH_SHORT).show();
        } else if (emailr.isEmpty()) {
            Toast.makeText(this, "!!POR FAVOR LLENE CAMPO **Email**", Toast.LENGTH_SHORT).show();
        } else if (numeror.isEmpty()) {
            Toast.makeText(this, "!!POR FAVOR LLENE CAMPO **Telefono**", Toast.LENGTH_SHORT).show();
        }else if(clave.isEmpty()){
            Toast.makeText(this, "!!POR FAVOR LLENE CAMPO **Clave**", Toast.LENGTH_SHORT).show();
        } else if (confircontraseña.isEmpty()) {
            Toast.makeText(this, "!!POR FAVOR LLENE CAMPO **Validar Clave**", Toast.LENGTH_SHORT).show();
        }
        else if (!clave.equals(confircontraseña)) {
            Toast.makeText(this, "**POR FAVOR VERIFIQUE QUE LAS CONTRASEÑAS SEAN IGUALES*", Toast.LENGTH_SHORT).show();
        }

        else if(!isValidEmailId(email.getText().toString().trim())){
            Toast.makeText(this, "Digite un Email correcto", Toast.LENGTH_SHORT).show();

        } else if (numeror.length()<10) {
            numero.setError("El campo **Telefono** debe contener 10 Digitos ");
            return;
        } else {

            values.put("Email", emailr);
            values.put("Usuario", usuarior);
            values.put("Telefono", numeror);
            values.put("Clave1", clave);
            values.put("ConfirmarClave", confircontraseña);
            values.put("Dinero", dinerr);


            db.insert("RegTable", null, values);


            Toast toast = Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT);
            toast.show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            db.close();
    }

    }


    private boolean isValidEmailId(String emailr){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(emailr).matches();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(registrousuarios.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}