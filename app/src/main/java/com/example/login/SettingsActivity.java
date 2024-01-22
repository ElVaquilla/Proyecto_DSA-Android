package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    String elementos[] = {"Fácil", "Medio", "Difícil"};
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter=
                new ArrayAdapter<String>(this,R.layout.spinner_item, elementos);
        spinner.setAdapter(adapter);

        int posicionDeseada = SessionManager.getKeyDif(SettingsActivity.this);
        spinner.setSelection(posicionDeseada);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String dificultadSeleccionada = (String) parentView.getSelectedItem();
                int d = 0;
                if (dificultadSeleccionada.equals("Fácil")){
                    d = 0;
                } else if (dificultadSeleccionada.equals("Medio")){
                    d = 1;
                } else if (dificultadSeleccionada.equals("Difícil")){
                    d = 2;
                }
                Context context = SettingsActivity.this;
                SessionManager.dif(context, d);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });

        Switch switchWidget = findViewById(R.id.swOnOff);


        switchWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    System.out.println("Switch activado");
                } else {
                    // El switch está desactivado
                    System.out.println("Switch desactivado");
                }
                Context context = SettingsActivity.this;
                SessionManager.sonido(context, isChecked);
            }
        });

    }

    public void atrasClick(View view){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }



}