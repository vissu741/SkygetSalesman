package com.skyget.counsellor.presenters.implementations;

import android.content.Context;

import com.skyget.counsellor.model.response.SubscriptionsResponse;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.IGetSubscriptionsDataPresenter;
import com.skyget.counsellor.presenters.interfaces.IGetSubscriptionsMainView;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetSubscriptionsDataPresenterImpl implements IGetSubscriptionsDataPresenter {

    IGetSubscriptionsMainView iLoginMainView;
    Context i;

    public GetSubscriptionsDataPresenterImpl(IGetSubscriptionsMainView iLoginMainView, Context i) {
        this.iLoginMainView = iLoginMainView;
        this.i = i;
    }

    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

    @Override
    public void getSubscriptionsDetails(int std_id) {
        iLoginMainView.showProgress();
        Call<List<SubscriptionsResponse>> call = apiInterface.getStudentSubscriptionDetails(std_id);
        call.enqueue(new Callback<List<SubscriptionsResponse>>() {
            @Override
            public void onResponse(Call<List<SubscriptionsResponse>> call, Response<List<SubscriptionsResponse>> response) {
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
            public void onFailure(Call<List<SubscriptionsResponse>> call, Throwable t) {

                iLoginMainView.failure("Error " + t.getMessage().toString());
                iLoginMainView.hideProgress();

            }
        });

    }


}
