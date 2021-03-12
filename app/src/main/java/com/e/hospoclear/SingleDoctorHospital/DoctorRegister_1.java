package com.e.hospoclear.SingleDoctorHospital;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.e.hospoclear.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class DoctorRegister_1 extends Fragment {

    EditText mDoctorName,mQualification,mExperience;
    Button mBtnSubmit;
    String mSpecialty,UserId;

    String[] Speciality = {"Select your speciality","Anesthesia","Cardiology","E.N.T","Psychologist","Surgeon","Urologist","Gastroentero logist","Gynecologist","Neurologist","Orthopedics","Skin & Hair","Dermatologist","Child Specialist","General Physician","Dental Surgeon","Ear, Nose, Throat(ENT)",
            "Homoeopathy","Bone & Joints","Sex Specialist","Eye Specialist","Digestive Issues","Mental Wellness","Physiotherapy","Diabetes Management",
            "Brain & Nerves","Urinary Issues","Kidney Issues","Ayurveda","General Surgery","Lungs & Breathing","Heart Specialist","Cancer Specialist"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_register_1, container, false);

        mDoctorName = view.findViewById(R.id.doctorName);
        mQualification = view.findViewById(R.id.doctorQualification);
        mExperience = view.findViewById(R.id.doctorExperience);
        mBtnSubmit = view.findViewById(R.id.btnSubmit);

        Spinner speciality = view.findViewById(R.id.doctorSpeciality);

        speciality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSpecialty = Speciality[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter Special = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,Speciality);
        Special.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speciality.setAdapter(Special);

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String DoctorName = mDoctorName.getText().toString();
                String Qualification = mQualification.getText().toString();
                String Experience = mExperience.getText().toString();

                if (TextUtils.isEmpty(DoctorName)){
                    mDoctorName.setError("Doctor Name");
                }else if (TextUtils.isEmpty(Qualification)){
                    mQualification.setError("Qualification");
                }else if (TextUtils.isEmpty(Experience)){
                    mExperience.setError("Experience");
                }else if (mSpecialty.equals("Select your speciality")){
                    Toast.makeText(getContext(), "Select speciality", Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("HospitalData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("DoctorName",DoctorName);
                    editor.putString("Qualification",Qualification);
                    editor.putString("Experience",Experience);
                    editor.putString("Speciality",mSpecialty);
                    editor.commit();

                    Fragment fragment = new All_InformationFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack(null)
                            .commit();
                }
            }
        });

        return view;
    }
}