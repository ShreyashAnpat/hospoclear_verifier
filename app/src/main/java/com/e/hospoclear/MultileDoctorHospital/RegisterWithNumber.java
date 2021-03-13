package com.e.hospoclear.MultileDoctorHospital;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.e.hospoclear.R;
import com.e.hospoclear.SingleDoctorHospital.RegisterOtp;
import com.hbb20.CountryCodePicker;

public class RegisterWithNumber extends Fragment {

    CountryCodePicker mCpp;
    EditText mMobileNumber;
    Button mBtnContinue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register_with_number, container, false);
        mCpp = view.findViewById(R.id.ccp);
        mMobileNumber = view.findViewById(R.id.phoneNumber);
        mBtnContinue = view.findViewById(R.id.btnContinue);

        mCpp.registerCarrierNumberEditText(mMobileNumber);

        mBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Fragment fragment = new RegisterOtp_1();
                bundle.putString("mPhoneNumber",mCpp.getFullNumberWithPlus().replace(" ",""));
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack(null).commit();

            }
        });

        return view;

    }
}