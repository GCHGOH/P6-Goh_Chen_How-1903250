package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText name_input,age_input,gender_input,contact_input,email_input;
    Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addactivity);

        name_input=findViewById(R.id.friendname);
        age_input=findViewById(R.id.age);
        gender_input=findViewById(R.id.gender);
        contact_input=findViewById(R.id.contact);
        email_input=findViewById(R.id.Email);
        add_button=findViewById(R.id.addbutton);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //store the data into database
                FriendDatabase myDB= new FriendDatabase (AddActivity.this);
                myDB.addfriend(name_input.getText().toString().trim(),Integer.valueOf(age_input.getText().toString().trim()), gender_input.getText().toString().trim(),contact_input.getText().toString().trim(),email_input.getText().toString().trim());
            }
        });
    }
}