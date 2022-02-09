package com.example.appsales18102021.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderModel {

    private Integer total;
    @SerializedName("items")
    @Expose
    private List<FoodModel> items = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<FoodModel> getItems() {
        return items;
    }

    public void setItems(List<FoodModel> items) {
        this.items = items;
    }

}
