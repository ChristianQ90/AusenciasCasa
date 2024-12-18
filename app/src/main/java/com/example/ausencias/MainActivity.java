package com.example.ausencias;

import android.content.Intent;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextInputEditText campoUsuario;
    private TextInputEditText campoContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.contenedorPrincipal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textViewTituloPI;
        textViewTituloPI = findViewById(R.id.textViewTituloPantallaInicio);

        firebaseAuth = FirebaseAuth.getInstance();

        campoUsuario = findViewById(R.id.campoTextoNombreMaterial);
        campoContrasena = findViewById(R.id.campoTextoContrasenaMaterial);


        MaterialButton botonInicioSesion = (MaterialButton) findViewById(R.id.botonRecyclerAlumnos);
        botonInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuarioIntroducido = String.valueOf(campoUsuario.getText());
                String contrasenaIntroducida = String.valueOf(campoContrasena.getText());
                firebaseAuth.signInWithEmailAndPassword(usuarioIntroducido, contrasenaIntroducida)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.i("INICIO_SESION", "signInWithEmail:success");
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    startActivity(new Intent(MainActivity.this, MenuPrincipal.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.i("INICIO_SESION", "signInWithEmail:failure", task.getException());
                                    Snackbar.make(v,"Fallo en el inicio de sesión",Snackbar.LENGTH_LONG).show();
                                    recargarInterfaz();
                                }
                            }
                        });

            }
        });

        MaterialButton botonRegistro = (MaterialButton) findViewById(R.id.botonRegistroMaterial);
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistroUsuario.class));
            }
        });

    }

    public void recargarInterfaz(){
        campoUsuario.getText().clear();
        campoContrasena.getText().clear();
    }
}