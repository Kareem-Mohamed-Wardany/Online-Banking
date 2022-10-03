package com.example.onlinebanking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class Profile extends AppCompatActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Database db = new Database(this);

        Intent intent = getIntent();
        username = intent.getExtras().getString("msg");// Used to Pass username to this activity from Home page to fill in
        TextView name = findViewById(R.id.hisnameprofile);
        name.setText(username);
        TextView mail = findViewById(R.id.hismailprofile);
        mail.setText(db.getUserMail(username));
        TextView phone = findViewById(R.id.hisPhoneProfile);
        phone.setText(db.getUserPhone(username));
        TextView Balance = findViewById(R.id.hisBalanceProfile);
        Balance.setText(db.getUserBalance(username));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appmenu,menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.HomePage:
                Intent intent = new Intent(Profile.this, AppHome.class);//change Profile.class to CurrencyConvert java class
                intent.putExtra("msg", username);
                startActivity(intent);
                return true;
            case R.id.ProfilePage:
                Toast.makeText(Profile.this, "You are Already in this tab!", Toast.LENGTH_LONG).show();// Use this to show message when user choose from the menu the same page he is in
                return true;
            case R.id.TransactionsPage:
                Intent trans = new Intent(Profile.this, Transactions.class);//change AppHome.class to TransactionsPage java class
                trans.putExtra("msg", username);
                startActivity(trans);
                return true;
            case R.id.Logoutpage:
                Intent log = new Intent(Profile.this, Login.class);//change AppHome.class to login java class
                log.putExtra("msg", username);
                startActivity(log);
                return true;
                 case R.id.Editpage:
                Intent edit = new Intent(Profile.this, EditData.class);//change AppHome.class to Editpage java class
                edit.putExtra("msg", username);
                startActivity(edit);
                return true;
            case R.id.CurrencyPage:
                Intent curr = new Intent(Profile.this, CurrencyConvert.class);//change AppHome.class to CurrencyConvert java class
                curr.putExtra("msg", username);
                startActivity(curr);
                return true;
        }
        return true;
    }
}