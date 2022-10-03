package com.example.onlinebanking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    //*Variables
    Button callSignUp, btn, callforgetpass;
    ImageView image;
    TextView logoText, slogan;
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        Database db = new Database(this);
        final MediaPlayer successSound = MediaPlayer.create(this, R.raw.ssound);
        final MediaPlayer errorSound = MediaPlayer.create(this, R.raw.esound);
        //* Hooks
        callSignUp = findViewById(R.id.signup);
        image = findViewById(R.id.image);
        logoText = findViewById(R.id.logo_name);
        slogan = findViewById(R.id.slogan_name);
        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        btn = findViewById(R.id.btn1);
        callforgetpass=findViewById(R.id.forgetpassBTN);
        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, SignUp.class);

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair <View,String>(image, "logo_image");
                pairs[1] = new Pair <View,String>(logoText, "logo_text");
                pairs[2] = new Pair <View,String>(slogan, "logo_text2");
                pairs[3] = new Pair <View,String>(username, "UT1");
                pairs[4] = new Pair <View,String>(password, "PT1");
                pairs[5] = new Pair <View,String>(btn, "logo_button");
                pairs[6] = new Pair <View,String>(callSignUp, "logo_button2");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login. this, pairs);
                    startActivity(intent, options.toBundle());
                }

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Uname= username.getEditText().getText().toString();
                String Upassword= password.getEditText().getText().toString();

                if (db.checkuser(Uname,Upassword))
                {
                    Toast.makeText(getApplicationContext(), "Welcome Back", Toast.LENGTH_LONG).show();
                    successSound.start();
                    Intent intent = new Intent(Login.this, AppHome.class);
                    intent.putExtra("msg", Uname);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong UserName or Password", Toast.LENGTH_LONG).show();
                    errorSound.start();
                }
            }
        });
    }
}