package com.example.hadirsamir.myshop.JsonClasses;

import java.io.Serializable;

/**
 * Created by hadirsamir on 05/03/18.
 */

public class Reviews implements Serializable {
    private String UserName;
    private double UserProductRating;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public double getUserProductRating() {
        return UserProductRating;
    }

    public void setUserProductRating(double userProductRating) {
        UserProductRating = userProductRating;
    }
}
