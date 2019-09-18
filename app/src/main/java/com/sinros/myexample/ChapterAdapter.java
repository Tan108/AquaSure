package com.sinros.myexample;

/**
 * Created by Tanmay Ranjan on 19-Apr-18.
 */

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;


public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Chapter> chapterList;
    private  Chapter product;

    public ChapterAdapter(Context mCtx, List<Chapter> chapterList) {
        this.mCtx = mCtx;
        this.chapterList = chapterList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cart_layout, null);
         view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ProductViewHolder(view,mCtx, (ArrayList<Chapter>) chapterList);



    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Chapter chapter = chapterList.get(position);


        Glide.with(mCtx)
                .load(chapter.getImage())
                .into(holder.imageView);

        holder.tname.setText(String.valueOf(chapter.getName()));
        holder.tqty.setText(String.valueOf(chapter.getQuantity()));
        holder.total.setText(String.valueOf(chapter.getTotal()));




    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


       TextView tname,tqty,total;
       ImageView imageView;

        ArrayList<Chapter> properties = new ArrayList<Chapter>();
        Context ctx;
        public ProductViewHolder(View itemView, Context ctx, ArrayList<Chapter> properties) {
            super(itemView);
            this.properties = properties;
            this.ctx=ctx;
            itemView.setOnClickListener(this);

            imageView = itemView.findViewById(R.id.image1);
            tname = itemView.findViewById(R.id.text_name);
            tqty = itemView.findViewById(R.id.text_qty);
            total = itemView.findViewById(R.id.text_grand);

        }


        @Override
        public void onClick(View v) {
            int postion = getAdapterPosition();
             product = this.properties.get(postion);


        }
    }
}
