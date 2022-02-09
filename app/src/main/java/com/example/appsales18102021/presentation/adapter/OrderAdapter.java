package com.example.appsales18102021.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appsales18102021.R;
import com.example.appsales18102021.data.model.FoodModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<FoodModel> lstFoodModel;
    private OnListenerCartItem onListenerCartItem;

    public OrderAdapter() {

    }

    public void updateCart(List<FoodModel> lstFoodModel) {
        if (this.lstFoodModel != null && this.lstFoodModel.size() > 0 ){
            this.lstFoodModel.clear();
        }
        this.lstFoodModel = lstFoodModel;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.bind(lstFoodModel.get(position));
    }

    @Override
    public int getItemCount() {
        return lstFoodModel == null ? 0 : lstFoodModel.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        ImageView img, imgDelete;
        TextView tvName,tvPrice,tvQuantity;

        public OrderViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageViewOrder);
            imgDelete = itemView.findViewById(R.id.imageDelete);
            tvName = itemView.findViewById(R.id.textViewName);
            tvPrice = itemView.findViewById(R.id.textViewPrice);
            tvQuantity = itemView.findViewById(R.id.textviewQuantity);
        }

        void bind(FoodModel foodModel) {
            //Image
            Glide.with(itemView)
                    .load(foodModel.getImages().get(0).getImageUrl())
                    .into(img);
            tvName.setText(foodModel.getFoodName());
            NumberFormat formatter = new DecimalFormat("#,###");
            tvPrice.setText("Giá : " + formatter.format(foodModel.getPrice()));
            tvQuantity.setText(foodModel.getQuantity() + "");
        }
    }

    public void setOnListenerCartItem(OnListenerCartItem onListenerCartItem) {
        this.onListenerCartItem = onListenerCartItem;
    }

    public interface OnListenerCartItem {
        void onPlus(int position);

        void onMinus(int position);

        void onDelete(int position);
    }
}
