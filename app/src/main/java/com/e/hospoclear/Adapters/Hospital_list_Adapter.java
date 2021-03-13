package com.e.hospoclear.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.hospoclear.R;
import com.e.hospoclear.model.hospitalData;

import java.util.List;

public class Hospital_list_Adapter extends RecyclerView.Adapter<Hospital_list_Adapter.ViewHolder> {

    Context context ;
    List<hospitalData> dataList ;

    public Hospital_list_Adapter(List<hospitalData> dataList) {
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

        holder.hospitalName.setText(HospitalName);
        holder.hospitalAddress.setText(Address);
        holder.phoneNo.setText(PhoneNumber);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hospitalName , hospitalAddress , state , phoneNo ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalName = itemView.findViewById(R.id.hospitalName);
            hospitalAddress = itemView.findViewById(R.id.hospitalAddress);
            phoneNo = itemView.findViewById(R.id.hospitalContactNumber);
        }
    }
}
