package com.example.bpeeten.auto_dashboard_2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bpeeten.auto_dashboard_2.R;
import com.example.bpeeten.auto_dashboard_2.controllers.PreferencesImpl;
import com.example.bpeeten.auto_dashboard_2.dbHelpers.UserOperations;
import com.example.bpeeten.auto_dashboard_2.interfaces.Preferences;
import com.example.bpeeten.auto_dashboard_2.models.User;


public class RegisterActivity extends AppCompatActivity{

        EditText name;
        EditText email;
        EditText passwd;
        String   userPasswd;
        String   userEmail;
        String   userName;

        UserOperations                    userOperations;
        android.support.v7.widget.Toolbar toolbar;
        Preferences                       preferences;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);

            name        = (EditText) findViewById(R.id.input_name);
            email       = (EditText) findViewById(R.id.input_email);
            passwd      = (EditText) findViewById(R.id.input_password);
            preferences = new PreferencesImpl(this);

            toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            userOperations = new UserOperations(this);

            checkBackgroundColor();
        }

    @Override
    protected void onResume() {
        super.onResume();
        checkBackgroundColor();
    }

    private void checkBackgroundColor() {
        if (preferences.getColor() != getResources().getColor(R.color.colorPrimary)){
            toolbar.setBackgroundColor(preferences.getColor());
            getWindow().setStatusBarColor(preferences.getColor());
        }
    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main_menu, menu);
            return true;
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Toast.makeText(this, "Settings is geselecteerd", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                Intent intentLogout = new Intent(this, LoginActivity.class);
                intentLogout.putExtra("logout", "LOGOUT");
                startActivity(intentLogout);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

        public void SignUp(View view){
            userName   = name.getText().toString();
            userEmail  = email.getText().toString();
            userPasswd = passwd.getText().toString();
            User user  = new User(userName, userEmail, userPasswd);
            long id    = userOperations.addUser(user);

            Toast.makeText(this, "User is added succesfully to DB.", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

    }
}
