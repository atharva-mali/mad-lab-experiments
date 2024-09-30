package com.example.experiment2;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize RadioGroup
        radioGroupCourses = findViewById(R.id.radioGroup_courses);

        // Set listener for RadioGroup
        radioGroupCourses.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Find which radio button was selected
                RadioButton selectedRadioButton = findViewById(checkedId);
                String selectedCourse = selectedRadioButton.getText().toString();

                // Display selected course using Toast
                Toast.makeText(MainActivity.this, "Selected PG Course: " + selectedCourse, Toast.LENGTH_SHORT).show();

            }
        });

    }
}