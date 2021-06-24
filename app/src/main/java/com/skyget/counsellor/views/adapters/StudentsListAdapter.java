package com.skyget.counsellor.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.skyget.counsellor.model.response.Post;
import com.skyget.counsellor.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentsListAdapter extends RecyclerView.Adapter<StudentsListAdapter.MyViewHolder> {
    private List<Post> postList;
    private Context context;
    private onTakeClickListener onTakeClickListener;

    public StudentsListAdapter(List<Post> postList, Context context,
                               StudentsListAdapter.onTakeClickListener onTakeClickListener) {
        this.postList = postList;
        this.context = context;
        this.onTakeClickListener = onTakeClickListener;
    }


    @NonNull
    @Override
    public StudentsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.desgine, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StudentsListAdapter.MyViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.name.setText(post.name);
        holder.studentid.setText(post.studentid+"");
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onTakeClickListener!=null)
                    onTakeClickListener.onTakeClick(post.studentid.toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        Button button;
        TextView studentid;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Text1);
            button = itemView.findViewById(R.id.btn);
            studentid = itemView.findViewById(R.id.text2);

        }
    }
    public interface onTakeClickListener{
       void onTakeClick(String studentId);
    }
}

