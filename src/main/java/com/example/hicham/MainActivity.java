package app.com.example.hicham.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView email;
    TextView password;
    TextView confirmPassword;
    TextView text;
    Button SignUp;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        email = (TextView)findViewById(R.id.email);
        password = (TextView)findViewById(R.id.password);
        confirmPassword = (TextView)findViewById(R.id.confirmPassword);
        SignUp = (Button)findViewById(R.id.SignUp);
        text = (TextView)findViewById(R.id.text);
        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase("https://shining-inferno-7961.firebaseio.com/");
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().equals(confirmPassword.getText().toString()))
                {
                    ref.createUser(email.getText().toString(), password.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            text.setText("success");
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            // there was an error
                            text.setText("error");
                        }
                    });
                }
            }
        });

    }
}

