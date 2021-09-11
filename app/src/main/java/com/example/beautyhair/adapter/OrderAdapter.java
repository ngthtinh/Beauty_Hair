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

import com.example.beautyhair.R;
import com.example.beautyhair.ShopkeeperOrderDetailsActivity;
import com.example.beautyhair.data.model.Order;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context mContext;
    private List<Order> mOrders;

    public OrderAdapter(Context mContext, List<Order> mOrders)
    {
        this.mContext = mContext;
        this.mOrders = mOrders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_item, parent, false);
        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = mOrders.get(position);
        holder.ordername.setText("Khách hàng " + order.getCustomer());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shopkeeperOrderDetailsActivity = new Intent(mContext, ShopkeeperOrderDetailsActivity.class);
                shopkeeperOrderDetailsActivity.putExtra("order_id", order.getId());
                mContext.startActivity(shopkeeperOrderDetailsActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView ordername;
        public ImageView customerlogo;

        public ViewHolder(View itemView){
            super(itemView);

            ordername = itemView.findViewById(R.id.ordername);
            customerlogo = itemView.findViewById(R.id.customerlogo);
        }
    }
}
