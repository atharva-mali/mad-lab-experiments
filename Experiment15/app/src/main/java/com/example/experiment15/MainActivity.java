package com.example.experiment15;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // BluetoothAdapter to interact with Bluetooth functionality
    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the BluetoothAdapter instance
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Check if Bluetooth is supported on the device
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported on this device", Toast.LENGTH_LONG).show();
            finish(); // Exit the app if Bluetooth is not supported
        }

        // Find the buttons from the layout
        Button checkStatusButton = findViewById(R.id.checkStatusButton);
        Button turnOnButton = findViewById(R.id.turnOnButton);
        Button turnOffButton = findViewById(R.id.turnOffButton);

        // Check Bluetooth status button listener
        checkStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBluetoothStatus();
            }
        });

        // Turn Bluetooth on button listener
        turnOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnBluetoothOn();
            }
        });

        // Turn Bluetooth off button listener
        turnOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnBluetoothOff();
            }
        });
    }

    // Method to check Bluetooth status
    private void checkBluetoothStatus() {
        if (bluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "Bluetooth is ON", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Bluetooth is OFF", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to turn Bluetooth ON
    private void turnBluetoothOn() {
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivityForResult(enableBtIntent, 1); // Request to enable Bluetooth
        } else {
            Toast.makeText(this, "Bluetooth is already ON", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to turn Bluetooth OFF
    private void turnBluetoothOff() {
        if (bluetoothAdapter.isEnabled()) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            bluetoothAdapter.disable();
            Toast.makeText(this, "Bluetooth turned OFF", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Bluetooth is already OFF", Toast.LENGTH_SHORT).show();
        }
    }
}
