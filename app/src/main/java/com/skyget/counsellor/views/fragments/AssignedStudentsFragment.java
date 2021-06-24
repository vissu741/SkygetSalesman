package com.skyget.counsellor.views.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.skyget.counsellor.R;
import com.skyget.counsellor.databinding.AssignmentContactBinding;
import com.skyget.counsellor.databinding.DoubtsHomeDialogBinding;
import com.skyget.counsellor.databinding.FragmentSalesmanStudentsBinding;
import com.skyget.counsellor.databinding.PopupGetStudentDetailsBinding;
import com.skyget.counsellor.model.request.ContactedRequest;
import com.skyget.counsellor.model.response.DefaultSuccessResponse;
import com.skyget.counsellor.model.response.GetStudentsforSalesmanResponse;
import com.skyget.counsellor.other.Constants;
import com.skyget.counsellor.presenters.implementations.ContactedDataPresenterImpl;
import com.skyget.counsellor.presenters.implementations.GetStudentsforSalesmanDataPresenterImpl;
import com.skyget.counsellor.presenters.implementations.GetUpdateViewDataPresenterImpl;
import com.skyget.counsellor.presenters.interfaces.IContactedDataPresenter;
import com.skyget.counsellor.presenters.interfaces.IContactedMainView;
import com.skyget.counsellor.presenters.interfaces.IGetStudentsforSalesmanDataPresenter;
import com.skyget.counsellor.presenters.interfaces.IGetStudentsforSalesmanMainView;
import com.skyget.counsellor.presenters.interfaces.IGetUpdateViewDataPresenter;
import com.skyget.counsellor.presenters.interfaces.IGetUpdateViewMainView;
import com.skyget.counsellor.views.adapters.GetAssignedStudentsViewAdapter;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class AssignedStudentsFragment extends Fragment implements IGetStudentsforSalesmanMainView, GetAssignedStudentsViewAdapter.PostClickAdapter, IGetUpdateViewMainView, IContactedMainView {

    int salesman_id;
    FragmentSalesmanStudentsBinding fragmentNewHomeBinding;
    Context mContext;
    String salesman_name;

    int width, heigth;

    AlertDialog dialog;

    IGetStudentsforSalesmanDataPresenter iGetStudentsforSalesmanDataPresenter;

    IGetUpdateViewDataPresenter iGetUpdateViewDataPresenter;
    IContactedDataPresenter iContactedDataPresenter;
    List<GetStudentsforSalesmanResponse> getStudentsforSalesmanResponseList;
    int get_position;


    public AssignedStudentsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentNewHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_salesman_students, container, false);
        mContext = getActivity();


        width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        heigth = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        SharedPreferences myPrefs = getActivity().getSharedPreferences(Constants.LOGIN_SHARED, MODE_PRIVATE);
        salesman_id = myPrefs.getInt(Constants.SALESMAN_ID, 0);

        salesman_name = myPrefs.getString(Constants.SALESMAN_NAME, "Faculty");

        iGetStudentsforSalesmanDataPresenter = new GetStudentsforSalesmanDataPresenterImpl(this, mContext);
        iGetUpdateViewDataPresenter = new GetUpdateViewDataPresenterImpl(this, mContext);
        iContactedDataPresenter = new ContactedDataPresenterImpl(this, mContext);
        iGetStudentsforSalesmanDataPresenter.getStudentsForSalesmanDetails(salesman_id);


        return fragmentNewHomeBinding.getRoot();
    }


    @Override
    public void showProgress() {

        fragmentNewHomeBinding.progressBarDoubts.setVisibility(VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }

    @Override
    public void hideProgress() {

        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        fragmentNewHomeBinding.progressBarDoubts.setVisibility(GONE);

    }

    @Override
    public void getResultContacted(DefaultSuccessResponse resp) {
        if (resp.status) {


            iGetStudentsforSalesmanDataPresenter.getStudentsForSalesmanDetails(salesman_id);
        }

    }

    @Override
    public void getResult(DefaultSuccessResponse resp) {
        if (resp.status) {

            PopupGetStudentDetailsBinding popupGetStudentDetailsBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.popup_get_student_details, null, false);
            dialog = new AlertDialog.Builder(getActivity()).create();
            dialog.setView(popupGetStudentDetailsBinding.getRoot());
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            popupGetStudentDetailsBinding.tbnCancelMissed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
            popupGetStudentDetailsBinding.testName.setText("Hi " + salesman_name);
            popupGetStudentDetailsBinding.mobileView.setText(getStudentsforSalesmanResponseList.get(get_position).mobile);
            popupGetStudentDetailsBinding.categoryView.setText(getStudentsforSalesmanResponseList.get(get_position).category);
            popupGetStudentDetailsBinding.cityView.setText(getStudentsforSalesmanResponseList.get(get_position).city);
            popupGetStudentDetailsBinding.stateView.setText(getStudentsforSalesmanResponseList.get(get_position).state);
            dialog.show();


        }
    }


    @Override
    public void getResultStudentsforSalesman(List<GetStudentsforSalesmanResponse> resp) {

        if (resp != null) {
            if (resp.size() > 0) {
                getStudentsforSalesmanResponseList = resp;
                fragmentNewHomeBinding.getSalesmanStudentsRecyclerview.setVisibility(VISIBLE);
                fragmentNewHomeBinding.noTextWatch.setVisibility(GONE);
                fragmentNewHomeBinding.getSalesmanStudentsRecyclerview.setHasFixedSize(true);
                fragmentNewHomeBinding.getSalesmanStudentsRecyclerview.setLayoutManager(new LinearLayoutManager(mContext,
                        LinearLayoutManager.VERTICAL, false));
                fragmentNewHomeBinding.getSalesmanStudentsRecyclerview.setNestedScrollingEnabled(false);
                GetAssignedStudentsViewAdapter getAssignedStudentsViewAdapter = new GetAssignedStudentsViewAdapter(mContext, this, resp);
                fragmentNewHomeBinding.getSalesmanStudentsRecyclerview.setAdapter(getAssignedStudentsViewAdapter);
            } else {
                fragmentNewHomeBinding.getSalesmanStudentsRecyclerview.setVisibility(GONE);
                fragmentNewHomeBinding.noTextWatch.setVisibility(VISIBLE);
            }
        }

    }


    @Override
    public void failure(String msg) {

        DoubtsHomeDialogBinding doubtsHomeDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.doubts_home_dialog, null, false);
        final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(getActivity()).create();
        alertDialog.setView(doubtsHomeDialogBinding.getRoot());
        alertDialog.setCancelable(true);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        doubtsHomeDialogBinding.doubtsTxt.setVisibility(GONE);
        doubtsHomeDialogBinding.doubtsNotifyTxt.setText(msg);
        doubtsHomeDialogBinding.okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        doubtsHomeDialogBinding.tbnCancelWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        alertDialog.show();

 /*Log.e("Info", " Error" + msg);
        fragmentNewHomeBinding.noTextWatch.setVisibility(View.VISIBLE);
        fragmentNewHomeBinding.noTextWatch.setText(msg);*/


    }


    @Override
    public void onTakeButtonClicked(GetStudentsforSalesmanResponse response, int i) {

        get_position = i;

        iGetUpdateViewDataPresenter.getUpdateView(response.salesmanId, response.studentId);

    }

    @Override
    public void onContactButtonClicked(GetStudentsforSalesmanResponse response) {


        AssignmentContactBinding assignmentContactBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.assignment_contact, null, false);
        final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(getActivity()).create();
        alertDialog.setView(assignmentContactBinding.getRoot());
        alertDialog.setCancelable(true);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        //final String contacted_text=null;

        if (response.comment != null) {
            assignmentContactBinding.contactedTxt.setText(response.comment);
            assignmentContactBinding.contactedTxt.setEnabled(false);
            assignmentContactBinding.contactedTxt.setFocusable(true);
            assignmentContactBinding.okButton.setVisibility(GONE);

        } else {
            assignmentContactBinding.contactedTxt.setEnabled(true);
            assignmentContactBinding.contactedTxt.setFocusable(true);
            assignmentContactBinding.okButton.setVisibility(VISIBLE);
        }


        assignmentContactBinding.okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContactedRequest contactedRequest = new ContactedRequest();
                String contacted_text = assignmentContactBinding.contactedTxt.getText().toString().trim();
                if (contacted_text != null && contacted_text.length() > 0) {
                    contactedRequest.id = response.id;
                    contactedRequest.comment = contacted_text;

                    iContactedDataPresenter.contacted(contactedRequest);

                    /*InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(doubtsHomeDialogBinding.contactedTxt.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);*/
                    hideSoftKeyboard(assignmentContactBinding.contactedTxt);
                }
                alertDialog.dismiss();
            }
        });

        assignmentContactBinding.tbnCancelContacted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        alertDialog.show();

        //iGetUpdateViewDataPresenter.getUpdateView(response.salesmanId, response.studentId);

    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}