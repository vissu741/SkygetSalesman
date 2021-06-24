package com.skyget.counsellor.presenters.implementations;

import android.content.Context;


import com.skyget.counsellor.model.request.LoginRequest;
import com.skyget.counsellor.model.response.LoginResponse;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.ILoginDataPresenter;
import com.skyget.counsellor.presenters.interfaces.ILoginMainView;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginDataPresenterImpl implements ILoginDataPresenter {

    ILoginMainView iLoginMainView;
    Context i;

    public LoginDataPresenterImpl(ILoginMainView iLoginMainView, Context i) {
        this.iLoginMainView = iLoginMainView;
        this.i = i;
    }

    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

    @Override
    public void getLoginDetails(LoginRequest loginRequest) {
        iLoginMainView.showProgress();
        Call<LoginResponse> call = apiInterface.loginUser(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                iLoginMainView.hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().status) {
                        iLoginMainView.getResult(response.body());
                    }

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        iLoginMainView.failure("" + jObjError.getString("errorMessage"));
                    } catch (Exception e) {
                        iLoginMainView.failure("" + e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                iLoginMainView.failure("Error " + t.getMessage().toString());
                iLoginMainView.hideProgress();

            }
        });

    }


}
