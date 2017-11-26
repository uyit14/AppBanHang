package com.example.uy.sellphone.model;

/**
 * Created by UY on 10/22/2017.
 */

public class Cart {
    private int productid;
    private String productname;
    private Integer price;
    private String image;
    private int total;

    public Cart(int productid, String productname, Integer price, String image, int total) {
        this.productid = productid;
        this.productname = productname;
        this.price = price;
        this.image = image;
        this.total = total;
    }

    public int getProductid() {
        return productid;
    }

    public Cart setProductid(int productid) {
        this.productid = productid;
        return this;
    }

    public String getProductname() {
        return productname;
    }

    public Cart setProductname(String productname) {
        this.productname = productname;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Cart setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Cart setImage(String image) {
        this.image = image;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public Cart setTotal(int total) {
        this.total = total;
        return this;
    }
}
