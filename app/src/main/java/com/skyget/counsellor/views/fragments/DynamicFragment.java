package com.skyget.counsellor.views.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.skyget.counsellor.R;
import com.skyget.counsellor.databinding.FragmentDynamicBinding;
import com.skyget.counsellor.model.request.Student;
import com.skyget.counsellor.model.request.StudentIdforChapter;
import com.skyget.counsellor.model.request.SubjectwiselistRequest;
import com.skyget.counsellor.model.request.TakeStudentRequest;
import com.skyget.counsellor.model.response.Chapterwise_list_Response;
import com.skyget.counsellor.other.Constants;
import com.skyget.counsellor.presenters.implementations.IntractiveDataPresenterImpl;
import com.skyget.counsellor.presenters.interfaces.IIntractivePracticeDataPresenter;
import com.skyget.counsellor.presenters.interfaces.I_IntractivePracticemainview;
import com.skyget.counsellor.views.adapters.FragementsAdapter1;
import com.skyget.counsellor.views.adapters.IntractivepracticeAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DynamicFragment extends Fragment implements I_IntractivePracticemainview {
    private FragmentDynamicBinding fragmentDynamicBinding;
    private IntractivepracticeAdapter intractivepracticeAdapter;
    Context mContext;
    private IIntractivePracticeDataPresenter iIntractivePracticeDataPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDynamicBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_dynamic, container, false);
        iIntractivePracticeDataPresenter = new IntractiveDataPresenterImpl(this, mContext);
        StudentIdforChapter studentIdforChapter = new StudentIdforChapter();
        studentIdforChapter.studentId = "3365";
        studentIdforChapter.subjectId = "2";
        iIntractivePracticeDataPresenter.getChapterList(studentIdforChapter);
        return fragmentDynamicBinding.getRoot();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }




    @Override
    public void showProgress() {
        fragmentDynamicBinding.progressBarIneractive.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


    }

    @Override
    public void hideProgress() {
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        fragmentDynamicBinding.progressBarIneractive.setVisibility(View.GONE);


    }

    @Override
    public void IntractivePracticeTab(List<SubjectwiselistRequest> subjectwiselistRequests) {

    }


    @Override
    public void failure(String msg) {
        fragmentDynamicBinding.IneractiveRecyclerview.setVisibility(View.GONE);
        fragmentDynamicBinding.IneractiveText.setVisibility(View.VISIBLE);

    }

    @Override
    public void getChaptersResponse(List<Chapterwise_list_Response> chapterwise_list_response) {
        if (chapterwise_list_response != null && chapterwise_list_response.size() > 0) {
            IntractivepracticeAdapter intractivepracticeAdapter = new IntractivepracticeAdapter(getContext(), chapterwise_list_response);
            fragmentDynamicBinding.IneractiveText.setVisibility(View.GONE);
            fragmentDynamicBinding.IneractiveRecyclerview.setVisibility(View.VISIBLE);
            fragmentDynamicBinding.IneractiveRecyclerview.setLayoutManager
                    (new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.VERTICAL, false));
            fragmentDynamicBinding.IneractiveRecyclerview.setAdapter(intractivepracticeAdapter);
            fragmentDynamicBinding.IneractiveRecyclerview.setNestedScrollingEnabled(true);
        } else {
            fragmentDynamicBinding.IneractiveRecyclerview.setVisibility(View.GONE);
            fragmentDynamicBinding.IneractiveText.setVisibility(View.VISIBLE);


        }
    }
}





