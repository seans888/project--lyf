package com.example.lyf_registerscreen;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.*;
import android.content.*;
import android.os.Bundle.*;
import android.text.method.*;
import android.widget.*;
import android.view.*;
import android.content.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextInputLayout nameWrapper = (TextInputLayout) findViewById(R.id.nameWrapper);
        final TextInputLayout emailWrapper = (TextInputLayout) findViewById(R.id.emailWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);

        nameWrapper.setHint("Name");
        emailWrapper.setHint("Email");
        passwordWrapper.setHint("Password");



    }
}
