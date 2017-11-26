package com.example.uy.sellphone.model;

import java.io.Serializable;

/**
 * Created by UY on 10/17/2017.
 */

public class Product implements Serializable {
    public int ProductID;
    public String ProductName;
    public Integer Price;
    public String ImageProduct;
    public String Detail;
    public int CategoryID;

    public Product(int productID, String productName, Integer price, String imageProduct, String detail, int categoryID) {
        ProductID = productID;
        ProductName = productName;
        Price = price;
        ImageProduct = imageProduct;
        Detail = detail;
        CategoryID = categoryID;
    }

    public int getProductID() {
        return ProductID;
    }

    public Product setProductID(int productID) {
        ProductID = productID;
        return this;
    }

    public String getProductName() {
        return ProductName;
    }

    public Product setProductName(String productName) {
        ProductName = productName;
        return this;
    }

    public Integer getPrice() {
        return Price;
    }

    public Product setPrice(Integer price) {
        Price = price;
        return this;
    }

    public String getImageProduct() {
        return ImageProduct;
    }

    public Product setImageProduct(String imageProduct) {
        ImageProduct = imageProduct;
        return this;
    }

    public String getDetail() {
        return Detail;
    }

    public Product setDetail(String detail) {
        Detail = detail;
        return this;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public Product setCategoryID(int categoryID) {
        CategoryID = categoryID;
        return this;
    }
}
