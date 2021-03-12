package com.e.hospoclear.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.e.hospoclear.R;
import com.e.hospoclear.SingleDoctorHospital.RegisterHospital_1;

public class AddHospitalFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_hospital, container, false);


        CardView mBtnSingleDoctor = view.findViewById(R.id.singleDoctor);
        mBtnSingleDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new RegisterHospital_1();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
