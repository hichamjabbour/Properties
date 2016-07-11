package com.example.hicham.properties;

import android.accounts.AccountManager;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;

import org.json.JSONObject;

public class LoginActivity extends FragmentActivity implements
        GoogleApiClient.OnConnectionFailedListener{
    EditText email;
    EditText password;
    TextView label;
    Button login;
    Button signUp;
    EditText show;
    SignInButton google;
    public static Firebase myFirebaseRef;
    public static GoogleSignInOptions gso;
    public static GoogleApiClient mGoogleApiClient;
    JSONObject json;
    public static final int RC_SIGN_IN = 9001;
    public static final int RC_REQUEST_ACCOUNT = 1000;
    GoogleSignInAccount account;
    public static String mEmail;
    /* A flag indicating that a PendingIntent is in progress and prevents us from starting further intents. */
    public static boolean mGoogleIntentInProgress;

    /* Track whether the sign-in button has been clicked so that we know to resolve all issues preventing sign-in
     * without waiting. */
    public static boolean mGoogleLoginClicked;

    /* Store the connection result from onConnectionFailed callbacks so that we can resolve them when the user clicks
     * sign-in. */
    public static ConnectionResult mGoogleConnectionResult;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        label = (TextView)findViewById(R.id.label);
        login = (Button)findViewById(R.id.login);
        signUp = (Button)findViewById(R.id.SignUp);
        google = (SignInButton) findViewById(R.id.google);
        show = (EditText)findViewById(R.id.show);
        myFirebaseRef = new Firebase("https://shining-inferno-7961.firebaseio.com/?page=Auth");


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                login.setEnabled(false);
                myFirebaseRef.authWithPassword(email.getText().toString(), password.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Intent myIntent = new Intent(LoginActivity.this, PropertiesActivity.class);
                        myIntent.putExtra("email", email.getText().toString());
                        myIntent.putExtra("password", password.getText().toString());
                        startActivity(myIntent);
                    }
                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {

                    }
                });
            }
        });


        google.setScopes(new Scope[]{new Scope(Scopes.EMAIL),new Scope(Scopes.PROFILE),new Scope(Scopes.PLUS_LOGIN)});

        google.setSize(SignInButton.SIZE_STANDARD);
        google.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {

                                          PickAccount();
                                      }
                                  }

        );

        label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ResetActivity.class);
                LoginActivity.this.startActivity(intent);
            }

        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                LoginActivity.this.startActivity(intent);
            }

        });
    }

    public void getGoogleOAuthTokenAndLogin() {
        // [START_EXCLUDE silent]
        // [END_EXCLUDE]
        GetUsernameTask task = (GetUsernameTask) new GetUsernameTask(this,this.getBaseContext(),account,show).execute();

    }
    // [END auth_with_google]

    // [START on_start_add_listener]


    // [START signin]
    private void PickAccount() {
        String[] accountTypes = new String[]{GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE};
        Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                accountTypes, false, null, null, null, null);
        startActivityForResult(intent, RC_REQUEST_ACCOUNT);

    }

    private void SignIn(String Email) {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .setAccountName(Email)
                .requestEmail()
                .requestProfile()
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                //.requestIdToken(getString(R.string.client_id))
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(LoginActivity.this,LoginActivity.this)/* OnConnectionFailedListener */
                .addOnConnectionFailedListener(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        mGoogleLoginClicked = true;

        if (!mGoogleApiClient.isConnecting()) {
            if (mGoogleConnectionResult != null) {
                resolveSignInError();
            } else if (mGoogleApiClient.isConnected()) {
                getGoogleOAuthTokenAndLogin();
            } else {
                    /* connect API now */
                Log.d(GetUsernameTask.TAG, "Trying to connect to Google API");
                mGoogleApiClient.connect();

            }
        }

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent,RC_SIGN_IN);

    }

    // [END signin]

    private void signOut() {
        // Firebase sign out

        // Google sign out
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode,data);
        if(requestCode == RC_REQUEST_ACCOUNT)
        {
            if(resultCode == RESULT_OK)
            {
                mEmail = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                SignIn(mEmail);
            }
        }
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN || requestCode==GetUsernameTask.REQUEST_AUTHORIZATION) {


            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }

            String token = data.getStringExtra(AccountManager.KEY_INTENT);

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(result.isSuccess())
            {
                account = result.getSignInAccount();
                getGoogleOAuthTokenAndLogin();
            }




        }

        if(requestCode == GetUsernameTask.REQUEST_AUTHORIZATION)
        {
            getGoogleOAuthTokenAndLogin();
        }
    }
    private void revokeAccess() {
        // Firebase sign out
        // Google revoke access
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                    }
                });
    }

    /* A helper method to resolve the current ConnectionResult error. */
    private void resolveSignInError() {
        if (mGoogleConnectionResult.hasResolution()) {
            try {
                mGoogleIntentInProgress = true;
                mGoogleConnectionResult.startResolutionForResult(this,LoginActivity.RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                // The intent was canceled before it was sent.  Return to the default
                // state and attempt to connect to get an updated ConnectionResult.
                mGoogleIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }


}
