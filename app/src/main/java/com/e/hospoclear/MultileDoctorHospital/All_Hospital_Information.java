package com.e.hospoclear.MultileDoctorHospital;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.e.hospoclear.R;
import com.e.hospoclear.SingleDoctorHospital.RegisterWithNoFragment;

import static android.content.ContentValues.TAG;


public class All_Hospital_Information extends Fragment {

    // TODO: Rename parameter arguments, choose names that match

    EditText mHospitalName, mCity, mState, mNumber, mAmbulance;
    Button mBtnSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all__hospital__information, container, false);

        mBtnSubmit = view.findViewById(R.id.btnSubmit);
        mHospitalName = view.findViewById(R.id.hospitalName);
        mCity = view.findViewById(R.id.cityName);
        mState = view.findViewById(R.id.state);
        mNumber = view.findViewById(R.id.hospitalContactNumber);

        mAmbulance = view.findViewById(R.id.ambulance);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MultipleHospitalData", 0);
        String HospitalName = sharedPreferences.getString("HospitalName", null);
        String City = sharedPreferences.getString("City", null);
        String State = sharedPreferences.getString("State", null);
        String Number = sharedPreferences.getString("HospitalContactNumber", null);
        String Ambulance = sharedPreferences.getString("Ambulance", null);
        mHospitalName.setText(HospitalName);
        mCity.setText(City);
        mState.setText(State);
        mNumber.setText(Number);
        mAmbulance.setText(Ambulance);

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String HospitalName = mHospitalName.getText().toString();
                String City = mCity.getText().toString();
                String State = mState.getText().toString();
                String Number = mNumber.getText().toString();
                String Ambulance = mAmbulance.getText().toString();

                if (TextUtils.isEmpty(HospitalName)) {
                    mHospitalName.setError("Empty");
                } else if (TextUtils.isEmpty(City)) {
                    mCity.setError("Empty");
                } else if (TextUtils.isEmpty(State)) {
                    mState.setError("Empty");
                } else if (TextUtils.isEmpty(Number)) {
                    mNumber.setError("Empty");
                } else if (TextUtils.isEmpty(Ambulance)) {
                    mAmbulance.setError("Empty");
                } else {
                    Editor editor = sharedPreferences.edit();
                    Log.d(TAG, "onClick: " + HospitalName + "\n" + City );
                    editor.putString("HospitalName", HospitalName);
                    editor.putString("City", City);
                    editor.putString("State", State);
                    editor.putString("HospitalContactNumber", Number);
                    editor.putString("Ambulance", Ambulance);
                    editor.commit();
                    Fragment fragment = new upload_hospital_image();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
                }
            }
        });

        return  view ;
    }
}