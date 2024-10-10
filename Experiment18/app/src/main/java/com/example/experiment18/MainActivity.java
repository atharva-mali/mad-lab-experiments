package com.example.experiment18;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private EditText editTextName;
    private EditText editTextDeleteId;
    private Button buttonSubmit, buttonRetrieve, buttonDelete;
    private ListView listViewUsers;
    private ArrayList<String> userList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        editTextName = findViewById(R.id.editTextName);
        editTextDeleteId = findViewById(R.id.editTextDeleteId);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonRetrieve = findViewById(R.id.buttonRetrieve);
        buttonDelete = findViewById(R.id.buttonDelete);
        listViewUsers = findViewById(R.id.listViewUsers);
        userList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        listViewUsers.setAdapter(adapter);

        buttonSubmit.setOnClickListener(view -> submitData());
        buttonRetrieve.setOnClickListener(view -> retrieveData());
        buttonDelete.setOnClickListener(view -> deleteData());
    }

    private void submitData() {
        String name = editTextName.getText().toString().trim();
        if (!name.isEmpty()) {
            String id = databaseReference.push().getKey();
            User user = new User(id, name);
            databaseReference.child(id).setValue(user).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Data submitted", Toast.LENGTH_SHORT).show();
                    editTextName.setText(""); // Clear the input field
                } else {
                    Toast.makeText(MainActivity.this, "Failed to submit data", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(e -> Log.e("MainActivity", "Error: " + e.getMessage()));
        } else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
        }
    }

    private void retrieveData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    if (user != null) {
                        userList.add(user.getName());
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MainActivity", "Error retrieving data: " + error.getMessage());
            }
        });
    }

    private void deleteData() {
        String userId = editTextDeleteId.getText().toString().trim();
        if (!userId.isEmpty()) {
            databaseReference.child(userId).removeValue().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "User deleted", Toast.LENGTH_SHORT).show();
                    editTextDeleteId.setText(""); // Clear the input field
                } else {
                    Toast.makeText(MainActivity.this, "Failed to delete user", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(e -> Log.e("MainActivity", "Error: " + e.getMessage()));
        } else {
            Toast.makeText(this, "Please enter a user ID", Toast.LENGTH_SHORT).show();
        }
    }
}
