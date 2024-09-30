package com.example.experiment6;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private Spinner colorSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize layout and Spinner
        mainLayout = findViewById(R.id.mainLayout);
        colorSpinner = findViewById(R.id.colorSpinner);

        // Array of secondary color names
        String[] secondaryColors = {"Orange", "Green", "Purple"};

        // Set up the adapter for the Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, secondaryColors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);

        // Set listener for Spinner selection
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedColor = secondaryColors[position];

                // Change background color based on selection
                switch (selectedColor) {
                    case "Orange":
                        mainLayout.setBackgroundColor(Color.parseColor("#FFA500")); // Orange
                        break;
                    case "Green":
                        mainLayout.setBackgroundColor(Color.GREEN); // Green
                        break;
                    case "Purple":
                        mainLayout.setBackgroundColor(Color.parseColor("#800080")); // Purple
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });
    }
}