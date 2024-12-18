package com.example.ausencias;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistroUsuario extends AppCompatActivity {
    private DatabaseReference baseDatosAusencias;
    private FirebaseAuth firebaseAuth;

    private EditText campoNombreUsuario;
    private EditText campoContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.contenedorPrincipal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        campoNombreUsuario = findViewById(R.id.campoTextoNombreUsuario);
        campoContrasena = findViewById(R.id.campoTextoContrasena);


        CheckBox casillaProgramacion, casillaBasesDatos, casillaSistemas;
        Button botonRegistro;

        casillaProgramacion = findViewById(R.id.casillaProgramacion);
        casillaBasesDatos = findViewById(R.id.casillaBasesDatos);
        casillaSistemas = findViewById(R.id.casillaSistemas);
        botonRegistro = findViewById(R.id.botonRegistro);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pruebaausencias-default-rtdb.europe-west1.firebasedatabase.app/");
        baseDatosAusencias = database.getReference("Usuarios");// es un puntero al nodo usuarios
        firebaseAuth = FirebaseAuth.getInstance();

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> modulosSeleccionados = new ArrayList<>();

                if (casillaProgramacion.isChecked()) modulosSeleccionados.add("Programación");
                if (casillaBasesDatos.isChecked()) modulosSeleccionados.add("Bases de datos");
                if (casillaSistemas.isChecked()) modulosSeleccionados.add("Sistemas informáticos");

                String usuarioIntro = String.valueOf(campoNombreUsuario.getText());
                String contrasenaIntro = String.valueOf(campoContrasena.getText());

                firebaseAuth.createUserWithEmailAndPassword(usuarioIntro, contrasenaIntro) // Para crear usuarios en authentication, copiado de Firebase menos las ultimas dos lineas
                        .addOnCompleteListener(RegistroUsuario.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("REGISTRO", "createUserWithEmail:success");
                                    FirebaseUser user = firebaseAuth.getCurrentUser();

                                    // usuarioRegistrado = new Usuario(String.valueOf(campoNombreUsuario.getText()).replace(".","_"),modulosSeleccionados);
                                    anadirUsuario(String.valueOf(campoNombreUsuario.getText()).replace(".","_"),modulosSeleccionados);
                                    //baseDatosAusencias.child("Usuarios").child(usuarioRegistrado.getNombreUsuario()).setValue(usuarioRegistrado);

                                    startActivity(new Intent(RegistroUsuario.this, MainActivity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("REGISTRO", "createUserWithEmail:failure", task.getException());
                                    Snackbar.make(v,"Fallo en el registro.",Snackbar.LENGTH_LONG).show();
                                    recargarInterfaz();
                                }
                            }
                        });
            }
        });
    }

    public void anadirUsuario(String nombre, List<String> modulosImpartidos) {
        Usuario usuarioRegistrado = new Usuario(nombre,modulosImpartidos);
        baseDatosAusencias.child(nombre).setValue(usuarioRegistrado);
    }

    public void recargarInterfaz(){
        campoNombreUsuario.getText().clear();
        campoContrasena.getText().clear();
    }
}