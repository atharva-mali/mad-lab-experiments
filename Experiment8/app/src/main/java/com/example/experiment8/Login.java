package com.example.experiment8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLoginCredentials();
            }
        });
    }

    private void checkLoginCredentials() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.equals("mca") && password.equals("android")) {
            // Login successful, navigate to the next activity
            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);
            finish(); // Close the login activity
        } else {
            // Login failed, show a toast message
            Toast.makeText(this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}