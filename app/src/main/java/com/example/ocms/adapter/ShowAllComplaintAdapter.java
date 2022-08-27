package com.example.ocms.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ocms.OpenComplaintActivity;
import com.example.ocms.R;
import com.example.ocms.model.complaint;

import java.util.ArrayList;
import java.util.List;

public class ShowAllComplaintAdapter extends RecyclerView.Adapter<ShowAllComplaintAdapter.viewholder> {

    private Context mContext;
    private List<complaint> mList = new ArrayList<>();

    public ShowAllComplaintAdapter(Context mContext, List<complaint> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.complaint_list_item, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        complaint complaint = mList.get(position);
        Glide.with(mContext).load(complaint.getImage()).into(holder.iv_complaintImage);
        holder.tv_department.setText("Water");
        holder.tv_description.setText(complaint.getDescription());
        holder.tv_status.setText("status : " + "pending");
        holder.tv_reply.setText("reply : " + "pending");
        holder.complaintCard.setOnClickListener(v -> {
            mContext.startActivity(new Intent(mContext, OpenComplaintActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        ImageView iv_complaintImage;
        TextView tv_department, tv_description, tv_status, tv_reply;
        CardView complaintCard;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            iv_complaintImage = itemView.findViewById(R.id.iv_complaintImage);
            tv_department = itemView.findViewById(R.id.tv_department);
            tv_description = itemView.findViewById(R.id.tv_complaintDescription);
            tv_status = itemView.findViewById(R.id.tv_complaintStatus);
            tv_reply = itemView.findViewById(R.id.tv_complaintReply);
            complaintCard = itemView.findViewById(R.id.complaintCard);
        }
    }
}
