package com.example.ausencias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorAlumnoRV extends RecyclerView.Adapter<AdaptadorAlumnoRV.ViewHolder>{
    private ArrayList<?> listado;
    private Context contexto;
    private int r_layout_id;

    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public AdaptadorAlumnoRV(ArrayList<?> listado, Context contexto, int r_layout_id) {
        this.listado = listado;
        this.contexto = contexto;
        this.r_layout_id = r_layout_id;
    }

    @NonNull
    @Override
    public AdaptadorAlumnoRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_alumno,parent,false);

        //Aquí estableceremos los eventos de click
        elemento.setOnClickListener(onClickListener);//llama al método

        return new AdaptadorAlumnoRV.ViewHolder(elemento);
    }
    @Override
    public void onBindViewHolder(@NonNull AdaptadorAlumnoRV.ViewHolder holder, int position) {
        Alumno al = (Alumno) listado.get(position);
        holder.representacionElementos(al);
    }
    @Override
    public int getItemCount() {
        return listado.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreyApellidos;
        public TextView dni;
        public ImageView foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreyApellidos = itemView.findViewById(R.id.textViewNombreApe);
            dni = itemView.findViewById(R.id.textViewDNI);
            foto = itemView.findViewById(R.id.imageViewFotoAlumno);

        }
        public void representacionElementos (Alumno alumno){
            nombreyApellidos.setText(alumno.getNombre()+" "+alumno.getApellidos());
            dni.setText(alumno.getDni());
            foto.setImageResource(android.R.drawable.star_big_on);
        }
    }
}
