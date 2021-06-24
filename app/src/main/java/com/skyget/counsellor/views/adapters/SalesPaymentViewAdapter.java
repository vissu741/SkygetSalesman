package com.skyget.counsellor.views.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.skyget.counsellor.R;
import com.skyget.counsellor.model.response.SalesDetailsResponse;
import com.skyget.counsellor.databinding.RowDoubtsViewAllBinding;

import java.util.List;


@SuppressLint("RecyclerView")
public class SalesPaymentViewAdapter extends RecyclerView.Adapter<SalesPaymentViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<SalesDetailsResponse> salesDetailsResponses;
    RowDoubtsViewAllBinding binding;

    private PostClickAdapter listener;

    public SalesPaymentViewAdapter(Context mContext, PostClickAdapter listener, List<SalesDetailsResponse> salesDetailsResponses) {
        this.mContext = mContext;
        this.listener = listener;
        this.salesDetailsResponses = salesDetailsResponses;
    }

    @NonNull
    @Override
    public SalesPaymentViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.row_doubts_view_all, viewGroup, false);
       /* View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_doubts_view_all, viewGroup, false);*/
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final List<SalesDetailsResponse> result = salesDetailsResponses;

        if (result != null) {
            if (result.size() > 0) {

                mContext = myViewHolder.itemView.getContext();

                binding.studentId.setText(result.get(i).studentId + "");

                binding.subName.setText(result.get(i).subscriptionName);

                binding.offerAmount.setText(result.get(i).offerAmount+"");

                binding.total.setText(result.get(i).subscriptionAmount+"");


                binding.iWillTake.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onTakeButtonClicked(result.get(i));
                    }
                });


            }
        }

    }


    @Override
    public int getItemCount() {
        return salesDetailsResponses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        RowDoubtsViewAllBinding binding;


        public MyViewHolder(RowDoubtsViewAllBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface PostClickAdapter {


        void onTakeButtonClicked(SalesDetailsResponse response);

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
