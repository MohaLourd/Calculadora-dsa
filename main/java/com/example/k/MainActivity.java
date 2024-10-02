package com.example.k;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private TextView mostrador;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String operation = "";
    private boolean isDegree = true; // Por defecto grados
    private Vector<Double> numeros = new Vector<>();
    private Vector<String> operaciones = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ajustar el margen para evitar interferencias con las barras del sistema (EdgeToEdge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar elementos de la interfaz
        mostrador = findViewById(R.id.mostrador);
        Switch switchTrig = findViewById(R.id.switchTrig);

        // Cambiar entre grados y radianes
        switchTrig.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isDegree = !isChecked; // Grados si no está marcado
        });

        // Inicialización de botones y eventos
        initializeButtons();
    }

    private void initializeButtons() {

        Button button1 = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button0 = findViewById(R.id.button10);


        Button addButton = findViewById(R.id.button11);
        Button subtractButton = findViewById(R.id.button12);
        Button multiplyButton = findViewById(R.id.button13);
        Button divideButton = findViewById(R.id.button14);


        Button sinButton = findViewById(R.id.button15);
        Button cosButton = findViewById(R.id.button16);


        Button equalButton = findViewById(R.id.button17);
        Button clearButton = findViewById(R.id.button18);


        button1.setOnClickListener(v -> appendNumber("1"));
        button2.setOnClickListener(v -> appendNumber("2"));
        button3.setOnClickListener(v -> appendNumber("3"));
        button4.setOnClickListener(v -> appendNumber("4"));
        button5.setOnClickListener(v -> appendNumber("5"));
        button6.setOnClickListener(v -> appendNumber("6"));
        button7.setOnClickListener(v -> appendNumber("7"));
        button8.setOnClickListener(v -> appendNumber("8"));
        button9.setOnClickListener(v -> appendNumber("9"));
        button0.setOnClickListener(v -> appendNumber("0"));

        addButton.setOnClickListener(v -> setOperation("+"));
        subtractButton.setOnClickListener(v -> setOperation("-"));
        multiplyButton.setOnClickListener(v -> setOperation("*"));
        divideButton.setOnClickListener(v -> setOperation("/"));

        sinButton.setOnClickListener(v -> calculateTrig("sin"));
        cosButton.setOnClickListener(v -> calculateTrig("cos"));

        equalButton.setOnClickListener(v -> calculateResult());
        clearButton.setOnClickListener(v -> clear());
    }

    private void appendNumber(String number) {
        String currentText = mostrador.getText().toString();
        if (currentText.equals("0")) {
            mostrador.setText(number);
        } else {
            mostrador.setText(currentText + number);
        }
    }

    private void setOperation(String op) {
        double number = Double.parseDouble(mostrador.getText().toString());
        numeros.add(number);

        operaciones.add(op);
        mostrador.setText("0");
    }
    double currentresult;
    private void calculateResult() {
        double lastnumber = Double.parseDouble(mostrador.getText().toString());
        numeros.add(lastnumber);
        double result = 0;
        int i=0;

        while(i<operaciones.size())
        {
            if(i==0) {
                operation = operaciones.get(i);
                switch (operation) {
                    case "+":
                        currentresult = numeros.get(i) + numeros.get(i + 1);
                        break;
                    case "-":
                        currentresult = numeros.get(i) - numeros.get(i + 1);
                        break;
                    case "*":
                        currentresult = numeros.get(i) * numeros.get(i + 1);
                        break;
                    case "/":
                        if (numeros.get(i + 1) != 0) {
                            currentresult = numeros.get(i) / numeros.get(i + 1);
                        } else {
                            mostrador.setText("Error");
                            return;
                        }
                        break;
                }
            }
            else {
                operation = operaciones.get(i);
                switch (operation) {
                    case "+":
                        currentresult = currentresult + numeros.get(i + 1);
                        break;
                    case "-":
                        currentresult = currentresult - numeros.get(i + 1);
                        break;
                    case "*":
                        currentresult = currentresult * numeros.get(i + 1);
                        break;
                    case "/":
                        if (numeros.get(i + 1) != 0) {
                            currentresult = currentresult / numeros.get(i + 1);
                        } else {
                            mostrador.setText("Error");
                            return;
                        }
                        break;

                }
            }
                i=i+1;
        }

        mostrador.setText(String.valueOf(currentresult));
        numeros.clear();
        operaciones.clear();
        operation = "";
    }

    private void calculateTrig(String function) {
        double value = Double.parseDouble(mostrador.getText().toString());
        if (isDegree) {
            value = Math.toRadians(value);
        }

        double result = 0;
        switch (function) {
            case "sin":
                result = Math.sin(value);
                break;
            case "cos":
                result = Math.cos(value);
                break;
        }
        mostrador.setText(String.valueOf(result));
    }

    private void clear() {
        mostrador.setText("0");
        numeros.clear();
        operaciones.clear();
        operation = "";
    }
}
