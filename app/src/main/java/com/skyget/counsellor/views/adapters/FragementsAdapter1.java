package com.skyget.counsellor.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skyget.counsellor.R;
import com.skyget.counsellor.model.response.TakeStudentResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class FragementsAdapter1 extends RecyclerView.Adapter<FragementsAdapter1.MyViewHolder> {

    private Context context;
    List<TakeStudentResponse.Listitem> ChapterlistResponces;


    public FragementsAdapter1(List<TakeStudentResponse.Listitem> chapterlistResponces, Context mcontext) {
        this.ChapterlistResponces = chapterlistResponces;
        this.context = mcontext;
    }

    @NonNull
    @Override
    public FragementsAdapter1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerviewdesginlayout, parent, false);
        return new FragementsAdapter1.MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull FragementsAdapter1.MyViewHolder holder, int position) {
        TakeStudentResponse.Listitem chapterlistResponse = ChapterlistResponces.get(position);

        holder.tv_chapter1.setText(chapterlistResponse.chaptername);


       // Glide.with(holder.Iview1).load(chapterlistResponse.videosForUnitWise.get(0).tumbNail).into(holder.Iview1);

      /*  Glide.with(context)
                .load(chapterlistResponse)
                .placeholder(R.drawable.ic_video_place_holder)
                .into(imageView);
        //holder.tv_chapter2.setText(chapterlistResponse.chaptername);
        //Glide.with(holder.Iview2).load(chapterlistResponse.videosForUnitWise.get(1).tumbNail).into(holder.Iview2);
          Glide.with(holder.Iview2).load( chapterlistResponse.videosForUnitWise.get(0).tumbNail).into(holder.Iview1);
*/
    }

    @Override
    public int getItemCount() {
        return ChapterlistResponces != null ? ChapterlistResponces.size() : 0;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_chapter1;
      //  ImageView Iview1;
        RecyclerView HarirzontalRV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            tv_chapter1 = itemView.findViewById(R.id.Physicschaptername1);


            HarirzontalRV=itemView.findViewById(R.id.HarizontalRv);

        }
    }


}

