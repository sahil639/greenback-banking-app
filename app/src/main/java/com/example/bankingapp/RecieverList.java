package com.example.bankingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecieverList extends AppCompatActivity {
    DatabaseHelper myDB ;
    List<Model> ModelList_ReceiverList = new ArrayList<>();

    String Name,Email,SelectedUserEmail,SelectedUserName;
    Integer SelectedUserBalance;
    Integer  CurrentBalance,TransferAmount,RemainingAmount;

    RecieverAdapter reciever_adapter;
    RecyclerView Reciever_ViewList;
    RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this);

    private RecieverAdapter.RecyclerviewClickListener listener;
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
        setContentView(R.layout.activity_reciever_list);
        Reciever_ViewList = (RecyclerView) findViewById(R.id.reciever_list_view);

        Reciever_ViewList.setLayoutManager(layoutManager);
        Reciever_ViewList.scheduleLayoutAnimation();

        myDB = new DatabaseHelper(RecieverList.this);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            Name = bundle.getString("name");
            Email = bundle.getString("email");
            TransferAmount = bundle.getInt("transfer amount");
            CurrentBalance = bundle.getInt("Current Balance");
            showData(Email);
        }

        reciever_adapter =new RecieverAdapter(RecieverList.this,ModelList_ReceiverList,listener);
        Reciever_ViewList.setAdapter(reciever_adapter);

    }

    private void showData(String Email) {
        ModelList_ReceiverList.clear();
        Log.d("DEMO",Email);
        Cursor cursor = myDB.ReadSelectedData(Email);

        if(cursor != null){
            cursor.moveToFirst();
        }

        do{
            String name = cursor.getString(1);
            int balance_in_db = cursor.getInt(3);
            String email = cursor.getString(2);
            Model model = new Model(cursor.getString(1),cursor.getString(2),balance_in_db);
            ModelList_ReceiverList.add(model);
        }while(cursor.moveToNext());

    }



    public  void selectUser(int position) {
        SelectedUserEmail = ModelList_ReceiverList.get(position).getEmail();
        System.out.println(SelectedUserEmail);
        Cursor cursor = new DatabaseHelper(this).readParticularData(SelectedUserEmail);

        while (cursor.moveToNext()){
            SelectedUserBalance = cursor.getInt(3);
            SelectedUserName = cursor.getString(1);
            Integer FinalAmount = SelectedUserBalance + TransferAmount;

            new DatabaseHelper(this).UpdateAmount(SelectedUserEmail,FinalAmount.toString());
            Integer RemainingAmount = CurrentBalance -TransferAmount;
            new DatabaseHelper(this).UpdateAmount(Email,RemainingAmount.toString());
            new DatabaseHelper(this).insertTransferData(Name,SelectedUserName,TransferAmount,"SUCCESSFUL");
            Toast.makeText(this,"Transaction Successful",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RecieverList.this,SuccessFullSplash.class);
            startActivity(intent);
            finish();
        }

    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder_exitbutton = new AlertDialog.Builder(RecieverList.this);
        builder_exitbutton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new DatabaseHelper(RecieverList.this).insertTransferData(Name, "Not selected", TransferAmount, "FAILED");
                        Toast.makeText(RecieverList.this, "Transaction Cancelled!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RecieverList.this, ViewList.class));
                        finish();
                    }
                }).setNegativeButton("No", null);
        AlertDialog alert_exit = builder_exitbutton.create();
        alert_exit.show();
    }

}
