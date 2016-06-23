package com.example.hicham.properties;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Properties;

public class LoginActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    EditText label;
    Button login;
    Firebase myFirebaseRef;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        label = (EditText)findViewById(R.id.label);
        login = (Button)findViewById(R.id.login);
        myFirebaseRef = new Firebase("https://shining-inferno-7961.firebaseio.com/?page=Auth");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                login.setEnabled(false);
                myFirebaseRef.authWithPassword(email.getText().toString(), password.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        label.setText("success");
                        Intent myIntent = new Intent(LoginActivity.this, PropertiesActivity.class);
                        myIntent.putExtra("email", email.getText().toString());
                        myIntent.putExtra("password", password.getText().toString());
                        startActivity(myIntent);
                    }
                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        label.setText(firebaseError.getMessage());

                    }
                });
            }
        }

        );
    }
}
