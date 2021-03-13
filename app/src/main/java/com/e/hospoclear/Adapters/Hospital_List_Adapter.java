package com.e.hospoclear.Adapters;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.e.hospoclear.Fragments.HospitalDetailsFragment;
import com.e.hospoclear.R;
import com.e.hospoclear.model.HospitalData;

import java.util.List;

public class Hospital_List_Adapter extends RecyclerView.Adapter<Hospital_List_Adapter.ViewHolder> {

    Context context ;
    List<HospitalData> dataList ;

    public Hospital_List_Adapter(List<HospitalData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospitals_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String HospitalName = dataList.get(position).getHospitalName();
        String PhoneNumber = dataList.get(position).getContactNumber();
        String Address = dataList.get(position).getCity() + " "+dataList.get(position).getState() ;
        String HospitalId = dataList.get(position).getHospitalId();

        holder.hospitalName.setText(HospitalName);
        holder.hospitalAddress.setText(Address);
        holder.phoneNo.setText(PhoneNumber);
        holder.hospitalDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment fragment = new HospitalDetailsFragment() ;
                Bundle bundle = new Bundle();
                bundle.putInt("Position" , position);
                bundle.putString("HospitalId" , HospitalId);
                bundle.putString("HospitalName" , HospitalName);
                bundle.putString("PhoneNumber" , PhoneNumber);
                bundle.putString("Address" , Address);
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit() ;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hospitalName , hospitalAddress , state , phoneNo ;
        RelativeLayout hospitalDetail ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalName = itemView.findViewById(R.id.hospitalName);
            hospitalAddress = itemView.findViewById(R.id.hospitalAddress);
            phoneNo = itemView.findViewById(R.id.hospitalContactNumber);
            hospitalDetail = itemView.findViewById(R.id.HospitalDetail);
        }
    }
}
