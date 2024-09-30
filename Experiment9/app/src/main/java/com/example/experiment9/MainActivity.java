package com.example.experiment9;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements ExampleFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add fragment to the activity dynamically
        if (savedInstanceState == null) {
            ExampleFragment fragment = new ExampleFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
    }

    // Handle communication from Fragment
    @Override
    public void onFragmentInteraction(String message) {
        // Show a toast with the message from the fragment
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
