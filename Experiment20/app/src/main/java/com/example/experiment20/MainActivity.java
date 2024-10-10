package com.example.experiment20;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "data.json";
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonWrite = findViewById(R.id.buttonWrite);
        Button buttonRead = findViewById(R.id.buttonRead);
        textViewResult = findViewById(R.id.textViewResult);

        buttonWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeJsonToFile();
            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readJsonFromFile();
            }
        });
    }

    private void writeJsonToFile() {
        String jsonData = "{\"name\":\"John Doe\", \"age\":30, \"city\":\"New York\"}";
        File file = new File(getExternalFilesDir(null), FILENAME);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(jsonData.getBytes());
            textViewResult.setText("JSON data written to " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            textViewResult.setText("Error writing file: " + e.getMessage());
        }
    }

    private void readJsonFromFile() {
        File file = new File(getExternalFilesDir(null), FILENAME);

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            String jsonData = new String(data);
            textViewResult.setText("JSON data: " + jsonData);
        } catch (IOException e) {
            e.printStackTrace();
            textViewResult.setText("Error reading file: " + e.getMessage());
        }
    }
}
