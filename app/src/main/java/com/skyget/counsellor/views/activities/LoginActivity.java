package com.skyget.counsellor.views.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.skyget.counsellor.R;
import com.skyget.counsellor.model.request.LoginRequest;
import com.skyget.counsellor.model.response.LoginResponse;
import com.skyget.counsellor.other.ConnectivityDetector;
import com.skyget.counsellor.other.Constants;
import com.skyget.counsellor.presenters.implementations.LoginDataPresenterImpl;
import com.skyget.counsellor.presenters.interfaces.ILoginDataPresenter;
import com.skyget.counsellor.presenters.interfaces.ILoginMainView;
import com.skyget.counsellor.databinding.ActivityLoginBinding;
import com.skyget.counsellor.databinding.DoubtsHomeDialogBinding;


public class LoginActivity extends AppCompatActivity implements ILoginMainView/*, GoogleApiClient.ConnectionCallbacks,
        OtpReceivedInterface, GoogleApiClient.OnConnectionFailedListener, ILogsMainView */ {

    ActivityLoginBinding activityLoginBinding;

    String mobileNumber, passwordValue;

    ILoginDataPresenter iLoginDataPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        iLoginDataPresenter = new LoginDataPresenterImpl(this, this);


        activityLoginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                signIn();
            }
        });


    }

    public void signIn() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            //
            // TODO: handle exception
        }
        mobileNumber = activityLoginBinding.editUsername.getText().toString().trim();
        passwordValue = activityLoginBinding.editPassword.getText().toString().trim();


        LoginRequest loginRequest = new LoginRequest();
        loginRequest.phone = mobileNumber;
        loginRequest.password = passwordValue;


        if (ConnectivityDetector.isConnectingToInternet(LoginActivity.this)) {
            //Your Code when connected.

            if (mobileNumber.length() >= 1) {
                if (passwordValue.length() >= 1) {
                    iLoginDataPresenter.getLoginDetails(loginRequest);

                } else {
                    activityLoginBinding.textinputUser.setErrorEnabled(false);
                    activityLoginBinding.textinputPassword.setError(getString(R.string.password_error));//"Please enter password");
                    activityLoginBinding.textinputPassword.setErrorEnabled(true);
                }
            } else {
                activityLoginBinding.textinputUser.setError(getString(R.string.username_error));//"Please enter valid email");
                activityLoginBinding.textinputUser.setErrorEnabled(true);
            }

        }

    }


    @Override
    public void showProgress() {

        activityLoginBinding.progressBarDoubts.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }

    @Override
    public void hideProgress() {

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        activityLoginBinding.progressBarDoubts.setVisibility(View.GONE);

    }

    @Override
    public void getResult(LoginResponse resp) {

        if (resp.status) {


            SharedPreferences sharedPreferences = getSharedPreferences(Constants.LOGIN_SHARED, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear().commit();

            editor.putBoolean(Constants.LOGIN_STATUS, true);
            editor.putInt(Constants.SALESMAN_ID, resp.id);
            editor.putString(Constants.SALESMAN_NAME, resp.salesMan);
            editor.putString(Constants.SALESMAN_MOB_NO, resp.phone);
            editor.putString(Constants.SALESMAN_CITY, resp.city);
            editor.putString(Constants.SALESMAN_STATE, resp.state);
            editor.putInt(Constants.SALESMAN_PERCENTAGE, resp.percentage);
            editor.putInt(Constants.SALESMAN_VALUE, resp.value);
            editor.putBoolean(Constants.EMI_STATUS, resp.emi);

            ///////Newly Added
            editor.putString(Constants.SALESMAN_ADDRESS1, resp.address1);
            editor.putString(Constants.SALESMAN_ADDRESS2, resp.address2);
            editor.putString(Constants.SALESMAN_COUNTRY, resp.country);
            editor.putString(Constants.SALESMAN_BANK_DETAILS, resp.details);


            editor.commit();
            // editor.putString(Constants.STUDENT_GENDER, resp.gender);
            Intent homeIntent = new Intent(LoginActivity.this, NavigationActivity.class);
            startActivity(homeIntent);
            finishAffinity();
        }

    }

    @Override
    public void failure(String msg) {

        DoubtsHomeDialogBinding doubtsHomeDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(LoginActivity.this), R.layout.doubts_home_dialog, null, false);
        final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(LoginActivity.this).create();
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
}
