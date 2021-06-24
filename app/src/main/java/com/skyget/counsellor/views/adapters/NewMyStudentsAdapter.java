package com.skyget.counsellor.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.skyget.counsellor.R;
import com.skyget.counsellor.model.request.MynewStudentsListResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class NewMyStudentsAdapter extends RecyclerView.Adapter<NewMyStudentsAdapter.MyViewHolder> {
    private List<MynewStudentsListResponse> Responselist;
    private Context context;
    private FeedbackAlert feedbackAlert;


    public NewMyStudentsAdapter(List<MynewStudentsListResponse> postList, Context context, FeedbackAlert feedbackAlert) {
        this.Responselist = postList;
        this.context = context;
        this.feedbackAlert = feedbackAlert;

    }

    @NonNull
    @Override
    public NewMyStudentsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mynewliststudentsexample, parent, false);
        return new NewMyStudentsAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NewMyStudentsAdapter.MyViewHolder holder, int position) {
        MynewStudentsListResponse post = Responselist.get(position);
        holder.name.setText(post.id + "");
        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (feedbackAlert != null)
                    feedbackAlert.FedBackClick(post.id + "");

            }
        });
        holder.cliking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (feedbackAlert != null)
                    feedbackAlert.clieckHereClick(post.feedBackStates);

            }
        });
        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (feedbackAlert != null)
                    feedbackAlert.Viewclicking((String) post.name, post.city, post.mobile, post.state, post.id);


            }
        });
        holder.videostab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackAlert.videosbutton();


            }
        });


    }

    @Override
    public int getItemCount() {
        return Responselist.size();
    }

    public interface FeedbackAlert {


        void FedBackClick(String studentId);

        void clieckHereClick(String feedBackComments);

        void Viewclicking(String name, String city, String mobile, String state, int id);
        void videosbutton();
    }










    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        Button button1, button2, submit;
        TextView cliking, videostab;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text12);
            button2 = itemView.findViewById(R.id.view);
            button1 = itemView.findViewById(R.id.feedback);
            submit = itemView.findViewById(R.id.btnSubmit);
            cliking = itemView.findViewById(R.id.clicktext);
            videostab = itemView.findViewById(R.id.videoTXT);


        }


    }

}

