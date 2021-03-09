package com.e.hospoclear.ui.addNewHospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class register_OTP extends AppCompatActivity {

    String phoneNumber , userName , location , otpID ;
    Button verify ;
    EditText otp;
    FirebaseAuth mAuth  ;
    String hospitalName , city , State  , doctorName_txt , qualification_txt , Experience_txt ,Ambulance_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__o_t_p);
        getSupportActionBar().hide();
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        verify = findViewById(R.id.verify);
        otp = findViewById(R.id.otp);
        mAuth = FirebaseAuth.getInstance();
        hospitalName = getIntent().getStringExtra("hospitalName");
        city = getIntent().getStringExtra("city");
        State = getIntent().getStringExtra("State");
        doctorName_txt = getIntent().getStringExtra("doctorName_txt");
        qualification_txt = getIntent().getStringExtra("qualification_txt");
        Experience_txt = getIntent().getStringExtra("Experience_txt");
        Ambulance_txt = getIntent().getStringExtra("Ambulance_txt");

        InitiateOtp(phoneNumber);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp_txt = otp.getText().toString();
                if (otp_txt.isEmpty() || otp_txt.length()<6){
                    otp.setError("Enter valid OTP");
                }
                else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpID , otp_txt);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });
    }

    private void InitiateOtp(String phoneNumber) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        otpID = s;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                        Toast.makeText(register_OTP.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });        // OnVerificationStateChangedCallbacks

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String timeStamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
                    FirebaseAuth  auth = FirebaseAuth.getInstance();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    Map<String, Object> register = new HashMap<>();
                    register.put("phoneNumber" , phoneNumber);
                    register.put("City" , city);
                    register.put("ContactNumber" , phoneNumber);
                    register.put("HospitalName" , hospitalName);
                    register.put("Ambulance" , Ambulance_txt);
                    register.put("State" ,State) ;
                    register.put("UserId" ,auth.getCurrentUser().getUid());
                    register.put("isUser" , "1");
                    register.put("TimeStamp" , timeStamp);
                    Toast.makeText(register_OTP.this, auth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
                    db.collection("Hospitals").document(auth.getCurrentUser().getUid()).set(register);
                    Intent intent = new Intent(register_OTP.this, MainActivity.class);
                    auth.signOut();
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(register_OTP.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}