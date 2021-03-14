package com.e.hospoclear.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.e.hospoclear.R;
import com.e.hospoclear.model.HospitalData;

import java.util.List;

import static android.content.ContentValues.TAG;


public class HospitalDetailsFragment extends Fragment {

    TextView hospitalName ,hospitalContactNumber ,hospitalEmail ,hospitalAddress  , availableDoctors;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hospital_details, container, false);
        hospitalAddress = view.findViewById(R.id.hospitalAddress);
        hospitalContactNumber = view.findViewById(R.id.hospitalContactNumber);
        hospitalEmail = view.findViewById(R.id.hospitalEmail);
        hospitalName = view.findViewById(R.id.hospitalName);
        availableDoctors = view.findViewById(R.id.availableDr);

        Bundle bundle = getArguments();
        String HospitalId = bundle.getString("HospitalId");
        String HospitalName = bundle.getString("HospitalName");
        String HospitalContactNumber = bundle.getString("PhoneNumber");
        String HospitalAddress = bundle.getString("Address");


        availableDoctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment  = new AvailableDoctorsList();
                Bundle bundle1 = new Bundle();
                bundle1.putString("hospitalId" , HospitalId);
                fragment.setArguments(bundle1);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });



        hospitalName.setText(HospitalName);
        hospitalContactNumber.setText(HospitalContactNumber);
        hospitalAddress.setText(HospitalAddress);

        return view;
    }
}