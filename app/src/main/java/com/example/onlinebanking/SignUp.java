package com.example.onlinebanking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignUp extends AppCompatActivity {

    //*Variables
    Button callLogin, signbtn;
    ImageView image;
    TextView logoText, slogan;
    TextInputLayout username, password,balance,MobileNumber,Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        Database db = new Database(this);
        db.adduser("test","test","0111111111","test@test.com",5000);
        //*Hooks
        callLogin = findViewById(R.id.login);
        image = findViewById(R.id.image2);
        logoText = findViewById(R.id.logo_name2);
        slogan = findViewById(R.id.slogan_name2);
        username = findViewById(R.id.username2);
        password = findViewById(R.id.password2);
        balance = findViewById(R.id.StartingBalance);
        MobileNumber = findViewById(R.id.phoneNo);
        Email = findViewById(R.id.email);
        signbtn = findViewById(R.id.btn2);



        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Uname= Objects.requireNonNull(username.getEditText()).getText().toString();
                String Upassword= Objects.requireNonNull(password.getEditText()).getText().toString();
                String Ubalance= Objects.requireNonNull(balance.getEditText()).getText().toString();
                int userbal= Integer.parseInt(Ubalance);
                String Uphone= Objects.requireNonNull(MobileNumber.getEditText()).getText().toString();
                String Umail= Objects.requireNonNull(Email.getEditText()).getText().toString();
                db.adduser(Uname,Upassword,Uphone,Umail,userbal);
                Toast.makeText(getApplicationContext(), "Account Created Successfully!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignUp.this, AppHome.class);
                intent.putExtra("msg", Uname);
                startActivity(intent);

            }
        });

        callLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this, Login.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair <View,String>(image, "logo_image");
                pairs[1] = new Pair <View,String>(logoText, "logo_text");
                pairs[2] = new Pair <View,String>(slogan, "logo_text2");
                pairs[3] = new Pair <View,String>(username, "UT1");
                pairs[4] = new Pair <View,String>(password, "PT1");
                pairs[5] = new Pair <View,String>(signbtn, "logo_button");
                pairs[6] = new Pair <View,String>(callLogin, "logo_button2");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp. this, pairs);
                    startActivity(intent, options.toBundle());
                }

            }
        });
    }
}