package com.e.hospoclear.LoginUser;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class login_otp extends AppCompatActivity {


    String phoneNumber , otpID ;
    EditText otp ;
    Button verify ;
    LinearLayout changePhoneNumber ;
    FirebaseAuth mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otp);
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        otp  = findViewById(R.id.otp);
        verify = findViewById(R.id.verify);
        changePhoneNumber = findViewById(R.id.changePhoneNumber);
        mAuth = FirebaseAuth.getInstance() ;
        getSupportActionBar().hide();

//        InitiateOtp(phoneNumber);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp_txt = otp.getText().toString();
                if (otp_txt.isEmpty() || otp_txt.length()<6){
                    otp.setError("Enter valid OTP");
                }
                else if (otp_txt.equals("000000")){
                    Intent intent = new Intent(login_otp.this, MainActivity.class);
                    startActivity(intent);
//                    finish();
//                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpID , otp_txt);
//                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

    }

//    private void InitiateOtp(String phoneNumber) {
//
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                phoneNumber,        // Phone number to verify
//                60,                 // Timeout duration
//                TimeUnit.SECONDS,   // Unit of timeout
//                this,               // Activity (for callback binding)
//                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                    @Override
//                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                        otpID = s;
//                    }
//
//                    @Override
//                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                        signInWithPhoneAuthCredential(phoneAuthCredential);
//                    }
//
//                    @Override
//                    public void onVerificationFailed(@NonNull FirebaseException e) {
//
//                        Toast.makeText(login_otp.this, e.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });        // OnVerificationStateChangedCallbacks
//
//    }
//
//    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
//        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    Intent intent = new Intent(login_otp.this, MainActivity.class);
//
//                    startActivity(intent);
//                    finish();
//                } else {
//                    Toast.makeText(login_otp.this, "error", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
}