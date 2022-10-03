package com.example.onlinebanking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

public class CurrencyConvert extends AppCompatActivity {

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_convert);
        username= getIntent().getStringExtra("msg");
        final Spinner curroptions = findViewById(R.id.spinner);
        curroptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertcurr currentcurrency = new convertcurr();
                EditText currText = findViewById(R.id.txt_curr);
                if (!currText.getText().toString().equals("")){
            String SelectedOption = curroptions.getSelectedItem().toString();
            if (SelectedOption.equals("USD"))
            {
                float curr = Float.parseFloat(currText.getText().toString());
                currentcurrency.setCurrency(curr);
                curr = currentcurrency.convertToUSD();
                currText.setText((String.valueOf(curr)));

            }
            else if (SelectedOption.equals("EUR"))
            {
                float curr = Float.parseFloat(currText.getText().toString());
                currentcurrency.setCurrency(curr);
                curr = currentcurrency.convertToEUR();
                currText.setText((String.valueOf(curr)));

            }
            else if (SelectedOption.equals("JPY"))
            {
                float curr = Float.parseFloat(currText.getText().toString());
                currentcurrency.setCurrency(curr);
                curr = currentcurrency.convertToJPY();
                currText.setText((String.valueOf(curr)));

            }
            else if (SelectedOption.equals("INR"))
            {
                float curr = Float.parseFloat(currText.getText().toString());
                currentcurrency.setCurrency(curr);
                curr = currentcurrency.convertToINR();
                currText.setText((String.valueOf(curr)));

            }

            else {
                Toast.makeText(getApplicationContext(),"please enter a valid currency", Toast.LENGTH_LONG).show();}
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                Intent intent = new Intent(CurrencyConvert.this, AppHome.class);//change Profile.class to CurrencyConvert java class
                intent.putExtra("msg", username);
                startActivity(intent);
                return true;
            case R.id.ProfilePage:
                Intent prof = new Intent(CurrencyConvert.this, Profile.class);//change AppHome.class to CurrencyConvert java class
                prof.putExtra("msg", username);
                startActivity(prof);

                return true;
            case R.id.TransactionsPage:
                Intent trans = new Intent(CurrencyConvert.this, Transactions.class);//change AppHome.class to TransactionsPage java class
                trans.putExtra("msg", username);
                startActivity(trans);
                return true;
            case R.id.Logoutpage:
                Intent log = new Intent(CurrencyConvert.this, Login.class);//change AppHome.class to login java class
                log.putExtra("msg", username);
                startActivity(log);
                return true;
                 case R.id.Editpage:
                Intent edit = new Intent(CurrencyConvert.this, EditData.class);//change AppHome.class to Editpage java class
                     edit.putExtra("msg", username);
                startActivity(edit);
                return true;
            case R.id.CurrencyPage:
                Toast.makeText(CurrencyConvert.this, "You are Already in this tab!", Toast.LENGTH_LONG).show();
                return true;
        }
        return true;
    }

}