package com.skyget.counsellor.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.skyget.counsellor.R;
import com.skyget.counsellor.model.response.TakeStudentResponse;
import com.skyget.counsellor.presenters.implementations.VideosTabDataPresenterImpl;
import com.skyget.counsellor.presenters.interfaces.IPhysicsTabMainView;
import com.skyget.counsellor.presenters.interfaces.IVideosDataPresenter;
import com.skyget.counsellor.views.adapters.TabsAdapter;

import java.util.List;


public class MainActivityTabDesgin extends AppCompatActivity implements IVideosDataPresenter {

    TabLayout tablayout;
    ViewPager viewPager;
    TabsAdapter tabsAdapter;
    VideosTabDataPresenterImpl videosTabDataPresenter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab_desgin);
        tablayout = findViewById(R.id. tabLayout);
        viewPager = findViewById(R.id.view_pager);
        tablayout.addTab(tablayout.newTab().setText("Chemistry"));
        tablayout.addTab(tablayout.newTab().setText("Physics"));
        tablayout.addTab(tablayout.newTab().setText("Maths"));
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);

        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(),    tablayout .getTabCount());
        videosTabDataPresenter = new VideosTabDataPresenterImpl((IPhysicsTabMainView) this, getApplicationContext());
        videosTabDataPresenter.PhysicsTab();
        viewPager.setAdapter(tabsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
    }



    @Override
    public void PhysicsTab() {






    }

}