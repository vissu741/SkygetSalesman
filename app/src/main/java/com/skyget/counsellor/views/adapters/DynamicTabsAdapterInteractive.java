package com.skyget.counsellor.views.adapters;

import android.os.Bundle;

import com.skyget.counsellor.views.fragments.DynamicFragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class DynamicTabsAdapterInteractive extends FragmentStatePagerAdapter   {
    private int mNumOfTabs;
    public DynamicTabsAdapterInteractive(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    public Fragment getItem(int position) {
        Bundle b = new Bundle();
        b.putInt("position", position);
        Fragment frag = new DynamicFragment();
        frag.setArguments(b);
        return frag;
    }

    // get total number of tabs
    @Override
    public int getCount() {
        return mNumOfTabs;    }
}
