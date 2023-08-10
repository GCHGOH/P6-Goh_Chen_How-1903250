package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BillCustomAdapter extends RecyclerView.Adapter<BillCustomAdapter.MyViewHolder2> {
    private Context context;
    Activity activity;
    private ArrayList bill_id,bill_name,bill_amount,bill_num_people,bill_friend;

    Animation translate_anim;

    BillCustomAdapter(Activity activity, Context context, ArrayList bill_id, ArrayList bill_name, ArrayList bill_amount,
                      ArrayList bill_num_people, ArrayList bill_friend){
        this.activity=activity;
        this.context=context;
        this.bill_id=bill_id;
        this.bill_name=bill_name;
        this.bill_amount=bill_amount;
        this.bill_num_people=bill_num_people;
        this.bill_friend=bill_friend;

    }
    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.billrow,parent,false);
        return new MyViewHolder2(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, final int position) {
        holder.bill_id_txt.setText(String.valueOf(bill_id.get(position)));
        holder.Bill_name_txt.setText(String.valueOf(bill_name.get(position)));
        holder.bill_number_of_people_txt.setText(String.valueOf(bill_num_people.get(position)));
        holder.friend_involved.setText(String.valueOf(bill_friend.get(position)));
        holder.Total_Amount.setText(String.valueOf(bill_amount.get(position)));
    }

    @Override
    public int getItemCount() {
        return bill_id.size();
    }
    public class MyViewHolder2 extends RecyclerView.ViewHolder{

        TextView bill_id_txt,Bill_name_txt,bill_number_of_people_txt,friend_involved,Total_Amount;
        LinearLayout mainLayout;
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            bill_id_txt=itemView.findViewById(R.id.bill_id_txt);
            Bill_name_txt=itemView.findViewById(R.id.Bill_name_txt);
            bill_number_of_people_txt=itemView.findViewById(R.id.bill_number_of_people_txt);
            friend_involved=itemView.findViewById(R.id.friend_involved);
            Total_Amount=itemView.findViewById(R.id.Total_Amount);
            mainLayout=itemView.findViewById(R.id.MainLayout);
            //Animate Recyclerview
            translate_anim= AnimationUtils.loadAnimation(context,R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}