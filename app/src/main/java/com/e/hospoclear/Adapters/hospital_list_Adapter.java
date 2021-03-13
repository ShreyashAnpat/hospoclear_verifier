package com.e.hospoclear.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.hospoclear.R;

import java.util.List;

public class hospital_list_Adapter extends RecyclerView.Adapter<hospital_list_Adapter.ViewHolder> {

    Context context ;
    LayoutInflater inflater ;
    List<String>  state , city , profile , hospitalName , phoneNumber ;

    public hospital_list_Adapter(Context context, List<String> state, List<String> city, List<String> hospitalName, List<String> phoneNumber, List<String> profile) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.state = state ;
        this.city = city ;
        this.profile = profile ;
        this.hospitalName = hospitalName ;
        this.phoneNumber = phoneNumber ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.hospitals_list_item,parent,false);
        return new hospital_list_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.hospitalName.setText(hospitalName.get(position));
        holder.city.setText(city.get(position) + ", ");
        holder.state.setText(state.get(position));
        holder.phoneNo.setText(phoneNumber.get(position));
    }

    @Override
    public int getItemCount() {
        return phoneNumber.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hospitalName , city , state , phoneNo ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
