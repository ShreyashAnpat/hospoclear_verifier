package com.e.hospoclear.SingleDoctorHospital;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

public class All_InformationFragment extends Fragment {

    EditText mHospitalName,mCity,mState,mNumber,mDoctorName,mQualification,mExperience,mSpeciality,mAmbulance;
    Button mBtnSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all__information, container, false);



        mBtnSubmit = view.findViewById(R.id.btnSubmit);
        mHospitalName = view.findViewById(R.id.hospitalName);
        mCity = view.findViewById(R.id.cityName);
        mState = view.findViewById(R.id.state);
        mNumber = view.findViewById(R.id.hospitalContactNumber);
        mDoctorName = view.findViewById(R.id.doctorName);
        mQualification = view.findViewById(R.id.doctorQualification);
        mExperience = view.findViewById(R.id.doctorExperience);
        mSpeciality = view.findViewById(R.id.doctorSpeciality);
        mAmbulance = view.findViewById(R.id.ambulance);


        SharedPreferences sharedPreferences = getContext().getSharedPreferences("HospitalData",0);
        String HospitalName = sharedPreferences.getString("HospitalName",null);
        String City = sharedPreferences.getString("City",null);
        String State = sharedPreferences.getString("State",null);
        String Number = sharedPreferences.getString("HospitalContactNumber",null);
        String Ambulance = sharedPreferences.getString("Ambulance",null);
        String DoctorName = sharedPreferences.getString("DoctorName",null);
        String Qualification = sharedPreferences.getString("Qualification",null);
        String Experience = sharedPreferences.getString("Experience",null);
        String Speciality = sharedPreferences.getString("Speciality",null);

        mHospitalName.setText(HospitalName);
        mCity.setText(City);
        mState.setText(State);
        mNumber.setText(Number);
        mAmbulance.setText(Ambulance);
        mDoctorName.setText(DoctorName);
        mQualification.setText(Qualification);
        mExperience.setText(Experience);
        mSpeciality.setText(Speciality);

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String HospitalName = mHospitalName.getText().toString();
                String City = mCity.getText().toString();
                String State = mState.getText().toString();
                String Number = mNumber.getText().toString();
                String Ambulance = mAmbulance.getText().toString();
                String DoctorName = mDoctorName.getText().toString();
                String Qualification = mQualification.getText().toString();
                String Experience = mExperience.getText().toString();
                String Speciality = mSpeciality.getText().toString();

                if (TextUtils.isEmpty(HospitalName)){
                    mHospitalName.setError("Empty");
                }else if (TextUtils.isEmpty(City)){
                    mCity.setError("Empty");
                }else if (TextUtils.isEmpty(State)){
                    mState.setError("Empty");
                }else if (TextUtils.isEmpty(Number)){
                    mNumber.setError("Empty");
                }else if (TextUtils.isEmpty(Ambulance)){
                    mAmbulance.setError("Empty");
                }else if (TextUtils.isEmpty(DoctorName)){
                    mDoctorName.setError("Empty");
                }else if (TextUtils.isEmpty(Qualification)){
                    mQualification.setError("Empty");
                }else if (TextUtils.isEmpty(Experience)){
                    mExperience.setError("Empty");
                }else if (TextUtils.isEmpty(Speciality)){
                    mSpeciality.setError("Empty");
                }else {
                    Editor editor = sharedPreferences.edit();
                    editor.putString("HospitalName",HospitalName);
                    editor.putString("City",City);
                    editor.putString("State",State);
                    editor.putString("HospitalContactNumber",Number);
                    editor.putString("Ambulance",Ambulance);
                    editor.putString("DoctorName",DoctorName);
                    editor.putString("Qualification",Qualification);
                    editor.putString("Experience",Experience);
                    editor.putString("Speciality",Speciality);
                    editor.commit();

                    Fragment fragment = new RegisterWithNoFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack(null).commit();
                }
            }
        });


        return view;
    }
}