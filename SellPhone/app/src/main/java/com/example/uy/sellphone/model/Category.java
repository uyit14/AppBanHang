package com.example.uy.sellphone.model;

/**
 * Created by UY on 10/17/2017.
 */

public class Category {
    public int CategoryID;
    //private String str = "F:\\HOC\\AppBanHang\\Image\\";
    public String CategoryName;
    public String Image;

    public Category(int categoryID, String categoryName, String image) {
        CategoryID = categoryID;
        CategoryName = categoryName;
        Image = image;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public Category setCategoryID(int categoryID) {
        CategoryID = categoryID;
        return this;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public Category setCategoryName(String categoryName) {
        CategoryName = categoryName;
        return this;
    }

    public String getImage() {
        return Image;
    }

    public Category setImage(String image) {
        Image = image;
        return this;
    }
}
