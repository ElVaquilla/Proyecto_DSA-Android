package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos>{
    private ArrayList<ProductoVo> listaProductos;
    private Context context;

    public AdapterDatos(ArrayList<ProductoVo> listaProductos, Context context){
        this.listaProductos=listaProductos;
        this.context = context;
        //listaProductos=new ArrayList<>();
    }
    /*public void setListaProductos(ArrayList<ProductoVo> listaProducts)
    {
        listaProductos = listaProducts;
        notifyDataSetChanged();
    }*/
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_productos, parent,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.etiN.setText(listaProductos.get(position).getNombre().toString());
        holder.etiD.setText(listaProductos.get(position).getEfect().toString());
        holder.etiNiv.setText(listaProductos.get(position).getPrecio().toString());
        //holder.etiP.setText(listaProductos.get(position).getPrecio().toString());

        //holder.foto.setImageResource(listaProductos.get(position).getFoto());

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView etiN, etiNiv, etiD;
        //ImageView foto;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            etiN=(TextView) itemView.findViewById(R.id.n_product);

            etiNiv=(TextView)itemView.findViewById(R.id.niv_product);
            etiD=(TextView)itemView.findViewById(R.id.p_product);
            //foto=(ImageView) itemView.findViewById(R.id.idImagen);

        }


    }
}


