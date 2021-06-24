package com.skyget.counsellor.views.activities;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.skyget.counsellor.R;
import com.skyget.counsellor.model.request.SubjectwiselistRequest;
import com.skyget.counsellor.model.response.Chapterwise_list_Response;
import com.skyget.counsellor.presenters.implementations.IntractiveDataPresenterImpl;
import com.skyget.counsellor.presenters.interfaces.IIntractivePracticeDataPresenter;
import com.skyget.counsellor.presenters.interfaces.I_IntractivePracticemainview;
import com.skyget.counsellor.views.adapters.DynamicTabsAdapterInteractive;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class IntractivePracticeAi extends AppCompatActivity implements
        I_IntractivePracticemainview {
    private ViewPager viewPager;
    private TabLayout mTabLayout;
    private IIntractivePracticeDataPresenter iIntractivePracticeDataPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intractive_practice_ai);
        initViews();
    }

    private void initViews() {

        // initialise the layout
        viewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tabs);

        // setOffscreenPageLimit means number
        // of tabs to be shown in one page
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // setCurrentItem as the tab position
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        iIntractivePracticeDataPresenter = new IntractiveDataPresenterImpl(this,
                this);
        iIntractivePracticeDataPresenter.IntractivePracticeTab();
        ;

    }

    // show all the tab using DynamicFragmnetAdapter


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void IntractivePracticeTab(List<SubjectwiselistRequest> chapterwise_list_responses) {
        for (int i = 0; i < chapterwise_list_responses.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(chapterwise_list_responses.get(i).subjectName));

        }
        DynamicTabsAdapterInteractive mDynamicFragmentAdapter = new DynamicTabsAdapterInteractive(getSupportFragmentManager(), mTabLayout.getTabCount());

        // set the adapter
        viewPager.setAdapter(mDynamicFragmentAdapter);


        // set the current item as 0 (when app opens for first time)
        viewPager.setCurrentItem(0);

    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void getChaptersResponse(List<Chapterwise_list_Response> chapterwise_list_response) {

    }


}
