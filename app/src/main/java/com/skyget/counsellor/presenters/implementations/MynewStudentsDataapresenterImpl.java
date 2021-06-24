package com.skyget.counsellor.presenters.implementations;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.skyget.counsellor.model.request.MynewStudentsListResponse;
import com.skyget.counsellor.model.request.UpdateStudentDetailsRequest;
import com.skyget.counsellor.model.response.FeedBackAlertResponce;
import com.skyget.counsellor.model.response.Update_city_Response;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.InewMyStudentsView;
import com.skyget.counsellor.presenters.interfaces.InewMystudentsPresenter;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MynewStudentsDataapresenterImpl implements InewMystudentsPresenter {

    InewMyStudentsView inewMyStudentsView;
    Context i;
    private MynewStudentsDataapresenterImpl mynewStudentsDataapresenterr;
    private Dialog viewclickingDialog;


    public MynewStudentsDataapresenterImpl(InewMyStudentsView inewMyStudentsView, Context i) {
        this.inewMyStudentsView = inewMyStudentsView;
        this.i = i;
    }

    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);


    @Override
    public void MynewStudents() {
        inewMyStudentsView.showProgress();
        Call<List<MynewStudentsListResponse>> call = apiInterface.MynewStudents();
        call.enqueue(new Callback<List<MynewStudentsListResponse>>() {
            @Override
            public void onResponse(Call<List<MynewStudentsListResponse>> call, Response<List<MynewStudentsListResponse>> response) {
                inewMyStudentsView.hideProgress();
                if (response.isSuccessful()) {
                    inewMyStudentsView.MynewStudents(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        inewMyStudentsView.failure("" + jObjError.getString("errorMessage"));
                    } catch (Exception e) {
                        inewMyStudentsView.failure("" + e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<MynewStudentsListResponse>> call, Throwable t) {
                inewMyStudentsView.hideProgress();
            }

        });
    }


    public void givingFeedback(String studentId, String feedBackId) {
        inewMyStudentsView.showProgress();
        Call<FeedBackAlertResponce> feedBackAlertResponceCall = apiInterface.FeedbackAlert(studentId, feedBackId);
        feedBackAlertResponceCall.enqueue(new Callback<FeedBackAlertResponce>() {
            @Override
            public void onResponse(Call<FeedBackAlertResponce> call, Response<FeedBackAlertResponce> response) {
                inewMyStudentsView.hideProgress();
                if (response.isSuccessful()) {
                    inewMyStudentsView.sendFeedBackSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        inewMyStudentsView.sendFeedBackFailed("" + jObjError.getString("errorMessage"));
                    } catch (Exception e) {
                        inewMyStudentsView.sendFeedBackFailed("" + e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<FeedBackAlertResponce> call, Throwable t) {
                inewMyStudentsView.hideProgress();
            }
        });
    }

    public void updatecityes(int id, UpdateStudentDetailsRequest updateStudentDetailsRequest) {
        inewMyStudentsView.showProgress();
       

        Call<Update_city_Response> feedBackAlertResponceCall = apiInterface.cityesupdate(id, updateStudentDetailsRequest);
        feedBackAlertResponceCall.enqueue(new Callback<Update_city_Response>() {
            @Override
            public void onResponse(Call<Update_city_Response> call, Response<Update_city_Response> response) {
                inewMyStudentsView.hideProgress();
              
                
                if (response.isSuccessful()) {
                    inewMyStudentsView.updateCityDetailSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        mynewStudentsDataapresenterr.MynewStudents();
                        inewMyStudentsView.sendFeedBackFailed("" + jObjError.getString("errorMessage"));
                    } catch (Exception e) {
                        inewMyStudentsView.sendFeedBackFailed("" + e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<Update_city_Response> call, Throwable t) {
                inewMyStudentsView.hideProgress();

            }
        });
    }



}




