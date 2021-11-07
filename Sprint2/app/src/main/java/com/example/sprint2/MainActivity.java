package com.example.sprint2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calcular = (Button) findViewById(R.id.btnCalcular);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rgSexo);

        EditText peso = (EditText) findViewById(R.id.txtBoxPeso);
        EditText edad = (EditText) findViewById(R.id.txtBoxEdad);
        EditText estatura = (EditText) findViewById(R.id.txtBoxEstatura);

        Spinner actividad = (Spinner) findViewById(R.id.cbActividad);


        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCalcular = new Intent(MainActivity.this, Resultados.class);

                radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                String strGenero = (String) radioButton.getText();

                String strActividad = actividad.getSelectedItem().toString();

                String strPeso = peso.getText().toString();
                String strEdad = edad.getText().toString();
                String strEstatura = estatura.getText().toString();

                intentCalcular.putExtra("genero", strGenero);
                intentCalcular.putExtra("peso", peso.getText().toString());
                intentCalcular.putExtra("edad", edad.getText().toString());
                intentCalcular.putExtra("estatura", estatura.getText().toString());
                intentCalcular.putExtra("actividad", actividad.getSelectedItem().toString());

                startActivity(intentCalcular);

                //finish();
            }
        });
    }
}