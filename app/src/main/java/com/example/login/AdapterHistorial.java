package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.ModelosDeClases.Partida;

import java.util.ArrayList;

public class AdapterHistorial extends RecyclerView.Adapter<AdapterHistorial.ViewHolderDatos> implements View.OnClickListener {
    private ArrayList<Partida> listaPartidas;
    private Context context;
    private View.OnClickListener listener;

    public AdapterHistorial(ArrayList<Partida> listaPartidas, Context context){
        this.listaPartidas=listaPartidas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_historial, parent,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.fecha.setText(listaPartidas.get(position).getFecha().toString());
        holder.puntos.setText(String.valueOf(listaPartidas.get(position).getPuntos()));
        holder.dificultad.setText(String.valueOf(listaPartidas.get(position).getDif()));
    }


    @Override
    public int getItemCount() {
        return listaPartidas.size();
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView fecha, puntos, dificultad, mapa;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            fecha=(TextView) itemView.findViewById(R.id.fecha);
            puntos=(TextView)itemView.findViewById(R.id.puntos);
            dificultad=(TextView)itemView.findViewById(R.id.dif);

        }

    }
}
