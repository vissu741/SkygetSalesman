package com.skyget.counsellor.views.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skyget.counsellor.R;
import com.skyget.counsellor.model.request.SubjectwiselistRequest;
import com.skyget.counsellor.model.response.Chapterwise_list_Response;
import com.skyget.counsellor.model.response.LinkingResponse;
import com.skyget.counsellor.views.fragments.DynamicFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class IntractivepracticeAdapter extends RecyclerView.Adapter<IntractivepracticeAdapter.MyViewHolder> {

    RecyclerView mrecyclerview;
    private Context mcontext;
    private int i;
    private List<Chapterwise_list_Response> chapterwise_list_responses;


    public IntractivepracticeAdapter(Context mcontext, List<Chapterwise_list_Response> chapterwise_list_responses) {

        this.mcontext = mcontext;

        this.chapterwise_list_responses = chapterwise_list_responses;
    }



    @NonNull
    @Override
    public IntractivepracticeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                     int viewType) {

        View view = LayoutInflater.from(mcontext).inflate(R.layout.intractive_practice_desgine_layot, parent, false);
        return new IntractivepracticeAdapter.MyViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull IntractivepracticeAdapter.MyViewHolder holder, int position) {
        Chapterwise_list_Response chapterwise_list_response =  chapterwise_list_responses.get(position);
        holder.tv1.setText(chapterwise_list_responses.get(position).chapter);


    }

    @Override
    public int getItemCount() {

        return chapterwise_list_responses == null ? 0 :chapterwise_list_responses.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2, tv3, tv4, tv5;
        RecyclerView HarirzontalRV;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.Cardtext1);
            tv2 = itemView.findViewById(R.id.Cardtext2);
            tv3 = itemView.findViewById(R.id.Cardtext3);
            tv4 = itemView.findViewById(R.id.Cardtext4);
            tv5 = itemView.findViewById(R.id.Cardtext5);
            HarirzontalRV = itemView.findViewById(R.id.Ineractive_recyclerview);

        }
    }
}
