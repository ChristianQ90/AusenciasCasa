package com.example.ausencias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorModulo extends RecyclerView.Adapter<AdaptadorModulo.ViewHolder> {

    private ArrayList<?> listado;
    private Context contexto;
    private int r_layout_id;
    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public AdaptadorModulo(ArrayList<?> listado, Context contexto, int r_layout_id) {
        this.listado = listado;
        this.contexto = contexto;
        this.r_layout_id = r_layout_id;
    }


    @NonNull
    @Override
    public AdaptadorModulo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_modulo,parent,false);

        //Aquí estableceremos los eventos de clic
        elemento.setOnClickListener(onClickListener);


        return new ViewHolder(elemento);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorModulo.ViewHolder holder, int position) {
        Modulo m = (Modulo) listado.get(position);
        holder.representacionElementos(m);
    }

    @Override
    public int getItemCount() {
        return listado.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nombre, grupo;
        public ImageView foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreModuloElementoLista);
            grupo = itemView.findViewById(R.id.grupoModuloElementoLista);
            foto = itemView.findViewById(R.id.imagenModuloLista);
        }

        public void representacionElementos (Modulo m){
            nombre.setText(m.getNombre());
            grupo.setText(m.getGrupo());
            foto.setImageResource(m.getImagen());
        }
    }
}
