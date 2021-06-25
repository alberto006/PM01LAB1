package com.aplicacion.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.aplicacion.app01sqlite.tablas.Personas;
import com.aplicacion.app01sqlite.transacciones.Transacciones;

import java.sql.SQLData;
import java.util.ArrayList;

public class ActivityListView extends AppCompatActivity {
    // Variablea Globales de la Actividad
    SQLiteConexion conexion;
    ListView listapersonas;
    ArrayList<Personas> lista;
    ArrayList<String>  ArregloPersonas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        listapersonas = (ListView)findViewById(R.id.listapersonas);

        ObtenerListaPersonas();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloPersonas );
        listapersonas.setAdapter(adp);

        listapersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String informacion = "ID : " + lista.get(position).getId() +"\n";
                informacion += "Nombre : " + lista.get(position).getNombres();

                Toast.makeText(getApplicationContext(), informacion, Toast.LENGTH_LONG).show();

                Intent Compartir = new Intent();
                Compartir.setAction(Intent.ACTION_SEND);
                Compartir.putExtra(Intent.EXTRA_TEXT, informacion);
                Compartir.setType("text/plain");

                Intent Share = Intent.createChooser(Compartir, null);
                startActivity(Share);

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

        fillList();
    }

    private void fillList() {

        ArregloPersonas = new ArrayList<String>();
        for(int i = 0; i < lista.size(); i++)
        {
            ArregloPersonas.add(lista.get(i).getId() + " | "
                               +lista.get(i).getNombres() + " | "
                               +lista.get(i).getApellidos());
        }
    }
}