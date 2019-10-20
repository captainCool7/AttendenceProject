package com.captaincool.attendenceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

public class Login extends AppCompatActivity {

    private EditText name,uname,pass;
    private Button loginb;
    private mydb db;
    ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.name);
        uname = findViewById(R.id.uname);
        pass = findViewById(R.id.pass);
        loginb = findViewById(R.id.loginb);
        user = new ParseUser();

        loginb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }
    void signUp()
    {
        user.add("name",name.getText().toString());
        user.setUsername(uname.getText().toString());
        user.setPassword(pass.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(Login.this, "Signup Successful",Toast.LENGTH_SHORT).show();
                    ParseObject obj = new ParseObject("Teacher");
                    obj.put("Teachername",name.getText().toString());
                    obj.put("Password",pass.getText().toString());
                    obj.saveInBackground();
                    Intent i = new Intent(Login.this,MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
