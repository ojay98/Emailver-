package com.example.user.emaillogin;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import com.android.volley.RequestQueue;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;




import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private StorageReference mStorageRef;
    // Write a message to the database

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mStorageRef = FirebaseStorage.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText etpassword =(EditText) findViewById(R.id.etpassword);
        final EditText etname =(EditText) findViewById(R.id.etname);
        final Button blogin =(Button) findViewById(R.id.blogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvregisterhere);
        registerLink.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
               Intent registerIntent = new Intent(MainActivity.this, Register.class);
               MainActivity.this.startActivity(registerIntent);
            }
        });

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final String name =etname.getText().toString();
                final String password = etpassword.getText().toString();

                Response.Listener<String>ResponseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse= new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("sucess");
                            if (success){
                               String name = jsonResponse.getString("name");
                                String password = jsonResponse.getString("password");

                                Intent intent = new Intent(MainActivity.this, UserAreaActivity.class);
                                 intent.putExtra("name",name);
                                intent.putExtra ("password",password);
                                MainActivity.this.startActivity(intent);

                            }

                            else

                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Login failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Loginrequest loginrequest = new Loginrequest(name, password, ResponseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginrequest);
            }
        });
    }







    // Read from the database






}
