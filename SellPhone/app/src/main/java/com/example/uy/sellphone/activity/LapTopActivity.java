package com.example.uy.sellphone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.uy.sellphone.R;
import com.example.uy.sellphone.adapter.LapTopAdapter;
import com.example.uy.sellphone.model.Product;
import com.example.uy.sellphone.ultil.CheckConnection;
import com.example.uy.sellphone.ultil.KeyPut;
import com.example.uy.sellphone.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LapTopActivity extends AppCompatActivity {

    LapTopAdapter lapTopAdapter;
    ArrayList<Product> products;
    mHandler mHandler;
    android.support.v7.widget.Toolbar toolbarLapTop;
    ListView listviewLapTop;
    View footer;
    boolean isLoading = false;
    boolean limitdata = false;
    int cateid=0;
    int page = 1;
    private int productid=0;
    private String productname="";
    private Integer price=0;
    private String image="";
    private String detail="";
    private int categoryid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_top);
        intit();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            GetCategoryID();
            ActionToolbar();
            GetDaTaLapTop(page);
            LoadMoreData();
        }
        else{
            CheckConnection.ShowToast_Short(getApplicationContext(), "Check your connection!!");
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

    private void LoadMoreData() {
        //
        listviewLapTop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(LapTopActivity.this, DetailActivity.class);
                intent.putExtra(KeyPut.PRODUCT_KEY_PUT, products.get(i));
                startActivity(intent);
            }
        });


        listviewLapTop.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int FirstItem, int VisibleItem, int TotalItem) {
                if(FirstItem + VisibleItem == TotalItem && TotalItem!=0 && isLoading==false && limitdata == false){
                    isLoading = true;
                     ThreadData threadData = new ThreadData();
                     threadData.start();
                }

            }
        });
    }

    private void GetDaTaLapTop(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String Link = Server.LinkProduct + String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null && response.length()!=2){
                    listviewLapTop.removeFooterView(footer);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            productid = jsonObject.getInt("productid");
                            productname = jsonObject.getString("productname");
                            price = jsonObject.getInt("price");
                            image = jsonObject.getString("image");
                            detail = jsonObject.getString("detail");
                            categoryid = jsonObject.getInt("categoryid");
                            products.add(new Product(productid, productname, price, image, detail, categoryid));
                            lapTopAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }
                else{
                    listviewLapTop.removeFooterView(footer);
                    limitdata=true;
                    CheckConnection.ShowToast_Short(getApplicationContext(), "No longer data!");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("categoryid",String.valueOf(cateid));
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarLapTop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLapTop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetCategoryID() {
        cateid = getIntent().getIntExtra(KeyPut.CATEGORY_KEY_PUT, -1);
        Log.d("testcateid", cateid+"");
    }

    private void intit() {
        toolbarLapTop = findViewById(R.id.toolbarLaptop);
        listviewLapTop = findViewById(R.id.listViewLapTop);
        products = new ArrayList<>();
        lapTopAdapter = new LapTopAdapter(getApplicationContext(), products);
        listviewLapTop.setAdapter(lapTopAdapter);
        //
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footer = inflater.inflate(R.layout.loadmore, null);
        mHandler = new mHandler();
    }

    //ong chu cua thread
    public class mHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    listviewLapTop.addFooterView(footer);
                    break;
                case 1:
                    GetDaTaLapTop(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    //tao thread
    public class ThreadData extends java.lang.Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }
}
