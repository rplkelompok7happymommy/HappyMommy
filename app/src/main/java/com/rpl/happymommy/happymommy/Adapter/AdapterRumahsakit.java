package com.rpl.happymommy.happymommy.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rpl.happymommy.happymommy.Interface.ItemClickListener;
import com.rpl.happymommy.happymommy.R;

/**
 * Created by M. Satria Wibawa on 28/04/2018.
 */
    //Adapter untuk RecyclerView Rumah Sakit
public class AdapterRumahsakit extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtHospital;
    public ImageView imageView;

    //ItemClicListener (interface)
    private ItemClickListener itemClickListener;

    public AdapterRumahsakit(View itemView) {
        super(itemView);

        txtHospital = itemView.findViewById(R.id.hospital_name);
        imageView = itemView.findViewById(R.id.hospital_image);

        itemView.setOnClickListener(this);

    }

    //Pasang interface intemClickListener
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    //Pasang Onclick agar dapat di klik
    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(),false);
    }
}
