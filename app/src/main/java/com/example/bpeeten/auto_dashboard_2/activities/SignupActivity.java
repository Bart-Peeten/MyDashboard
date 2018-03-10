package com.example.bpeeten.auto_dashboard_2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bpeeten.auto_dashboard_2.R;
import com.example.bpeeten.auto_dashboard_2.dbHelpers.UserOperations;
import com.example.bpeeten.auto_dashboard_2.models.User;


public class SignupActivity extends AppCompatActivity{

        EditText name;
        EditText email;
        EditText passwd;
        String userPasswd;
        String userEmail;
        String userName;

        UserOperations userOperations;
        android.support.v7.widget.Toolbar toolbar;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);

            name = (EditText) findViewById(R.id.input_name);
            email = (EditText) findViewById(R.id.input_email);
            passwd = (EditText) findViewById(R.id.input_password);

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

        public void SignUp(View view){
            userName = name.getText().toString();
            userEmail = email.getText().toString();
            userPasswd = passwd.getText().toString();

            User user = new User(userName, userEmail, userPasswd);

            long id = userOperations.addUser(user);

            Toast.makeText(this, "User is added succesfully to DB.", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

    }
}
