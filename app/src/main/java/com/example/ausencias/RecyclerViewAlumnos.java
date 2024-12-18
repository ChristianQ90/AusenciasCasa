package com.example.ausencias;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAlumnos extends AppCompatActivity {

    private RecyclerView recyclerAlumnos;
    private AdaptadorAlumnoRV adaptadorAlumnos;
    private RecyclerView.LayoutManager layoutManagerAlumnos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view_alumnos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //List<String> listaModulos = new ArrayList<>();
        ArrayList<Alumno> listadoAlumnosMostrar = new ArrayList<Alumno>();
        /*
        for (String s: listaModulos){
            if (s.equals("Bases de datos")){
                Modulo mTemp = new Modulo(R.drawable.outline_animation_24,s,"1ºDAM");
                listadoModulosMostrar.add(mTemp);
            } else {
                Modulo mTemp = new Modulo(R.drawable.baseline_bug_report_24,s,"2ºDAM");
                listadoModulosMostrar.add(mTemp);
            }

        }*/

        Alumno aTemp = new Alumno("326598653E","Juan Benito","del Castillo");
        Alumno aTemp2 = new Alumno("326598653Y","Carlos","Perea Bolengo");
        Alumno aTemp3 = new Alumno("326598653Z","Ramon","Castro Pinedo");
        Alumno aTemp4 = new Alumno("326598653Z","Roberto","Fontana Rosa");
        Alumno aTemp5 = new Alumno("326598653Z","Simon","Bolivar Juarez");
        Alumno aTemp6 = new Alumno("326598653Z","Ricardo","Perez Gomez");

        listadoAlumnosMostrar.add(aTemp);
        listadoAlumnosMostrar.add(aTemp2);
        listadoAlumnosMostrar.add(aTemp3);
        listadoAlumnosMostrar.add(aTemp4);
        listadoAlumnosMostrar.add(aTemp5);
        listadoAlumnosMostrar.add(aTemp6);


        //Log.w("TAMAÑO 4",""+listaModulos.size()+"..."+listadoAlumnosMostrar.size());

        adaptadorAlumnos = new AdaptadorAlumnoRV(listadoAlumnosMostrar,this,R.layout.elemento_lista);


        adaptadorAlumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicionClic = recyclerAlumnos.getChildAdapterPosition(view);
                String mensaje = "Has seleccionado "+listadoAlumnosMostrar.get(posicionClic).getNombre();
                View vistaGeneral = findViewById(R.id.main);
                Snackbar.make(vistaGeneral,mensaje,Snackbar.LENGTH_SHORT).show();
            }
        });

        recyclerAlumnos = findViewById(R.id.recyclerListadoAlumnos);;
        recyclerAlumnos.setAdapter(adaptadorAlumnos);
        layoutManagerAlumnos = new LinearLayoutManager(this);
        recyclerAlumnos.setLayoutManager(layoutManagerAlumnos);




    }
}