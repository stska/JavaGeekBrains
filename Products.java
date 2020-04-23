package com.company;

public class Products implements Comparable<Products>{
    private String productName;
    private String productCost;

    public void setProductName(String productName){
        this.productName = productName;
    }
    public void setProductCost(String productCost){
        this.productCost = productCost;
    }
    public String getProductName(){
        return productName;
    }
    public String getProductCost(){
        return productCost;
    }

    @Override
    public int compareTo(Products products) {
        return productName.compareTo(products.productName);
    }

    @Override
    public String toString() {
        return  this.productName + " " + this.productCost;
    }
}
