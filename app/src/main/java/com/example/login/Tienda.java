package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Tienda extends AppCompatActivity {

    private ArrayList<ProductoVo> listProductos;
    private AdapterDatos adaptador;
    private RecyclerView recyclerProd;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        listProductos=new ArrayList<>();
        recyclerProd=(RecyclerView) findViewById(R.id.recyclerId);
        recyclerProd.setLayoutManager(new LinearLayoutManager(this));

        llenarProductManual();
        AdapterDatos adapter=new AdapterDatos(listProductos,this);
        recyclerProd.setAdapter(adapter);
    }
    public void menuClick(){
        startActivity(new Intent(Tienda.this, MainActivity.class));
    }
    //public void logonClick(View v) {
      //  startActivity(new Intent(MainActivity.this, LoginActivity.class));
    //}
    private void llenarProductManual(){
        listProductos.add(new ProductoVo("VIDA",8,"mkver",10,"jre",2));
        listProductos.add(new ProductoVo("VIDA",8,"mkver",10,"jre",2));
        listProductos.add(new ProductoVo("VIDA",8,"mkver",10,"jre",2));
        listProductos.add(new ProductoVo("VIDA",8,"mkver",10,"jre",2));
        listProductos.add(new ProductoVo("VIDA",8,"mkver",10,"jre",2));
        listProductos.add(new ProductoVo("VIDA",8,"mkver",10,"jre",2));
        listProductos.add(new ProductoVo("VIDA",8,"mkver",10,"jre",2));
        listProductos.add(new ProductoVo("VIDA",8,"mkver",10,"jre",2));




    }
}