package com.skyget.counsellor.presenters.implementations;

import android.content.Context;

import com.skyget.counsellor.model.request.StudentIdforChapter;
import com.skyget.counsellor.model.request.SubjectwiselistRequest;
import com.skyget.counsellor.model.response.Chapterwise_list_Response;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.IIntractivePracticeDataPresenter;
import com.skyget.counsellor.presenters.interfaces.I_IntractivePracticemainview;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntractiveDataPresenterImpl implements IIntractivePracticeDataPresenter {


    I_IntractivePracticemainview i_intractivePracticemainview;
    private Object Chapterwise_list_Response;
    private StudentIdforChapter studentIdforChapter;


    public IntractiveDataPresenterImpl(I_IntractivePracticemainview i_intractivePracticemainview, Context i) {
        this.i_intractivePracticemainview = i_intractivePracticemainview;


        this.studentIdforChapter = studentIdforChapter;
    }


    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);


    @Override
    public void IntractivePracticeTab() {
        i_intractivePracticemainview.showProgress();
        Call<List<SubjectwiselistRequest>> call = apiInterface.IntractivePracticeAiTab();
        call.enqueue(new Callback<List<SubjectwiselistRequest>>() {
            @Override
            public void onResponse(Call<List<SubjectwiselistRequest>> call, Response<List<SubjectwiselistRequest>> response) {
                i_intractivePracticemainview.hideProgress();
                if (response.isSuccessful()) {
                    i_intractivePracticemainview.IntractivePracticeTab(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        i_intractivePracticemainview.failure("" + jObjError.getString("errorMessage"));
                    } catch (Exception e) {
                        i_intractivePracticemainview.failure("" + e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<SubjectwiselistRequest>> call, Throwable t) {
                i_intractivePracticemainview.hideProgress();
            }
        });


    }

    @Override
    public void getChapterList(StudentIdforChapter studentIdforChapter) {
        i_intractivePracticemainview.showProgress();

        Call<List<Chapterwise_list_Response>> chapterwise_list_responseCall = apiInterface.IntractivePracticeAiTabs(studentIdforChapter);
        chapterwise_list_responseCall.enqueue(new Callback<List<com.skyget.counsellor.model.response.Chapterwise_list_Response>>() {
            @Override
            public void onResponse(Call<List<com.skyget.counsellor.model.response.Chapterwise_list_Response>> call, Response<List<com.skyget.counsellor.model.response.Chapterwise_list_Response>> response) {
                i_intractivePracticemainview.hideProgress();
                if (response.isSuccessful()) {
                    i_intractivePracticemainview.getChaptersResponse(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        i_intractivePracticemainview.failure("" + jObjError.getString("errorMessage"));
                    } catch (Exception e) {
                        i_intractivePracticemainview.failure("" + e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<com.skyget.counsellor.model.response.Chapterwise_list_Response>> call, Throwable t) {
                i_intractivePracticemainview.hideProgress();
            }
        });
    }
}
