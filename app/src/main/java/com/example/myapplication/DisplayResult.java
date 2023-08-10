package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayResult extends AppCompatActivity {

    Button Save_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);

        Intent intent = getIntent();

        if (intent != null) {
            String userName = intent.getStringExtra("Bill Name");
            String amount = intent.getStringExtra("Bill Amount"); // 0 is a default value if not found
            String num_people = intent.getStringExtra("Number of people");
            String friend_Names = intent.getStringExtra("Selected friend");

            // Now you can display the data in your UI elements
            TextView nameTextView = findViewById(R.id.Bill_name_txt);
            TextView amountTextView = findViewById(R.id.Total_Amount);
            TextView num_peopleTextView = findViewById(R.id.bill_number_of_people_txt);
            TextView friend_namesTextView=findViewById(R.id.friend_involved);

            nameTextView.setText("Name: " + userName);
            amountTextView.setText("Bill amount: " + amount);
            num_peopleTextView.setText("Number of people: " + num_people);
            friend_namesTextView.setText("Friend involved: " + "\n"+friend_Names);



            Save_button=findViewById(R.id.Save_button);
            Save_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ResultDatabase myDB= new ResultDatabase (DisplayResult.this);
                    //store the data into database
                    String nameWithoutPrefix = nameTextView.getText().toString().replace("Name: ", "");
                    String amountWithoutPrefix = amountTextView.getText().toString().replace("Bill amount: ", "");
                    String numPeopleWithoutPrefix = num_peopleTextView.getText().toString().replace("Number of people: ", "");
                    String friendNamesWithoutPrefix = friend_namesTextView.getText().toString().replace("Friend involved: ", "");
                    myDB.addbill(nameWithoutPrefix.toString().trim(),Integer.valueOf(amountWithoutPrefix.toString().trim()), Integer.valueOf(numPeopleWithoutPrefix.toString().trim()),friendNamesWithoutPrefix.toString().trim());
                    Intent intent=new Intent(DisplayResult.this,Bill.class);
                    startActivity(intent);
                }
            });



        }

    }
}