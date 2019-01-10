package com.example.bobby.afinal;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText etLogUser,etLogPass;
    Button btnLog;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db= new DatabaseHelper(this);
        etLogUser = (EditText)findViewById(R.id.edtLogUs);
        etLogPass = (EditText)findViewById(R.id.edtLogPass);
        btnLog = (Button)findViewById(R.id.btnLogIn);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etLogUser.getText().toString();
                String password = etLogPass.getText().toString();
                Boolean Chkemailpass = db.usernamepassword(username,password);
                if(Chkemailpass==true) {
                    Toast.makeText(getApplicationContext(), "Succesfull Login", Toast.LENGTH_SHORT).show();
                    Intent b = new Intent(Login.this, MainActivity.class);
                    startActivity(b);
                }else {
                    Toast.makeText(getApplicationContext(), "Failed Login Attempt", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btnChange = findViewById(R.id.fragment_setting_change);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(i);
            }
        });
        Button btnSignUp = findViewById(R.id.btnSignIn);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,SignUpActivity.class);
                startActivity(i);
            }
        });

    }
}
