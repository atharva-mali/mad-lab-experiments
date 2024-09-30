package com.example.experiment3;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private SeekBar seekBar;
    private TextView textViewRating, textViewSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize views
        ratingBar = findViewById(R.id.ratingBar);
        seekBar = findViewById(R.id.seekBar);
        textViewRating = findViewById(R.id.textView_rating);
        textViewSeekBar = findViewById(R.id.textView_seekbar);

        // Handle RatingBar value change
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textViewRating.setText("Rating: " + rating);
            }
        });

        // Handle SeekBar value change
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewSeekBar.setText("Value Rating: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not needed in this simple example
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not needed in this simple example
            }
        });

    }
}