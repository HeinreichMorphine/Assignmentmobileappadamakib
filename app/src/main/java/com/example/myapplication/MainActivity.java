package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText vehiclePrice, downPayment, interestRate, customLoanPeriod;
    private RadioGroup loanPeriodRadioGroup;
    private TextView loanAmountResult, totalInterestResult, totalPaymentResult, monthlyPaymentResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vehiclePrice = findViewById(R.id.vehiclePrice);
        downPayment = findViewById(R.id.downPayment);
        interestRate = findViewById(R.id.interestRate);
        customLoanPeriod = findViewById(R.id.customLoanPeriod);
        loanPeriodRadioGroup = findViewById(R.id.loanPeriodRadioGroup);

        loanAmountResult = findViewById(R.id.loanAmountResult);
        totalInterestResult = findViewById(R.id.totalInterestResult);
        totalPaymentResult = findViewById(R.id.totalPaymentResult);
        monthlyPaymentResult = findViewById(R.id.monthlyPaymentResult);

        loanPeriodRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.periodCustom) {
                customLoanPeriod.setVisibility(View.VISIBLE);
            } else {
                customLoanPeriod.setVisibility(View.GONE);
            }
        });

        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(v -> calculateLoan());
    }

    private void calculateLoan() {
        String priceStr = vehiclePrice.getText().toString();
        String downPaymentStr = downPayment.getText().toString();
        String rateStr = interestRate.getText().toString();

        if (priceStr.isEmpty() || downPaymentStr.isEmpty() || rateStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedId = loanPeriodRadioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select a loan period", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            double dp = Double.parseDouble(downPaymentStr);
            double rate = Double.parseDouble(rateStr);

            int years = 0;
            if (selectedId == R.id.periodCustom) {
                String customYearsStr = customLoanPeriod.getText().toString();
                if (customYearsStr.isEmpty()) {
                    Toast.makeText(this, "Please enter a custom loan period", Toast.LENGTH_SHORT).show();
                    return;
                }
                years = Integer.parseInt(customYearsStr);
            } else {
                RadioButton selectedRadioButton = findViewById(selectedId);
                String radioButtonText = selectedRadioButton.getText().toString();
                if (radioButtonText.equals("5 Years")) {
                    years = 5;
                } else if (radioButtonText.equals("7 Years")) {
                    years = 7;
                } else if (radioButtonText.equals("9 Years")) {
                    years = 9;
                }
            }

            double loanAmount = price - dp;
            double totalInterest = loanAmount * (rate / 100) * years;
            double totalPayment = loanAmount + totalInterest;
            double monthlyPayment = totalPayment / (years * 12);

            DecimalFormat df = new DecimalFormat("#.00");

            loanAmountResult.setText("Loan Amount: RM" + df.format(loanAmount));
            totalInterestResult.setText("Total Interest: RM" + df.format(totalInterest));
            totalPaymentResult.setText("Total Payment: RM" + df.format(totalPayment));
            monthlyPaymentResult.setText("Monthly Payment: RM" + df.format(monthlyPayment));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            startActivity(new Intent(this, HomeActivity.class));
            return true;
        } else if (id == R.id.nav_calculation) {
            // Already here
            return true;
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
