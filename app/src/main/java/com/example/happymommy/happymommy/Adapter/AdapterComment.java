package com.example.happymommy.happymommy.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.happymommy.happymommy.Model.CommentModel;
import com.example.happymommy.happymommy.R;

import java.util.ArrayList;

/**
 * Created by M. Satria Wibawa on 28/04/2018.
 */

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.ViewHolder> {
    Context context;
    ArrayList<CommentModel> komen;

    public AdapterComment (Context context, ArrayList<CommentModel> komen) {
        this.context = context;
        this.komen = komen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CommentModel commentModel = komen.get(position);

        holder.mUsername.setText(commentModel.getUsername());
        holder.mComment.setText(commentModel.getIsi());
        holder.ava.setImageResource(R.drawable.ic_account);
    }

    @Override
    public int getItemCount() {
        return komen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //variable
        TextView mUsername;
        TextView mComment;
        ImageView ava;

        public ViewHolder(View itemView) {
            super(itemView);
            //bind data
            mUsername = itemView.findViewById(R.id.tv_username);
            mComment = itemView.findViewById(R.id.tv_comment);
            ava = itemView.findViewById(R.id.img_ava);
        }
    }
}

