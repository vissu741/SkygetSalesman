package com.skyget.counsellor.views.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.skyget.counsellor.R;
import com.skyget.counsellor.databinding.RowDoubtsSalesmanStudentsBinding;
import com.skyget.counsellor.model.response.GetStudentsforSalesmanResponse;

import java.util.List;


@SuppressLint("RecyclerView")
public class  GetAssignedStudentsViewAdapter extends RecyclerView.Adapter<GetAssignedStudentsViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<GetStudentsforSalesmanResponse> getStudentsforSalesmanResponses;
    RowDoubtsSalesmanStudentsBinding binding;

    private PostClickAdapter listener;

    public GetAssignedStudentsViewAdapter(Context mContext, PostClickAdapter listener, List<GetStudentsforSalesmanResponse> getStudentsforSalesmanResponses) {
        this.mContext = mContext;
        this.listener = listener;
        this.getStudentsforSalesmanResponses = getStudentsforSalesmanResponses;
    }

    @NonNull
    @Override
    public GetAssignedStudentsViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.row_doubts_salesman_students, viewGroup, false);
       /* View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_doubts_view_all, viewGroup, false);*/
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final List<GetStudentsforSalesmanResponse> result = getStudentsforSalesmanResponses;

        if (result != null) {
            if (result.size() > 0) {


                mContext = myViewHolder.itemView.getContext();

                //binding.infoTxt.setPaintFlags(binding.infoTxt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                if(result.get(i).comment!=null && !result.get(i).comment.isEmpty())
                {
                    binding.contactButton.setBackgroundDrawable(mContext.getDrawable(R.drawable.button_border_green));
                    binding.contactButton.setText("Contacted");
                }
                else {
                    binding.contactButton.setBackgroundDrawable(mContext.getDrawable(R.drawable.button_border_red));
                    binding.contactButton.setText("Contact");
                }



                binding.studentId.setText(result.get(i).studentId + "");

                binding.studentName.setText(result.get(i).name);
/*
                binding.offerAmount.setText(result.get(i).offerAmount+"");

                binding.total.setText(result.get(i).subscriptionAmount+"");*/


                binding.viewButtonTake.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onTakeButtonClicked(result.get(i), i);
                    }
                });

                binding.contactButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onContactButtonClicked(result.get(i));
                    }
                });


            }
        }

    }


    @Override
    public int getItemCount() {
        return getStudentsforSalesmanResponses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        RowDoubtsSalesmanStudentsBinding binding;


        public MyViewHolder(RowDoubtsSalesmanStudentsBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface  PostClickAdapter {


        void onTakeButtonClicked(GetStudentsforSalesmanResponse response, int i);
        void onContactButtonClicked(GetStudentsforSalesmanResponse response);


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}
