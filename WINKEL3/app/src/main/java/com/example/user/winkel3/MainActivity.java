package com.example.user.winkel3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener{


    private  Button buttonRegister;
    private EditText editTextEmail;
    private EditText  editTextPassword;
    private EditText editTextName;
    private TextView textViewSignin;


    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            //start profile here
            startActivity(new Intent(getApplicationContext(),profileActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        editTextEmail    = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);
        editTextName = (EditText) findViewById(R.id.editTextName);


        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);


    }

    private void  sendEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "You have been sent an E-mail please verify your account ", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

    }
    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT).show();
            return; // stops the functon from excecuting further
        }
        if (TextUtils.isEmpty(password)){
            //passwordd is empty
            Toast.makeText(this, "please enter Password", Toast.LENGTH_SHORT).show();
            return; //stopping the function from executing further

        }

        progressDialog.setMessage("Registering User");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            sendEmail();

                                 finish();
                               //start profile here
                                startActivity(new Intent(getApplicationContext(),profileActivity.class));

                        }

                        else{
                            Toast.makeText(MainActivity.this, "Registration Failed, please make sure all feids are complete", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }



    public void onClick(View view){
        if (view == buttonRegister) {
            registerUser();
        }

        if (view == textViewSignin){
            // openlog in activity
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
