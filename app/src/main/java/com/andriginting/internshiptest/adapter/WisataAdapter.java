package com.andriginting.internshiptest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andriginting.internshiptest.R;
import com.andriginting.internshiptest.model.WisataModel;
import com.andriginting.internshiptest.view.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.andriginting.internshiptest.network.ApiClient.IMAGE_URL;
import static com.andriginting.internshiptest.view.DetailActivity.ITEM_ID;

/**
 * Created by Andri Ginting on 2/7/2018.
 */

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.ViewHolder>{
    private ArrayList<WisataModel> wisataModelArrayList;
    Context context;

    public WisataAdapter(ArrayList<WisataModel> wisataModelArrayList, Context context) {
        this.wisataModelArrayList = wisataModelArrayList;
        this.context = context;
    }

    @Override
    public WisataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_wisata,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WisataAdapter.ViewHolder holder, final int position) {
        WisataModel wisataModel = wisataModelArrayList.get(position);
        holder.titleKontenWisata.setText(wisataModel.getTitle());

        Picasso.with(context)
                .load(IMAGE_URL+wisataModel.getImageUrl())
                .placeholder(R.color.cardview_shadow_start_color)
                .into(holder.imageKontenWisata);

        holder.imageKontenWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetail = new Intent(context, DetailActivity.class);
                intentDetail.putExtra(ITEM_ID,wisataModelArrayList.get(position).getDataId());
                context.startActivity(intentDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wisataModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageKontenWisata;
        TextView titleKontenWisata;
        public ViewHolder(View itemView) {
            super(itemView);

            imageKontenWisata = itemView.findViewById(R.id.image_konten_wisata);
            titleKontenWisata = itemView.findViewById(R.id.text_judul_konten_wisata);
        }
    }
}
