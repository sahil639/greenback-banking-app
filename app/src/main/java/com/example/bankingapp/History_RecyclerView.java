package com.example.bankingapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class History_RecyclerView extends RecyclerView.Adapter<History_RecyclerView.MyViewHolder> {
    List<Model> ModelList_history;
    private Context context;

    public History_RecyclerView(Context context,List<Model> model_history) {
        ModelList_history = model_history;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.history_block,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_Title.setText(ModelList_history.get(position).getName_sender());
        holder.txt_Title_receiver.setText(ModelList_history.get(position).getName_reciever());
        holder.transfer_amount.setText(""+ModelList_history.get(position).getBalance());
        holder.transaction_status.setText(ModelList_history.get(position).getTransaction_status());
        if(ModelList_history.get(position).getTransaction_status().equals("FAILED")){
            holder.transaction_status.setTextColor(Color.parseColor("#FF0000"));
        }

    }


    @Override
    public int getItemCount() {
        return ModelList_history.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_Title;
        private TextView txt_Title_receiver;
        private TextView transfer_amount;
        private TextView transaction_status;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_Title =(TextView) itemView.findViewById(R.id.Transaction_history_name);
            txt_Title_receiver =(TextView) itemView.findViewById(R.id.Transaction_history_name_receiver);
            transfer_amount = (TextView) itemView.findViewById(R.id.Amount_Number);
            transaction_status = (TextView) itemView.findViewById(R.id.transaction_status);
        }
    }

}
