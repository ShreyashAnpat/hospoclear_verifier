package com.e.hospoclear.ui.addNewHospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.e.hospoclear.R;

public class personInformation extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    EditText doctorName , qualification , Experience ;
    String hospitalName , city , State  , doctorName_txt , qualification_txt , Experience_txt ,Ambulance_txt;
    Spinner speciality ;
    String selected ;
    CheckBox Ambulance ;
    String[] speciality_Array = {"Select your Speciality" , "Women's Health" , "Skin & Hair" , "Child Specialist" };
    Button next ;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_information);
        getSupportActionBar().hide();
        doctorName = findViewById(R.id.DoctorName);
        qualification = findViewById(R.id.Qualification);
        Experience = findViewById(R.id.Experience);
        speciality = findViewById(R.id.speciality);
        Ambulance = findViewById(R.id.Ambulance);
        next = findViewById(R.id.next);
        speciality.setOnItemSelectedListener(this);
        hospitalName = getIntent().getStringExtra("hospitalName" );
        city = getIntent().getStringExtra("city");
        State = getIntent().getStringExtra("state");

        ArrayAdapter category_adapter1 = new ArrayAdapter(personInformation.this, android.R.layout.simple_spinner_item,speciality_Array);
        category_adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speciality.setAdapter(category_adapter1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorName_txt =doctorName.getText().toString();
                qualification_txt = qualification.getText().toString() ;
                Experience_txt = Experience.getText().toString();
                if (doctorName_txt.isEmpty()){
                    doctorName.setError("Enter doctor name");
                }
                else if (qualification_txt.isEmpty()){
                    qualification.setError("Qualification");
                }
                else if (Experience_txt.isEmpty()){
                    Experience.setError("Experience");
                }
                else if (selected.equals("Select your Speciality")){
                    Toast.makeText(personInformation.this, "Select your Speciality", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (Ambulance.isChecked()){
                     Ambulance_txt = "Yes";
                    }
                    Intent intent = new Intent(personInformation.this,login_doctor.class );
                    intent.putExtra("hospitalName" , hospitalName);
                    intent.putExtra("city" , city);
                    intent.putExtra("State" ,State);
                    intent.putExtra("selectedSpeciality" , selected);
                    intent.putExtra("doctorName_txt" , doctorName_txt);
                    intent.putExtra("qualification_txt" , qualification_txt);
                    intent.putExtra("Experience_txt", Experience_txt);
                    intent.putExtra("Ambulance_txt" ,Ambulance_txt);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected = speciality_Array[position] ;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}