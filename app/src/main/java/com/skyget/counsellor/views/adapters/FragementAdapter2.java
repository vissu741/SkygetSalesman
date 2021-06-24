package com.skyget.counsellor.views.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.skyget.counsellor.model.response.TakeStudentResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FragementAdapter2 extends RecyclerView.Adapter<FragementsAdapter1.MyViewHolder> {

    private Context context;
    List<TakeStudentResponse.Listitem> chapterlistResponces;


    public FragementAdapter2 (List<TakeStudentResponse.Listitem> chapterlistResponces, Context context) {
        this.chapterlistResponces = chapterlistResponces;
        this.context = context;
    }

    @NonNull
    @Override
    public FragementsAdapter1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FragementsAdapter1.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
