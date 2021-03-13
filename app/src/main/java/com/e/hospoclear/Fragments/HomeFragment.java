package com.e.hospoclear.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
<<<<<<< HEAD
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
=======
>>>>>>> a2aad467ee4157921acc1ae2a25cf8d367c622cc
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.hospoclear.Adapters.TabAdapter;
import com.e.hospoclear.R;
import com.google.android.material.tabs.TabLayout;
<<<<<<< HEAD

=======
>>>>>>> a2aad467ee4157921acc1ae2a25cf8d367c622cc

public class HomeFragment extends Fragment {

<<<<<<< HEAD
    TabLayout tabLayout ;
    ViewPager viewPager ;

=======
    TabLayout tabLayout;
    ViewPager viewPager;
>>>>>>> a2aad467ee4157921acc1ae2a25cf8d367c622cc

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
<<<<<<< HEAD
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Single Doctor"));
        tabLayout.addTab(tabLayout.newTab().setText("Multiple Doctor"));

        TabAdapter adapter = new TabAdapter(getChildFragmentManager() , FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.removeOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
            @Override
            public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

=======
        View view = inflater.inflate(R.layout.fragment_home, container, false);

>>>>>>> a2aad467ee4157921acc1ae2a25cf8d367c622cc
        return view;
    }
}