package com.example.ausencias;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListadoSencillo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listados);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listadoAlumnos = (ListView) findViewById(R.id.listadoAlumnos);
        String[] datosAlumnos = new String[]{"Miguel","Eduardo","Luis","Claudia","Julia","María"};
        ArrayAdapter<String> adaptadorListaAlumnos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datosAlumnos);
        listadoAlumnos.setAdapter(adaptadorListaAlumnos);

        listadoAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String elementoSeleccionado = (String) parent.getItemAtPosition(position);
                String elementoSel = (String) parent.getAdapter().getItem(position);
                Log.i("SELECCIÓN:",""+elementoSeleccionado);
            }
        });



    }
}