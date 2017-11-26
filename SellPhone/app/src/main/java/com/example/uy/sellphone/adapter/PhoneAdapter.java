package com.example.uy.sellphone.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uy.sellphone.R;
import com.example.uy.sellphone.model.Product;
import com.example.uy.sellphone.ultil.Server;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by UY on 10/19/2017.
 */

public class PhoneAdapter extends BaseAdapter {

    Context context;
    ArrayList<Product> arraylistPhone;

    public PhoneAdapter(Context context, ArrayList<Product> arraylistPhone) {
        this.context = context;
        this.arraylistPhone = arraylistPhone;
    }

    @Override
    public int getCount() {
        return arraylistPhone.size();
    }

    @Override
    public Object getItem(int i) {
        return arraylistPhone.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        TextView txtPhone, txtPrice, txtDetail;
        ImageView imgPhone;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_phone, null);
            viewHolder.txtPhone = view.findViewById(R.id.txtphonename_itemphone);
            viewHolder.txtPrice = view.findViewById(R.id.txtprice_itemphone);
            viewHolder.txtDetail = view.findViewById(R.id.txtdetail_itemphone);
            viewHolder.imgPhone = view.findViewById(R.id.imgphone_itemphone);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Product product = (Product) getItem(i);
        viewHolder.txtPhone.setText(product.getProductName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtPrice.setText("Price: " + decimalFormat.format(product.getPrice())+" VND");
        //set so luong row show ra cho detail
        viewHolder.txtDetail.setMaxLines(2);
        viewHolder.txtDetail.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtDetail.setText(product.getDetail());
        Picasso.with(context).load(Server.LinkImage+ product.getImageProduct())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.error)
                .into(viewHolder.imgPhone);
        return view;
    }
}
