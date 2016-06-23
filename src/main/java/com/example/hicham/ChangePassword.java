package app.com.example.hicham.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class ChangePassword extends AppCompatActivity {
    TextView password;
    TextView confirmPassword;
    Button changePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        password = (TextView)findViewById(R.id.password);
        confirmPassword = (TextView)findViewById(R.id.confirmPassword);

        changePassword = (Button)findViewById(R.id.changePassword);
        Firebase.setAndroidContext(this);
        final Firebase ref  = new Firebase("https://shining-inferno-7961.firebaseio.com/");
        Bundle bundle = this.getIntent().getExtras();
        final String email = (String)bundle.get("email");
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().equals(confirmPassword.getText().toString()))
                {
                   ref.changePassword(email);
                }
            }
        });

    }
}
