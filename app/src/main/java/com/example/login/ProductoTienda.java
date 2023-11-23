package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProductoTienda extends AppCompatActivity {
    TextView id = (TextView) findViewById(R.id.idd);
    TextView nom = (TextView) findViewById(R.id.nombre);
    TextView precio= (TextView) findViewById(R.id.precio);
    TextView descrip = (TextView) findViewById(R.id.descrip);
    TextView estado = (TextView) findViewById(R.id.efectType);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_tienda);

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