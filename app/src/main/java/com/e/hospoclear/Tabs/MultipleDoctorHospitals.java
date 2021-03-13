package com.e.hospoclear.Tabs;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.e.hospoclear.Adapters.Hospital_List_Adapter;
import com.e.hospoclear.R;
import com.e.hospoclear.model.HospitalData;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MultipleDoctorHospitals extends Fragment {

    Hospital_List_Adapter adapter;
    RecyclerView multipleDoctorHospital ;
    List<HospitalData> hospitalDataList ;
    FirebaseFirestore firebaseFirestore ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_multiple_doctor_hospitals, container, false);

        multipleDoctorHospital = view.findViewById(R.id.multipleDoctorHospital);

        firebaseFirestore = FirebaseFirestore.getInstance() ;
        hospitalDataList = new ArrayList<>();

        multipleDoctorHospital.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Hospital_List_Adapter(hospitalDataList);
        multipleDoctorHospital.setAdapter(adapter);

        firebaseFirestore.collection("Hospitals").whereEqualTo("Status" ,"Multiple")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (!value.isEmpty()){
                            for (DocumentSnapshot doc : value.getDocuments()){
                                HospitalData mHospitalData = doc.toObject(HospitalData.class) ;
                                hospitalDataList.add(mHospitalData);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
        return  view ;
    }
}