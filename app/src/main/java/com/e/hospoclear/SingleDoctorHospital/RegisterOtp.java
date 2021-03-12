package com.e.hospoclear.SingleDoctorHospital;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.e.hospoclear.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterOtp extends Fragment {

    String mPhoneNumber;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_otp, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        Bundle bundle = this.getArguments();
        if (bundle!=null){
            mPhoneNumber = bundle.getString("mPhoneNumber");
        }



        return view;

    }
}
