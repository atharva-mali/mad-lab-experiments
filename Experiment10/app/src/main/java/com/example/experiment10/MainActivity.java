package com.example.experiment10;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Step 1: Find the ListView from the layout
        ListView listViewFruits = findViewById(R.id.listViewFruits);

        // Step 2: Create a list of fruits (array of strings)
        String[] fruits = {"Apple", "Banana", "Orange", "Mango", "Grapes", "Pineapple", "Strawberry"};

        // Step 3: Create an ArrayAdapter to connect the data to the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fruits);

        // Step 4: Set the adapter to the ListView
        listViewFruits.setAdapter(adapter);

    }
}