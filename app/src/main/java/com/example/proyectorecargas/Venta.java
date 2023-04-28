package com.example.proyectorecargas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class Venta extends AppCompatActivity {

    private TextView recibirdiner, fecha;
    private TextView nombre;
    private TextView descrip;
    private Spinner spinner;
    private EditText editText;
    private EditText telefono;
    private String mandarTelefono;

    private Cursor file;
    int dinero;
    String keyname;
    String email;
    String operadorU;
    String operador;
    String des;
    String consultando;

    String selectedItem;
    Button btn2;
    String contains;

    int x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_venta);

        spinner = findViewById(R.id.spinner);
        editText = findViewById(R.id.edit_text);
        recibirdiner = findViewById(R.id.recibirdiner);
        descrip = findViewById(R.id.descrip);
        nombre = findViewById(R.id.nombre);
        btn2 = findViewById(R.id.button2);
        fecha = findViewById(R.id.fecha);
        telefono = findViewById(R.id.telefono);
        setDinero();
        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("Keyemail");
        operadorU = bundle.getString("Keyoperador");
        btn2.setEnabled(false);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprar();
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                telefono.setText(selectedItem);
                telefono.setEnabled(false);

                telefono.setSelection(telefono.getText().length());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        telefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText.length() < 7 || telefono.length() != 3) {
                    btn2.setEnabled(false);
                } else {
                    btn2.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editText.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText.length() < 7 || telefono.length() != 3) {
                    btn2.setEnabled(false);
                } else {
                    btn2.setEnabled(true);
                }
            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        String[] opciones = new String[0];
        String[] opcionescl = {"", "313", "314", "315"};
        String[] opcionesmo = {"", "316", "317", "318"};
        String[] opcionestg = {"", "319", "320", "321"};


        switch (operador) {
            case "movistar":
                opciones = opcionesmo;
                break;
            case "claro":
                opciones = opcionescl;
                break;
            case "tigo":
                opciones = opcionestg;
                break;

            default:
                Toast.makeText(this, "no existe el operador", Toast.LENGTH_SHORT).show();
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void setDinero() {
        Bundle bundle = getIntent().getExtras();
        keyname = bundle.getString("keyname");
        dinero = bundle.getInt("Keyprecio");
        des = bundle.getString("Keydescripcion");
        recibirdiner.setText(String.valueOf(dinero));
        nombre.setText(keyname);
        descrip.setText(des);
        operador = bundle.getString("operador");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy \n\nhh:mma");
        String datetimeString = sdf.format(new Date());
        fecha.setText(datetimeString);


    }

    public void comprar() {

        BasedatosSqlite admin = new BasedatosSqlite(Venta.this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor fila = db.rawQuery("select * from RegTable where Usuario=?", new String[]{keyname});

        if (fila.moveToNext()) {
            x = fila.getInt(5);
            x -= dinero;
        }

        ContentValues registro = new ContentValues();
        registro.put("Dinero", x);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación de acción");
        builder.setMessage("¿Estás seguro de realizar la compra?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                guardar();

                int cant = db.update("RegTable", registro, "Usuario=?", new String[]{keyname});
                Intent intent = new Intent(Venta.this, Menu.class);
                intent.putExtra("Keynombre", keyname);
                intent.putExtra("Keyemail", email);

                Cursor fila1 = db.rawQuery("select * from RegTable where Usuario=?", new String[]{keyname});


                if (fila1.moveToNext()) {
                    intent.putExtra("Keydinero", fila1.getInt(5));
                } else {
                    return;
                }
                db.close();
                startActivity(intent);
                finish();
            }

        });
        builder.setNegativeButton("No", null);
        builder.show();
//            Bundle bundle = getIntent().getExtras();
//            String dato = bundle.getString("Keynombre");
//            nombre.setText(dato);

    }

    public void guardar() {
        BasedatosSqlite admin = new BasedatosSqlite(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String descripcion = des;
        String mandarTelefonos = telefono.getText().toString() + "" + editText.getText().toString();
        String valor = String.valueOf(dinero);
//        String telefono = editText.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mma");
        String datetimeString = sdf.format(new Date());

        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("descripcion", descripcion);
        values.put("valor", valor);
        values.put("telefono", mandarTelefonos);
        values.put("fecha", datetimeString);
        values.put("operadorB", operadorU);

        try {
            db.insert("Historial", null, values);
            db.close();
        } catch (Exception e) {
            Toast.makeText(this, "No se guardo la venta: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
