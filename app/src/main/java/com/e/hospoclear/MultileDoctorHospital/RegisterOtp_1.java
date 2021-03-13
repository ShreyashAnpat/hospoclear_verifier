package com.e.hospoclear.MultileDoctorHospital;

import android.app.ProgressDialog;
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

public class RegisterOtp_1 extends Fragment {

    String mPhoneNumber;
    Button mBtnVerifyOtp;
    SharedPreferences sharedPreferences;
    EditText mGetOtp;
    String OtpId;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog pd ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_otp_1, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        mBtnVerifyOtp = view.findViewById(R.id.verify);
        mGetOtp = view.findViewById(R.id.otp);

        sharedPreferences = getContext().getSharedPreferences("MultipleHospitalData",0);

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
                        }
                    }
                });
    }

    private void AddData() {
        String HospitalName = sharedPreferences.getString("HospitalName",null);
        String City = sharedPreferences.getString("City",null);
        String State = sharedPreferences.getString("State",null);
        String Number = sharedPreferences.getString("HospitalContactNumber",null);
        String Ambulance = sharedPreferences.getString("Ambulance",null);


        HashMap<String, Object> hospitalData = new HashMap<>();
        hospitalData.put("HospitalName",HospitalName);
        hospitalData.put("City",City);
        hospitalData.put("State",State);
        hospitalData.put("ContactNumber",Number);
        hospitalData.put("Ambulance",Ambulance);
        hospitalData.put("TimeStamp",System.currentTimeMillis());
        hospitalData.put("UserId",firebaseAuth.getCurrentUser().getUid());

        firebaseFirestore.collection("Hospitals").document(firebaseAuth.getCurrentUser().getUid())
                .set(hospitalData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getContext(), "Hospital Add successfully !!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        firebaseFirestore.collection("AppUsers").document(firebaseAuth.getCurrentUser().getUid()).set(hospitalData);

        HashMap<String,Object> location = new HashMap<>();
        location.put("City",City);
        Log.d(TAG, "AddData: " + City);
        firebaseFirestore.collection("Locations").document(City).set(location);


    }
}