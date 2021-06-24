package com.skyget.counsellor.views.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.skyget.counsellor.R;
import com.skyget.counsellor.databinding.DoubtsHomeDialogBinding;
import com.skyget.counsellor.databinding.FragmentSettlementBinding;
import com.skyget.counsellor.model.response.SettlementResponse;
import com.skyget.counsellor.other.Constants;
import com.skyget.counsellor.presenters.implementations.SettlementDataPresenterImpl;
import com.skyget.counsellor.presenters.interfaces.ISettlementDataPresenter;
import com.skyget.counsellor.presenters.interfaces.ISettlementMainView;
import com.skyget.counsellor.views.adapters.SettlementViewAdapter;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SettlementFragment extends Fragment implements ISettlementMainView, View.OnClickListener, SettlementViewAdapter.PostClickAdapter {

    int salesman_id;
    FragmentSettlementBinding fragmentSettlementBinding;
    Context mContext;
    String salesman_name;

    int width, heigth;

    AlertDialog dialog;

    ImageView et_suggest_txt;


    ISettlementDataPresenter iSettlementDataPresenter;


    public SettlementFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSettlementBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settlement, container, false);
        mContext = getActivity();


        width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        heigth = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        SharedPreferences myPrefs = getActivity().getSharedPreferences(Constants.LOGIN_SHARED, MODE_PRIVATE);
        salesman_id = myPrefs.getInt(Constants.SALESMAN_ID, 0);

        salesman_name = myPrefs.getString(Constants.SALESMAN_NAME, "Faculty");

        iSettlementDataPresenter = new SettlementDataPresenterImpl(this, mContext);

        iSettlementDataPresenter.getSalesmanSetlementDetails(salesman_id);


        return fragmentSettlementBinding.getRoot();
    }


    @Override
    public void showProgress() {

        fragmentSettlementBinding.progressBarDoubts.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }

    @Override
    public void hideProgress() {

        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        fragmentSettlementBinding.progressBarDoubts.setVisibility(View.GONE);

    }

    @Override
    public void getSalesmanSetlementDetails(List<SettlementResponse> resp) {
        if (resp != null) {
            if (resp.size() > 0) {
                fragmentSettlementBinding.settlementRecyclerview.setVisibility(View.VISIBLE);
                fragmentSettlementBinding.noTextWatch.setVisibility(View.GONE);
                fragmentSettlementBinding.settlementRecyclerview.setHasFixedSize(true);
                fragmentSettlementBinding.settlementRecyclerview.setLayoutManager(new LinearLayoutManager(mContext,
                        LinearLayoutManager.VERTICAL, false));
                fragmentSettlementBinding.settlementRecyclerview.setNestedScrollingEnabled(false);
                SettlementViewAdapter salesPaymentViewAdapter = new SettlementViewAdapter(mContext, this, resp);
                fragmentSettlementBinding.settlementRecyclerview.setAdapter(salesPaymentViewAdapter);
            } else {
                fragmentSettlementBinding.settlementRecyclerview.setVisibility(View.GONE);
                fragmentSettlementBinding.noTextWatch.setVisibility(View.VISIBLE);
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


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:


                break;
            case R.id.proceed_btn:
                break;

            default:
                break;
        }
    }

    @Override
    public void onTakeButtonClicked(String image_url) {
        if (image_url != null && image_url != "") {
            suggetsionAlert(image_url, "Hey, " + salesman_name, "", "baseImg");
        } else {

            DoubtsHomeDialogBinding doubtsHomeDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.doubts_home_dialog, null, false);
            final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(getActivity()).create();
            alertDialog.setView(doubtsHomeDialogBinding.getRoot());
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            doubtsHomeDialogBinding.doubtsTxt.setText("Hi " + salesman_name + ",");
            doubtsHomeDialogBinding.doubtsNotifyTxt.setText("Please wait ");
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

        }

    }

    @Override
    public void onViewClicked(String image) {

        LayoutInflater factory = LayoutInflater.from(getContext());
        final View dialogView = factory.inflate(R.layout.dialog_layout_promotional, null);
        dialog = new AlertDialog.Builder(getContext()).create();
        dialog.setView(dialogView);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView dialog_title = dialogView.findViewById(R.id.dialog_title);
        dialog_title.setText("Doubt");
        TextView textViewMsg = dialogView.findViewById(R.id.txtMsg);
        textViewMsg.setTextColor(getResources().getColor(R.color.grayColor_2));
        textViewMsg.setText(image);
        dialogView.findViewById(R.id.tbnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }


    public void suggetsionAlert(String su_value, String user_name, String type, String text_type) {

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_feedback_dialog, null);
        final TextView et_username = alertLayout.findViewById(R.id.et_username);
        final TextView sugg_feature_text = alertLayout.findViewById(R.id.sugg_feature_text);
        et_suggest_txt = alertLayout.findViewById(R.id.suggetion_txt);
        final TextView html_txt = alertLayout.findViewById(R.id.html_txt);
        final Button tbnCancel = alertLayout.findViewById(R.id.tbnCancel);
        sugg_feature_text.setText(type);

        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setView(alertLayout);
        alert.setCancelable(true);
        final AlertDialog dialog = alert.create();
        if (text_type.equalsIgnoreCase("baseImg")) {
            html_txt.setVisibility(View.GONE);
            et_suggest_txt.setVisibility(View.VISIBLE);
            sugg_feature_text.setVisibility(View.GONE);
            final String encodedString = su_value;

            if (encodedString != null && encodedString != "") {

                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_placeholder);


                Glide.with(getActivity()).load(encodedString).apply(options).into(et_suggest_txt);

            } else {

            }

        }
        et_username.setText(user_name);

        tbnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }


}