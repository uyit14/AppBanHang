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
 * Created by UY on 10/21/2017.
 */

public class LapTopAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> arraylistProduct;

    public LapTopAdapter(Context context, ArrayList<Product> arraylistProduct) {
        this.context = context;
        this.arraylistProduct = arraylistProduct;
    }

    @Override
    public int getCount() {
        return arraylistProduct.size();
    }

    @Override
    public Object getItem(int i) {
        return arraylistProduct.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //
    private class ViewHolder{
        TextView txtName, txtPrice, txtDetail;
        ImageView imgLapTop;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_laptop, null);
            viewHolder.txtName = view.findViewById(R.id.txtlaptopname_itemlaptop);
            viewHolder.txtPrice = view.findViewById(R.id.txtlaptopprice_itemlaptop);
            viewHolder.txtDetail = view.findViewById(R.id.txtlaptopdetail_itemlaptop);
            viewHolder.imgLapTop = view.findViewById(R.id.imglaptop_itemlaptop);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Product product = (Product) getItem(i);
        viewHolder.txtName.setText(product.getProductName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtPrice.setText("Price: " + decimalFormat.format(product.getPrice()) + "VND");
        //set so luong row show ra cho detail
        viewHolder.txtDetail.setMaxLines(2);
        viewHolder.txtDetail.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtDetail.setText(product.getDetail());
        //
        Picasso.with(context).load(Server.LinkImage + product.getImageProduct())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.error)
                .into(viewHolder.imgLapTop);
        return view;
    }
}
