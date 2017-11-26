package com.example.uy.sellphone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uy.sellphone.R;
import com.example.uy.sellphone.activity.CartActivity;
import com.example.uy.sellphone.activity.MainActivity;
import com.example.uy.sellphone.model.Cart;
import com.example.uy.sellphone.ultil.Server;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by UY on 10/22/2017.
 */

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<Cart> arraylistCart;

    public CartAdapter(Context context, ArrayList<Cart> arraylistCart) {
        this.context = context;
        this.arraylistCart = arraylistCart;
    }

    @Override
    public int getCount() {
        return arraylistCart.size();
    }

    @Override
    public Object getItem(int i) {
        return arraylistCart.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    //
    public class ViewHolder{
        TextView txtProductname, txtPrice;
        ImageView imgProduct;
        Button btnminus, btntotal, btnplus;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_cart, null);
            viewHolder.txtProductname = view.findViewById(R.id.txtprductname_cartitem);
            viewHolder.txtPrice = view.findViewById(R.id.txtprice_cartitem);
            viewHolder.imgProduct = view.findViewById(R.id.imgproduct_cartitem);
            viewHolder.btnminus = view.findViewById(R.id.btnminus_cartitem);
            viewHolder.btntotal = view.findViewById(R.id.btntotal_cartitem);
            viewHolder.btnplus = view.findViewById(R.id.btnplus_cartitem);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Cart cart = (Cart) getItem(i);
        viewHolder.txtProductname.setText(cart.getProductname());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtPrice.setText("Price: "+decimalFormat.format(cart.getPrice())+"VND");
        Picasso.with(context).load(Server.LinkImage+cart.getImage())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.error)
                .into(viewHolder.imgProduct);
        viewHolder.btntotal.setText(cart.getTotal()+"");
        int gettotal = Integer.parseInt(viewHolder.btntotal.getText().toString());
        if(gettotal>=10){
            viewHolder.btnplus.setVisibility(View.INVISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }else if(gettotal<=1){
            viewHolder.btnplus.setVisibility(View.VISIBLE);
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
        }else{
            viewHolder.btnplus.setVisibility(View.VISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }
        //
        final ViewHolder finalViewHolder = viewHolder;
        final ViewHolder finalViewHolder1 = viewHolder;
        final ViewHolder finalViewHolder2 = viewHolder;
        final ViewHolder finalViewHolder3 = viewHolder;
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //so luong update = so luong hien tai + 1 moi lan click vao btnphlus
                int numberupdate = Integer.parseInt(finalViewHolder.btntotal.getText().toString())+1;
                //lay ra so luong hien co trong gio hang (chua cap nhat)
                int numbercurent = MainActivity.arraylistCart.get(i).getTotal();
                //tong tien hien tai trong gio hang
                long curenttotalprice = MainActivity.arraylistCart.get(i).getPrice();
                //cong thuc tinh tong tien moi nhat trong gio hang
                //newtotal = (curenttotalprice*numberupdate)/numbercurent (quy tat duong cheo')
                //set so luong moi nhat vao gio hang (sau khi click btn plus)
                MainActivity.arraylistCart.get(i).setTotal(numberupdate);
                long newtotalprice = (curenttotalprice * numberupdate)/numbercurent;
                MainActivity.arraylistCart.get(i).setPrice((int) newtotalprice);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder1.txtPrice.setText("Price: "+decimalFormat.format(newtotalprice)+"VND");
                CartActivity.EventUltil();
                if(numberupdate>9){
                    finalViewHolder2.btnplus.setVisibility(View.INVISIBLE);
                    finalViewHolder2.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder3.btntotal.setText(numberupdate+"");
                }else{
                    finalViewHolder2.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder2.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder3.btntotal.setText(numberupdate+"");
                }
            }
        });
        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //so luong update = so luong hien tai + 1 moi lan click vao btnphlus
                int numberupdate = Integer.parseInt(finalViewHolder.btntotal.getText().toString())-1;
                //lay ra so luong hien co trong gio hang (chua cap nhat)
                int numbercurent = MainActivity.arraylistCart.get(i).getTotal();
                //tong tien hien tai trong gio hang
                long curenttotalprice = MainActivity.arraylistCart.get(i).getPrice();
                //cong thuc tinh tong tien moi nhat trong gio hang
                //newtotal = (curenttotalprice*numberupdate)/numbercurent (quy tat duong cheo')
                //set so luong moi nhat vao gio hang (sau khi click btn plus)
                MainActivity.arraylistCart.get(i).setTotal(numberupdate);
                long newtotalprice = (curenttotalprice * numberupdate)/numbercurent;
                MainActivity.arraylistCart.get(i).setPrice((int) newtotalprice);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder1.txtPrice.setText("Price: "+decimalFormat.format(newtotalprice)+"VND");
                CartActivity.EventUltil();
                if(numberupdate<2){
                    finalViewHolder2.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder2.btnminus.setVisibility(View.INVISIBLE);
                    finalViewHolder3.btntotal.setText(numberupdate+"");
                }else{
                    finalViewHolder2.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder2.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder3.btntotal.setText(numberupdate+"");
                }
            }
        });
        return view;
    }
}
