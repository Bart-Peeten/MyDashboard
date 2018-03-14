package com.example.bpeeten.auto_dashboard_2.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.bpeeten.auto_dashboard_2.R;
import com.example.bpeeten.auto_dashboard_2.controllers.PreferencesImpl;
import com.example.bpeeten.auto_dashboard_2.dbHelpers.UserOperations;
import com.example.bpeeten.auto_dashboard_2.interfaces.Preferences;
import com.example.bpeeten.auto_dashboard_2.models.User;

import java.io.File;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private EditText                  passwd;
    private EditText                  mail;
    private UserOperations            userOperations;
    private User                      logedInUser;
    private String                    prefEmail;
    private String                    prefPasswd;
    android.support.v7.widget.Toolbar toolbar;
    private static String             USERINFO = "USERINFO";
    private Preferences               preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passwd              = (EditText) findViewById(R.id.input_password);
        mail                = (EditText) findViewById(R.id.input_email);
        Button signUpButton = (Button) findViewById(R.id.btn_signup);
        toolbar             = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        preferences         = new PreferencesImpl(this);
        userOperations      = new UserOperations(this);
        setSupportActionBar(toolbar);
        // Check if the color of the app matched with the color
        // in the SharedPreferences.
        checkBackgroundColor();
    }

    private void checkBackgroundColor() {
        if (preferences.getColor() != getResources().getColor(R.color.colorPrimary)){
            toolbar.setBackgroundColor(preferences.getColor());
            getWindow().setStatusBarColor(preferences.getColor());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkBackgroundColor();
        Bundle b = getIntent().getExtras();
        String result = b.getString("logout");
        if (result != null) {
            Log.d("INTENT_TAG", "Logout is gedrukt.");
            if (result.equals("LOGOUT")) {
                passwd.setText("");
                mail.setText("");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    boolean pref = deleteSharedPreferences(USERINFO);
                    clearPreferences();
                    Log.d("DELETE_TAG", "Delete SharedPreferences");
                }
                else {
                    String filePath = getApplicationContext().getFilesDir().getParent()+"/shared_prefs/USERINFO.xml";
                    File deletePrefFile = new File(filePath );
                    deletePrefFile.delete();
                    clearPreferences();
                    Log.d("DELETE_TAG", "Delete SharedPreferences");
                }
            }
            else{
                getUserInfoFromPref();
                Log.e("PREF TAG", prefEmail + " " + prefPasswd);
                mail.setText(prefEmail);
                passwd.setText(prefPasswd);
                if (!mail.getText().toString().isEmpty() && !passwd.getText().toString().isEmpty()) {
                    openHomeActivity();
                }
            }
        }
        else {
            getUserInfoFromPref();
            Log.e("PREF TAG", prefEmail + " " + prefPasswd);
            mail.setText(prefEmail);
            passwd.setText(prefPasswd);
            if (!mail.getText().toString().isEmpty() && !passwd.getText().toString().isEmpty()) {
                openHomeActivity();
            }
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
            case R.id.logout:
                Intent intentLogout = new Intent(this, LoginActivity.class);
                intentLogout.putExtra("logout", "LOGOUT");
                startActivity(intentLogout);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view){
        String givenPassword = passwd.getText().toString();
        String givenEmail    = mail.getText().toString();

        if (givenEmail.isEmpty() && givenPassword.isEmpty()) {
            Toast.makeText(this, "Vul alle velden in a.u.b", Toast.LENGTH_LONG).show();
        }
        else{
            // Check if the user login is correct.
            logedInUser = userOperations.checkUser(new User(givenEmail, givenPassword));
            if (logedInUser != null) {
                // Save the user login info in sharedPreferences.
                saveLoginPreferences(givenPassword, givenEmail);
                // Show a toast to inform the user the login is saved in SharedPreferences.
                Toast.makeText(this, "login information bewaard", Toast.LENGTH_LONG).show();
                // Open the Home activity.
                openHomeActivity();
            }
        }
    }

    private void saveLoginPreferences(String givenPassword, String givenEmail) {
        // safe login in SharedPreferences.
        SharedPreferences preferences = getSharedPreferences(USERINFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Password", givenPassword);
        editor.putString("Email", givenEmail);
        editor.apply();
    }

    private void clearPreferences(){
        SharedPreferences pref = getSharedPreferences(USERINFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    private void openHomeActivity() {
        Toast.makeText(this, "User " + "'" +
                mail.getText().toString().toUpperCase() +
                "' en Paswoord zijn een match.",
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("User", logedInUser);
        startActivity(intent);
    }

    private void getUserInfoFromPref(){
        SharedPreferences preferences = getSharedPreferences(USERINFO, MODE_PRIVATE);
        prefEmail = preferences.getString("Email", "");
        prefPasswd = preferences.getString("Password", "");
    }
}

