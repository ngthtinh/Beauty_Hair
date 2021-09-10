package com.example.beautyhair.adapter;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beautyhair.CustomerBookingActivity;
import com.example.beautyhair.R;
import com.example.beautyhair.SignInActivity;
import com.example.beautyhair.data.model.Shop;
import com.example.beautyhair.data.model.User;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private Context mContext;
    private List<Shop> mShops;

    public ShopAdapter(Context mContext, List<Shop> mShops)
    {
        this.mContext = mContext;
        this.mShops = mShops;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.shop_item, parent, false);
        return new ShopAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Shop shop = mShops.get(position);
        holder.shopname.setText(shop.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent customerBookingActivity = new Intent(mContext, CustomerBookingActivity.class);
                customerBookingActivity.putExtra("shop_phone", shop.getPhone());
                mContext.startActivity(customerBookingActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mShops.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView shopname;
        public ImageView shoplogo;

        public ViewHolder(View itemView){
            super(itemView);

            shopname = itemView.findViewById(R.id.shopname);
            shoplogo = itemView.findViewById(R.id.shoplogo);
        }
    }
}
