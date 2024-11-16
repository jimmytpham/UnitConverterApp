package com.example.unitconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private EditText enterTemp;
    double result1;

    Spinner ConvertFromUnitTypeSpinner, ConvertToUnitTypeSpinner;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set spinner variables
        ConvertFromUnitTypeSpinner = findViewById(R.id.ConvertFromUnitTypeSpinner);
        ConvertToUnitTypeSpinner = findViewById(R.id.ConvertToUnitTypeSpinner);

        //String for spinner items
        String [] unitTypes = {"Fahrenheit", "Celsius"};

        //set up adapter for the layout of the spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, unitTypes);

        //set up adapter to spinner
        ConvertFromUnitTypeSpinner.setAdapter(spinnerAdapter);
        ConvertToUnitTypeSpinner.setAdapter(spinnerAdapter);

        //set up convert button
        Button convert = findViewById(R.id.convert);
        result = findViewById(R.id.result);
        enterTemp = findViewById(R.id.enterTemp);
        DecimalFormat value = new DecimalFormat("#.#");

        convert.setOnClickListener(v -> {

            if (enterTemp.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "It's empty", Toast.LENGTH_SHORT).show();
            } else {
                double temp = Double.parseDouble(enterTemp.getText().toString());

                if (ConvertFromUnitTypeSpinner.getSelectedItem().equals("Fahrenheit")
                        && ConvertToUnitTypeSpinner.getSelectedItem().toString().equals("Celsius")) {
                    result1 = (temp - 32) * 5 / 9;
                    result.setText(String.valueOf(value.format(result1)));
                }
                if (ConvertFromUnitTypeSpinner.getSelectedItem().equals("Celsius")
                        && ConvertToUnitTypeSpinner.getSelectedItem().toString().equals("Fahrenheit")) {
                    result1 = (temp * 9 / 5) + 32;
                    result.setText(String.valueOf(value.format(result1)));
                } else if (ConvertFromUnitTypeSpinner.getSelectedItem().equals(ConvertToUnitTypeSpinner.getSelectedItem())) {
                    Toast.makeText(MainActivity.this, "Units are the same", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}