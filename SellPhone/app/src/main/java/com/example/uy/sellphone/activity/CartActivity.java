package com.example.uy.sellphone.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.uy.sellphone.R;
import com.example.uy.sellphone.adapter.CartAdapter;
import com.example.uy.sellphone.model.Cart;
import com.example.uy.sellphone.ultil.CheckConnection;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    TextView txtEmptycart;
    static TextView txtTotal;
    ListView listViewcart;
    Button btnpayment, btncontinue;
    Toolbar toolbar;
    CartAdapter cartAdapter;
    ArrayList<Cart> carts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        init();
        ActionToolbar();
        CheckData();
        EventUltil();
        EventDeleteProduct();
        EventContinueClick();
        EventPaymentClick();
    }

    private void EventPaymentClick() {
        btnpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.arraylistCart.size()>0){
                    Intent intent = new Intent(new Intent(getApplicationContext(), InforCustomerActivity.class));
                    startActivity(intent);
                }else{
                    CheckConnection.ShowToast_Short(getApplicationContext(), "No product for payment! re-check your cart!");
                }
            }
        });
    }

    private void EventContinueClick() {
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(getApplicationContext(), MainActivity.class));
                startActivity(intent);
            }
        });
    }

    private void EventDeleteProduct() {
        listViewcart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int pos, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Delete product");
                builder.setMessage("Do you want to delete this product?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(MainActivity.arraylistCart.size()<=0){
                            txtEmptycart.setVisibility(View.VISIBLE);
                        }else{
                            MainActivity.arraylistCart.remove(pos);
                            cartAdapter.notifyDataSetChanged();
                            EventUltil();
                            if(MainActivity.arraylistCart.size()<=0){
                                txtEmptycart.setVisibility(View.VISIBLE);
                            }else{
                                txtEmptycart.setVisibility(View.INVISIBLE);
                                cartAdapter.notifyDataSetChanged();
                                EventUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cartAdapter.notifyDataSetChanged();
                        EventUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EventUltil() {
        long total=0;
        for(int i=0;i<MainActivity.arraylistCart.size();i++){
            total += MainActivity.arraylistCart.get(i).getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotal.setText(decimalFormat.format(total)+" VND");
    }

    private void CheckData() {
        if(MainActivity.arraylistCart.size()<=0){
            cartAdapter.notifyDataSetChanged();
            txtEmptycart.setVisibility(View.VISIBLE);
            listViewcart.setVisibility(View.INVISIBLE);
        }else{
            cartAdapter.notifyDataSetChanged();
            txtEmptycart.setVisibility(View.INVISIBLE);
            listViewcart.setVisibility(View.VISIBLE);
        }
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

    private void init() {
        txtEmptycart = findViewById(R.id.txtemptycart_cart);
        txtTotal = findViewById(R.id.txtTotalPrice_cart);
        listViewcart = findViewById(R.id.listViewCart);
        btnpayment = findViewById(R.id.btnPayment_cart);
        btncontinue = findViewById(R.id.btncontinue_cart);
        toolbar = findViewById(R.id.toolbarcart);
        cartAdapter = new CartAdapter(CartActivity.this, MainActivity.arraylistCart);
        listViewcart.setAdapter(cartAdapter);
    }
}
