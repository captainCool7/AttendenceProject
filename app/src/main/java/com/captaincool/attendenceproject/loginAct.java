package com.captaincool.attendenceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class loginAct extends AppCompatActivity {

    EditText username,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        if (ParseUser.getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
        public void Login(View v)
        {
            if(TextUtils.isEmpty(username.getText()))
            {
                username.setError("Username is required");
            } else
            if(TextUtils.isEmpty(pass.getText()))
            {
                username.setError("Password is required");
            } else
            {
                final ProgressDialog progress = new ProgressDialog(this);
                progress.setMessage("Loading.....");
                progress.show();
                ParseUser.logInInBackground(username.getText().toString(),pass.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        progress.dismiss();
                        if (parseUser != null) {
                            Toast.makeText(loginAct.this,"Welcome back",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(loginAct.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            ParseUser.logOut();
                            Toast.makeText(loginAct.this,e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
    public void signUp(View v)
    {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
}
