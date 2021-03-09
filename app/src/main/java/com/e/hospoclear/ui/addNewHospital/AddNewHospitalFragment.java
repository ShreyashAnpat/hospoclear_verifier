package com.e.hospoclear.ui.addNewHospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.e.hospoclear.R;

import java.lang.reflect.Array;
import java.util.List;

public class AddNewHospitalFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    Spinner state ;
    String selectedState ;
    Button next ;
    EditText hospitalName , cityName ;
    String[] stateArray = { "Select State", "Andhra Pradesh" ,"Assam" , "Bihar" , "Chhattisgarh" , "Goa" , "Gujarat" , "Haryana" , "Himachal Pradesh"
                            , "Jharkhand" , "Karnataka" , "Kerala" ,"Madhya Pradesh" , "Maharashtra" , "Manipur" ,"Meghalaya" ,"Mizoram"
                            , "Nagaland" , "Odisha" ,"Punjab","Rajasthan" ,"Sikkim" ,"Tamil Nadu" ,"Telangana" ,"Tripura" ,"Uttar Pradesh"
                            , "Uttarakhand" , "West Bengal"} ;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        state = root.findViewById(R.id.state);
        next  = root.findViewById(R.id.next);
        hospitalName = root.findViewById(R.id.hospitalName);
        cityName = root.findViewById(R.id.cityName);
        state.setOnItemSelectedListener(this);


        ArrayAdapter category_adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,stateArray);
        category_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(category_adapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hospitalName.getText().toString().isEmpty()){
                    hospitalName.setError("Enter hospital name");
                }
                else if (cityName.getText().toString().isEmpty()){
                    cityName.setError("Enter city name");
                }
                else if (selectedState.equals("Select State")){
                    Toast.makeText(root.getContext(), "Select State", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(root.getContext() , personInformation.class);
                    intent.putExtra("state", selectedState);
                    intent.putExtra("hospitalName" , hospitalName.getText().toString());
                    intent.putExtra("city" , cityName.getText().toString());
                    startActivity(intent);
                }
            }
        });

        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedState = stateArray[position] ;
        if (selectedState.equals("Select State")){
            Toast.makeText(getContext(), "Select State", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}