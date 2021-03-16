package com.e.hospoclear.MultileDoctorHospital;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.e.hospoclear.R;
import com.e.hospoclear.SingleDoctorHospital.RegisterWithNoFragment;


public class All_Doctor_Info extends Fragment {
    EditText mDoctorName,mQualification,mExperience,mSpeciality,mAmbulance;
    Button mBtnSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all__doctor__info, container, false);

        mBtnSubmit = view.findViewById(R.id.btnSubmit);
        mDoctorName = view.findViewById(R.id.doctorName);
        mQualification = view.findViewById(R.id.doctorQualification);
        mExperience = view.findViewById(R.id.doctorExperience);
        mSpeciality = view.findViewById(R.id.doctorSpeciality);



        SharedPreferences sharedPreferences = getContext().getSharedPreferences("DoctorsData",0);

        String DoctorName = sharedPreferences.getString("DoctorName",null);
        String Qualification = sharedPreferences.getString("Qualification",null);
        String Experience = sharedPreferences.getString("Experience",null);
        String Speciality = sharedPreferences.getString("Speciality",null);


        mDoctorName.setText(DoctorName);
        mQualification.setText(Qualification);
        mExperience.setText(Experience);
        mSpeciality.setText(Speciality);

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String DoctorName = mDoctorName.getText().toString();
                String Qualification = mQualification.getText().toString();
                String Experience = mExperience.getText().toString();
                String Speciality = mSpeciality.getText().toString();

                if (TextUtils.isEmpty(DoctorName)){
                    mDoctorName.setError("Empty");
                }else if (TextUtils.isEmpty(Qualification)){
                    mQualification.setError("Empty");
                }else if (TextUtils.isEmpty(Experience)){
                    mExperience.setError("Empty");
                }else if (TextUtils.isEmpty(Speciality)){
                    mSpeciality.setError("Empty");
                }else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("DoctorName",DoctorName);
                    editor.putString("Qualification",Qualification);
                    editor.putString("Experience",Experience);
                    editor.putString("Speciality",Speciality);
                    editor.commit();

                    Fragment fragment = new Register_Dr_PhoneNumebr();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack(null).commit();
                }
            }
        });


        return  view ;
    }
}