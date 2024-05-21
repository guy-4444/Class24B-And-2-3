package com.guy.class24b_and_2_3;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class Activity_Tax extends AppCompatActivity {

    private EditText priceEdt;
    private MaterialButton calcBtn;
    private MaterialTextView resultLbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax);

        priceEdt = findViewById(R.id.priceEdt);
        calcBtn = findViewById(R.id.calcBtn);
        resultLbl = findViewById(R.id.resultLbl);

        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateClicked();
            }
        });
    }

    private void calculateClicked() {
        // take the input
        String input = priceEdt.getText().toString();
        int price = Integer.valueOf(input);

        // calculate
        price *= 1.17;

        // set the text
        resultLbl.setText("After tax:\n" + price);
    }
}