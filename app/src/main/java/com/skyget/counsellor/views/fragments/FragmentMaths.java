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
import com.skyget.counsellor.databinding.FragmentMaths2Binding;
import com.skyget.counsellor.model.response.TakeStudentResponse;
import com.skyget.counsellor.presenters.implementations.VideosTabDataPresenterImpl;
import com.skyget.counsellor.presenters.interfaces.IMathsTabMainview;
import com.skyget.counsellor.views.adapters.FragementsAdapter1;

import java.util.List;

public class FragmentMaths extends Fragment implements IMathsTabMainview {

    private FragementsAdapter1 fragementsAdapter1;
    private FragmentMaths2Binding fragmentMaths2Binding;
    private VideosTabDataPresenterImpl videosTabDataPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMaths2Binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_maths2, container, false);
        videosTabDataPresenter = new VideosTabDataPresenterImpl(this, getActivity());
        videosTabDataPresenter. PhysicsTab();
        return fragmentMaths2Binding.getRoot();
    }

    @Override
    public void showProgress() {
        fragmentMaths2Binding.progressBarMaths.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


    }

    @Override
    public void hideProgress() {
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        fragmentMaths2Binding.progressBarMaths.setVisibility(View.GONE);


    }

    @Override
    public void MathsTab (List<TakeStudentResponse> chapterlistResponce) {
        if (chapterlistResponce != null) {
            fragementsAdapter1 = new FragementsAdapter1(chapterlistResponce.get(2).listitems, getActivity());
            fragmentMaths2Binding.Recyclerview3.setVisibility(View.VISIBLE);
            fragmentMaths2Binding.noStudentslistText3.setVisibility(View.GONE);
            fragmentMaths2Binding.Recyclerview3.setHasFixedSize(true);
            fragmentMaths2Binding.Recyclerview3.setLayoutManager
                    (new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.VERTICAL, false));
            fragmentMaths2Binding.Recyclerview3.setAdapter(fragementsAdapter1);
            fragmentMaths2Binding.Recyclerview3.setNestedScrollingEnabled(true);
        } else {
            fragmentMaths2Binding.Recyclerview3.setVisibility(View.GONE);
            fragmentMaths2Binding.noStudentslistText3.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void failure(String msg) {
        fragmentMaths2Binding.Recyclerview3.setVisibility(View.GONE);
        fragmentMaths2Binding.noStudentslistText3.setVisibility(View.VISIBLE);


    }
}