package com.example.appsales18102021.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appsales18102021.R;
import com.example.appsales18102021.data.model.FoodModel;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<FoodModel> lstFoodModels;
    private OnFoodItemListener onFoodItemListener;
    private Context context;

    public FoodAdapter() {
        lstFoodModels = new ArrayList<>();
    }

    public void updateListFoodModels(List<FoodModel> foodModels) {
        if (lstFoodModels.size() > 0) {
            lstFoodModels.clear();
        }
        lstFoodModels.addAll(foodModels);
        notifyDataSetChanged();
    }


    public List<FoodModel> getList(){
        return lstFoodModels;
    }


    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_food,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        holder.bind(lstFoodModels.get(position),context);

    }

    @Override
    public int getItemCount() {
        return lstFoodModels != null && lstFoodModels.size() > 0 ? lstFoodModels.size() : 0;
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvName,tvPrice;
        Button btnAddCart;

        public FoodViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.imageView);
            tvName = view.findViewById(R.id.textViewName);
            tvPrice = view.findViewById(R.id.textViewPrice);
            btnAddCart = view.findViewById(R.id.buttonAddCart);

            btnAddCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onFoodItemListener != null){
                        onFoodItemListener.onClick(getAdapterPosition());
                    }
                }
            });
        }

        void bind(FoodModel foodModel , Context context) {
            Glide
                    .with(context)
                    .load(foodModel.getImages().get(0).getImageUrl())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(img);

            tvName.setText(foodModel.getFoodName());
            NumberFormat formatter = new DecimalFormat("#,###");
            tvPrice.setText("Giá " + formatter.format(foodModel.getPrice()) + " đ");
        }
    }

    public void setOnFoodItemListener(OnFoodItemListener onFoodItemListener){
        this.onFoodItemListener = onFoodItemListener;
    }

    public interface OnFoodItemListener{
        public void onClick(int position);
    }


}