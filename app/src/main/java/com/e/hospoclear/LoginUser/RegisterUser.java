package com.e.hospoclear.LoginUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.e.hospoclear.R;
import com.e.hospoclear.ui.addNewHospital.register_OTP;
import com.hbb20.CountryCodePicker;

public class RegisterUser extends AppCompatActivity {

    Button registerUser ;
    CountryCodePicker ccp ;
    EditText phoneNumber , location, userName ;
    String phoneNumber_txt , location_txt , userName_txt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        registerUser = findViewById(R.id.registerUser);
        phoneNumber = findViewById(R.id.phoneNumber);
        location = findViewById(R.id.location);
        userName = findViewById(R.id.userName);
        ccp = findViewById(R.id.cpp1);
        ccp.registerCarrierNumberEditText(phoneNumber);

        getSupportActionBar().hide();


        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location_txt = location.getText().toString();
                userName_txt = userName.getText().toString() ;
                phoneNumber_txt =  ccp.getFullNumberWithPlus().replace(" ","");

                if (userName_txt.isEmpty()){
                    userName.setError("Enter user name");
                }
                else if (location_txt.isEmpty()){
                    phoneNumber.setError("Enter phone number");
                }
                else if (phoneNumber.getText().toString().isEmpty()){
                    phoneNumber.setError("Enter phone number");
                }
                else    {
                    Intent intent = new Intent(RegisterUser.this , register_OTP.class);
                    intent.putExtra("phoneNumber" ,phoneNumber_txt);
                    intent.putExtra("location", location_txt);
                    intent.putExtra("userName" , userName_txt);
                    startActivity(intent);
                }
            }
        });
    }
}