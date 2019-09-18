package com.sinros.myexample;

/**
 * Created by Tanmay Ranjan on 19-Apr-18.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class RestraAdapter extends RecyclerView.Adapter<RestraAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Restra> restraList;

    public RestraAdapter(Context mCtx, List<Restra> restraList) {
        this.mCtx = mCtx;
        this.restraList = restraList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.res_layout, null);
         view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ProductViewHolder(view,mCtx, (ArrayList<Restra>) restraList);



    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Restra restra = restraList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(restra.getImage())
                .into(holder.imageView);

        holder.address.setText(restra.getPrice());
         holder.restra_name.setText(restra.getName());
       // holder.cat1.setText(restra.getCat1());
        holder.rate.setRating(Float.parseFloat(restra.getRate()));
        holder.time.setText(String.valueOf(restra.getTime())+" min");



    }

    @Override
    public int getItemCount() {
        return restraList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView address, restra_name,cat1,time;
        ImageView imageView;
        RatingBar rate;

        ArrayList<Restra> properties = new ArrayList<Restra>();
        Context ctx;
        public ProductViewHolder(View itemView, Context ctx, ArrayList<Restra> properties) {
            super(itemView);
            this.properties = properties;
            this.ctx=ctx;
            itemView.setOnClickListener(this);


            imageView = itemView.findViewById(R.id.image);
            address=itemView.findViewById(R.id.text_location);
            restra_name=itemView.findViewById(R.id.text_name);
           // cat1=itemView.findViewById(R.id.text_menu1);
           // cat2=itemView.findViewById(R.id.text_menu2);
            //cat3=itemView.findViewById(R.id.text_menu3);
            rate=itemView.findViewById(R.id.myRatingBar);
            time=itemView.findViewById(R.id.ctime);
        }


        @Override
        public void onClick(View v) {
            int postion = getAdapterPosition();
            Restra product = this.properties.get(postion);
            Intent intent = new Intent(this.ctx,DetailActivity.class);
           intent.putExtra("id",String.valueOf(product.getId()));
           intent.putExtra("img",product.getImage());
            intent.putExtra("name",product.getName());
            intent.putExtra("price",product.getPrice());
            this.ctx.startActivity(intent);


        }
    }
}
