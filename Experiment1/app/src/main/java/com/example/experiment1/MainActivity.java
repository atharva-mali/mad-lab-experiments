package com.example.experiment1;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private CheckBox orange, green, purple;
    private TextView selectedColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        orange = findViewById(R.id.checkbox_orange);
        green = findViewById(R.id.checkbox_green);
        purple = findViewById(R.id.checkbox_purple);
        selectedColors = findViewById(R.id.selected_colors);

        // Set up checkbox listeners
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                displaySelectedColors();
            }
        };

        orange.setOnCheckedChangeListener(listener);
        green.setOnCheckedChangeListener(listener);
        purple.setOnCheckedChangeListener(listener);
    }

    private void displaySelectedColors() {
        StringBuilder selected = new StringBuilder("Selected Colors: ");

        if (orange.isChecked()) selected.append("Orange ");
        if (green.isChecked()) selected.append("Green ");
        if (purple.isChecked()) selected.append("Purple ");

        selectedColors.setText(selected.toString());

    }
}