package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;


public class EqualBreakdown extends AppCompatActivity {
    private EditText editTextTotalBill;
    private EditText editTextNumberOfPeople;
    private Button calculateButton;
    private TextView textViewResult;
    private EditText Bill_name;
    private EditText friendsInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equal_breakdown);
        // Initialize views
        Bill_name=findViewById(R.id.Bill_name);
        editTextTotalBill = findViewById(R.id.bill_amount);
        editTextNumberOfPeople = findViewById(R.id.num_people);
        friendsInput = findViewById(R.id.friendsInput);
        calculateButton = findViewById(R.id.calculateButton);

        // Set click listener for calculate button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateFriendsPayment();
            }
        });
    }


    private void calculateFriendsPayment() {
        String inputText = friendsInput.getText().toString();
        String[] friendNames = inputText.split(",");
        int numberOfPeople = Integer.parseInt(editTextNumberOfPeople.getText().toString());
        double totalBill = Double.parseDouble(editTextTotalBill.getText().toString());

        // Calculate the amount each friend needs to pay
        double paymentPerPerson = totalBill / numberOfPeople;

        StringBuilder resultText = new StringBuilder();

        Intent intent=new Intent(EqualBreakdown.this,DisplayResult.class);
        intent.putExtra("Bill Name",Bill_name.getText().toString());
        intent.putExtra("Bill Amount",editTextTotalBill.getText().toString());
        intent.putExtra("Number of people",editTextNumberOfPeople.getText().toString());
        // Create an ArrayList to hold the combined strings
        StringBuilder combinedStringBuilder = new StringBuilder();
        for (String friendName : friendNames) {
            String formattedName = friendName.trim(); // Remove leading/trailing spaces

            double friendPayment = paymentPerPerson;
            String formattedPayment = String.format("%.2f", friendPayment);
            // Combine the formatted name and payment into a single string
            String combinedString = formattedName + " owes $" + formattedPayment;

            // Append the combined string with a line break
            combinedStringBuilder.append(combinedString).append("\n");

        }
        // Convert the StringBuilder to a single string
        String combinedResult = combinedStringBuilder.toString();
        intent.putExtra("Selected friend",combinedResult);

        startActivity(intent);

    }


}