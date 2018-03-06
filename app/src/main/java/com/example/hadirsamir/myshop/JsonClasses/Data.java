package com.example.hadirsamir.myshop.JsonClasses;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hadirsamir on 05/03/18.
 */

public class Data implements Serializable {
    private String ProductId;
    private String ProductName;
    private String ProductImage;
    private int ProductPrice;
    private int ProductType;
    private int ProductGender;
    private String CompanyID;
    private ArrayList<Reviews>ReviewsList;

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public int getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(int productPrice) {
        ProductPrice = productPrice;
    }

    public int getProductType() {
        return ProductType;
    }

    public void setProductType(int productType) {
        ProductType = productType;
    }

    public int getProductGender() {
        return ProductGender;
    }

    public void setProductGender(int productGender) {
        ProductGender = productGender;
    }

    public String getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(String companyID) {
        CompanyID = companyID;
    }

    public ArrayList<Reviews> getReviewsList() {
        return ReviewsList;
    }

    public void setReviewsList(ArrayList<Reviews> reviewsList) {
        ReviewsList = reviewsList;
    }
}
