package com.example.user.emaillogin;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 07/02/2017.
 */

public class Registerrequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://picandlaugh.co.uk/register.php";
    private Map<String, String> params;

    public Registerrequest(String name, String mail, String password, Response.Listener<String> listener){

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("email",mail);
        params.put("password",password);
    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }


}
