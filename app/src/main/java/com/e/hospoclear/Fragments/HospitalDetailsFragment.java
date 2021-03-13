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

    TextView hospitalName ,hospitalContactNumber ,hospitalEmail ,hospitalAddress ;
    List<HospitalData> DataList ;
    String hospitalId ;
    int position ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hospital_details, container, false);
        hospitalAddress = view.findViewById(R.id.hospitalName);
        hospitalContactNumber = view.findViewById(R.id.hospitalContactNumber);
        hospitalEmail = view.findViewById(R.id.hospitalEmail);
        hospitalName = view.findViewById(R.id.hospitalName);
        Bundle bundle = getArguments();
        position = bundle.getInt("Position");






        String HospitalName = bundle.getString("HospitalName");
        String HospitalContactNumber = bundle.getString("PhoneNumber");

        String HospitalAddress = bundle.getString("Address");

        Log.d(TAG, "onCreateView: " + HospitalAddress);


        hospitalName.setText(HospitalName);
        hospitalContactNumber.setText(HospitalContactNumber);
        hospitalAddress.setText(HospitalName);

        return view;
    }
}