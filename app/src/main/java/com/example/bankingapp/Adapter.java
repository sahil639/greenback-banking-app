package com.example.bankingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context context;

    ArrayList name,email,balance;
    private RecyclerViewClickListener listener;

    public Adapter(Context context,ArrayList name,ArrayList email,ArrayList balance,RecyclerViewClickListener listener){
        this.context =context;
        this.name = name;
        this.listener = listener;
        this.email = email;
        this.balance = balance;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_layout, parent ,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtTitle.setText(String.valueOf(name.get(position)));
        holder.email_sender.setText(String.valueOf(email.get(position)));
        holder.balance_sender.setText(String.valueOf(balance.get(position)));

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtTitle;
        private TextView email_sender;
        private TextView balance_sender;

        public MyViewHolder(final View itemView) {
            super(itemView);
            txtTitle =(TextView) itemView.findViewById(R.id.txtTitle);
            email_sender =(TextView) itemView.findViewById(R.id.email_sender);
            balance_sender =(TextView) itemView.findViewById(R.id.balance_sender);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener !=null)
            listener.onClick(itemView,getAdapterPosition());
        }
    }
    public interface RecyclerViewClickListener{
        void onClick(View v,int position);
    }

}
