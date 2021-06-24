package com.skyget.counsellor.presenters.implementations;

import android.content.Context;

import com.skyget.counsellor.model.response.TakeStudentResponse;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.IChemistryTabMainview;
import com.skyget.counsellor.presenters.interfaces.IMathsTabMainview;
import com.skyget.counsellor.presenters.interfaces.IVideosDataPresenter;
import com.skyget.counsellor.presenters.interfaces.IPhysicsTabMainView;
import com.skyget.counsellor.views.fragments.FragmentMaths;

import org.json.JSONObject;

import java.util.List;

import androidx.fragment.app.FragmentActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideosTabDataPresenterImpl implements IVideosDataPresenter {

    IPhysicsTabMainView iPhysicsTabMainView;
    Context i;
    private VideosTabDataPresenterImpl videosTabDataPresenter;


    public VideosTabDataPresenterImpl(IPhysicsTabMainView iPhysicsTabMainView, Context i) {
        this.iPhysicsTabMainView =iPhysicsTabMainView;
        this.i = i;

    }

    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
    IChemistryTabMainview iChemistryTabMainview;
    IMathsTabMainview iMathsTabMainview;


    public VideosTabDataPresenterImpl(IChemistryTabMainview iChemistryTabMainview, FragmentActivity activity) {
        this.iChemistryTabMainview = iChemistryTabMainview;
    }


    public VideosTabDataPresenterImpl(IMathsTabMainview iMathsTabMainview, FragmentActivity activity) {
        this.iMathsTabMainview = iMathsTabMainview;
    }


    @Override
    public void PhysicsTab() {
        iPhysicsTabMainView.showProgress();
        Call<List<TakeStudentResponse>> call = apiInterface.PhysicsTab();
        call.enqueue(new Callback<List<TakeStudentResponse>>() {
            @Override
            public void onResponse(Call<List<TakeStudentResponse>> call, Response<List<TakeStudentResponse>> response) {
                iPhysicsTabMainView.hideProgress();
                if (response.isSuccessful()) {
                    iPhysicsTabMainView.PhysicsTab(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        iPhysicsTabMainView.failure("" + jObjError.getString("errorMessage"));
                    } catch (Exception e) {
                        iPhysicsTabMainView.failure("" + e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<TakeStudentResponse>> call, Throwable t) {
                iPhysicsTabMainView.hideProgress();

            }


        });
    }


    }









