package com.example.uy.sellphone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.uy.sellphone.R;
import com.example.uy.sellphone.model.Cart;
import com.example.uy.sellphone.model.Product;
import com.example.uy.sellphone.ultil.KeyPut;
import com.example.uy.sellphone.ultil.Server;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class DetailActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    ImageView imageView;
    Spinner spinner;
    Button btnAddcocart;
    TextView txtName, txtPrice, txtDetail;
    //
    int productid=0;
    String productname = "";
    Integer price = 0;
    String img = "";
    String detail="";
    int categoryid=0;
    //
    boolean exists = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        ActionToolBar();
        GetInformationProduct();
        SetInformationProduct();
        CatchEventSpinner();
        AddtoCartClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menucart:
                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void AddtoCartClick() {
        btnAddcocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //neu co du lieu roi thi + them vao
                if(MainActivity.arraylistCart.size()>0){
                    int num = Integer.parseInt(spinner.getSelectedItem().toString());
                    //kiem tra trong mang xem co ton tai khong (da them vao gio hang truoc do)
                    for(int i=0;i<MainActivity.arraylistCart.size();i++){
                        //neu id cua 1 thang trong gio hang trung vs id 1 thang o ngoai thi + them vao
                        if(MainActivity.arraylistCart.get(i).getProductid()==productid){
                            //set so luong hien tai = song luong cu + so luong trong spinner
                            MainActivity.arraylistCart.get(i).setTotal(MainActivity.arraylistCart.get(i).getTotal()+num);
                            if(MainActivity.arraylistCart.get(i).getTotal()>10){
                                MainActivity.arraylistCart.get(i).setTotal(10);
                            }
                            int numb = MainActivity.arraylistCart.get(i).getTotal();
                            MainActivity.arraylistCart.get(i).setPrice(price * numb);
                            exists = true;
                        }
                    }
                    //neu san pham trong gio hang khong giong sp sap mua thi them moi
                    if(exists==false){
                        int number = Integer.parseInt(spinner.getSelectedItem().toString());
                        int totalPrice = number * price;
                        MainActivity.arraylistCart.add(new Cart(productid, productname, totalPrice, img, number));
                    }
                }

                //neu chua cho du lieuj thi them moi
                else{
                    int number = Integer.parseInt(spinner.getSelectedItem().toString());
                    int totalPrice = number * price;
                    MainActivity.arraylistCart.add(new Cart(productid, productname, totalPrice, img, number));
                }
                //chuyen mang hinh
                Intent intent = new Intent(DetailActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer [] total = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, total);
        spinner.setAdapter(arrayAdapter);
    }


    private void GetInformationProduct() {
        Product product = (Product) getIntent().getSerializableExtra(KeyPut.PRODUCT_KEY_PUT);
        productid = product.getProductID();
        productname = product.getProductName();
        price = product.getPrice();
        img = product.getImageProduct();
        detail = product.getDetail();
        categoryid = product.getCategoryID();
        //set text
    }
    //
    private void SetInformationProduct() {
        txtName.setText(productname);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtPrice.setText("Price: " +decimalFormat.format(price)+ "VND");
        txtDetail.setText(detail);
        Picasso.with(getApplicationContext()).load(Server.LinkImage+img)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.error)
                .into(imageView);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void init() {
        toolbar = findViewById(R.id.toolbardetail);
        imageView = findViewById(R.id.imgproduct_detail);
        spinner = findViewById(R.id.spinner_detail);
        btnAddcocart = findViewById(R.id.btn_addtocard_detail);
        txtName = findViewById(R.id.txtproductname_detail);
        txtPrice = findViewById(R.id.txtproductprice_detail);
        txtDetail = findViewById(R.id.txtdetail_detailproduct);
    }
}
