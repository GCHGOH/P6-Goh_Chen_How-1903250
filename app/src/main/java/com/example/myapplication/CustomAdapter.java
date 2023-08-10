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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{


    private Context context;
    Activity activity;
    private ArrayList friend_id,friend_name,friend_age,friend_gender,friend_contact,friend_email;

    Animation translate_anim;

    CustomAdapter(Activity activity, Context context, ArrayList friend_id, ArrayList friend_name, ArrayList friend_age,
                  ArrayList friend_gender,ArrayList friend_contact,ArrayList friend_email){
        this.activity=activity;
        this.context=context;
        this.friend_id=friend_id;
        this.friend_name=friend_name;
        this.friend_age=friend_age;
        this.friend_gender=friend_gender;
        this.friend_contact=friend_contact;
        this.friend_email=friend_email;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myrow,parent,false);
        return new MyViewHolder(view);

    }

    //Use for updateActivity
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.friend_id_txt.setText(String.valueOf(friend_id.get(position)));
        holder.friend_name_txt.setText(String.valueOf(friend_name.get(position)));
        holder.friend_age_txt.setText(String.valueOf(friend_age.get(position)));
        holder.friend_gender_txt.setText(String.valueOf(friend_gender.get(position)));
        holder.friend_contact_txt.setText(String.valueOf(friend_contact.get(position)));
        holder.friend_email_txt.setText(String.valueOf(friend_email.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context,UpdateAcitivity.class);
                intent.putExtra("id",String.valueOf(friend_id.get(position)));
                intent.putExtra("name",String.valueOf(friend_name.get(position)));
                intent.putExtra("age",String.valueOf(friend_age.get(position)));
                intent.putExtra("gender",String.valueOf(friend_gender.get(position)));
                intent.putExtra("contact",String.valueOf(friend_contact.get(position)));
                intent.putExtra("email",String.valueOf(friend_email.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return friend_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView friend_id_txt,friend_name_txt,friend_age_txt,friend_gender_txt,friend_contact_txt,friend_email_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            friend_id_txt=itemView.findViewById(R.id.friend_id_txt);
            friend_name_txt=itemView.findViewById(R.id.friend_name_txt);
            friend_age_txt=itemView.findViewById(R.id.friend_age_txt);
            friend_gender_txt=itemView.findViewById(R.id.friend_gender_txt);
            friend_contact_txt=itemView.findViewById(R.id.friend_contact_txt);
            friend_email_txt=itemView.findViewById(R.id.friend_email_txt);
            mainLayout=itemView.findViewById(R.id.MainLayout);
            //Animate Recyclerview
            translate_anim= AnimationUtils.loadAnimation(context,R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
