package com.skyget.counsellor.presenters.interfaces;


import com.skyget.counsellor.model.response.GetStudentsforSalesmanResponse;

import java.util.List;

public interface IGetStudentsforSalesmanMainView {

    void showProgress();

    void hideProgress();

    void getResultStudentsforSalesman(List<GetStudentsforSalesmanResponse> resp);

    void failure(String msg);


}
