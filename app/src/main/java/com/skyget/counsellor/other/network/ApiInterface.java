package com.skyget.counsellor.other.network;


import com.skyget.counsellor.model.request.ContactedRequest;
import com.skyget.counsellor.model.request.CreateSalesOfferRequest;
import com.skyget.counsellor.model.request.GetWalletAmountRequest;
import com.skyget.counsellor.model.request.LoginRequest;
import com.skyget.counsellor.model.request.MynewStudentsListResponse;
import com.skyget.counsellor.model.request.StudentFilterRequest;
import com.skyget.counsellor.model.request.StudentIdforChapter;
import com.skyget.counsellor.model.request.TakeStudentRequest;
import com.skyget.counsellor.model.request.UpdateStudentDetailsRequest;
import com.skyget.counsellor.model.response.AllSubscriptionsResponse;
import com.skyget.counsellor.model.response.Chapterwise_list_Response;
import com.skyget.counsellor.model.response.DefaultSuccessResponse;
import com.skyget.counsellor.model.response.FeedBackAlertResponce;
import com.skyget.counsellor.model.response.GetStudentDetailsResponse;
import com.skyget.counsellor.model.response.GetStudentsforSalesmanResponse;
import com.skyget.counsellor.model.response.GetWalletAmountResponse;
import com.skyget.counsellor.model.response.LinkingResponse;
import com.skyget.counsellor.model.response.LoginResponse;
import com.skyget.counsellor.model.response.Post;
import com.skyget.counsellor.model.response.SalesDetailsResponse;
import com.skyget.counsellor.model.response.SalesmanReportsResponse;
import com.skyget.counsellor.model.response.SettlementResponse;
import com.skyget.counsellor.model.request.SubjectwiselistRequest;
import com.skyget.counsellor.model.response.SubscriptionsResponse;
import com.skyget.counsellor.model.response.TakeStudentResponse;
import com.skyget.counsellor.model.response.Update_city_Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("salesman/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("salesman/getstudent/{student_mob_no}")
    Call<GetStudentDetailsResponse> getStudentDetails(@Path("student_mob_no") String student_mob_no);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("subscribe/getSubscription/{std_id}")
    Call<List<SubscriptionsResponse>> getStudentSubscriptionDetails(@Path("std_id") int std_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("admin/getAllSubscriptions")
    Call<List<AllSubscriptionsResponse>> getallStudentSubscriptionDetails();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("salesman/salesOffer/create")
    Call<DefaultSuccessResponse> createSalesOffer(@Body CreateSalesOfferRequest createSalesOfferRequest);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("salesman/salesdetails/{salesman_id}")
    Call<List<SalesDetailsResponse>> getSalesmanSalesDetails(@Path("salesman_id") int salesman_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("salesman/getStudentsforSalesman/{salesman_id}")
    Call<List<GetStudentsforSalesmanResponse>> getStudentsforSalesman(@Path("salesman_id") int salesman_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("salesman/report/{salesman_id}/{mnth_id}")
    Call<SalesmanReportsResponse> getSalesmanReports(@Path("salesman_id") int salesman_id, @Path("mnth_id") int mnth_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("salesman/updateView/{salesman_id}/{std_id}")
    Call<DefaultSuccessResponse> getUpdateView(@Path("salesman_id") int salesman_id, @Path("std_id") int std_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("salesman/linkReport/{salesman_id}/{mnth_id}")
    Call<List<LinkingResponse>> getLinkingViews(@Path("salesman_id") int salesman_id, @Path("mnth_id") int mnth_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("salesman/settlementDetails/{salesman_id}")
    Call<List<SettlementResponse>> getSalesmanSetlementDetails(@Path("salesman_id") int salesman_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("salesman/contactedInformation")
    Call<DefaultSuccessResponse> contacted(@Body ContactedRequest contactedRequest);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("salesman/getwalletGst")
    Call<GetWalletAmountResponse> getWalletAmount(@Body GetWalletAmountRequest contactedRequest);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("salesman/getStudents/0/0")
    Call<List<Post>> getNewStudents();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("salesman/assignStudentsToSalesPerson")
    Call<DefaultSuccessResponse> TakeButtonApi(@Body TakeStudentRequest takeStudentRequest);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("salesman/getStudentsfilter")
    Call<List<Post>> getNewStudents(@Body StudentFilterRequest studentFilterRequest);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("salesman/getStudentsforSalesmanForself/3")
    Call<List<MynewStudentsListResponse>> MynewStudents();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("salesman/giveStudentsfeedback/{student_id}/{selected_id}")
    Call<FeedBackAlertResponce> FeedbackAlert(@Path("student_id") String studentId,
                                              @Path("selected_id") String selectedId);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("salesman/updateStudentDetails/{row_id}")
    Call<Update_city_Response> cityesupdate(@Path("row_id") int row_id, @Body UpdateStudentDetailsRequest studentFilterRequest);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("homepagevideos/video/Crash%20Course/1")
    Call<List<TakeStudentResponse>> PhysicsTab();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("chapterInteractive/getCISubofExamTypeByStu/3365 ")
    Call<List<SubjectwiselistRequest>> IntractivePracticeAiTab();


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("chapterInteractive/CIGetChapterInfo")
    Call<List<Chapterwise_list_Response>> IntractivePracticeAiTabs(@Body StudentIdforChapter studentIdforChapter);


    //  http://skyget-testing-env.eba-w8hdengf.ap-south-1.elasticbeanstalk.com/rest/salesman/getStudentsforSalesmanForself/3



    /*@Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("faculty/getDoubtsFaculty/{fac_id}")
    Call<List<GetAllDoubtsResponse>> getallDoubts(@Path("fac_id") int fac_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("faculty/takeDoubt")
    Call<DefaultSuccessResponse> takeDoubt(@Body TakeDoubtRequest takeDoubtRequest);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("faculty/history/{fac_id}")
    Call<List<GetAllDoubtsResponse>> getallHistoryDoubts(@Path("fac_id") int fac_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("faculty/getfacultyAmount")
    Call<MyAccountResponse> getFacultyAccount(@Body MyAccountRequest myAccountRequest);

    @Multipart
    @POST("uploadfile.php")
    Call<String> uploadImage(
            @Part MultipartBody.Part file, @Part("filename") RequestBody name);*/
}



