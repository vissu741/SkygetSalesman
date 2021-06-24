package com.skyget.counsellor.views.adapters;

import com.google.android.material.tabs.TabLayout;
import com.skyget.counsellor.model.response.PhysicsChapterlistResponce;
import com.skyget.counsellor.model.response.TakeStudentResponse;
import com.skyget.counsellor.views.activities.MainActivityTabDesgin;
import com.skyget.counsellor.views.fragments.FragmentChemistry;
import com.skyget.counsellor.views.fragments.FragmentMaths;
import com.skyget.counsellor.views.fragments.FragmentPhysics;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabsAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
  TakeStudentResponse takeStudentResponse ;


    public TabsAdapter(FragmentManager fm, int NoofTabs) {
        super(fm);
        this.mNumOfTabs = NoofTabs;

    }




    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragmentPhysics physics = new FragmentPhysics();
                return physics;
            case 1:
                FragmentChemistry chemisty = new FragmentChemistry();
                return chemisty;
            case 2:
                FragmentMaths maths = new FragmentMaths();
                return maths;


        }

        return null;
    }
}