package com.skyget.counsellor.views.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.skyget.counsellor.R;
import com.skyget.counsellor.model.response.SalesDetailsResponse;
import com.skyget.counsellor.model.response.SalesmanReportsResponse;
import com.skyget.counsellor.databinding.RowSalesmanReportsBinding;

import java.util.List;


@SuppressLint("RecyclerView")
public class SalesmanReportViewAdapter extends RecyclerView.Adapter<SalesmanReportViewAdapter.MyViewHolder> {

    private Context mContext;
    private SalesmanReportsResponse salesDetailsResponses;
    RowSalesmanReportsBinding binding;

    private PostClickAdapter

            listener;

    public SalesmanReportViewAdapter(Context mContext, PostClickAdapter listener, SalesmanReportsResponse salesDetailsResponses) {
        this.mContext = mContext;
        this.listener = listener;
        this.salesDetailsResponses = salesDetailsResponses;
    }

    @NonNull
    @Override
    public SalesmanReportViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.row_salesman_reports, viewGroup, false);
       /* View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_doubts_view_all, viewGroup, false);*/
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final List<SalesmanReportsResponse.Salesinfo> result = salesDetailsResponses.salesinfo;

        if (result != null) {
            if (result.size() > 0) {

                mContext = myViewHolder.itemView.getContext();

                binding.studentId.setText(result.get(i).studentId + "");
                binding.paidAmount.setText(result.get(i).paidAmount+"");
                binding.percentage.setText(result.get(i).paidpercentage + "%");
                binding.salesmanAmount.setText(result.get(i).salesManAmount + "");

                if(result.get(i).paid )
                {
                    binding.paidStatus.setText("Paid");
                }
                else
                {
                    binding.paidStatus.setText("Pending");
                }


              /*  binding.iWillTake.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onTakeButtonClicked(result.get(i));
                    }
                });*/


            }
        }

    }


    @Override
    public int getItemCount() {
        return salesDetailsResponses.salesinfo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        RowSalesmanReportsBinding binding;


        public MyViewHolder(RowSalesmanReportsBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface  PostClickAdapter {


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
