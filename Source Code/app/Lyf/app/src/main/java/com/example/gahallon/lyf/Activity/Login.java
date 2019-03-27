package com.example.gahallon.lyf.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gahallon.lyf.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private static final String LOGIN_URL="http://206.189.209.210/lyfapi/public/user/login";
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        edtPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPassword.setError(null);

            }
        });

        edtUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtUsername.setError(null);

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String password = edtPassword.getText().toString();
                if (usernameCheck()){
                    edtUsername.setError("Username field is required");
                }
                else {
                    edtUsername.setError(null);
                }
                if (passwordCheck()){
                    edtPassword.setError("Password field is required");
                }
                else {
                    edtPassword.setError(null);
                }
                if (!passwordCheck()&&!usernameCheck()){
                    userLogin();
                }
            }
        });
    }
    private boolean passwordCheck() {
         String pass = edtPassword.getText().toString().trim();
       // Toast.makeText(Login.this,pass,Toast.LENGTH_LONG).show();

        if (pass.length()==0) {
            return true;
        }
        return false;
    }
    private boolean usernameCheck( ) {
         String username = edtUsername.getText().toString().trim();

        if (username.length()==0) {
            return true;
        }
        return false;
    }
    private void userLogin(){
        RequestQueue queue = Volley.newRequestQueue(Login.this);
        final String username = edtUsername.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseObject = new JSONObject(response);
                             String message = responseObject.getString("message");
                             if (message.equals("fail")){
                                 Toast.makeText(Login.this,"Username and password do not match",Toast.LENGTH_LONG).show();

                             }
                             else if (message.equals("404")){
                                 Toast.makeText(Login.this,"User does not exist",Toast.LENGTH_LONG).show();

                             }
                             else if (message.equals("success")){
                                 Toast.makeText(Login.this,"loggedin",Toast.LENGTH_LONG).show();
                             }

                             } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this,error.toString(),Toast.LENGTH_LONG).show();

                    }
                }
        ){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",username);
                params.put("password",password);

                return params;
            }
        };
        queue.add(stringRequest);
    }
}
