package com.skyget.counsellor.presenters.implementations;

import android.content.Context;

import com.skyget.counsellor.model.response.AllSubscriptionsResponse;
import com.skyget.counsellor.model.response.SubscriptionsResponse;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.IGetAllSubscriptionsDataPresenter;
import com.skyget.counsellor.presenters.interfaces.IGetAllSubscriptionsMainView;
import com.skyget.counsellor.presenters.interfaces.IGetSubscriptionsDataPresenter;
import com.skyget.counsellor.presenters.interfaces.IGetSubscriptionsMainView;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetAllSubscriptionsDataPresenterImpl implements IGetAllSubscriptionsDataPresenter {

    IGetAllSubscriptionsMainView iLoginMainView;
    Context i;

    public GetAllSubscriptionsDataPresenterImpl(IGetAllSubscriptionsMainView iLoginMainView, Context i) {
        this.iLoginMainView = iLoginMainView;
        this.i = i;
    }

    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

    @Override
    public void getAllSubscriptionsDetails() {
        iLoginMainView.showProgress();
        Call<List<AllSubscriptionsResponse>> call = apiInterface.getallStudentSubscriptionDetails();
        call.enqueue(new Callback<List<AllSubscriptionsResponse>>() {
            @Override
            public void onResponse(Call<List<AllSubscriptionsResponse>> call, Response<List<AllSubscriptionsResponse>> response) {
                iLoginMainView.hideProgress();
                if (response.isSuccessful()) {
                        iLoginMainView.getResult(response.body());

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
            public void onFailure(Call<List<AllSubscriptionsResponse>> call, Throwable t) {

                iLoginMainView.failure("Error " + t.getMessage().toString());
                iLoginMainView.hideProgress();

            }
        });

    }


}
