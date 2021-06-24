package com.skyget.counsellor.presenters.implementations;

import android.content.Context;

import com.skyget.counsellor.model.request.StudentFilterRequest;
import com.skyget.counsellor.model.request.TakeStudentRequest;
import com.skyget.counsellor.model.response.DefaultSuccessResponse;
import com.skyget.counsellor.model.response.GetStudentDetailsResponse;
import com.skyget.counsellor.model.response.Post;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.INewstudentsPresenter;
import com.skyget.counsellor.presenters.interfaces.InewStudentsView;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewStudentsDataPresenterImpl implements INewstudentsPresenter {
    InewStudentsView inewStudentsView;  
    Context i;


    public NewStudentsDataPresenterImpl(InewStudentsView inewStudentsView, Context i) {
        this.inewStudentsView = inewStudentsView;
        this.i = i;
    }

    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);


    @Override
    public void newstudentsList(StudentFilterRequest studentFilterRequest) {
        inewStudentsView.showProgress();
        Call<List<Post>> call = apiInterface.getNewStudents(studentFilterRequest);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                inewStudentsView.hideProgress();
                if (response.isSuccessful()) {
                    inewStudentsView.NewStudentsList(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        inewStudentsView.failure("" + jObjError.getString("errorMessage"));
                    } catch (Exception e) {
                        inewStudentsView.failure("" + e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                inewStudentsView.hideProgress();
            }
        });

    }

    @Override
    public void TakeButtonApi(TakeStudentRequest takeStudentRequest) {
        inewStudentsView.showProgress();
        Call<DefaultSuccessResponse> defaultSuccessResponseCall = apiInterface.TakeButtonApi(takeStudentRequest);
        defaultSuccessResponseCall.enqueue(new Callback<DefaultSuccessResponse>() {
            @Override
            public void onResponse(Call<DefaultSuccessResponse> call, Response<DefaultSuccessResponse> response) {
                inewStudentsView.hideProgress();
                if (response.isSuccessful()) {
                    inewStudentsView.takeButtonSuccessResponse(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        inewStudentsView.takeButtonFailure("" + jObjError.getString("errorMessage"));
                    } catch (Exception e) {
                        inewStudentsView.failure("" + e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<DefaultSuccessResponse> call, Throwable t) {
                inewStudentsView.hideProgress();
            }
        });

    }


}