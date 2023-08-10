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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;


public class CombinationBreakdown extends AppCompatActivity {
    private EditText editTextTotalBill;
    private EditText editTextNumberOfPeople;
    private Button calculateButton;
    private TextView textViewResult;
    private EditText Bill_name;
    private EditText friendsInput;
    private EditText InputSelf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combination_breakdown);
        // Initialize views
        Bill_name=findViewById(R.id.Bill_name);
        editTextTotalBill = findViewById(R.id.bill_amount);
        editTextNumberOfPeople = findViewById(R.id.num_people);
        friendsInput = findViewById(R.id.friendsInput);
        calculateButton = findViewById(R.id.calculateButton);
        InputSelf=findViewById(R.id.self_input);


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
        String OwnInput=InputSelf.getText().toString();
        String[] SelfInput = OwnInput.split(",");// Get the input percentages as an array of strings

        //Check whether the number of friends is same as the number of amount entered
        if (SelfInput.length != friendNames.length) {
            Toast.makeText(this,"Please enter same number of amount with the number of friends you entered above.",Toast.LENGTH_SHORT).show();
            return;
        }

        double[] Input = new double[SelfInput.length];
        double totalSelfInput = 0;
        String total_bill_amount = editTextTotalBill.getText().toString();
        double parsedBillAmount = Double.parseDouble(total_bill_amount.trim());

        for (int i = 0; i < SelfInput.length; i++) {
            Double parsedSelfInput = Double.parseDouble(SelfInput[i].trim());
            Input[i] = parsedSelfInput;
            totalSelfInput += parsedSelfInput;
        }

        totalSelfInput=totalSelfInput*1.00;

        if (totalSelfInput != parsedBillAmount) {
            // Handle the case where the total percentage is not 100
            Toast.makeText(this,"The total of the amount must equal to bill amount!.",Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder resultText = new StringBuilder();

        //Open the DisplayResult activity using Intent
        Intent intent=new Intent(CombinationBreakdown.this,DisplayResult.class);
        intent.putExtra("Bill Name",Bill_name.getText().toString());
        intent.putExtra("Bill Amount",editTextTotalBill.getText().toString());
        intent.putExtra("Number of people",editTextNumberOfPeople.getText().toString());
        // Create an ArrayList to hold the combined strings
        StringBuilder combinedStringBuilder = new StringBuilder();
        double checkAmount=0;

        for (int i = 0; i < friendNames.length; i++) {
            String formattedName = friendNames[i].trim();

            String formattedPayment = String.format("%.2f", Input[i]);
            String combinedString = formattedName + " owes $" + formattedPayment;

            combinedStringBuilder.append(combinedString).append("\n");
        }
        // Convert the StringBuilder to a single string
        String combinedResult = combinedStringBuilder.toString();
        intent.putExtra("Selected friend",combinedResult);

        startActivity(intent);

    }

}