package com.example.onlinebanking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

public class Transactions extends AppCompatActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        Database db= new Database(this);
        Intent intent = getIntent();
        username = intent.getExtras().getString("msg");// Used to Pass username to this activity from Home page to fill in
        final MediaPlayer successSound = MediaPlayer.create(this, R.raw.ssound);
        final MediaPlayer errorSound = MediaPlayer.create(this, R.raw.esound);
        Date currentTime = Calendar.getInstance().getTime();
        ListView myList = (ListView)findViewById(R.id.logsList);
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        myList.setAdapter(listAdapter);

        TextView Balance= findViewById(R.id.blancetxt);
        Balance.setText(db.getUserBalance(username));

        Button deposit = findViewById(R.id.depositbtn);

        Button withdraw = (Button) findViewById(R.id.withdrawbtn);
        withdraw .setOnClickListener(new View.OnClickListener() {//---Withdraw
            @Override
            public void onClick(View view) {
                float amount = Float.parseFloat(((EditText) findViewById(R.id.amounttxt)).getText().toString());
                float userBalance = Float.parseFloat(db.getUserBalance(username));
                float newBalance;
                String amountTxt = ((EditText) findViewById(R.id.amounttxt)).getText().toString();

                if(amountTxt.trim().length() == 0)//the field isn't empty
                {
                    Toast.makeText(getApplicationContext(), "Please provide an input!", Toast.LENGTH_LONG).show();
                    errorSound.start();
                    return;
                }
                else {

                    if (amount < userBalance && amount > 0) {
                        newBalance = userBalance - amount;
                        db.updateBalance(username, newBalance);
                        Balance.setText(String.valueOf(newBalance));
                        String value = "You withdrew " +String.valueOf(amount)+"L.E from your account on "+ currentTime;
                        listAdapter.add(value);
                        Toast.makeText(getApplicationContext(), "The amount has been successfully withdrawn", Toast.LENGTH_LONG).show();
                        successSound.start();
                    } else {
                        Toast.makeText(getApplicationContext(), "You don't have that amount of money to withdraw", Toast.LENGTH_LONG).show();
                        errorSound.start();
                    }
                }


            }

        });

        deposit.setOnClickListener(new View.OnClickListener() {//---DEPOSIT
            @Override
            public void onClick(View view) {
                float amount = Float.parseFloat(((EditText) findViewById(R.id.amounttxt)).getText().toString());
                float userBalance = Float.parseFloat(db.getUserBalance(username));
                float newBalance;
                String amountTxt = ((EditText) findViewById(R.id.amounttxt)).getText().toString();
                if(amountTxt.trim().length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "Please provide an input!", Toast.LENGTH_LONG).show();
                    errorSound.start();
                    return;
                }
                else {

                    if (amount > 0) {
                        newBalance = userBalance + amount;
                        db.updateBalance(username, newBalance);
                        Balance.setText(String.valueOf(newBalance));
                        String value = "You deposited " +String.valueOf(amount)+"L.E to your account on "+ String.valueOf(currentTime);
                        listAdapter.add(value);
                        Toast.makeText(getApplicationContext(), "The amount has been successfully deposited to your account", Toast.LENGTH_LONG).show();
                        successSound.start();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_LONG).show();
                        errorSound.start();
                    }
                }
            }


        });
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() { //show the log when clicking on it
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), ((TextView)view).getText().toString(), Toast.LENGTH_LONG).show();

            }
        });

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
                Intent intent = new Intent(Transactions.this, AppHome.class);//change Profile.class to CurrencyConvert java class
                intent.putExtra("msg", username);
                startActivity(intent);
                return true;
            case R.id.ProfilePage:
                Intent prof = new Intent(Transactions.this, Profile.class);
                prof.putExtra("msg", username);
                startActivity(prof);
                return true;
                case R.id.Editpage:
                Intent edit = new Intent(Transactions.this, EditData.class);
                edit.putExtra("msg", username);
                startActivity(edit);
                return true;
            case R.id.CurrencyPage:
                Intent curr = new Intent(Transactions.this, CurrencyConvert.class);//change AppHome.class to CurrencyConvert java class
                curr.putExtra("msg", username);
                startActivity(curr);
                return true;
            case R.id.TransactionsPage:
           Toast.makeText(Transactions.this, "You are Already in this tab!", Toast.LENGTH_LONG).show();// Use this to show message when user choose from the menu the same page he is in
              return true;
            case R.id.Logoutpage:
                Intent log = new Intent(Transactions.this, Login.class);//change AppHome.class to login java class
                log.putExtra("msg", username);
                startActivity(log);
                return true;
        }
        return true;
    }

}

