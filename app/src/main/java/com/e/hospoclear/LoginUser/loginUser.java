package com.e.hospoclear.LoginUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.e.hospoclear.MainActivity;
import com.e.hospoclear.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hbb20.CountryCodePicker;

public class loginUser extends AppCompatActivity {


    EditText Phone_number ;
    Button Continue ;
    LinearLayout signUp ;
    CountryCodePicker ccp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        getSupportActionBar().hide();

        Phone_number = findViewById(R.id.Phone_number);
        Continue = findViewById(R.id.Continue);
        signUp = findViewById(R.id.signUp);
        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(Phone_number);
        getSupportActionBar().hide();

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Phone_number.getText().toString().isEmpty()){
                    Phone_number.setError("Enter Phone Number ");
                }
                else if (Phone_number.getText().toString().length() < 9){
                    Phone_number.setError("Enter Valid Phone Number");
                }
                else if (Phone_number.getText().toString().equals("123456789")){
                    Intent intent = new Intent(loginUser.this ,login_otp.class);
                    startActivity(intent);
                }
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (loginUser.this , RegisterUser.class));
                Toast.makeText(loginUser.this, "hiii", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user !=null){
            startActivity(new Intent(loginUser.this, MainActivity.class));
            finish();
        }

    }
}