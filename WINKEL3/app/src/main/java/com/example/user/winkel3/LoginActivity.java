package com.example.user.winkel3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

     private Button buttonLogin;
     private EditText editTextEmail;
    private EditText editTextPassword;

    private TextView textViewSignup;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //start profile here
            startActivity(new Intent(getApplicationContext(),profileActivity.class));
        }

        progressDialog = new ProgressDialog(this);


        editTextEmail    = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignup = (TextView) findViewById(R.id.textViewSignup);

        buttonLogin = (Button)  findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT).show();
            return; // stops the functon from excecuting further
        }
        if (TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "please enter Password", Toast.LENGTH_SHORT).show();
            return; //stopping the function from executing further

        }

        progressDialog.setMessage("Logging in ");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            //Start profile activity
                            startActivity(new Intent(getApplicationContext(),profileActivity.class));
                        }
                    }
                });

    }


    public void onClick(View view){
        if (view == buttonLogin){
            userLogin();
        }
        if(view == textViewSignup){
            finish();
            startActivity(new Intent(this,MainActivity.class ));
        }
    }
}
