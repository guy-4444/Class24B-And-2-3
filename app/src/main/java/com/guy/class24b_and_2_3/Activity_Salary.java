package com.guy.class24b_and_2_3;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

public class Activity_Salary extends AppCompatActivity {

    private MaterialTextView salary_LBL_title;
    private TextInputLayout salary_EDT_gross;
    private MaterialButton salary_BTN_calculate;
    private MaterialTextView salary_LBL_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_salary);

        salary_LBL_title = findViewById(R.id.salary_LBL_title);
        salary_EDT_gross = findViewById(R.id.salary_EDT_gross);
        salary_BTN_calculate = findViewById(R.id.salary_BTN_calculate);
        salary_LBL_result = findViewById(R.id.salary_LBL_result);

        salary_LBL_result.setText("");

        salary_BTN_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateClicked();
            }
        });

    }

    private void calculateClicked() {
        salary_EDT_gross.setError("");
        String grossInput = salary_EDT_gross.getEditText().getText().toString();
        int gross = 0;
        try {
            gross = Integer.parseInt(grossInput);
        } catch (NumberFormatException ex) {
            salary_EDT_gross.setError("wrong input");
            Toast.makeText(this, "Wrong Input", Toast.LENGTH_SHORT).show();
        }

        if (gross > 0) {
            double result = calculate(gross);
            salary_LBL_result.setText("" + result);
        }



        hideKeyboard();
    }

    private double calculate(int gross) {
        double result = 0;


        return result;
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
    }
}