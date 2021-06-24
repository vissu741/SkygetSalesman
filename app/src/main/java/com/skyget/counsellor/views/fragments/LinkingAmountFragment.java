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
import com.skyget.counsellor.databinding.FragmentLinkingAmountBinding;
import com.skyget.counsellor.databinding.FragmentMyAccountBinding;
import com.skyget.counsellor.model.response.LinkingResponse;
import com.skyget.counsellor.other.Constants;
import com.skyget.counsellor.presenters.implementations.LinkingDataDataPresenterImpl;
import com.skyget.counsellor.presenters.interfaces.ILinkingDataOfferMainView;
import com.skyget.counsellor.presenters.interfaces.ILinkingDataPresenter;
import com.skyget.counsellor.views.adapters.LinkingReportViewAdapter;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class LinkingAmountFragment extends Fragment implements ILinkingDataOfferMainView, View.OnClickListener, LinkingReportViewAdapter.PostClickAdapter {

    int salesman_id;
    FragmentLinkingAmountBinding fragmentMyAccountBinding;
    Context mContext;
    String salesman_name;

    int width, heigth;

    AlertDialog dialog;

    int indexofmonth;


    ILinkingDataPresenter iLinkingDataPresenter;


    public LinkingAmountFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMyAccountBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_linking_amount, container, false);
        mContext = getActivity();


        width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        heigth = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        SharedPreferences myPrefs = getActivity().getSharedPreferences(Constants.LOGIN_SHARED, MODE_PRIVATE);
        salesman_id = myPrefs.getInt(Constants.SALESMAN_ID, 0);

        salesman_name = myPrefs.getString(Constants.SALESMAN_NAME, "Faculty");

        iLinkingDataPresenter = new LinkingDataDataPresenterImpl(this, mContext);

        iLinkingDataPresenter.getLinkingViews(salesman_id, 0);

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
                    iLinkingDataPresenter.getLinkingViews(salesman_id, indexofmonth);
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
    public void getLinkingViews(List<LinkingResponse> resp) {

        if (resp != null && resp.size()>0) {
            fragmentMyAccountBinding.salesmanLinkingRecyclerview.setVisibility(View.VISIBLE);
            fragmentMyAccountBinding.noTextWatch.setVisibility(View.GONE);
            fragmentMyAccountBinding.salesmanLinkingRecyclerview.setHasFixedSize(true);
            fragmentMyAccountBinding.salesmanLinkingRecyclerview.setLayoutManager(new LinearLayoutManager(mContext,
                    LinearLayoutManager.VERTICAL, false));
            fragmentMyAccountBinding.salesmanLinkingRecyclerview.setNestedScrollingEnabled(false);
            LinkingReportViewAdapter salesmanReportViewAdapter = new LinkingReportViewAdapter(mContext, this, resp);
            fragmentMyAccountBinding.salesmanLinkingRecyclerview.setAdapter(salesmanReportViewAdapter);
        } else {
            fragmentMyAccountBinding.salesmanLinkingRecyclerview.setVisibility(View.GONE);
            fragmentMyAccountBinding.noTextWatch.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void failure(String msg) {

        fragmentMyAccountBinding.salesmanLinkingRecyclerview.setVisibility(View.GONE);
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


                break;
            case R.id.proceed_btn:

            default:
                break;
        }
    }

    @Override
    public void onTakeButtonClicked(LinkingResponse response) {

    }
}