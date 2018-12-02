package com.example.colliensepodder.sharedpreferenceassignment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colliensepodder.sharedpreferenceassignment.Helper.DatabaseHelper;
import com.example.colliensepodder.sharedpreferenceassignment.R;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button button_SignUp;
    private EditText editText_Semail;
    private EditText editText_SPassword;
    //private Button button_SignUp;
    private TextView text_Login;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = new DatabaseHelper(this);
        button_SignUp = findViewById(R.id.button_SignUp);
        editText_Semail = findViewById(R.id.editText_Semail);
        editText_SPassword = findViewById(R.id.editText_SPassword);
        text_Login =findViewById(R.id.text_Login);
        button_SignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button_SignUp:
                register();
                break;
            case R.id.text_Login:
                startActivity(new Intent(SignUp.this,Login.class));
                default:
        }
    }

    private void register(){
        String email = editText_Semail.getText().toString();
        String password = editText_SPassword.getText().toString();
        if (email.isEmpty() && password.isEmpty()){
            displayToast("Email/Password field empty");
        } if (editText_SPassword.length()<8){
            editText_SPassword.setError("Password length minimum 8 character");
            return;
        }
        else {
            db.addUser(email,password);
            displayToast("User registered");
            finish();
        }
    }

    private void displayToast (String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
