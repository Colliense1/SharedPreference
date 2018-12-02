package com.example.colliensepodder.sharedpreferenceassignment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.colliensepodder.sharedpreferenceassignment.Class.Session;
import com.example.colliensepodder.sharedpreferenceassignment.Helper.DatabaseHelper;
import com.example.colliensepodder.sharedpreferenceassignment.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHelper db;
    private Session session;
    private Button login_button;
    private Button signup_button;
    private EditText editText_email;
    private EditText editText_password;
    ProgressBar mprogressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mprogressbar = (ProgressBar)findViewById(R.id.pb);
        mprogressbar.setVisibility(View.GONE);
        db = new DatabaseHelper(this);
        session = new Session(this);
        login_button = findViewById(R.id.login_button);
        signup_button = findViewById(R.id.signup_button);
        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        login_button.setOnClickListener(this);
        signup_button.setOnClickListener(this);

        if (session.loggedin()){
            startActivity(new Intent(Login.this,MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_button:
                login();
                break;
            case R.id.signup_button:
               Intent i = new Intent(Login.this,SignUp.class);
               startActivity(i);

                break;
                default:
        }

    }
    private void login(){
        String email = editText_email.getText().toString();
        String password = editText_password.getText().toString();

        if (db.getUser(email,password)){
            session.setLoggedin(true);
            Intent i = new Intent(Login.this,MainActivity.class);
            i.putExtra("mode",email);
            startActivity(i);
            finish();

        }
        if (editText_password.length()<8) {
            editText_password.setError("Password length minimum 8 character");
            return;
        }
        else {
            Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
