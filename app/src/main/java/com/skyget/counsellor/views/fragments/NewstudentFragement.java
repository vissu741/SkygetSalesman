package com.skyget.counsellor.views.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.skyget.counsellor.R;
import com.skyget.counsellor.databinding.FragmentNewstudentsBinding;
import com.skyget.counsellor.model.request.Student;
import com.skyget.counsellor.model.request.StudentFilterRequest;
import com.skyget.counsellor.model.request.TakeStudentRequest;
import com.skyget.counsellor.model.response.DefaultSuccessResponse;
import com.skyget.counsellor.model.response.Post;
import com.skyget.counsellor.other.Constants;
import com.skyget.counsellor.presenters.implementations.NewStudentsDataPresenterImpl;
import com.skyget.counsellor.presenters.interfaces.InewStudentsView;
import com.skyget.counsellor.views.adapters.StudentsListAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

public class NewstudentFragement extends Fragment implements InewStudentsView,
        StudentsListAdapter.onTakeClickListener {
    private FragmentNewstudentsBinding fragmentNewstudentsBinding;
    private StudentsListAdapter studentsListAdapter;
    private NewStudentsDataPresenterImpl newStudentsDataPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentNewstudentsBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_newstudents, container, false);
        newStudentsDataPresenter = new NewStudentsDataPresenterImpl(this, getActivity());
        StudentFilterRequest studentFilterRequest = new StudentFilterRequest();
        studentFilterRequest.setCityId(0);
        studentFilterRequest.setStateId(0);
        studentFilterRequest.setFromdate("");
        studentFilterRequest.setTodate("");
        newStudentsDataPresenter.newstudentsList(studentFilterRequest);
        return fragmentNewstudentsBinding.getRoot();
    }


    @Override
    public void showProgress() {
        fragmentNewstudentsBinding.progressBarStudents.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }

    @Override
    public void hideProgress() {
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        fragmentNewstudentsBinding.progressBarStudents.setVisibility(View.GONE);
    }

    @Override
    public void NewStudentsList(List<Post> resp) {
        if (resp != null) {
            if (resp.size() > 0) {
                fragmentNewstudentsBinding.newStudentsRecyclerview.setVisibility(View.VISIBLE);
                fragmentNewstudentsBinding.noStudentsText.setVisibility(View.GONE);
                fragmentNewstudentsBinding.newStudentsRecyclerview.setHasFixedSize(true);
                fragmentNewstudentsBinding.newStudentsRecyclerview.setLayoutManager
                        (new LinearLayoutManager(getActivity(),
                                LinearLayoutManager.VERTICAL, false));
                studentsListAdapter = new StudentsListAdapter(resp, getActivity(), this::onTakeClick);
                fragmentNewstudentsBinding.newStudentsRecyclerview.setAdapter(studentsListAdapter);
                fragmentNewstudentsBinding.newStudentsRecyclerview.setNestedScrollingEnabled(true);
            } else {
                fragmentNewstudentsBinding.newStudentsRecyclerview.setVisibility(View.GONE);
                fragmentNewstudentsBinding.noStudentsText.setVisibility(View.VISIBLE);
            }
        }


    }

    @Override
    public void failure(String msg) {
        fragmentNewstudentsBinding.newStudentsRecyclerview.setVisibility(View.GONE);
        fragmentNewstudentsBinding.noStudentsText.setVisibility(View.VISIBLE);

    }

    @Override
    public void takeButtonSuccessResponse(DefaultSuccessResponse resp) {
        Toast.makeText(getActivity(), resp.message, Toast.LENGTH_SHORT).show();
        StudentFilterRequest studentFilterRequest = new StudentFilterRequest();
        studentFilterRequest.setCityId(0);
        studentFilterRequest.setStateId(0);
        studentFilterRequest.setFromdate("");
        studentFilterRequest.setTodate("");
        newStudentsDataPresenter.newstudentsList(studentFilterRequest);
    }

    @Override
    public void takeButtonFailure(String erroMessage) {
        Toast.makeText(getActivity(), erroMessage, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onTakeClick(String studentId) {
        SharedPreferences myPrefs = getActivity().getSharedPreferences(Constants.LOGIN_SHARED, MODE_PRIVATE);
        int salesman_id = myPrefs.getInt(Constants.SALESMAN_ID, 0);
        TakeStudentRequest takeStudentRequest = new TakeStudentRequest();
        takeStudentRequest.salesManId = salesman_id + "";
        List<Student> studentList = new ArrayList<>();
        Student student = new Student();
        student.selfassign = true;
        student.studentId = Integer.valueOf(studentId);
        studentList.add(student);
        takeStudentRequest.students = studentList;
        newStudentsDataPresenter.TakeButtonApi(takeStudentRequest);
    }
}
