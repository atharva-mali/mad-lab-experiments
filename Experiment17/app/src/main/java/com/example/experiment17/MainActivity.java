package com.example.experiment17;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText etName, etDuration, etDescription, etId;
    private Button btnAdd, btnUpdate, btnDelete, btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        etName = findViewById(R.id.etName);
        etDuration = findViewById(R.id.etDuration);
        etDescription = findViewById(R.id.etDescription);
        etId = findViewById(R.id.etId);

        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnRead = findViewById(R.id.btnRead);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String duration = etDuration.getText().toString();
                String description = etDescription.getText().toString();
                dbHelper.addCourse(name, duration, description);
                Toast.makeText(MainActivity.this, "Course Added", Toast.LENGTH_SHORT).show();
                clearFields();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(etId.getText().toString());
                String name = etName.getText().toString();
                String duration = etDuration.getText().toString();
                String description = etDescription.getText().toString();
                dbHelper.updateCourse(id, name, duration, description);
                Toast.makeText(MainActivity.this, "Course Updated", Toast.LENGTH_SHORT).show();
                clearFields();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(etId.getText().toString());
                dbHelper.deleteCourse(id);
                Toast.makeText(MainActivity.this, "Course Deleted", Toast.LENGTH_SHORT).show();
                clearFields();
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Course> courses = dbHelper.getAllCourses();
                StringBuilder sb = new StringBuilder();
                for (Course course : courses) {
                    sb.append("ID: ").append(course.getId()).append(", Name: ").append(course.getName())
                            .append(", Duration: ").append(course.getDuration())
                            .append(", Description: ").append(course.getDescription()).append("\n");
                }
                Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void clearFields() {
        etId.setText("");
        etName.setText("");
        etDuration.setText("");
        etDescription.setText("");
    }
}
