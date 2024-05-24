package com.example.blooddonors;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.example.blooddonors.donorestore.DeletedDonorCallBack;
import com.example.blooddonors.donorestore.DonorStoreImpl;

import java.util.Date;
import java.util.List;
import java.util.Objects;


public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.DonorViewHolder>{
    List<Donor> donors;
    LayoutInflater mInflater;
    Context context;
    public DonorAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public DonorAdapter(List<Donor> donors, Context context) {
        this.donors = donors;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setDonors(List<Donor> donors) {
        this.donors = donors;
    }


    class DonorViewHolder extends RecyclerView.ViewHolder{

        TextView donorNameTextView, BGTextView, donorAddressTextView, donorDeptTextView,donorPhoneTextView,donorLastDonationTextView, timeStampTextView;
        ImageButton imgBtn;

        public DonorViewHolder(@NonNull View itemView) {
            super(itemView);
            donorNameTextView = itemView.findViewById(R.id.donor_name_text_view);
            BGTextView = itemView.findViewById(R.id.donor_BG_text_view);
            donorAddressTextView = itemView.findViewById(R.id.donor_address_text_view);
            donorDeptTextView = itemView.findViewById(R.id.donor_dept_text_view);
            donorPhoneTextView = itemView.findViewById(R.id.donor_phone_text_view);
            donorLastDonationTextView = itemView.findViewById(R.id.donor_lastDonationTime_text_view);
            timeStampTextView = itemView.findViewById(R.id.donor_timestamp_text_view);
            imgBtn = itemView.findViewById(R.id.edit_btn);
        }
        public TextView getDonorNameTextView() {
            return donorNameTextView;
        }

        public TextView getBGTextView() {
            return BGTextView;
        }

        public TextView getDonorAddressTextView() {
            return donorAddressTextView;
        }

        public TextView getDonorDeptTextView() {
            return donorDeptTextView;
        }

        public TextView getDonorPhoneTextView() {
            return donorPhoneTextView;
        }

        public TextView getDonorLastDonationTextView() {
            return donorLastDonationTextView;
        }

        public TextView getTimeStampTextView() {
            return timeStampTextView;
        }

        public ImageButton getImgBtn() {
            return imgBtn;
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
        holder.getDonorAddressTextView().setText(donors.get(position).address);
        holder.getDonorDeptTextView().setText(donors.get(position).department);
        holder.getDonorPhoneTextView().setText(donors.get(position).phone);
        holder.getDonorLastDonationTextView().setText(donors.get(position).lastDonationDate);
        Date date = donors.get(position).timestamp.toDate();
        holder.getTimeStampTextView().setText(date.toString());

        if(Utility.getCurrentUser().getUid().equals(donors.get(position).getDonorId()))holder.getImgBtn().setVisibility(View.VISIBLE);
        holder.getImgBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(context,holder.getImgBtn());
                menu.getMenu().add("Edit");
                menu.getMenu().add("Delete");
                menu.show();
                Donor clickedDonor = donors.get(holder.getAdapterPosition());
                String docId = clickedDonor.getDocId();

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(Objects.equals(menuItem.getTitle(), "Edit")){
                            Intent intent = new Intent(context,DonorDetailsActivity.class);

                            if(docId!=null){
                                intent.putExtra("doc_id",docId);
                            }
                            intent.putExtra("edit",true);
                            ContextCompat.startActivity(context,intent,null);
                            return true;
                        }
                        else if(Objects.equals(menuItem.getTitle(), "Delete")) {
                            DonorStoreImpl.getInstance().deleteDonor(docId, new DeletedDonorCallBack() {
                                @Override
                                public void onDeletedDonor(boolean success) {
                                    if(success){
                                        Utility.showToast(context,"Deleted Successfully");
                                    }
                                    else Utility.showToast(context,"Couldn't delete donor.Try" +
                                            " again later");
                                }
                            });
                        }
                        return false;
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        if(donors == null) return 0;
        return donors.size();
    }
}
