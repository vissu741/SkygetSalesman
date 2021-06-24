package com.skyget.counsellor.views.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.skyget.counsellor.R;
import com.skyget.counsellor.databinding.FragmentPhysics2Binding;
import com.skyget.counsellor.model.response.TakeStudentResponse;
import com.skyget.counsellor.presenters.implementations.VideosTabDataPresenterImpl;
import com.skyget.counsellor.presenters.interfaces.IPhysicsTabMainView;
import com.skyget.counsellor.views.adapters.FragementsAdapter1;

import java.util.List;

public class FragmentPhysics extends Fragment implements IPhysicsTabMainView {


    private FragementsAdapter1 fragementsAdapter1;
    private FragmentPhysics2Binding FragmentPhysics2Binding;
    private VideosTabDataPresenterImpl videosTabDataPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentPhysics2Binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_physics2, container, false);
        videosTabDataPresenter = new VideosTabDataPresenterImpl(this, getActivity());
        videosTabDataPresenter.PhysicsTab();
        return FragmentPhysics2Binding.getRoot();
    }

    @Override
    public void showProgress() {
        FragmentPhysics2Binding.progressBarphysics.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


    }

    @Override
    public void hideProgress() {
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        FragmentPhysics2Binding.progressBarphysics.setVisibility(View.GONE);


    }

    @Override
    public void PhysicsTab(List<TakeStudentResponse> chapterlistResponce) {
        if (chapterlistResponce != null) {
            fragementsAdapter1 = new FragementsAdapter1(chapterlistResponce.get(2).listitems, getActivity());
            FragmentPhysics2Binding.Recyclerview1.setVisibility(View.VISIBLE);
            FragmentPhysics2Binding.noStudentslistText1.setVisibility(View.GONE);
            FragmentPhysics2Binding.Recyclerview1.setHasFixedSize(true);
            FragmentPhysics2Binding.chapter1.setVisibility(View.VISIBLE);
            FragmentPhysics2Binding.Recyclerview1.setLayoutManager
                    (new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.VERTICAL, false));
            FragmentPhysics2Binding.Recyclerview1.setAdapter(fragementsAdapter1);
            FragmentPhysics2Binding.Recyclerview1.setNestedScrollingEnabled(true);
        } else {
            FragmentPhysics2Binding.Recyclerview1.setVisibility(View.GONE);
            FragmentPhysics2Binding.noStudentslistText1.setVisibility(View.VISIBLE);

        }
    }


    @Override
    public void failure(String msg) {
        FragmentPhysics2Binding.Recyclerview1.setVisibility(View.GONE);
        FragmentPhysics2Binding.noStudentslistText1.setVisibility(View.VISIBLE);

    }

}