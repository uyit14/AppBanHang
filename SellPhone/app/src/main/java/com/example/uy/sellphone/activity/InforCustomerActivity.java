package com.example.uy.sellphone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.uy.sellphone.R;
import com.example.uy.sellphone.ultil.CheckConnection;
import com.example.uy.sellphone.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InforCustomerActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText edtname, edtemail, edtphone;
    Button btnOK, btnCancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_customer);
        init();
        ActionToolbar();
        EventbtnClick();
    }

    private void EventbtnClick() {
        //btncancel
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                //startActivity(intent);
                finish();
            }
        });

        //btn OK
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String customername = edtname.getText().toString().trim();
                final String email = edtemail.getText().toString().trim();
                final String phone = edtphone.getText().toString().trim();
                if(customername.length()>0 && email.length()>0 && phone.length()>0){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.LinkBill, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String billid) {
                            Log.d("billid", billid);
                            if(Integer.parseInt(billid)>0){
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, Server.LinkBillDetail, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("1")){
                                            MainActivity.arraylistCart.clear();
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Buy success!!");
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Continue Shopping!");
                                        }else{
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Faill, check you connection!!");
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for(int i=0;i<MainActivity.arraylistCart.size();i++){
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("billid", billid);
                                                jsonObject.put("productid", MainActivity.arraylistCart.get(i).getProductid());
                                                jsonObject.put("productname", MainActivity.arraylistCart.get(i).getProductname());
                                                jsonObject.put("price", MainActivity.arraylistCart.get(i).getPrice());
                                                jsonObject.put("number", MainActivity.arraylistCart.get(i).getTotal());

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String, String> hashMap = new HashMap<String, String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
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
                            hashMap.put("customername", customername);
                            hashMap.put("email", email);
                            hashMap.put("phone", phone);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else{
                    CheckConnection.ShowToast_Short(getApplicationContext(), "Please fill out your info!!");
                }
            }
        });
    }

    private void init() {
        toolbar = findViewById(R.id.toolbarinfo_customer);
        edtname = findViewById(R.id.edtname_infor);
        edtemail = findViewById(R.id.edtemai_infor);
        edtphone = findViewById(R.id.edtphone_infor);
        btnOK = findViewById(R.id.btnOK_infor);
        btnCancle = findViewById(R.id.btnCancle_infor);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
