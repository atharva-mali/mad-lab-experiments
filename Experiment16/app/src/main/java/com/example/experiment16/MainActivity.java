package com.example.experiment16;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // WifiManager to interact with Wi-Fi functionality
    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the WifiManager instance
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        // Check if Wifi is supported on the device
        if (wifiManager == null) {
            Toast.makeText(this, "Wi-Fi is not supported on this device", Toast.LENGTH_LONG).show();
            finish(); // Exit the app if Wi-Fi is not supported
        }

        // Find the buttons from the layout
        Button checkStatusButton = findViewById(R.id.checkStatusButton);
        Button turnOnButton = findViewById(R.id.turnOnButton);
        Button turnOffButton = findViewById(R.id.turnOffButton);

        // Check Wi-Fi status button listener
        checkStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkWifiStatus();
            }
        });

        // Turn Wi-Fi on button listener
        turnOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnWifiOn();
            }
        });

        // Turn Wi-Fi off button listener
        turnOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnWifiOff();
            }
        });
    }

    // Method to check Wi-Fi status
    private void checkWifiStatus() {
        if (wifiManager.isWifiEnabled()) {
            Toast.makeText(this, "Wi-Fi is ON", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wi-Fi is OFF", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to turn Wi-Fi ON
    private void turnWifiOn() {
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
            Toast.makeText(this, "Wi-Fi turned ON", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wi-Fi is already ON", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to turn Wi-Fi OFF
    private void turnWifiOff() {
        if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
            Toast.makeText(this, "Wi-Fi turned OFF", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wi-Fi is already OFF", Toast.LENGTH_SHORT).show();
        }
    }
}
