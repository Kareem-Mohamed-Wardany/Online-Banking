package com.example.onlinebanking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditData extends AppCompatActivity {
String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        Database db= new Database(this);
        Intent intent = getIntent();
        username = intent.getExtras().getString("msg");// Used to Pass username to this activity from Home page to fill in
        EditText emailVisible = (EditText) findViewById(R.id.emailTxt);
        EditText phoneVisible = (EditText) findViewById(R.id.phoneTxt);
        EditText passwordVisible = (EditText)findViewById(R.id.passwordTxt);
        emailVisible.setVisibility(View.INVISIBLE);
        phoneVisible.setVisibility(View.INVISIBLE);
        passwordVisible.setVisibility(View.INVISIBLE);
        Button emailBtn = (Button) findViewById(R.id.emailBtn);
        Button phoneBtn = (Button) findViewById(R.id.phoneBtn);
        Button passwordBtn = (Button) findViewById(R.id.passwordBtn);
        Button submitBtn = (Button) findViewById(R.id.submitBtn);

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                emailVisible.setVisibility(View.VISIBLE);


            }
        });


        phoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneVisible.setVisibility(View.VISIBLE);

            }
        });

        passwordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordVisible.setVisibility(View.VISIBLE);


            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTxt = emailVisible.getText().toString();
                String phoneTxt = phoneVisible.getText().toString();
                String passwordTxt = passwordVisible.getText().toString();

                if(emailTxt.isEmpty() && phoneTxt.isEmpty())
                {
                  db.updatePassword(username, passwordTxt);
                  db.updateEmail(username, db.getUserMail(username));
                  db.updatePhone(username, db.getUserPhone(username));
                  Toast.makeText(getApplicationContext(), "Your personal data has been successfully updated", Toast.LENGTH_LONG).show();
                }
                else if(phoneTxt.isEmpty() && passwordTxt.isEmpty())
                {
                  db.updateEmail(username, emailTxt);
                  db.updatePhone(username, db.getUserPhone(username));
                  db.updatePassword(username, db.getUserPassword(username));
                  Toast.makeText(getApplicationContext(), "Your personal data has been successfully updated", Toast.LENGTH_LONG).show();
                }
                else if(emailTxt.isEmpty() && passwordTxt.isEmpty())
                {
                   db.updatePhone(username, phoneTxt);
                   db.updateEmail(username, db.getUserMail(username));
                   db.updatePassword(username, db.getUserPassword(username));
                   Toast.makeText(getApplicationContext(), "Your personal data has been successfully updated", Toast.LENGTH_LONG).show();
                }
               else if((emailTxt.isEmpty()) && (passwordTxt.isEmpty()) && (phoneTxt.isEmpty()))
                {
                    Toast.makeText(getApplicationContext(), "You didn't provide any inputs", Toast.LENGTH_LONG).show();
                   return;
                }
               else if(emailTxt.isEmpty())
                {
                    db.updateEmail(username,db.getUserMail(username));
                    db.updatePassword(username,passwordTxt);
                    db.updatePhone(username, passwordTxt);
                    Toast.makeText(getApplicationContext(), "Your personal data has been successfully updated", Toast.LENGTH_LONG).show();
                }
               else if(phoneTxt.isEmpty())
                {
                    db.updatePhone(username, db.getUserPhone(username));
                    db.updatePassword(username,passwordTxt);
                    db.updateEmail(username, emailTxt);
                    Toast.makeText(getApplicationContext(), "Your personal data has been successfully updated", Toast.LENGTH_LONG).show();
                }
               else if(passwordTxt.isEmpty())
                {
                    db.updatePassword(username, db.getUserPassword(username));
                    db.updateEmail(username, emailTxt);
                    db.updatePhone(username, phoneTxt);
                    Toast.makeText(getApplicationContext(), "Your personal data has been successfully updated", Toast.LENGTH_LONG).show();
                }
                else
                {
                    db.updateEmail(username, emailTxt);
                    db.updatePhone(username, phoneTxt);
                    db.updatePassword(username, passwordTxt);
                    Toast.makeText(getApplicationContext(), "Your personal data has been successfully updated", Toast.LENGTH_LONG).show();
                }



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
                Intent intent = new Intent(EditData.this, AppHome.class);//change Profile.class to CurrencyConvert java class
                intent.putExtra("msg", username);
                startActivity(intent);
                return true;
            case R.id.ProfilePage:
                Intent prof = new Intent(EditData.this, Profile.class);
                prof.putExtra("msg", username);
                startActivity(prof);
                return true;
            case R.id.Editpage:
                Toast.makeText(EditData.this, "You are Already in this tab!", Toast.LENGTH_LONG).show();
                return true;
            case R.id.CurrencyPage:
                Intent curr = new Intent(EditData.this, CurrencyConvert.class);//change AppHome.class to CurrencyConvert java class
                curr.putExtra("msg", username);
                startActivity(curr);
                return true;
            case R.id.TransactionsPage:
                Intent trans = new Intent(EditData.this, Transactions.class);
                trans.putExtra("msg", username);
                startActivity(trans);
                return true;
            case R.id.Logoutpage:
                Intent log = new Intent(EditData.this, Login.class);//change AppHome.class to login java class
                log.putExtra("msg", username);
                startActivity(log);
                return true;
        }
        return true;
    }

}