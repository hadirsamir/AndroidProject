package com.example.hadirsamir.myshop.JsonClasses;

import java.util.ArrayList;

/**
 * Created by hadirsamir on 26/02/18.
 */
public class Location {
    private int id;
    private String BrandName;
    private ArrayList<Branch>  BranchesNames;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public ArrayList<Branch> getBranchesNames() {
        return BranchesNames;
    }

    public void setBranchesNames(ArrayList<Branch> branchesNames) {
        BranchesNames = branchesNames;
    }
}