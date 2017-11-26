package com.example.uy.sellphone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.uy.sellphone.R;
import com.example.uy.sellphone.adapter.CategoryAdapter;
import com.example.uy.sellphone.adapter.ProductAdapter;
import com.example.uy.sellphone.model.Cart;
import com.example.uy.sellphone.model.Category;
import com.example.uy.sellphone.model.Product;
import com.example.uy.sellphone.ultil.CheckConnection;
import com.example.uy.sellphone.ultil.KeyPut;
import com.example.uy.sellphone.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listViewCategory;
    DrawerLayout drawerLayout;
    ArrayList<Category> arraylistCategory;
    CategoryAdapter categoryAdapter;
    ArrayList<Product> arrayListProduct;
    ProductAdapter productAdapter;

    private int categoryid=0;
    private String categoryname="";
    private String imagecategory="";
    private int productid=0;
    private String productname="";
    private Integer price=0;
    private String imageproduct="";
    private String detail="";
    private int catepid =0;

    //mang toan cuc, xai cho tat ca mang hinh
    public static ArrayList<Cart> arraylistCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            //Nut Menu
            ActionBar();
            //Chay quang cao
            ActionViewFlipper();
            //Get du lieu
            GetDataCategory();
            GetDataNewProduct();
            CatListCategory();
        }
        else{
            CheckConnection.ShowToast_Short(getApplicationContext(), "Test your connection again!!");
            finish();
        }

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

    private void CatListCategory() {
        listViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, PhoneActivity.class);
                        intent1.putExtra(KeyPut.CATEGORY_KEY_PUT, arraylistCategory.get(i).getCategoryID());
                        startActivity(intent1);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, LapTopActivity.class);
                        intent2.putExtra(KeyPut.CATEGORY_KEY_PUT, arraylistCategory.get(i).getCategoryID());
                        startActivity(intent2);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(intent3);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        Intent intent4 = new Intent(MainActivity.this, ContactActivity.class);
                        startActivity(intent4);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void GetDataNewProduct() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.LinkNewProduct, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){
                    for(int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            productid = jsonObject.getInt("productid");
                            productname = jsonObject.getString("productname");
                            price = jsonObject.getInt("price");
                            imageproduct = jsonObject.getString("image");
                            detail = jsonObject.getString("detail");
                            catepid = jsonObject.getInt("categoryid");
                            arrayListProduct.add(new Product(productid, productname, price, imageproduct, detail, catepid));
                            productAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetDataCategory() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.LinkCategory, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){ //neu JSON tra ve khac null
                    for(int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            categoryid = jsonObject.getInt("categoryid");
                            categoryname = jsonObject.getString("categoryname");
                            imagecategory = jsonObject.getString("image");
                            arraylistCategory.add(new Category(categoryid,categoryname,imagecategory));
                            categoryAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(), error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        //Mang chua duong dan toi Hinh Anh
        ArrayList<String> arrayListImage = new ArrayList<>();
        arrayListImage.add("https://img.youtube.com/vi/D8Ert5yjMV4/0.jpg");
        arrayListImage.add("https://www.imore.com/sites/imore.com/files/styles/larger_wm_brw/public/field/image/2017/09/iphone-x-wonder-woman-video-hdr.jpg?itok=iuuOCoTF");
        arrayListImage.add("https://farm2.staticflickr.com/1684/25226241865_edafbc672b_b.jpg");
        //gan hinh vao imageview (viewflipper chi nhan imageview chu khong nhan hinh anh truc tiep, nen phai gang')
        for(int i=0;i<arrayListImage.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            //Su dung thu vien picasso de load hinh anh tu internet
            Picasso.with(getApplicationContext()).load(arrayListImage.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //add vao viewflipper
            viewFlipper.addView(imageView);
        }
        //bat su kien cho viewflipper tu chay
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        //Goi animation
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void ActionBar() {
        //Ham ho tro toolbar nhu actionbar
        setSupportActionBar(toolbar);
        //Set cho nut Home cua toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Set lai icon cho nut Home
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        //Bat su kien khi click vao se mo ra thanh Menu
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Mo drawable layout
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void init() {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarHome);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipperQC);
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewNewProduct);
        navigationView = (NavigationView) findViewById(R.id.navigationViewCategory);
        listViewCategory = (ListView) findViewById(R.id.listViewCategory);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayoutHome);
        arraylistCategory = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(arraylistCategory, getApplicationContext());
        listViewCategory.setAdapter(categoryAdapter);
        arrayListProduct = new ArrayList<>();
        productAdapter = new ProductAdapter(getApplicationContext(), arrayListProduct);
        //set fixsize cho recyclerView
        recyclerView.setHasFixedSize(true);
        //ep ve gridview va set cho gridview 2 cot
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(productAdapter);
        if(arraylistCart!=null){

        }else{
            arraylistCart = new ArrayList<>();
        }
    }


}
