package com.e.hospoclear.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.e.hospoclear.Adapters.hospital_list_Adapter;
import com.e.hospoclear.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView HospitalList ;
    List<String >  state , city , hospitalName , phoneNumber ,profile;
    FirebaseFirestore db ;
    hospital_list_Adapter adapter ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        HospitalList = root.findViewById(R.id.HospitalList);

        db = FirebaseFirestore.getInstance() ;
        ProgressDialog pd = new ProgressDialog(root.getContext());
        pd.setCanceledOnTouchOutside(false);
        pd.setMessage("Loading");
        pd.show();
        state = new ArrayList<>();
        city = new ArrayList<>();
        hospitalName = new ArrayList<>();
        phoneNumber = new ArrayList<>();
        profile = new ArrayList<>();
        db.collection("Hospitals").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    for (DocumentSnapshot doc : value.getDocuments()){
                        state.add(doc.get("State").toString());
                        city.add(doc.get("City").toString());
                        hospitalName.add(doc.get("HospitalName").toString());
                        phoneNumber.add(doc.get("ContactNumber").toString());
//                        profile.add(doc.getString("ProfileImgUrl"));
                    }
                    HospitalList.setLayoutManager(new LinearLayoutManager(root.getContext()));
                    adapter = new hospital_list_Adapter(root.getContext() , state,city , hospitalName,phoneNumber ,profile);
                    HospitalList.setAdapter(adapter);
            }
        });

        pd.cancel();

        return root;
    }
}