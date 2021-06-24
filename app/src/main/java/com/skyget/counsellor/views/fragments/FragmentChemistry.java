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
import com.skyget.counsellor.databinding.ChemistryfragmentBinding;
import com.skyget.counsellor.model.response.TakeStudentResponse;
import com.skyget.counsellor.presenters.implementations.VideosTabDataPresenterImpl;
import com.skyget.counsellor.presenters.interfaces.IChemistryTabMainview;
import com.skyget.counsellor.views.adapters.FragementsAdapter1;

import java.util.List;

public class FragmentChemistry extends Fragment implements IChemistryTabMainview {
    private FragementsAdapter1 fragementsAdapter1;

    private ChemistryfragmentBinding chemistryfragmentBinding;
    private VideosTabDataPresenterImpl  videosTabDataPresenter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        chemistryfragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.chemistryfragment, container, false);
        videosTabDataPresenter = new VideosTabDataPresenterImpl(this, getActivity());
        videosTabDataPresenter.PhysicsTab();
        return  chemistryfragmentBinding.getRoot();
    }

    @Override
    public void showProgress() {
        chemistryfragmentBinding.progressBarChemistry.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);



    }

    @Override
    public void hideProgress() {

            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            chemistryfragmentBinding.progressBarChemistry.setVisibility(View.GONE);



        }

    @Override
    public void ChemistryTab(List<TakeStudentResponse> chapterlistResponce) {
        if (chapterlistResponce != null) {
            fragementsAdapter1 = new FragementsAdapter1(chapterlistResponce.get(2).listitems, getActivity());
            chemistryfragmentBinding.Recyclerview2.setVisibility(View.VISIBLE);
            chemistryfragmentBinding.noStudentslistText2.setVisibility(View.GONE);
            chemistryfragmentBinding.Recyclerview2.setHasFixedSize(true);
            chemistryfragmentBinding.Recyclerview2.setLayoutManager
                    (new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.VERTICAL, false));
            chemistryfragmentBinding.Recyclerview2.setAdapter(fragementsAdapter1);
            chemistryfragmentBinding.Recyclerview2.setNestedScrollingEnabled(true);

        } else {
            chemistryfragmentBinding.Recyclerview2.setVisibility(View.GONE);
            chemistryfragmentBinding.noStudentslistText2.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void failure(String msg) {

        chemistryfragmentBinding.Recyclerview2.setVisibility(View.GONE);
        chemistryfragmentBinding.noStudentslistText2.setVisibility(View.VISIBLE);


    }
}

