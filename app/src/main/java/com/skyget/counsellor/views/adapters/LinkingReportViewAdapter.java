package com.skyget.counsellor.views.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.skyget.counsellor.R;
import com.skyget.counsellor.databinding.RowSalesmanLinkingReportsBinding;
import com.skyget.counsellor.model.request.SubjectwiselistRequest;
import com.skyget.counsellor.model.response.LinkingResponse;
import com.skyget.counsellor.views.fragments.DynamicFragment;

import java.util.List;


@SuppressLint("RecyclerView")
public class LinkingReportViewAdapter extends RecyclerView.Adapter<LinkingReportViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<LinkingResponse>  salesDetailsResponses;
    RowSalesmanLinkingReportsBinding binding;

    private PostClickAdapter listener;

    public LinkingReportViewAdapter(Context mContext, PostClickAdapter listener, List<LinkingResponse> salesDetailsResponses) {
        this.mContext = mContext;
        this.listener = listener;
        this.salesDetailsResponses = salesDetailsResponses;
    }



    @NonNull
    @Override
    public LinkingReportViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.row_salesman_linking_reports, viewGroup, false);
       /* View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_doubts_view_all, viewGroup, false);*/
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final List<LinkingResponse> result = salesDetailsResponses;

        if (result != null) {
            if (result.size() > 0) {

                mContext = myViewHolder.itemView.getContext();

                binding.id.setText(result.get(i).id + "");
                binding.supervisorId.setText(result.get(i).supervisor + "");
                binding.salesmanId.setText(result.get(i).salesmanId + "");
                binding.salesmanAmount.setText(result.get(i).amount + "");
                binding.supervisorAmt.setText(result.get(i).superVisorAmount + "");

                if (result.get(i).paid) {
                    binding.paidStatus.setText("Paid");
                } else {
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
        return salesDetailsResponses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        RowSalesmanLinkingReportsBinding binding;


        public MyViewHolder(RowSalesmanLinkingReportsBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface PostClickAdapter {


        void onTakeButtonClicked(LinkingResponse response);

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
