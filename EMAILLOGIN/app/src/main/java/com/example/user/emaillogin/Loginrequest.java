package com.example.user.emaillogin;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by User on 07/02/2017.
 */

public class Loginrequest extends StringRequest{
    private static final String LOGIN_REQUEST_URL = "http://picandlaugh.co.uk/login.php";
    private Map<String, String> params;

    public Loginrequest(String name, String password, Response.Listener<String> listener){

        super(Request.Method.POST,LOGIN_REQUEST_URL,listener, null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("password",password);
    }


    @Override
    public Map<String, String> getParams() {

        return params;
    }


}

