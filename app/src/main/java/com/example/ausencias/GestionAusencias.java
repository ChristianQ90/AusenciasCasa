package com.example.ausencias;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GestionAusencias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gestion_ausencias);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.contenedorPrincipal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button botonInsertarAusencia, botonBorrarAusencia, botonConsultarAusencias;
        EditText campoTextoNombre, campoTextoFecha, campoTextoHoraAusencia;
        TextView textoResultadoConsulta;

        botonInsertarAusencia = findViewById(R.id.botonInsertarAusencia);
        botonBorrarAusencia = findViewById(R.id.botonBorrarAusencia);
        botonConsultarAusencias = findViewById(R.id.botonConsultarAusencia);
        campoTextoNombre = findViewById(R.id.campoTextoNombre);
        campoTextoFecha = findViewById(R.id.campoTextoFecha);
        campoTextoHoraAusencia = findViewById(R.id.campoTextoHora);
        textoResultadoConsulta = findViewById(R.id.textoResultadoConsulta);
        String nombreBBDD, fechaBBDD;
        int horaBBDD;

        SQLiteDatabase baseDatos;
        AusenciaSQLite baseDatosAusencias = new AusenciaSQLite(this,"BBDDAusencias",null,1);
        baseDatos = baseDatosAusencias.getWritableDatabase();

        botonInsertarAusencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //baseDatos.execSQL("INSERT INTO ausencias values('"+campoTextoNombre.getText()+"','"+campoTextoFecha.getText()+"',"+campoTextoHoraAusencia.getText()+")");

                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("nombre",String.valueOf(campoTextoNombre.getText()));
                nuevoRegistro.put("fecha",String.valueOf(campoTextoFecha.getText()));
                nuevoRegistro.put("hora", Integer.valueOf(String.valueOf(campoTextoHoraAusencia.getText())));
                baseDatos.insert("ausencias",null,nuevoRegistro);
            }
        });

        botonBorrarAusencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //baseDatos.execSQL("DELETE FROM ausencias WHERE nombre='"+campoTextoNombre.getText()+"'");

                //baseDatos.delete("ausencias","nombre='"+campoTextoNombre.getText()+"'",null);

                String[] argumentos = new String[]{String.valueOf(campoTextoNombre.getText())};
                baseDatos.delete("ausencias","nombre= ?",argumentos);



                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("nombre",String.valueOf(campoTextoNombre.getText()));
                baseDatos.update("ausencias",nuevoRegistro,"nombre=?",argumentos);


            }
        });

        botonConsultarAusencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] argsQuery = new String[]{"17/11/2022"};

                /*
                Cursor cursorConsultaAusencias = baseDatos.rawQuery("SELECT nombre, hora FROM ausencias WHERE fecha = ?",argsQuery);

                textoResultadoConsulta.setText("");

                if (cursorConsultaAusencias.moveToFirst()){
                    do {
                        String nombre = cursorConsultaAusencias.getString(0);
                        String hora = cursorConsultaAusencias.getString(1);
                        textoResultadoConsulta.append(nombre+" --> "+hora+"\n");
                    } while (cursorConsultaAusencias.moveToNext());
                }

                cursorConsultaAusencias.close();
                */
                String[] campos = new String[]{"nombre","hora"};
                Cursor cursorConsultaAusencias = baseDatos.query(false,"ausencias",campos,"fecha = ?",argsQuery,null, null,null, null);

                textoResultadoConsulta.setText("");

                if (cursorConsultaAusencias.moveToFirst()){
                    do {
                        String nombre = cursorConsultaAusencias.getString(0);
                        String hora = cursorConsultaAusencias.getString(1);
                        textoResultadoConsulta.append(nombre+" --> "+hora+"\n");
                    } while (cursorConsultaAusencias.moveToNext());
                }

                cursorConsultaAusencias.close();


            }
        });


    }
}