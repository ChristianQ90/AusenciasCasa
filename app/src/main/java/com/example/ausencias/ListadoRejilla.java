package com.example.ausencias;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListadoRejilla extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado_rejilla);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        GridView listadoAlumnos = (GridView) findViewById(R.id.listadoRejillaModulos);
        String[] datosModulos = new String[]{"Bases de datos","Programación","Entornos de desarrollo","Sistemas informáticos","IPE I","Lenguajes de marcas"};
        ArrayAdapter<String> adaptadorListadoModulos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datosModulos);
        listadoAlumnos.setAdapter(adaptadorListadoModulos);

    }
}