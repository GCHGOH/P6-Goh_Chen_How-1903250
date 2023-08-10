package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class friend extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;


    FriendDatabase myDB;
    ArrayList<String> friend_id, friend_name, friend_age,friend_gender,friend_contact,friend_email;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendlist);

        recyclerView=findViewById(R.id.recyclerView);
        add_button=findViewById(R.id.add_button);
        empty_imageview=findViewById(R.id.empty_imageView);
        no_data=findViewById(R.id.no_data);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(friend.this, AddActivity.class);
                startActivity(intent);
            }
        });
        myDB=new FriendDatabase(friend.this);
        friend_id=new ArrayList<>();
        friend_name=new ArrayList<>();
        friend_age=new ArrayList<>();
        friend_gender=new ArrayList<>();
        friend_contact=new ArrayList<>();
        friend_email=new ArrayList<>();

        storeDataInArrays();

        customAdapter=new CustomAdapter(friend.this,this,friend_id,friend_name,friend_age,friend_gender,friend_contact,friend_email);

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(friend.this));

        LinearLayout HomeLayout = findViewById(R.id.Home);
        HomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, navigate to another activity
                Intent intent = new Intent(friend.this, MainActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout friendListLayout = findViewById(R.id.friend);
        friendListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for the FriendList LinearLayout
                // Replace the following line with the logic to navigate to the desired page
                Intent intent = new Intent(friend.this, friend.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor=myDB.readAllData();
        if (cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while(cursor.moveToNext()){
                friend_id.add(cursor.getString(0));
                friend_name.add(cursor.getString(1));
                friend_age.add(cursor.getString(2));
                friend_gender.add(cursor.getString(3));
                friend_contact.add(cursor.getString(4));
                friend_email.add(cursor.getString(5));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete_all){
            confirmDialog();
        }

        return super.onOptionsItemSelected(item);
    }
    void confirmDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FriendDatabase myDB=new FriendDatabase(friend.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent=new Intent(friend.this,friend.class);
                startActivity(intent);
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