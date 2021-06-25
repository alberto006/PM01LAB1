package com.aplicacion.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aplicacion.app01sqlite.transacciones.Transacciones;

public class ActivityIngresar extends AppCompatActivity {

    EditText nombres, apellidos, edad, correo, direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        Button btn = (Button)findViewById(R.id.btnagregar);
        nombres = (EditText)findViewById(R.id.txtnombre);
        apellidos = (EditText)findViewById(R.id.txtapellidos);
        edad = (EditText)findViewById(R.id.txtedad);
        correo = (EditText)findViewById(R.id.txtcorreo);
        direccion = (EditText)findViewById(R.id.txtdireccion);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarPersona();
            }
        });
    }

    private void AgregarPersona()
    {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDataBase,  null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());
        valores.put(Transacciones.direccion, direccion.getText().toString());

        Long resultado = db.insert(Transacciones.tablapersonas, Transacciones.id, valores);
        // Toast
        Toast.makeText(getApplicationContext(), "Registro Ingresado : " + resultado.toString(), Toast.LENGTH_LONG).show();

        db.close();

        ClearScreen();

    }

    private void ClearScreen()
    {
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
        direccion.setText("");
    }

}