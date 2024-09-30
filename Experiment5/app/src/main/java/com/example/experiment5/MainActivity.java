package com.example.experiment5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private double firstNumber = 0;
    private String operator = "";
    private boolean isNewOperation = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.textView_display);

        // Set up number buttons
        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewOperation) {
                    display.setText("");
                    isNewOperation = false;
                }
                Button button = (Button) v;
                display.append(button.getText().toString());
            }
        };

        findViewById(R.id.button0).setOnClickListener(numberListener);
        findViewById(R.id.button1).setOnClickListener(numberListener);
        findViewById(R.id.button2).setOnClickListener(numberListener);
        findViewById(R.id.button3).setOnClickListener(numberListener);
        findViewById(R.id.button4).setOnClickListener(numberListener);
        findViewById(R.id.button5).setOnClickListener(numberListener);
        findViewById(R.id.button6).setOnClickListener(numberListener);
        findViewById(R.id.button7).setOnClickListener(numberListener);
        findViewById(R.id.button8).setOnClickListener(numberListener);
        findViewById(R.id.button9).setOnClickListener(numberListener);

        // Set up operator buttons
        findViewById(R.id.buttonAdd).setOnClickListener(new OperatorClickListener("+"));
        findViewById(R.id.buttonSubtract).setOnClickListener(new OperatorClickListener("-"));
        findViewById(R.id.buttonMultiply).setOnClickListener(new OperatorClickListener("*"));
        findViewById(R.id.buttonDivide).setOnClickListener(new OperatorClickListener("/"));
        findViewById(R.id.buttonModulus).setOnClickListener(new OperatorClickListener("%"));

        // Set up equals button
        findViewById(R.id.buttonEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double secondNumber = Double.parseDouble(display.getText().toString());
                double result = 0;

                switch (operator) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        if (secondNumber != 0) {
                            result = firstNumber / secondNumber;
                        } else {
                            display.setText("Error");
                            return;
                        }
                        break;
                    case "%":
                        result = firstNumber % secondNumber;
                        break;
                }
                display.setText(String.valueOf(result));
                isNewOperation = true;
            }
        });
    }
    // Custom OnClickListener for operator buttons
    private class OperatorClickListener implements View.OnClickListener {
        private String operatorSymbol;

        OperatorClickListener(String operatorSymbol) {
            this.operatorSymbol = operatorSymbol;
        }

        @Override
        public void onClick(View v) {
            firstNumber = Double.parseDouble(display.getText().toString());
            operator = operatorSymbol;
            isNewOperation = true;
        }
    }
}