package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewList extends AppCompatActivity {
    DatabaseHelper myDB;
    ArrayList<String> Name;
    ArrayList<String> Email;
    ArrayList<Integer> Balance;
    ArrayList<String> PhoneNumber;
    ArrayList<String> Account_Number;
    Adapter adapter;
    List<Model> model_user_list;
    private Adapter.RecyclerViewClickListener listener;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_history) {
            Intent intent =new Intent(this,TransactionHistory.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        RecyclerView ViewList = (RecyclerView) findViewById(R.id.ViewList);

        myDB = new DatabaseHelper(ViewList.this);
      //  id= new ArrayList<>();
        Name= new ArrayList<>();
        Email = new ArrayList<>();
        Balance = new ArrayList<>();
        PhoneNumber = new ArrayList<>();
        Account_Number = new ArrayList<>();

        storeDataInArrays();
        setOnClickListner();

        adapter = new Adapter(ViewList.this,Name,Email,Balance, listener);
        ViewList.scheduleLayoutAnimation();
        ViewList.setAdapter(adapter);
        ViewList.setLayoutManager(new LinearLayoutManager(ViewList.this));

    }
    void storeDataInArrays(){
        SQLiteDatabase database = myDB.getReadableDatabase();
        ContentValues values =new ContentValues();

        Cursor cursor = database.rawQuery("SELECT NAME, EMAIL, BALANCE,PHONENUMBER ,ACCOUNT  FROM USER_DETAILS", new String[]{});

        if(cursor != null){
            cursor.moveToFirst();
        }

        do{
            String name = cursor.getString(0);
            int balance = cursor.getInt(2);
            String email = cursor.getString(1);
            String phoneNumber = cursor.getString(3);
            String account = cursor.getString(4);
            Name.add(name);
            Email.add(email);
            Balance.add(balance);
            Account_Number.add(account);
            PhoneNumber.add(phoneNumber);
        }while(cursor.moveToNext());

    }
    private void setOnClickListner(){
        listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), Detail.class);
                 intent.putExtra("name",Name.get(position));
                 intent.putExtra("email",Email.get(position));
                 intent.putExtra("balance",Balance. get(position));
                intent.putExtra("account",Account_Number. get(position));
                intent.putExtra("phone",PhoneNumber. get(position));
                 startActivity(intent);
            }
        };
    }
}
