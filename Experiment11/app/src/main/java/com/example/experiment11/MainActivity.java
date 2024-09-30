package com.example.experiment11;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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

        // Step 1: Find the button in the layout
        Button buttonShowDialog = findViewById(R.id.buttonShowDialog);

        // Step 2: Set an OnClickListener on the button to show the AlertDialog
        buttonShowDialog.setOnClickListener(v -> showAlertDialog());

    }

    // Step 3: Function to show AlertDialog
    private void showAlertDialog() {
        // Step 4: Create an AlertDialog Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Step 5: Set the message, title, and button actions
        builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to continue?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Action for "Yes" button click
                })
                .setNegativeButton("No", (dialog, which) -> {
                    // Action for "No" button click
                    dialog.dismiss();
                });
        // Step 6: Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}