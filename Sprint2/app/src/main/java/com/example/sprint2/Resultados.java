
package com.example.sprint2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Resultados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Intent intent = getIntent();
        Button volver = (Button) findViewById(R.id.btnVolver);

        String peso = intent.getStringExtra("peso");
        String estatura = intent.getStringExtra("estatura");

        String genero =intent.getStringExtra("genero");
        int edad = Integer.parseInt(intent.getStringExtra("edad"));

        String actividad = intent.getStringExtra("actividad");

        calcularImc(peso, estatura);
        calcularMetabolismo(peso, estatura, genero, edad, actividad);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void calcularImc(String peso, String estatura) {
        TextView imcPuntos = (TextView) findViewById(R.id.textImcPuntos);
        TextView nivelPeso = (TextView) findViewById(R.id.textNivelPeso);

        int _peso = Integer.parseInt(peso);
        double _estatura = Double.parseDouble(estatura)/100;
        double imc = (double) _peso / ((_estatura*_estatura)) ;
        imc = new BigDecimal(imc).setScale(2, RoundingMode.HALF_UP).doubleValue();

        String resultado ="";
        if (imc < 18.50){
            resultado = "Bajo peso";
        }else if (imc >= 18.50 && imc <= 24.99){
            resultado = "Normal";
        }else if (imc >= 25.0 && imc <= 29.99){
            resultado = "Sobrepeso";
        }else if (imc > 30){
            resultado = "Obesidad";
        }
        imcPuntos.setText("Su puntaje IMC es: " + imc);
        nivelPeso.setText("Su nivel de peso es: " + resultado);
    }


    private void calcularMetabolismo(String peso, String estatura, String genero, int edad, String actividad) {
        int _peso = Integer.parseInt(peso);
        int _estatura = Integer.parseInt(estatura);

        TextView metabolismo = (TextView) findViewById(R.id.metabolismoBasal);
        TextView calMantener = (TextView) findViewById(R.id.caloriasMantenerPeso);

        double TMB = 0; //  tasa metabólica basal
        //Fórmulas de Harris-Benedict
        if (genero.equals("Masculino")){
            TMB = ((13.397 * _peso) + (4.799 * _estatura) - (5.677 * edad)) + 88.362;
        }else if (genero.equals("Femenino")){
            TMB = ((9.247 * _peso) + (3.098 * _estatura) - (4.330 * edad)) + 447.593;
        }

        double mantener = 0;
        if (actividad.equals("Sedentario"))
            mantener = 1.2;
        else if (actividad.equals("Actividad ligera"))
            mantener = 1.375;
        else if (actividad.equals("Actividad moderada"))
            mantener = 1.55;
        else if (actividad.equals("Actividad intensa"))
            mantener = 1.1725;
        else if (actividad.equals("Actividad muy intensa"))
            mantener = 1.9;

        mantener *= TMB;

        mantener = new BigDecimal(mantener).setScale(2, RoundingMode.HALF_UP).doubleValue();
        TMB = new BigDecimal(TMB).setScale(2, RoundingMode.HALF_UP).doubleValue();

        metabolismo.setText("Metabolismo basal:\n" + TMB);
        calMantener.setText("Calorías necesarias para mantener el peso:\n" + mantener);

    }
}