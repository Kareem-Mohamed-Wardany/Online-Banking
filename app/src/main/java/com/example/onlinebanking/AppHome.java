package com.example.onlinebanking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AppHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apphome);
        Database db = new Database(this);
        String username = getIntent().getStringExtra("msg");
        TextView name = findViewById(R.id.UserName);
        name.setText(username);
        TextView Balancetext = findViewById(R.id.BalanceText);
        Balancetext.setText(db.getUserBalance(username));



        ImageButton profile = findViewById(R.id.ProfileBTN);
        profile.setOnClickListener(view -> {
            Intent intent = new Intent(AppHome.this, Profile.class);
            intent.putExtra("msg", username);
            startActivity(intent);

        });
        ImageButton currency = findViewById(R.id.CurrencyConvertBTN);
        currency.setOnClickListener(view -> {
            Intent intent = new Intent(AppHome.this, CurrencyConvert.class);//change Profile.class to CurrencyConvert java class
            intent.putExtra("msg", username);
            startActivity(intent);
        });
        ImageButton transaction = findViewById(R.id.TransactionsBTN);
        transaction.setOnClickListener(view -> {
            Intent intent = new Intent(AppHome.this, Transactions.class);//change Profile.class to Transactions java class
            intent.putExtra("msg", username);
            startActivity(intent);
        });
        ImageButton edit = findViewById(R.id.EditBTN);
        edit.setOnClickListener(view -> {
            Intent intent = new Intent(AppHome.this, EditData.class);//change Profile.class to edit java class
            intent.putExtra("msg", username);
            startActivity(intent);
        });
        ImageButton logout = findViewById(R.id.Logout);//change Profile.class to login java class
        logout.setOnClickListener(view -> {
            Intent intent = new Intent(AppHome.this, Login.class);//change Profile.class to edit java class
            startActivity(intent);

        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Database db = new Database(this);
        String username;
        username=getIntent().getStringExtra("msg");
        TextView name = findViewById(R.id.UserName);
        name.setText(username);
        TextView Balancetext = findViewById(R.id.BalanceText);
        Balancetext.setText(db.getUserBalance(username));
        ImageButton profile = findViewById(R.id.ProfileBTN);
        profile.setOnClickListener(view -> {
            Intent intent = new Intent(AppHome.this, Profile.class);
            intent.putExtra("msg", username);
            startActivity(intent);
        });
    }
}
