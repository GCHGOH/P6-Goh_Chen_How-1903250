package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class select_breakdown_method extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_breakdown_method);

        LinearLayout rectangleLayout = findViewById(R.id.equal_breakdown);
        rectangleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, navigate to another activity
                Intent intent = new Intent(select_breakdown_method.this, EqualBreakdown.class);
                startActivity(intent);
            }
        });

        LinearLayout custom_layout = findViewById(R.id.custom_breakdown);
        custom_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, navigate to another activity
                Intent intent = new Intent(select_breakdown_method.this, CustomBreakdown.class);
                startActivity(intent);
            }
        });

        LinearLayout combination_layout = findViewById(R.id.combination_breakdown);
        combination_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, navigate to another activity
                Intent intent = new Intent(select_breakdown_method.this, CombinationBreakdown.class);
                startActivity(intent);
            }
        });

        LinearLayout HomeLayout = findViewById(R.id.Home);
        HomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, navigate to another activity
                Intent intent = new Intent(select_breakdown_method.this, MainActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout friendListLayout = findViewById(R.id.friend);
        friendListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for the FriendList LinearLayout
                // Replace the following line with the logic to navigate to the desired page
                Intent intent = new Intent(select_breakdown_method.this, friend.class);
                startActivity(intent);
            }
        });
    }
}