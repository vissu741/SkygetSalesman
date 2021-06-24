package com.skyget.counsellor.presenters.implementations;

import android.content.Context;

import com.skyget.counsellor.model.response.GetStudentDetailsResponse;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.IGetStudentDetailsDataPresenter;
import com.skyget.counsellor.presenters.interfaces.IGetStudentDetailsMainView;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetStudentDetailsDataPresenterImpl implements IGetStudentDetailsDataPresenter {

    IGetStudentDetailsMainView iLoginMainView;
    Context i;

    public GetStudentDetailsDataPresenterImpl(IGetStudentDetailsMainView iLoginMainView, Context i) {
        this.iLoginMainView = iLoginMainView;
        this.i = i;
    }

    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

    @Override
    public void getStudentDetails(String student_mobile_no) {
        iLoginMainView.showProgress();
        Call<GetStudentDetailsResponse> call = apiInterface.getStudentDetails(student_mobile_no);
        call.enqueue(new Callback<GetStudentDetailsResponse>() {
            @Override
            public void onResponse(Call<GetStudentDetailsResponse> call, Response<GetStudentDetailsResponse> response) {
                iLoginMainView.hideProgress();
                if (response.isSuccessful()) {

                    iLoginMainView.getStudentDetails(response.body());


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
            public void onFailure(Call<GetStudentDetailsResponse> call, Throwable t) {

                iLoginMainView.failure("Error " + t.getMessage().toString());
                iLoginMainView.hideProgress();

            }
        });

    }


}
