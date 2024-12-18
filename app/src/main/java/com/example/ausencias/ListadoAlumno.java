package com.example.ausencias;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class ListadoAlumno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado_alumno);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Alumno[] listaAlumnos = new Alumno[]{
                new Alumno("123123123A","Miguel","Pérez"),
                new Alumno("123123123B","Jose","Rojo"),
                new Alumno("123123123C","Juan","Zamora"),
                new Alumno("123123123D","Emilio","Muñoz"),
                new Alumno("123123123E","Claudia","González")
        };

        ListView listadoAlumnos = findViewById(R.id.listadoAlumnos);
        AdaptadorAlumno miAdaptadorAlumnos = new AdaptadorAlumno(this, listaAlumnos);
        listadoAlumnos.setAdapter(miAdaptadorAlumnos);

        listadoAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String alumnoSeleccionado = ((Alumno) parent.getItemAtPosition(position)).getNombre();
                Snackbar.make(view,"Se ha seleccionado: "+alumnoSeleccionado,Snackbar.LENGTH_LONG).show();
            }
        });

    }
}