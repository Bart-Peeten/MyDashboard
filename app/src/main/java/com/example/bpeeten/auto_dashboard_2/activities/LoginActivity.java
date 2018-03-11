package com.example.bpeeten.auto_dashboard_2.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.toolbox.StringRequest;
import com.example.bpeeten.auto_dashboard_2.R;
import com.example.bpeeten.auto_dashboard_2.dbHelpers.UserOperations;
import com.example.bpeeten.auto_dashboard_2.models.User;

public class LoginActivity extends AppCompatActivity {

    private EditText                  passwd;
    private EditText                  mail;
    private UserOperations            userOperations;
    private User                      logedInUser;
    private String                    prefEmail;
    private String                    prefPasswd;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passwd              = (EditText) findViewById(R.id.input_password);
        mail                = (EditText) findViewById(R.id.input_email);
        Button signUpButton = (Button) findViewById(R.id.btn_signup);
        toolbar             = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userOperations = new UserOperations(this);
        getUserInfoFromPref();

        Log.e("PREF TAG", prefEmail + " " + prefPasswd);

        mail.setText(prefEmail);
        passwd.setText(prefPasswd);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }



    public void register(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void login(View view){
        String givenPassword = passwd.getText().toString();
        String givenEmail    = mail.getText().toString();

        if (givenEmail.isEmpty() && givenPassword.isEmpty()) {
            Toast.makeText(this, "Vul alle velden in a.u.b", Toast.LENGTH_LONG).show();
        }
        else{
            // Save the user login info in sharedPreferences.
            saveLoginPreferences(givenPassword, givenEmail);

            // Show a toast to inform the user the login is saved in SharedPreferences.
            Toast.makeText(this, "login information is saved", Toast.LENGTH_LONG).show();

            // Check if the user login is correct.
            logedInUser = userOperations.checkUser(new User(givenEmail, givenPassword));
            if (logedInUser != null) {
                // Open the Home activity.
                openHomeActivity();
            }
        }
    }

    private void saveLoginPreferences(String givenPassword, String givenEmail) {
        // safe login in SharedPreferences.
        SharedPreferences preferences = getSharedPreferences("USERINFO", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Password", givenPassword);
        editor.putString("Email", givenEmail);
        editor.apply();
    }

    private void openHomeActivity() {
        Toast.makeText(this, "User" + mail.getText().toString() + " en Paswoord zijn een match.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("User", logedInUser);
        startActivity(intent);
    }

    private void getUserInfoFromPref(){
        SharedPreferences preferences = getSharedPreferences("USERINFO", MODE_PRIVATE);
        prefEmail = preferences.getString("Email", "");
        prefPasswd = preferences.getString("Password", "");
    }
}

