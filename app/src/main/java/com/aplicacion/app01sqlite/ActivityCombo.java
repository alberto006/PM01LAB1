package com.aplicacion.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.aplicacion.app01sqlite.tablas.Personas;
import com.aplicacion.app01sqlite.transacciones.Transacciones;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ActivityCombo extends AppCompatActivity {

    /* Variables Globales */
    SQLiteConexion conexion;
    Spinner comboPersonas;
    EditText txtnombre, txtapellidos, txtcorreo;

    ArrayList<String> ListaPersonas;
    ArrayList<Personas> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        comboPersonas = (Spinner)findViewById(R.id.comboPersona);
        txtnombre = (EditText) findViewById(R.id.txtnombres);
        txtapellidos = (EditText) findViewById(R.id.txtape);
        txtcorreo = (EditText) findViewById(R.id.txtemail);

        ObtenerListaPersonas();

        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ListaPersonas);
        comboPersonas.setAdapter(adp);

        comboPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txtnombre.setText(lista.get(position).getNombres());
                txtapellidos.setText(lista.get(position).getApellidos());
                txtcorreo.setText(lista.get(position).getCorreo());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void ObtenerListaPersonas() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas listPersonas = null;
        lista = new ArrayList<Personas>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablapersonas, null);

        while (cursor.moveToNext())
        {
            listPersonas = new Personas();
            listPersonas.setId(cursor.getInt(0));
            listPersonas.setNombres(cursor.getString(1));
            listPersonas.setApellidos(cursor.getString(2));
            listPersonas.setEdad(cursor.getInt(3));
            listPersonas.setCorreo(cursor.getString(4));
            listPersonas.setDireccion(cursor.getString(5));

            lista.add(listPersonas);
        }

        cursor.close();

        fillCombo();
    }

    private void fillCombo()
    {
        ListaPersonas = new ArrayList<String>();

        for(int i = 0; i < lista.size(); i++)
        {
            ListaPersonas.add(lista.get(i).getId() + " | "
                    +lista.get(i).getNombres() + " | "
                    +lista.get(i).getApellidos());
        }
    }

}