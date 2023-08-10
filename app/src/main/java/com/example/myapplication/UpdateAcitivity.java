package com.example.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateAcitivity extends AppCompatActivity {

    EditText name_input,age_input,gender_input, contact_input, email_input;
    Button update_button, delete_button;
    String id,name,age,gender,contact,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input=findViewById(R.id.friendname2);
        age_input=findViewById(R.id.age2);
        gender_input=findViewById(R.id.gender2);
        contact_input=findViewById(R.id.contact2);
        email_input=findViewById(R.id.Email2);
        update_button=findViewById(R.id.update_button);
        delete_button=findViewById(R.id.delete_button);
        //First we call this
        getAndSetIntentData();

        //set action bar
        ActionBar ab=getSupportActionBar();
        ab.setTitle(name);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FriendDatabase myDB=new FriendDatabase(UpdateAcitivity.this);
                name=name_input.getText().toString().trim();
                age=age_input.getText().toString().trim();
                gender=gender_input.getText().toString().trim();
                contact=contact_input.getText().toString().trim();
                email=email_input.getText().toString().trim();
                myDB.updateData(id,name,age,gender,contact,email);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name")&& getIntent().hasExtra("age")&& getIntent().hasExtra("gender")&& getIntent().hasExtra("contact")&& getIntent().hasExtra("email")){
            //Getting Data from Intent
            id=getIntent().getStringExtra("id");
            name=getIntent().getStringExtra("name");
            age=getIntent().getStringExtra("age");
            gender=getIntent().getStringExtra("gender");
            contact=getIntent().getStringExtra("contact");
            email=getIntent().getStringExtra("email");

            //Setting Intent Data
            name_input.setText(name);
            age_input.setText(age);
            gender_input.setText(gender);
            contact_input.setText(contact);
            email_input.setText(email);
        }else{
            Toast.makeText(this,"No data.",Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete "+name+ " ?");
        builder.setMessage("Are you sure you want to delete "+ name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FriendDatabase myDB=new FriendDatabase(UpdateAcitivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}