package com.e.hospoclear.MultileDoctorHospital;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
import com.e.hospoclear.SingleDoctorHospital.Register_hospital_2;

public class RegisterHospital_11  extends Fragment implements AdapterView.OnItemSelectedListener {
    Spinner state ;
    String selectedState ;
    Button next ;
    EditText hospitalName , cityName ;
    String[] stateArray = { "Select State", "Andhra Pradesh" ,"Assam" , "Bihar" , "Chhattisgarh" , "Goa" , "Gujarat" , "Haryana" , "Himachal Pradesh"
            , "Jharkhand" , "Karnataka" , "Kerala" ,"Madhya Pradesh" , "Maharashtra" , "Manipur" ,"Meghalaya" ,"Mizoram"
            , "Nagaland" , "Odisha" ,"Punjab","Rajasthan" ,"Sikkim" ,"Tamil Nadu" ,"Telangana" ,"Tripura" ,"Uttar Pradesh"
            , "Uttarakhand" , "West Bengal"} ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register_hospital_11, container, false);
        state = view.findViewById(R.id.state);
        next  = view.findViewById(R.id.next);
        hospitalName = view.findViewById(R.id.hospitalName);
        cityName = view.findViewById(R.id.cityName);
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
                    Toast.makeText(view.getContext(), "Select State", Toast.LENGTH_SHORT).show();
                }else {
                    String HospitalName = hospitalName.getText().toString();
                    String City = cityName.getText().toString();
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("MultipleHospitalData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("HospitalName",HospitalName);
                    editor.putString("City",City);
                    editor.putString("State",selectedState);
                    editor.commit();

                    Fragment fragment = new Register_hospital_22();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack(null)
                            .commit();
                }
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedState = stateArray[position] ;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}