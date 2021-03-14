package com.e.hospoclear.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.hospoclear.R;
import com.e.hospoclear.model.DoctorData;
import com.e.hospoclear.model.HospitalData;

import java.util.List;

import static android.content.ContentValues.TAG;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.ViewHolder> {

    List<DoctorData>  doctorDataList ;
    public DoctorListAdapter(List<DoctorData> doctorDataList) {
        this.doctorDataList = doctorDataList ;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_item,parent, false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String DoctorName = doctorDataList.get(position).getDoctorName();
        String DoctorExperience = doctorDataList.get(position).getExperience();
        String DoctorSpeciality = doctorDataList.get(position).getSpeciality();
        String DoctorQualification = doctorDataList.get(position).getQualification();

        Log.d(TAG, "onBindViewHolder: " + DoctorName);

        holder.doctorName.setText(DoctorName);
        holder.doctorExperience.setText(DoctorExperience);
        holder.doctorQualification.setText(DoctorQualification);
        holder.doctorSpeciality.setText(DoctorSpeciality);
    }

    @Override
    public int getItemCount() {
        return doctorDataList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView doctorName , doctorSpeciality,doctorExperience,doctorQualification ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.doctorName);
            doctorExperience= itemView.findViewById(R.id.doctorExperience);
            doctorSpeciality = itemView.findViewById(R.id.doctorSpeciality);
            doctorQualification = itemView.findViewById(R.id.doctorQualification);
        }
    }
}
