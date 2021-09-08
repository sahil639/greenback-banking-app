package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory extends AppCompatActivity {

    List<Model> History_Model_List =new ArrayList<>();
    RecyclerView recyclerView;
    History_RecyclerView history_adapter;
    RecyclerView.LayoutManager layoutManager;
    TextView hist_empty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        recyclerView =findViewById(R.id.history_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);
        hist_empty = findViewById(R.id.empty_text);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.scheduleLayoutAnimation();

        showData();

    }
    private void showData() {
        History_Model_List.clear();
        Cursor cursor = new DatabaseHelper(this).Read_Transfer_amount_Data();

        while (cursor.moveToNext()) {

            Integer balanceAmount =cursor.getInt(3);
            Model model = new Model(cursor.getString(1), cursor.getString(2), balanceAmount , cursor.getString(4));
            History_Model_List.add(model);
        }
        history_adapter = new History_RecyclerView( TransactionHistory.this,History_Model_List);
        recyclerView.setAdapter(history_adapter);

        if (History_Model_List.size() == 0) {
            hist_empty.setVisibility(View.VISIBLE);
        }

    }
}