package com.example.login.ModelosDeClases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;


import java.util.ArrayList;


public class MensajesAdapter extends RecyclerView.Adapter<MensajesAdapter.MensajesViewHolder> {

    private ArrayList<Mensaje> listaMensajes;
    private Context context;

    public MensajesAdapter(ArrayList<Mensaje> listaMensajes,Context context){
        this.listaMensajes=listaMensajes;
        this.context=context;
    }

    @NonNull
    @Override
    public MensajesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_mensajes, parent,false);
        return new MensajesViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MensajesViewHolder holder, int position) {
        holder.textViewMensaje.setText(listaMensajes.get(position).getMensaje().toString());

    }

    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }

    public static class MensajesViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMensaje;
        public MensajesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMensaje=(TextView) itemView.findViewById(R.id.textView15);
        }
    }
}
