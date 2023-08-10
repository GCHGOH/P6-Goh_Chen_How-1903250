package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout rectangleLayout = findViewById(R.id.addgroupbutton);
        rectangleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, navigate to another activity
                Intent intent = new Intent(MainActivity.this, select_breakdown_method.class);
                startActivity(intent);
            }
        });

        LinearLayout HomeLayout = findViewById(R.id.Home);
        HomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, navigate to another activity
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout friendListLayout = findViewById(R.id.friend);
        friendListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for the FriendList LinearLayout
                // Replace the following line with the logic to navigate to the desired page
                Intent intent = new Intent(MainActivity.this, friend.class);
                startActivity(intent);
            }
        });

        LinearLayout viewgroupLayout = findViewById(R.id.viewgroupbutton);
        viewgroupLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for the FriendList LinearLayout
                // Replace the following line with the logic to navigate to the desired page
                Intent intent = new Intent(MainActivity.this, Bill.class);
                startActivity(intent);
            }
        });

    }
}