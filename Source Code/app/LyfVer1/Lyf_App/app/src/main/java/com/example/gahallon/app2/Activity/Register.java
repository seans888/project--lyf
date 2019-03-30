package com.example.gahallon.app2.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gahallon.app2.MainActivity;
import com.example.gahallon.app2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Register extends AppCompatActivity {
    private static final String REGISTER_URL="http://206.189.209.210/lyfapi/public/user/register";

    private EditText edtFname;
    private EditText edtMname;
    private EditText edtLname;
    private EditText edtEmail;
    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtRePassword;
    private Button btnSignUp;
    private TextView linkLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtFname = findViewById(R.id.edtFname);
        edtMname = findViewById(R.id.edtMname);
        edtLname = findViewById(R.id.edtLname);
        edtEmail = findViewById(R.id.edtEmail);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtRePassword = findViewById(R.id.edtRePassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        linkLogin = findViewById(R.id.linkLogin);

        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        edtFname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtFname.setError(null);
            }
        });

        edtMname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtMname.setError(null);
            }
        });

        edtLname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtLname.setError(null);
            }
        });

        edtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtEmail.setError(null);
            }
        });

        edtUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtUsername.setError(null);
            }
        });

        edtPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPassword.setError(null);
            }
        });

        edtRePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtRePassword.setError(null);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fNameCheck()){
                    edtFname.setError("First Name field is required.");
                }
                if(lNameCheck()){
                    edtLname.setError("Last Name field is required.");
                }
                if(emailCheck()){
                    edtEmail.setError("Email field is required.");
                }
                if(usernameCheck1()){
                    edtUsername.setError("Username field is required.");
                }
                else if(usernameCheck2()){
                    edtUsername.setError("Username must be at least 8 characters");
                }
                if (rePasswordCheck()){
                    edtRePassword.setError("Re enter Password field is required");
                }
                if(passwordCheck1()){
                    edtPassword.setError("Password field is required");
                }
                else if (passwordCheck2()){
                    edtPassword.setError("Password must be at least 8 characters");
                }
                else if (passwordMatch()){
                    edtPassword.setError("Password do not match");
                    edtRePassword.setError("Password do not match");

                }

                if (!fNameCheck()&&!lNameCheck()&&!emailCheck()&&!usernameCheck1()&&!usernameCheck2()&&!rePasswordCheck()&&!passwordCheck1()&&!passwordCheck2()&&!passwordMatch()){
                    createAccount();
                }



            }
        });




    }

    private void createAccount(){
        RequestQueue queue = Volley.newRequestQueue(Register.this);
        final String fName = edtFname.getText().toString().trim();
        final String mName = edtMname.getText().toString().trim();
        final String lName = edtLname.getText().toString().trim();
        final String email = edtEmail.getText().toString().trim();
        final String username = edtUsername.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseObject = new JSONObject(response);
                            String message = responseObject.getString("message");

                            if (message.equals("fail")){
                                JSONArray arrJson = responseObject.getJSONArray("errors");
                                String[] errors = new String[arrJson.length()];
                                for(int i = 0; i < arrJson.length(); i++)
                                    errors[i] = arrJson.getString(i);
                                List<String> list = Arrays.asList(errors);

                                if (list.contains("The email has already been taken.")){
                                    edtEmail.setError("The email has already been taken.");
                                }
                                if (list.contains("The username has already been taken.")){
                                    edtUsername.setError("The username has already been taken.");
                                }
                            }
                            else if (message.equals("success")){
                                Toast.makeText(Register.this,"Account Succesfully Created",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Register.this, Login.class);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this,error.toString(),Toast.LENGTH_LONG).show();

                    }
                }
        ){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("first_name",fName);
                params.put("middle_name",mName);
                params.put("last_name",lName);
                params.put("email",email);
                params.put("username",username);
                params.put("password",password);

                return params;
            }
        };
        queue.add(stringRequest);
    }
    private boolean fNameCheck(){
        String fName = edtFname.getText().toString().trim();
        // Toast.makeText(Login.this,pass,Toast.LENGTH_LONG).show();

        if (fName.length()==0) {
            return true;
        }
        return false;
    }
    private boolean lNameCheck(){
        String lName = edtLname.getText().toString().trim();
        // Toast.makeText(Login.this,pass,Toast.LENGTH_LONG).show();

        if (lName.length()==0) {
            return true;
        }
        return false;
    }
    private boolean emailCheck(){
        String email = edtEmail.getText().toString().trim();
        // Toast.makeText(Login.this,pass,Toast.LENGTH_LONG).show();

        if (email.length()==0) {
            return true;
        }
        return false;
    }
    private boolean usernameCheck1(){
        String username = edtUsername.getText().toString().trim();
        // Toast.makeText(Login.this,pass,Toast.LENGTH_LONG).show();

        if (username.length()==0) {
            return true;
        }
        return false;
    }
    private boolean usernameCheck2(){
        String username = edtUsername.getText().toString().trim();
        // Toast.makeText(Login.this,pass,Toast.LENGTH_LONG).show();

        if (username.length()<8) {
            return true;
        }
        return false;
    }
    private boolean passwordCheck1(){
        String password = edtPassword.getText().toString().trim();

        if (password.length()==0){
            return true;
        }
        return false;
    }
    private boolean passwordCheck2(){
        String password = edtPassword.getText().toString().trim();
        // Toast.makeText(Login.this,pass,Toast.LENGTH_LONG).show();

        if (password.length()<8) {
            return true;
        }
        return false;
    }

    private boolean rePasswordCheck(){
        String rePassword = edtRePassword.getText().toString().trim();

        if (rePassword.length()==0){
            return true;
        }
        return false;
    }

    private boolean passwordMatch(){
        String password = edtPassword.getText().toString().trim();
        String rePassword = edtRePassword.getText().toString().trim();
        if (!password.equals(rePassword)){
            return true;
        }
        return false;
    }
}
