package com.skyget.counsellor.views.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.skyget.counsellor.R;
import com.skyget.counsellor.model.response.SalesDetailsResponse;
import com.skyget.counsellor.other.Constants;
import com.skyget.counsellor.presenters.implementations.SalesmanSalesDataPresenterImpl;
import com.skyget.counsellor.presenters.interfaces.ISalesmanSalesDataPresenter;
import com.skyget.counsellor.presenters.interfaces.ISalesmanSalesMainView;
import com.skyget.counsellor.views.adapters.SalesPaymentViewAdapter;
import com.skyget.counsellor.databinding.DoubtsHomeDialogBinding;
import com.skyget.counsellor.databinding.FragmentSalesPaymentBinding;
import com.skyget.counsellor.databinding.PopupGetSalesReportsBinding;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SalesPaymentFragment extends Fragment implements ISalesmanSalesMainView, View.OnClickListener, SalesPaymentViewAdapter.PostClickAdapter {

    int salesman_id;
    FragmentSalesPaymentBinding fragmentNewHomeBinding;
    Context mContext;
    String salesman_name;

    int width, heigth;

    AlertDialog dialog;


    ISalesmanSalesDataPresenter iSalesmanSalesDataPresenter;


    public SalesPaymentFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentNewHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sales_payment, container, false);
        mContext = getActivity();


        width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        heigth = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        SharedPreferences myPrefs = getActivity().getSharedPreferences(Constants.LOGIN_SHARED, MODE_PRIVATE);
        salesman_id = myPrefs.getInt(Constants.SALESMAN_ID, 0);

        salesman_name = myPrefs.getString(Constants.SALESMAN_NAME, "salesman");

        iSalesmanSalesDataPresenter = new SalesmanSalesDataPresenterImpl(this, mContext);

        iSalesmanSalesDataPresenter.getSalesmanSalesDetails(salesman_id);


        return fragmentNewHomeBinding.getRoot();
    }


    @Override
    public void showProgress() {

        fragmentNewHomeBinding.progressBarDoubts.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }

    @Override
    public void hideProgress() {

        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        fragmentNewHomeBinding.progressBarDoubts.setVisibility(View.GONE);

    }

    @Override
    public void getResult(List<SalesDetailsResponse> resp) {
        if (resp != null) {
            if (resp.size() > 0) {
                fragmentNewHomeBinding.salesmanPaymentRecyclerview.setVisibility(View.VISIBLE);
                fragmentNewHomeBinding.noTextWatch.setVisibility(View.GONE);
                fragmentNewHomeBinding.salesmanPaymentRecyclerview.setHasFixedSize(true);
                fragmentNewHomeBinding.salesmanPaymentRecyclerview.setLayoutManager(new LinearLayoutManager(mContext,
                        LinearLayoutManager.VERTICAL, false));
                fragmentNewHomeBinding.salesmanPaymentRecyclerview.setNestedScrollingEnabled(false);
                SalesPaymentViewAdapter salesPaymentViewAdapter = new SalesPaymentViewAdapter(mContext, this, resp);
                fragmentNewHomeBinding.salesmanPaymentRecyclerview.setAdapter(salesPaymentViewAdapter);
            } else {
                fragmentNewHomeBinding.salesmanPaymentRecyclerview.setVisibility(View.GONE);
                fragmentNewHomeBinding.noTextWatch.setVisibility(View.VISIBLE);
            }
        }

    }

   /* @Override
    public void getStudentDetails(GetStudentDetailsResponse resp) {


        if (resp != null) {

            getStudentDetailsResponse=resp;

                fragmentNewHomeBinding.hidingLayout.setVisibility(View.VISIBLE);


//                fragmentNewHomeBinding.profileImage.setImageDrawable();

                fragmentNewHomeBinding.layoutProfileDetails.editName.setText(resp.name);
                fragmentNewHomeBinding.layoutProfileDetails.editMobile.setText(resp.mobilenumber);

                fragmentNewHomeBinding.layoutProfileDetails.editEmail.setText(resp.emailid);
                fragmentNewHomeBinding.layoutProfileDetails.editState.setText(resp.state);

                fragmentNewHomeBinding.layoutProfileDetails.editCity.setText(resp.city);
                fragmentNewHomeBinding.layoutProfileDetails.editStandard.setText(resp.standardName);

                fragmentNewHomeBinding.layoutProfileDetails.editCategory.setText(resp.categoryName);
                fragmentNewHomeBinding.layoutProfileDetails.editRegisterDate.setText(resp.insertedDate);


        }


    }*/

    @Override
    public void failure(String msg) {

        DoubtsHomeDialogBinding doubtsHomeDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.doubts_home_dialog, null, false);
        final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(getActivity()).create();
        alertDialog.setView(doubtsHomeDialogBinding.getRoot());
        alertDialog.setCancelable(true);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        doubtsHomeDialogBinding.doubtsTxt.setVisibility(View.GONE);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:

              /*  try {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }

                String mobile_num=fragmentNewHomeBinding.editUsername.getText().toString();
                if(mobile_num!=null)
                {
                    mobile_num=mobile_num.trim();
                   // iGetStudentDetailsDataPresenter.getStudentDetails(mobile_num);
                }*/

                break;
            case R.id.proceed_btn:

              /*  Intent create_offer_intent=new Intent(mContext, CreateOfferActivity.class);
                create_offer_intent.putExtra("std_id",getStudentDetailsResponse.studentid);
                startActivity(create_offer_intent);

//                getChildFragmentManager().beginTransaction().replace(R.id.home_framelay, new CreateOfferFragment())
//                        .commit();
                break;*/
            default:
                break;
        }
    }

    @Override
    public void onTakeButtonClicked(SalesDetailsResponse response) {

        PopupGetSalesReportsBinding popupGetSalesReportsBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.popup_get_sales_reports, null, false);
        dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.setView(popupGetSalesReportsBinding.getRoot());
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        popupGetSalesReportsBinding.tbnCancelMissed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
        popupGetSalesReportsBinding.testName.setText("Hi " + salesman_name);
        popupGetSalesReportsBinding.mobileView.setText(response.mobileNumber);
        popupGetSalesReportsBinding.studentName.setText(response.studentName);
        popupGetSalesReportsBinding.validityView.setText(response.validityInhrs+"");
        //popupGetStudentDetailsBinding.stateView.setText(getStudentsforSalesmanResponseList.get(get_position).state);
        dialog.show();

    }
}