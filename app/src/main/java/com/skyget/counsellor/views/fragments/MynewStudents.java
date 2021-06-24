package com.skyget.counsellor.views.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.skyget.counsellor.R;
import com.skyget.counsellor.databinding.FragmentMynewStudentsFragementBinding;

import com.skyget.counsellor.model.request.MynewStudentsListResponse;
import com.skyget.counsellor.model.request.UpdateStudentDetailsRequest;
import com.skyget.counsellor.model.response.FeedBackAlertResponce;
import com.skyget.counsellor.model.response.Update_city_Response;
import com.skyget.counsellor.presenters.implementations.MynewStudentsDataapresenterImpl;
import com.skyget.counsellor.presenters.interfaces.InewMyStudentsView;
import com.skyget.counsellor.views.activities.MainActivityTabDesgin;
import com.skyget.counsellor.views.adapters.NewMyStudentsAdapter;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;


public class MynewStudents extends Fragment implements InewMyStudentsView, NewMyStudentsAdapter.FeedbackAlert {
    private FragmentMynewStudentsFragementBinding fragmentMynewStudentsFragementBinding;
    private MynewStudentsDataapresenterImpl mynewStudentsDataapresenterr;
    private NewMyStudentsAdapter NewMyStudentsAdapter;
    private Dialog feedBackDialog, clickHereDialog, viewclickingDialog, videosbutton;

    TabLayout tabLayout;
    FrameLayout frameLayout;

    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public MynewStudents() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentMynewStudentsFragementBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_mynew_students_fragement, container, false);
        mynewStudentsDataapresenterr = new MynewStudentsDataapresenterImpl(this, getActivity());
        mynewStudentsDataapresenterr. MynewStudents();
        return fragmentMynewStudentsFragementBinding.getRoot();
    }


    @Override
    public void showProgress() {
        fragmentMynewStudentsFragementBinding.progressBarNewstudents1.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


    }

    @Override
    public void sendFeedBackSuccess(FeedBackAlertResponce resp) {
        Toast.makeText(getActivity(), resp.message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void hideProgress() {
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        fragmentMynewStudentsFragementBinding.progressBarNewstudents1.setVisibility(View.GONE);

    }

    @Override
    public void MynewStudents(List<MynewStudentsListResponse> resp) {
        if (resp != null) {
            if (resp.size() > 0) {
                fragmentMynewStudentsFragementBinding.MynewStudentslistRecyclerview1.setVisibility(View.VISIBLE);
                fragmentMynewStudentsFragementBinding.noStudentslistText1.setVisibility(View.GONE);
                fragmentMynewStudentsFragementBinding.MynewStudentslistRecyclerview1.setHasFixedSize(true);
                fragmentMynewStudentsFragementBinding.MynewStudentslistRecyclerview1.setLayoutManager
                        (new LinearLayoutManager(getActivity(),
                                LinearLayoutManager.VERTICAL, false));
                NewMyStudentsAdapter = new NewMyStudentsAdapter(resp, getActivity(), this);
                fragmentMynewStudentsFragementBinding.MynewStudentslistRecyclerview1.setAdapter(NewMyStudentsAdapter);
                fragmentMynewStudentsFragementBinding.MynewStudentslistRecyclerview1.setNestedScrollingEnabled(true);
            } else {
                fragmentMynewStudentsFragementBinding.MynewStudentslistRecyclerview1.setVisibility(View.GONE);
                fragmentMynewStudentsFragementBinding.noStudentslistText1.setVisibility(View.VISIBLE);
            }
        }


    }

    @Override
    public void failure(String msg) {
        fragmentMynewStudentsFragementBinding.MynewStudentslistRecyclerview1.setVisibility(View.GONE);
        fragmentMynewStudentsFragementBinding.noStudentslistText1.setVisibility(View.VISIBLE);
    }



    @Override
    public void FedBackClick(String studentId) {
        feedBackDialog = new Dialog(getActivity());
        feedBackDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        feedBackDialog.setCancelable(true);
        feedBackDialog.setContentView(R.layout.feedbackdialouge);
        feedBackDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        RadioGroup radioGroup = feedBackDialog.findViewById(R.id.radioGroup);
        feedBackDialog.findViewById(R.id.tbnCancel_missed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedBackDialog.dismiss();
            }
        });
        feedBackDialog.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Please select radio button", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String selectedId = "";
                    int radioButtonID = radioGroup.getCheckedRadioButtonId();
                    switch (radioButtonID) {
                        case R.id.not_interested:
                            selectedId = "1";
                            break;
                        case R.id.take_other_subscription:
                            selectedId = "2";
                            break;
                        case R.id.interest_in_skyget:
                            selectedId = "3";
                            break;
                        case R.id.product_explained:
                            selectedId = "4";
                            break;
                        case R.id.called_not_lifted:
                            selectedId = "5";
                            break;
                    }
                    mynewStudentsDataapresenterr.givingFeedback(studentId, selectedId);
                    feedBackDialog.dismiss();

                }

            }
        });
        feedBackDialog.show();
    }

    @Override
    public void clieckHereClick(String feedBackComments) {
        clickHereDialog = new Dialog(getActivity());
        clickHereDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        clickHereDialog.setCancelable(true);
        clickHereDialog.setContentView(R.layout.feedback_states_dialog);
        clickHereDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView tv_feedback_statement = clickHereDialog.findViewById(R.id.tv_feedback_statement);


        clickHereDialog.findViewById(R.id.tbnCancel_missed1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickHereDialog.dismiss();
            }
        });

        tv_feedback_statement.setText(feedBackComments.replaceAll(",", "\n"));

        clickHereDialog.show();
    }


    @Override
    public void updateCityDetailSuccess(Update_city_Response resp) {

        Toast.makeText(getActivity(), resp.message, Toast.LENGTH_SHORT).show();
        mynewStudentsDataapresenterr.MynewStudents();
        viewclickingDialog.dismiss();

    }

    @Override
    public void sendFeedBackFailed(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void Viewclicking(String name, String city, String mobile, String state, int id) {


        viewclickingDialog = new Dialog(getActivity());
        viewclickingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        viewclickingDialog.setCancelable(true);
        viewclickingDialog.setContentView(R.layout.viewbuttonlayout);
        viewclickingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView tv_name = viewclickingDialog.findViewById(R.id.tv_name);
        EditText tv_city = viewclickingDialog.findViewById(R.id.tv_city1);
        TextView tv_mobile = viewclickingDialog.findViewById(R.id.tv_mobile);
        TextView tv_id = viewclickingDialog.findViewById(R.id.tv_id);
        EditText tv_state = viewclickingDialog.findViewById(R.id.tv_state);
        tv_name.setText(name);
        tv_city.setText(city);
        tv_mobile.setText(mobile);
        tv_state.setText(state);
        tv_id.setText(id + "");
        viewclickingDialog.findViewById(R.id.tbnCancel_missed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewclickingDialog.dismiss();

            }

        });

        viewclickingDialog.findViewById(R.id.updateBtn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String city = tv_city.getText().toString();
                String state = tv_state.getText().toString();

                if (state.equalsIgnoreCase("") || city.equalsIgnoreCase("")) {

                    Toast.makeText(getContext(), "Please enter state and city", Toast.LENGTH_SHORT).show();
                } else {
                    UpdateStudentDetailsRequest updateStudentDetailsRequest = new UpdateStudentDetailsRequest();
                    updateStudentDetailsRequest.city = city;
                    updateStudentDetailsRequest.state = state;
                    mynewStudentsDataapresenterr.updatecityes(id, updateStudentDetailsRequest);
                    //viewclickingDialog.dismiss();
                }
            }
        });
        //String Edittext = tv_city.getText().toString(
        viewclickingDialog.show();

    }

    @Override
    public void videosbutton(){
        Intent intent =  new Intent(getActivity(), MainActivityTabDesgin.class);
        startActivity(intent);



    }




    }
















