package com.example.uy.sellphone.ultil;

/**
 * Created by UY on 10/17/2017.
 */

public class Server {
    private static String localhost = "192.168.156.87:81";
    public static String LinkImage = "http://" + localhost + "/Image/";
    public static String LinkCategory = "http://" + localhost +"/server/getcategory.php";
    public static String LinkNewProduct = "http://" + localhost + "/server/getnewproduct.php";
    public static String LinkProduct = "http://" + localhost + "/server/getproductbycateid.php?page=";
    public static String LinkBill = "http://" + localhost + "/server/insertbill.php";
    public static String LinkBillDetail = "http://" + localhost + "/server/billdetail.php";
}
