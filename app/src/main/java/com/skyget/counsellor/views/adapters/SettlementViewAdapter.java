package com.skyget.counsellor.views.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.skyget.counsellor.R;
import com.skyget.counsellor.databinding.RowSettlementViewAllBinding;
import com.skyget.counsellor.model.response.SettlementResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@SuppressLint("RecyclerView")
public class SettlementViewAdapter extends RecyclerView.Adapter<SettlementViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<SettlementResponse> salesDetailsResponses;
    RowSettlementViewAllBinding binding;

    private PostClickAdapter listener;

    public SettlementViewAdapter(Context mContext, PostClickAdapter listener, List<SettlementResponse> salesDetailsResponses) {
        this.mContext = mContext;
        this.listener = listener;
        this.salesDetailsResponses = salesDetailsResponses;
    }

    @NonNull
    @Override
    public SettlementViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.row_settlement_view_all, viewGroup, false);
       /* View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_doubts_view_all, viewGroup, false);*/
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final List<SettlementResponse> result = salesDetailsResponses;

/*
        if (result != null) {
            if (result.size() > 0) {

                mContext = myViewHolder.itemView.getContext();

                binding.createDate.setText(result.get(i).insertedDate+"");

                binding.salesmanName.setText(result.get(i).salesmanName);

                binding.salesmanId.setText(result.get(i).salesmanId + "");

                binding.settlementPeriod.setText(result.get(i).peroid + "");

                binding.uploadProof.setText(result.get(i).image + "");

                binding.comments.setText(result.get(i).comment + "");


                binding.iWillTake.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onTakeButtonClicked(result.get(i));
                    }
                });


            }
        }
*/
        if (result != null) {
            if (result.size() > 0) {

                mContext = myViewHolder.itemView.getContext();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Date date = null;
                try {
                    long timestamp = result.get(i).insertedDate; //Example -> in ms
                    date = new Date(timestamp);//.getTime());
                    binding.createDate.setText(formatter.format(date) + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }


                binding.salesmanName.setText(result.get(i).salesmanName);

                binding.salesmanId.setText(result.get(i).salesmanId + "");

                binding.settlementPeriod.setText(result.get(i).peroid + "");

                //binding.uploadProof.setText(result.get(i).image + "");

                binding.comments.setText(result.get(i).comment + "");

                final String encodedString_faculty = result.get(i).image;

                if (encodedString_faculty != null && encodedString_faculty != "") {

                    RequestOptions options = new RequestOptions()
                            .centerCrop()
                            .placeholder(R.drawable.ic_placeholder)
                            .error(R.drawable.ic_placeholder);


                    Glide.with(mContext).load(encodedString_faculty).apply(options).into(binding.uploadProof);

                } else {

                    Glide.with(mContext)
                            .load("")
                            .apply(new RequestOptions().placeholder(R.drawable.ic_placeholder).override(15, 15))
                            .into(binding.uploadProof);

                }

                binding.uploadProof.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onTakeButtonClicked(result.get(i).image);
                    }
                });

                binding.comments.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onViewClicked(result.get(i).comment);
                    }
                });

                binding.createDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!binding.createDate.getText().toString().trim().isEmpty()) {
                            listener.onViewClicked(binding.createDate.getText().toString().trim());
                        } else {

                        }

                    }
                });

                binding.settlementPeriod.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        listener.onViewClicked(result.get(i).peroid);

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


        RowSettlementViewAllBinding binding;


        public MyViewHolder(RowSettlementViewAllBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

        }
    }

    public interface PostClickAdapter {

        void onTakeButtonClicked(String image);

        void onViewClicked(String image);


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
