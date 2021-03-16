package com.e.hospoclear.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.e.hospoclear.Adapters.DoctorListAdapter;
import com.e.hospoclear.R;
import com.e.hospoclear.model.DoctorData;
import com.e.hospoclear.model.HospitalData;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class AvailableDoctorsList extends Fragment {
    RecyclerView DoctorList ;
    DoctorListAdapter doctorListAdapter ;
    List<DoctorData> doctorDataList;
    FirebaseFirestore firebaseFirestore ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_available_doctors_list, container, false);
        firebaseFirestore = FirebaseFirestore.getInstance();
        DoctorList = view.findViewById(R.id.DoctorList);
        doctorDataList = new ArrayList<>();
        Bundle bundle = getArguments();
        String HospitalId = bundle.getString("hospitalId");

        firebaseFirestore.collection("Doctors").whereEqualTo("HospitalId" , HospitalId).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (!value.isEmpty()){
                    for (DocumentSnapshot doc : value.getDocuments()){
                        DoctorData mDoctorData = doc.toObject(DoctorData.class);
                        doctorDataList.add(mDoctorData);
                        doctorListAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


        DoctorList.setLayoutManager(new LinearLayoutManager(getContext()));
        doctorListAdapter = new DoctorListAdapter(doctorDataList);
        DoctorList.setAdapter(doctorListAdapter);

        return  view ;
    }
}