package com.example.colliensepodder.sharedpreferenceassignment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.colliensepodder.sharedpreferenceassignment.Class.Session;
import com.example.colliensepodder.sharedpreferenceassignment.R;

public class MainActivity extends AppCompatActivity {

    private Button btnLogout;
    private TextView tvEmail;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = findViewById(R.id.btnLogout);
        tvEmail = findViewById(R.id.tvEmail);
        session = new Session(this);
        tvEmail.setText(getIntent().getStringExtra("mode"));

        if (!session.loggedin()){
            logout();
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }
    private void logout(){
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MainActivity.this,Login.class));
    }
}
