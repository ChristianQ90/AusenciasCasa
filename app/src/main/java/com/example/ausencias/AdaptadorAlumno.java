package com.example.ausencias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdaptadorAlumno extends ArrayAdapter {
    private Alumno[] alumnos;

    public AdaptadorAlumno(Context context, Alumno[] alumnos){
        super(context, R.layout.elemento_lista, alumnos);
        this.alumnos = alumnos;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View vista = mostrado.inflate(R.layout.elemento_lista,parent,false);

        TextView campoTextoDNI = vista.findViewById(R.id.textViewDNIAlumno);
        TextView campoTextoNombre = vista.findViewById(R.id.textViewNombreAlumno);
        TextView campoTextoApellidos = vista.findViewById(R.id.textViewApellidosAlumno);

        campoTextoDNI.setText(alumnos[position].getDni());
        campoTextoNombre.setText(alumnos[position].getNombre());
        campoTextoApellidos.setText(alumnos[position].getApellidos());

        return vista;
    }

}
