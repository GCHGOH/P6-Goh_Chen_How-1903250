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
    import android.widget.TextView;
    import android.widget.Toast;

    import java.util.ArrayList;

    public class Bill extends AppCompatActivity {

        RecyclerView recyclerView;
        ImageView empty_imageview;
        TextView no_data2;


        ResultDatabase myDB;
        ArrayList<String> bill_id, bill_name, bill_amount,bill_num_people, bill_selected_friend;
        BillCustomAdapter customAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.bill_list);

            recyclerView=findViewById(R.id.recyclerView2);
            empty_imageview=findViewById(R.id.empty_imageView2);
            no_data2=findViewById(R.id.no_data2);
            myDB=new ResultDatabase(Bill.this);
            bill_id=new ArrayList<>();
            bill_name=new ArrayList<>();
            bill_amount=new ArrayList<>();
            bill_num_people=new ArrayList<>();
            bill_selected_friend=new ArrayList<>();

            storeDataInArrays();

            customAdapter=new BillCustomAdapter(Bill.this,this, bill_id, bill_name,bill_amount,bill_num_people,bill_selected_friend);

            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(Bill.this));

        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode,resultCode,data);
            if(requestCode==1){
                recreate();
            }
        }

        //Store the data in array
        void storeDataInArrays(){
            Cursor cursor=myDB.readAllData();
            if (cursor.getCount()==0){
                empty_imageview.setVisibility(View.VISIBLE);
                no_data2.setVisibility(View.VISIBLE);
            }else{
                while(cursor.moveToNext()){
                    bill_id.add(cursor.getString(0));
                    bill_name.add(cursor.getString(1));
                    bill_amount.add(cursor.getString(2));
                    bill_num_people.add(cursor.getString(3));
                    bill_selected_friend.add(cursor.getString(4));
                }
                empty_imageview.setVisibility(View.GONE);
                no_data2.setVisibility(View.GONE);
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
        //Prompt out a dialog for confirming deletion
        void confirmDialog(){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Delete All?");
            builder.setMessage("Are you sure you want to delete all Data?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ResultDatabase myDB=new ResultDatabase(Bill.this);
                    myDB.deleteAllData();
                    //Refresh Activity
                    Intent intent=new Intent(Bill.this,Bill.class);
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