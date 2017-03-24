package com.example.user.emaillogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText etname =(EditText) findViewById(R.id.etname);
        final EditText etemail =(EditText) findViewById(R.id.etemail);
        final TextView WelcomeMessage = (android.widget.TextView) findViewById(R.id.tvwelcomemessage);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");

        String message = name +"Welcome to Winkel";
        WelcomeMessage.setText(message);
        etname.setText(name);

        etemail.setText((CharSequence) etemail);
    }
}
