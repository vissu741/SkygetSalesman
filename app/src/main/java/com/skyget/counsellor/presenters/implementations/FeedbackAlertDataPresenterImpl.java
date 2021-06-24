package com.skyget.counsellor.presenters.implementations;

import android.content.Context;

import com.skyget.counsellor.model.response.FeedBackAlertResponce;
import com.skyget.counsellor.model.response.Post;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.IFeedBackAlertDialogPresenter;
import com.skyget.counsellor.presenters.interfaces.IFeedbackMainview;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackAlertDataPresenterImpl  implements IFeedBackAlertDialogPresenter {
    IFeedbackMainview iFeedbackMainview;
    Context i;


    public FeedbackAlertDataPresenterImpl( IFeedbackMainview  iFeedbackMainview, Context i) {
        this.  iFeedbackMainview =   iFeedbackMainview;
        this.i = i;
    }

    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);


    @Override
    public void FeedBackAlort() {

    }

    @Override
    public void FeedBackAlort(FeedBackAlertResponce feedBackAlertResponce) {
        Call<List<Post>> call = apiInterface.getNewStudents( );
        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    IFeedbackMainview.hideProgress(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        iFeedbackMainview.failure("" + jObjError.getString("errorMessage"));
                    } catch (Exception e) {
                        iFeedbackMainview.failure("" + e.getMessage());
                    }

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }

        });
    }
}

