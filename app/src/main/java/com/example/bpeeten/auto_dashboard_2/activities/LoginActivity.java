package com.example.bpeeten.auto_dashboard_2.activities;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.bpeeten.auto_dashboard_2.R;
import com.example.bpeeten.auto_dashboard_2.dbHelpers.UserOperations;
import com.example.bpeeten.auto_dashboard_2.models.User;

public class LoginActivity extends AppCompatActivity {

    private EditText passwd;
    private EditText mail;
    private UserOperations userOperations;
    private User logedInUser;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passwd = (EditText) findViewById(R.id.input_password);
        mail   = (EditText) findViewById(R.id.input_email);
        Button signUpButton = (Button) findViewById(R.id.btn_signup);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userOperations = new UserOperations(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }



    public void SignUP(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void SignIN (View view){
        String givenPassword = passwd.getText().toString();
        String givenEmail    = mail.getText().toString();

        logedInUser = userOperations.checkUser(new User(givenEmail, givenPassword));

        if (logedInUser != null){
            openHomeActivity();
        }

    }

    private void openHomeActivity() {
        Toast.makeText(this, "User en Paswoord zijn een match.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("User", logedInUser);
        startActivity(intent);
    }
}

