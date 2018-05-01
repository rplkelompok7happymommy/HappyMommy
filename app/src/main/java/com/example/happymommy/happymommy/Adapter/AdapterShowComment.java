package com.example.happymommy.happymommy.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.happymommy.happymommy.R;

/**
 * Created by M. Satria Wibawa on 29/04/2018.
 */

public class AdapterShowComment extends RecyclerView.ViewHolder {

    public TextView mUser;
    public TextView mIsi;
    public ImageView ava;
    public TextView mWaktu;


    public AdapterShowComment(View itemView) {
        super(itemView);

        mUser = itemView.findViewById(R.id.tv_username);
        mIsi = itemView.findViewById(R.id.tv_comment);
        mWaktu = itemView.findViewById(R.id.tv_waktu);
        ava = itemView.findViewById(R.id.img_ava);

    }
}
