package com.e.hospoclear.SingleDoctorHospital;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.e.hospoclear.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Register_hospital_2 extends Fragment {

    EditText mHospitalContactNumber;
    CheckBox mCheckBox;
    Button mBtnNext;
    String UserId,checkBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_hospital_2, container, false);

        mHospitalContactNumber = view.findViewById(R.id.hospitalContactNumber);
        mCheckBox = view.findViewById(R.id.checkbox);
        mBtnNext = view.findViewById(R.id.btnNext);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    checkBox = "Yes";
                }else {
                    checkBox = "No";
                }
            }
        });

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contactNumber = mHospitalContactNumber.getText().toString();
                if (TextUtils.isEmpty(contactNumber)){
                    mHospitalContactNumber.setError("Contact Number");
                }else {
                   SharedPreferences sharedPreferences = getContext().getSharedPreferences("HospitalData", Context.MODE_PRIVATE);
                   Editor editor = sharedPreferences.edit();
                   editor.putString("HospitalContactNumber",contactNumber);
                   editor.putString("Ambulance",checkBox);
                   editor.commit();

                   Fragment fragment = new DoctorRegister_1();
                   getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment)
                           .addToBackStack(null)
                           .commit();

                }
            }
        });

        return view;
    }
}