package com.example.blooddonors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;


public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.DonorViewHolder>{
    List<Donor> donors;
    LayoutInflater mInflater;
    public DonorAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public DonorAdapter(List<Donor> donors, Context context) {
        this.donors = donors;
        this.mInflater = LayoutInflater.from(context);
    }
    public void setDonors(List<Donor> donors) {
        this.donors = donors;
    }


    class DonorViewHolder extends RecyclerView.ViewHolder{

        TextView donorNameTextView, BGTextView, timeStampTextView;

        public DonorViewHolder(@NonNull View itemView) {
            super(itemView);
            donorNameTextView = itemView.findViewById(R.id.donor_name_text_view);
            BGTextView = itemView.findViewById(R.id.donor_BG_text_view);
            timeStampTextView = itemView.findViewById(R.id.donor_timestamp_text_view);
        }
        public TextView getDonorNameTextView() {
            return donorNameTextView;
        }

        public TextView getBGTextView() {
            return BGTextView;
        }

        public TextView getTimeStampTextView() {
            return timeStampTextView;
        }
    }
    @NonNull
    @Override
    public DonorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_donor_item,viewGroup,false);
        return new DonorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonorViewHolder holder, int position) {
        holder.getDonorNameTextView().setText(donors.get(position).name);
        holder.getBGTextView().setText(donors.get(position).bloodGrp);
        holder.getTimeStampTextView().setText(donors.get(position).timestamp.toString());
    }

    @Override
    public int getItemCount() {
        if(donors == null) return 0;
        return donors.size();
    }
}
