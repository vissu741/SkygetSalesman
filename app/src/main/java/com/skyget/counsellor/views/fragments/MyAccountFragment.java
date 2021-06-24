package com.skyget.counsellor.views.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.skyget.counsellor.R;
import com.skyget.counsellor.databinding.DoubtsHomeDialogBinding;
import com.skyget.counsellor.databinding.FragmentMyAccountBinding;
import com.skyget.counsellor.model.response.SalesDetailsResponse;
import com.skyget.counsellor.model.response.SalesmanReportsResponse;
import com.skyget.counsellor.other.Constants;
import com.skyget.counsellor.presenters.implementations.GetSalesmanReportsDataPresenterImpl;
import com.skyget.counsellor.presenters.interfaces.IGetSalesmanReportsDataPresenter;
import com.skyget.counsellor.presenters.interfaces.IGetSalesmanReportsMainView;
import com.skyget.counsellor.views.adapters.SalesmanReportViewAdapter;

import static android.content.Context.MODE_PRIVATE;

public class MyAccountFragment extends Fragment implements IGetSalesmanReportsMainView, View.OnClickListener, SalesmanReportViewAdapter.PostClickAdapter {

    int salesman_id;
    FragmentMyAccountBinding fragmentMyAccountBinding;
    Context mContext;
    String salesman_name;

    int width, heigth;

    AlertDialog dialog;

    int indexofmonth;


    IGetSalesmanReportsDataPresenter iGetSalesmanReportsDataPresenter;


    public MyAccountFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMyAccountBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_account, container, false);
        mContext = getActivity();


        width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        heigth = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        SharedPreferences myPrefs = getActivity().getSharedPreferences(Constants.LOGIN_SHARED, MODE_PRIVATE);
        salesman_id = myPrefs.getInt(Constants.SALESMAN_ID, 0);

        salesman_name = myPrefs.getString(Constants.SALESMAN_NAME, "Faculty");

        iGetSalesmanReportsDataPresenter = new GetSalesmanReportsDataPresenterImpl(this, mContext);

        iGetSalesmanReportsDataPresenter.getSalesmanReports(salesman_id, 0);

       /* ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, plans_list);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
        fragmentSalesmanReportsBinding.monthSpinner.setAdapter(dataAdapter);*/

        fragmentMyAccountBinding.monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                indexofmonth = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fragmentMyAccountBinding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (indexofmonth != 0) {
                    iGetSalesmanReportsDataPresenter.getSalesmanReports(salesman_id, indexofmonth);
                } else {
                    Toast.makeText(mContext, "Please select month", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return fragmentMyAccountBinding.getRoot();
    }


    @Override
    public void showProgress() {

        fragmentMyAccountBinding.progressBarDoubts.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }

    @Override
    public void hideProgress() {

        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        fragmentMyAccountBinding.progressBarDoubts.setVisibility(View.GONE);

    }

    @Override
    public void getResult(SalesmanReportsResponse resp) {
        if (resp != null) {
            fragmentMyAccountBinding.salesmanReportsRecyclerview.setVisibility(View.VISIBLE);
            fragmentMyAccountBinding.noTextWatch.setVisibility(View.GONE);
            fragmentMyAccountBinding.salesmanReportsRecyclerview.setHasFixedSize(true);
            fragmentMyAccountBinding.salesmanReportsRecyclerview.setLayoutManager(new LinearLayoutManager(mContext,
                    LinearLayoutManager.VERTICAL, false));
            fragmentMyAccountBinding.salesmanReportsRecyclerview.setNestedScrollingEnabled(false);
            SalesmanReportViewAdapter salesmanReportViewAdapter = new SalesmanReportViewAdapter(mContext, this, resp);
            fragmentMyAccountBinding.salesmanReportsRecyclerview.setAdapter(salesmanReportViewAdapter);
        } else {
            fragmentMyAccountBinding.salesmanReportsRecyclerview.setVisibility(View.GONE);
            fragmentMyAccountBinding.noTextWatch.setVisibility(View.VISIBLE);
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

        fragmentMyAccountBinding.salesmanReportsRecyclerview.setVisibility(View.GONE);
        fragmentMyAccountBinding.noTextWatch.setVisibility(View.VISIBLE);

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

    }

/*
    public int getMonth(String noMonth) {

        if (noMonth.equalsIgnoreCase("JANUARY") || noMonth.equalsIgnoreCase("January"))
            return 1;
        else if (noMonth.equalsIgnoreCase("FEBRUARY ") || noMonth.equalsIgnoreCase("February "))
            return 2;
        else if (noMonth.equalsIgnoreCase("MARCH") || noMonth.equalsIgnoreCase("March"))
            return 3;
        else if (noMonth.equalsIgnoreCase("APRIL") || noMonth.equalsIgnoreCase("April"))
            return 4;
        else if (noMonth.equalsIgnoreCase("MAY") || noMonth.equalsIgnoreCase("May"))
            return 5;
        else if (noMonth.equalsIgnoreCase("JUNE") || noMonth.equalsIgnoreCase("June"))
            return 6;
        else if (noMonth.equalsIgnoreCase("JULY") || noMonth.equalsIgnoreCase("July"))
            return 7;
        else if (noMonth.equalsIgnoreCase("AUGUST") || noMonth.equalsIgnoreCase("August"))
            return 8;
        else if (noMonth.equalsIgnoreCase("September") || noMonth.equalsIgnoreCase("SEPTEMBER "))
            return 9;
        else if (noMonth.equalsIgnoreCase("October") || noMonth.equalsIgnoreCase("OCTOBER "))
            return 10;
        else if (noMonth.equalsIgnoreCase("November") || noMonth.equalsIgnoreCase("NOVEMBER "))
            return 11;
        else if (noMonth.equalsIgnoreCase("December") || noMonth.equalsIgnoreCase("DECEMBER "))
            return 12;
        return 0;
    }
*/

}