package com.example.ausencias;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuPrincipal extends AppCompatActivity {
    private TextView mensajeFirebaseAuth, mensajeFirebaseNormal;

    private DatabaseReference punteroUsuariosBBDD,punteroUsuarioParticular;

    private RecyclerView recyclerModulos;
    private AdaptadorModulo adaptadorModulos;
    private RecyclerView.LayoutManager layoutManagerModulos;

    MaterialButton mbtRecyclerAlumnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.vistaGeneral), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mensajeFirebaseAuth = findViewById(R.id.mensajeFirebaseAuth);
        mensajeFirebaseNormal = findViewById(R.id.mensajeFirebaseUsuario);

        String email = "";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            email = user.getEmail();
        }
        mensajeFirebaseAuth.setText("Bienvenid@ "+email);

        punteroUsuariosBBDD = FirebaseDatabase.getInstance("https://gestorausencias-default-rtdb.europe-west1.firebasedatabase.app").getReference("Usuarios");

        punteroUsuarioParticular = punteroUsuariosBBDD.child(email.replace(".","_"));
        Log.i("MENSAJE",punteroUsuarioParticular.getKey());

        List<String> listaModulos = new ArrayList<>();
        String[] nombreUsuario = {""};


        Log.w("TAMAÑO 1",""+listaModulos.size());

        punteroUsuarioParticular.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nombreUsuario[0] = snapshot.child("nombreUsuario").getValue(String.class);
                Log.i("MENSAJE 2",nombreUsuario[0]);

                DatabaseReference listaModulosBBDD = punteroUsuarioParticular.child("modulos");
                listaModulosBBDD.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot ds : snapshot.getChildren()){
                            String modulo = ds.getValue(String.class);
                            listaModulos.add(modulo);
                        }
                        Log.w("TAMAÑO 2",""+listaModulos.size());
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mensajeFirebaseNormal.setText("Bienvenid@ "+ nombreUsuario[0]);

        Log.w("TAMAÑO 3",""+listaModulos.size());

        ArrayList<Modulo> listadoModulosMostrar = new ArrayList<Modulo>();
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

        Modulo mTemp = new Modulo(R.drawable.outline_animation_24,"Programación","2ºDAM");
        Modulo mTemp2 = new Modulo(R.drawable.outline_animation_24,"Sistemas Informáticos","2ºDAM");
        Modulo mTemp3 = new Modulo(R.drawable.baseline_bug_report_24,"Bases de datos","1ºDAM");
        listadoModulosMostrar.add(mTemp);
        listadoModulosMostrar.add(mTemp2);
        listadoModulosMostrar.add(mTemp3);


        Log.w("TAMAÑO 4",""+listaModulos.size()+"..."+listadoModulosMostrar.size());

        adaptadorModulos = new AdaptadorModulo(listadoModulosMostrar,this,R.layout.elemento_lista);

        adaptadorModulos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicionClic = recyclerModulos.getChildAdapterPosition(view);
                String mensaje = "Has seleccionado "+listadoModulosMostrar.get(posicionClic).getNombre();
                View vistaGeneral = findViewById(R.id.vistaGeneral);
                Snackbar.make(vistaGeneral,mensaje,Snackbar.LENGTH_SHORT).show();
            }
        });

        recyclerModulos = findViewById(R.id.recyclerListaModulos);
        recyclerModulos.setAdapter(adaptadorModulos);
        layoutManagerModulos = new LinearLayoutManager(this);
        recyclerModulos.setLayoutManager(layoutManagerModulos);

        mbtRecyclerAlumnos = findViewById(R.id.botonRecyclerAlumnos);

        mbtRecyclerAlumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this,RecyclerViewAlumnos.class));

            }
        });
    }


}