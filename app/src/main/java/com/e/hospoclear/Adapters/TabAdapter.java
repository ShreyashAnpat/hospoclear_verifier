package com.e.hospoclear.Adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.e.hospoclear.Tabs.MultipleDoctorHospitals;
import com.e.hospoclear.Tabs.SingleDoctorHospitals;

public class TabAdapter extends FragmentPagerAdapter {

    int totalTabs;

    public TabAdapter(@NonNull FragmentManager fm,int behavior, int tabCount) {
        super(fm, behavior);
        this.totalTabs = tabCount;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new SingleDoctorHospitals();
            case 1 :
                return new MultipleDoctorHospitals();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
