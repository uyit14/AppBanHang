package com.example.uy.sellphone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uy.sellphone.R;
import com.example.uy.sellphone.model.Category;
import com.example.uy.sellphone.ultil.Server;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by UY on 10/17/2017.
 */

public class CategoryAdapter extends BaseAdapter {
    ArrayList<Category> arraylistCategory;
    Context context;

    public CategoryAdapter(ArrayList<Category> arraylistCategory, Context context) {
        this.arraylistCategory = arraylistCategory;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arraylistCategory.size();
    }

    @Override
    public Object getItem(int i) {
        return arraylistCategory.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView txtCategoryname;
        ImageView imgImage;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null; //gan bang null
        //neu run project len thi view bang rong, thi se nhay vao ham if
        if(view==null){
            viewHolder = new ViewHolder(); //de su dung nhung thuoc tinh ben trong class
            //get service la layout ra
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //gan view vao
            view = inflater.inflate(R.layout.item_category, null);
            viewHolder.txtCategoryname = view.findViewById(R.id.txtitem_categoryName);
            viewHolder.imgImage = view.findViewById(R.id.imgitem_categoryImage);
            view.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) view.getTag();

        }
        Category category = (Category) getItem(i);
        viewHolder.txtCategoryname.setText(category.getCategoryName());
        String LinkImage = Server.LinkImage+category.getImage();
        Picasso.with(context).load(LinkImage)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.error)
                .into(viewHolder.imgImage);
        return view;
    }
}
