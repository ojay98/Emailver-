package com.example.user.winkel3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profileActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView textView3;
    private Button buttonLogout;

    private FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }


        FirebaseUser user = firebaseAuth.getCurrentUser();


        textView3 = (TextView) findViewById(R.id.textView3);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(this);

    }

    public void onClick(View view){
       if (view == buttonLogout){
           firebaseAuth.signOut();
           startActivity(new Intent(this,LoginActivity.class));
       }
    }
}
