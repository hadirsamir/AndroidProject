package com.example.hadirsamir.myshop.Models;

import java.util.ArrayList;

/**
 * Created by hadirsamir on 07/03/18.
 */

public class OrderItem {

    private Integer orderID;
    private Double orderCost;
    private ArrayList<Integer> product_ids = new ArrayList<>();
    private ArrayList<String> product_names = new ArrayList<>();
    private ArrayList<Double> product_prices = new ArrayList<>();

    public ArrayList<String> getProduct_names() {
        return product_names;
    }

    public void setProduct_names(ArrayList<String> product_names) {
        this.product_names = product_names;
    }

    public ArrayList<Double> getProduct_prices() {
        return product_prices;
    }

    public void setProduct_prices(ArrayList<Double> product_prices) {
        this.product_prices = product_prices;
    }

    public ArrayList<Integer> getProduct_ids() {
        return product_ids;
    }

    public void setProduct_ids(ArrayList<Integer> product_ids) {
        this.product_ids = product_ids;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Double getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(Double orderCost) {
        this.orderCost = orderCost;
    }

    public OrderItem(Integer orderID, Double orderCost) {
        this.setOrderID(orderID);
        this.setOrderCost(orderCost);
    }

    public OrderItem(Integer orderID, Double orderCost, ArrayList<Integer> product_ids, ArrayList<String> product_names, ArrayList<Double> product_prices) {
        this.setOrderID(orderID);
        this.setOrderCost(orderCost);
        this.setProduct_ids(product_ids);
        this.setProduct_names(product_names);
        this.setProduct_prices(product_prices);
    }

}
