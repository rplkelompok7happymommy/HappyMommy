package com.rpl.happymommy.happymommy.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rpl.happymommy.happymommy.Interface.ItemClickListener;
import com.rpl.happymommy.happymommy.R;

/**
 * Created by M. Satria Wibawa on 29/04/2018.
 */
    //Adapter untuk RecyclerView Rumah Sakit
public class AdapterShowComment extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView mUser;
    public TextView mIsi;
    public ImageView ava;
    public TextView mWaktu;

    //ItemClicListener (interface)
    private ItemClickListener itemClickListener;

    public AdapterShowComment(View itemView) {
        super(itemView);

        mUser = itemView.findViewById(R.id.tv_username);
        mIsi = itemView.findViewById(R.id.tv_comment);
        mWaktu = itemView.findViewById(R.id.tv_waktu);
        ava = itemView.findViewById(R.id.img_ava);

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