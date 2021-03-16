package com.e.hospoclear.MultileDoctorHospital;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

public class registerOtp_dr extends Fragment {

    String mPhoneNumber;
    Button mBtnVerifyOtp;
    SharedPreferences sharedPreferences;
    EditText mGetOtp;
    ProgressBar progressBar;
    String OtpId;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_otp_dr, container, false);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        mBtnVerifyOtp = view.findViewById(R.id.verify);
        mGetOtp = view.findViewById(R.id.otp);
        progressBar = view.findViewById(R.id.loader);

        sharedPreferences = getContext().getSharedPreferences("DoctorsData",0);

        Bundle bundle = this.getArguments();
        if (bundle!=null){
            mPhoneNumber = bundle.getString("mPhoneNumber");
        }


        InitiateOtp();

        mBtnVerifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGetOtp.getText().toString().isEmpty()){
                    mGetOtp.setError("Enter OTP");
                }else if (mGetOtp.getText().toString().length()!=6){
                    mGetOtp.setError("OTP will be 6 digit");
                }else {
                    mBtnVerifyOtp.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(OtpId,mGetOtp.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

        return view;

    }

    private void InitiateOtp() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mPhoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                getActivity(),               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        OtpId = s;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });        // OnVerificationStateChangedCallbacks

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            AddData();
                        } else {
                            mGetOtp.setError("Re-Enter OTP");
                            mBtnVerifyOtp.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void AddData() {

        String DoctorName = sharedPreferences.getString("DoctorName",null);
        String Qualification = sharedPreferences.getString("Qualification",null);
        String Experience = sharedPreferences.getString("Experience",null);
        String Speciality = sharedPreferences.getString("Speciality",null);
        String HospitalID = sharedPreferences.getString("HospitalID" , null);
        HashMap<String, Object> doctorData = new HashMap<>();
        doctorData.put("DoctorName",DoctorName);
        doctorData.put("Qualification",Qualification);
        doctorData.put("Experience",Experience);
        doctorData.put("Speciality",Speciality);
        doctorData.put("TimeStamp",System.currentTimeMillis());
        doctorData.put("HospitalId",HospitalID);
        doctorData.put("isUser" ,"3");

        firebaseFirestore.collection("Doctors").document(firebaseAuth.getCurrentUser().getUid())
                .set(doctorData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getContext(), "Doctor add successfully !!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        signOut();

    }

    private void signOut() {
        firebaseAuth.signOut();
    }
}