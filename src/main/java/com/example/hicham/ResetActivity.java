package com.example.hicham.properties;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class ResetActivity extends AppCompatActivity {
TextView email;
TextView text;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment temporaryPassword;

    Button resetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        email = (TextView)findViewById(R.id.email);
        text = (TextView)findViewById(R.id.text);
        resetPassword = (Button)findViewById(R.id.ResetPassword);
        Firebase.setAndroidContext(this);
        temporaryPassword = new Fragment();
        //fragmentManager = getFragmentManager();
        final Firebase ref  = new Firebase("https://shining-inferno-7961.firebaseio.com/");
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.resetPassword(email.getText().toString(), new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {
                        text.setText("success");
                        fragmentTransaction = fragmentManager.beginTransaction();
                        //.add(R.id.resetFragment,temporaryPassword);
                        Intent intent = new Intent(ResetActivity.this, TemporaryPassword.class);
                        intent.putExtra("email",email.getText());
                        startActivity(intent);
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        text.setText("error");
                    }
                });
            }
        });
    }
}
