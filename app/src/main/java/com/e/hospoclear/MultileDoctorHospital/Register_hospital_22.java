package com.e.hospoclear.MultileDoctorHospital;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.e.hospoclear.R;
import com.e.hospoclear.SingleDoctorHospital.DoctorRegister_1;

public class Register_hospital_22 extends Fragment {

    EditText mHospitalContactNumber;
    CheckBox mCheckBox;
    Button mBtnNext;
    String UserId,checkBox ="No";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_hospital_22, container, false);

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
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("MultipleHospitalData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("HospitalContactNumber",contactNumber);
                    editor.putString("Ambulance",checkBox);
                    editor.commit();

                    Fragment fragment = new All_Hospital_Information();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment)
                            .addToBackStack(null)
                            .commit();

                }
            }
        });

        return view;
    }
}