package com.e.hospoclear.ui.addNewHospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.e.hospoclear.R;
import com.hbb20.CountryCodePicker;

public class login_doctor extends AppCompatActivity {

    String hospitalName , city , State  ,phoneNumber_txt, doctorName_txt , qualification_txt ,Ambulance_txt, Experience_txt ,selected;
    EditText Phone_number ;
    Button Continue ;
    LinearLayout signUp ;
    CountryCodePicker ccp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_doctor);
        getSupportActionBar().hide();

        Phone_number = findViewById(R.id.Phone_number);
        Continue = findViewById(R.id.Continue);
        signUp = findViewById(R.id.signUp);
        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(Phone_number);

        hospitalName = getIntent().getStringExtra("hospitalName");
        city = getIntent().getStringExtra("city");
        State = getIntent().getStringExtra("State");
        doctorName_txt = getIntent().getStringExtra("doctorName_txt");
        qualification_txt = getIntent().getStringExtra("qualification_txt");
        Experience_txt  = getIntent().getStringExtra("Experience_txt");
        selected = getIntent().getStringExtra("selected");
        Ambulance_txt = getIntent().getStringExtra("Ambulance_txt");
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Phone_number.getText().toString().isEmpty()){
                    Phone_number.setError("Enter Phone Number ");
                }
                else if (Phone_number.getText().toString().length() < 11){
                    Phone_number.setError("Enter Valid Phone Number");
                }
                else {
                    phoneNumber_txt =  ccp.getFullNumberWithPlus().replace(" ","");

                    Intent intent = new Intent(login_doctor.this , register_OTP.class);
                    intent.putExtra("phoneNumber" ,phoneNumber_txt);
                    intent.putExtra("hospitalName" , hospitalName);
                    intent.putExtra("city" , city);
                    intent.putExtra("State" ,State);
                    intent.putExtra("selectedSpeciality" , selected);
                    intent.putExtra("doctorName_txt" , doctorName_txt);
                    intent.putExtra("qualification_txt" , qualification_txt);
                    intent.putExtra("Experience_txt", Experience_txt);
                    intent.putExtra("Ambulance_txt" , Ambulance_txt);
                    startActivity(intent);
                }
            }
        });

    }
}