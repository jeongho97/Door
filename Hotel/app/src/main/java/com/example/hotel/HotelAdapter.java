package com.example.hotel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.CustomViewHolder> {

    private ArrayList<HotelData> arrayList;

    public HotelAdapter(ArrayList<HotelData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public HotelAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_list,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotelAdapter.CustomViewHolder holder, int position) {
        holder.hotelName.setText(arrayList.get(position).getHotelName());
        holder.hotelAddress.setText(arrayList.get(position).getHotelAddress());
        holder.hotelNumber.setText(arrayList.get(position).getHotelNumber());
        holder.hotelCapacity.setText(arrayList.get(position).getHotelCapacity());
        holder.hotelCheckIn.setText(arrayList.get(position).getHotelCheckIn());
        holder.hotelCheckOut.setText(arrayList.get(position).getHotelCheckOut());
        holder.hotelPrice.setText(arrayList.get(position).getHotelPrice());
        holder.hotelUserID.setText(arrayList.get(position).getHotelUserID());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curName=holder.hotelName.getText().toString();
                Toast.makeText(v.getContext(),curName,Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition());


                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null !=arrayList ? arrayList.size() : 0);
    }

    public void remove(int position){
        try{
            arrayList.remove(position);
            notifyItemRemoved(position);

        }catch(IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView hotelName;
        protected TextView hotelAddress;
        protected TextView hotelNumber;
        protected TextView hotelCapacity;
        protected TextView hotelCheckIn;
        protected TextView hotelCheckOut;
        protected TextView hotelPrice;
        protected TextView hotelUserID;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.hotelName =itemView.findViewById(R.id.hotelName);
            this.hotelAddress =itemView.findViewById(R.id.hotelAddress);
            this.hotelNumber =itemView.findViewById(R.id.hotelNumber);
            this.hotelCapacity =itemView.findViewById(R.id.hotelCapacity);
            this.hotelCheckIn =itemView.findViewById(R.id.hotelCheckIn);
            this.hotelCheckOut =itemView.findViewById(R.id.hotelCheckOut);
            this.hotelPrice = itemView.findViewById(R.id.hotelPrice);
            this.hotelUserID =itemView.findViewById(R.id.hotelUserID);
        }
    }
}
