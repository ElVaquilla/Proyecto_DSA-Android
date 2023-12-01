package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProductoTienda extends AppCompatActivity {
    TextView id;
    TextView nom;
    TextView precio;
    TextView descrip ;
    TextView estado ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_tienda);
        id = (TextView) findViewById(R.id.idd);
        nom = (TextView) findViewById(R.id.nombre);
        precio= (TextView) findViewById(R.id.precio);
        descrip = (TextView) findViewById(R.id.descrip);
        estado = (TextView) findViewById(R.id.efectType);
        Bundle miBundle = this.getIntent().getExtras();
        if(miBundle!=null){
            Integer idd = miBundle.getInt("id");
            id.setText("Id: "+idd);

            String nomb = miBundle.getString("nombre");
            nom.setText(nomb);

            Integer pr = miBundle.getInt("precio");
            precio.setText("Precio: "+pr+" $");

            Integer stat = miBundle.getInt("efect");
            estado.setText("Estado: "+stat);

            String des = miBundle.getString("decrip");
            descrip.setText(des);
        }
    }
    public void salirClick(View view){
        finish();

    }
    public void comprarClick(View view){


    }
}