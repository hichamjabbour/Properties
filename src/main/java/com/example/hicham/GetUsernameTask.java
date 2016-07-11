package com.example.hicham.properties;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Scopes;

import java.io.IOException;

/**
 * Created by Hicham on 7/1/2016.
 */
public class GetUsernameTask extends AsyncTask<Void,Void,Void> {
    public static final String TAG = "";
    public static int REQUEST_AUTHORIZATION = 125;
    LoginActivity mActivity;
    Context mContext;
    GoogleSignInAccount mAccount;
    EditText label;
    public static String accessToken;
    String errorMessage;


    GetUsernameTask(LoginActivity _activity, Context context, GoogleSignInAccount _account, EditText _label) {
        this.mContext = context;
        this.mActivity = _activity;
        this.mAccount = _account;
        this.label = _label;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String token = null;
        Account account = new Account(mAccount.getEmail(),GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
        try {
            token = GoogleAuthUtil.getToken(mContext,account,"oauth2:" + Scopes.PLUS_LOGIN);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GoogleAuthException e) {
            e.printStackTrace();
        }

        if (token != null) {
                                LoginActivity.myFirebaseRef.authWithOAuthToken("google", token, new Firebase.AuthResultHandler() {
                                    @Override
                                    public void onAuthenticated(AuthData authData) {
                                       label.setText("success");
                                       Intent intent = new Intent(mActivity,PropertiesActivity.class);
                                       intent.putExtra("Name",mAccount.getDisplayName());
                                       intent.putExtra("Picture",mAccount.getPhotoUrl().toString());
                                       LoginActivity.mGoogleLoginClicked = false;
                                       mActivity.startActivity(intent);
                                    }

                                    @Override
                                    public void onAuthenticationError(FirebaseError firebaseError) {
                                        label.setText("error");
                                    }
                                });
                            }
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.

        return null;

    }
}
