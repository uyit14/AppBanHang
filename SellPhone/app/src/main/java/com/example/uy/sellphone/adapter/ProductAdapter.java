package com.example.uy.sellphone.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uy.sellphone.R;
import com.example.uy.sellphone.activity.DetailActivity;
import com.example.uy.sellphone.model.Product;
import com.example.uy.sellphone.ultil.KeyPut;
import com.example.uy.sellphone.ultil.Server;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by UY on 10/17/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder> {
    Context context;
    ArrayList<Product> arrProduct;

    public ProductAdapter(Context context, ArrayList<Product> listProduct) {
        this.context = context;
        this.arrProduct = listProduct;
    }


    //khoi tao lai layout da thiet ke ben ngoai
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }


    //Ho tro viec set va get thuoc tinh de gan len layout
    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Product products = arrProduct.get(position);
        holder.txtProductName.setText(products.getProductName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPrice.setText("Price: " + decimalFormat.format(products.getPrice())+" VND");
        Picasso.with(context).load(Server.LinkImage+products.getImageProduct())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.error)
                .into(holder.imgImageProduct);
    }

    @Override
    public int getItemCount() {
        return arrProduct.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgImageProduct;
        public TextView txtPrice, txtProductName;

        public ItemHolder(View itemView) {
            super(itemView);
            imgImageProduct = (ImageView) itemView.findViewById(R.id.img_newproduct);
            txtProductName = (TextView) itemView.findViewById(R.id.txtname_newproduct);
            txtPrice = (TextView) itemView.findViewById(R.id.txtprice_newproduct);

            //set event when click product in home(new product)
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(KeyPut.PRODUCT_KEY_PUT, arrProduct.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
