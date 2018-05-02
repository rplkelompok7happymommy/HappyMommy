package com.example.happymommy.happymommy;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happymommy.happymommy.Adapter.AdapterComment;
import com.example.happymommy.happymommy.Adapter.AdapterRumahsakit;
import com.example.happymommy.happymommy.Adapter.AdapterShowComment;
import com.example.happymommy.happymommy.Interface.ItemClickListener;
import com.example.happymommy.happymommy.Model.CommentModel;
import com.example.happymommy.happymommy.Model.InfoUser;
import com.example.happymommy.happymommy.Model.RumahSakit;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference mDatabase, databaseUser, databaseComments;
    private Query komenDatabase;
    private TextView mJudul;
    private EditText mComment;
    private ImageView mSubmit;

    private String idRumah = "";
    private String idDeskripsi = "";

    private FirebaseUser mCurrentUser;
    private FirebaseAuth mAuth;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerAdapter<CommentModel,AdapterShowComment> adapter;

    private Calendar calendar;

    private SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        mJudul = findViewById(R.id.judul_desktripsi);
        mComment = findViewById(R.id.input_comment);


        findViewById(R.id.btnSubmit).setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.rv_comment);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAuth = FirebaseAuth.getInstance();

        idRumah = getIntent().getStringExtra("IdKonten");

        komenDatabase = FirebaseDatabase.getInstance().getReference().child("Comment");
        databaseUser = FirebaseDatabase.getInstance().getReference().child("User");
        databaseComments = FirebaseDatabase.getInstance().getReference("InfoRumahsakit").child(idRumah).child("Comment");

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yy");
        long date = System.currentTimeMillis();
        final String CurrentDate = simpleDateFormat.format(date);

        if (getIntent() != null) {
            idRumah = getIntent().getStringExtra("IdKonten");
            idDeskripsi = getIntent().getStringExtra("IdDeskripsi");
        }
        if (!idRumah.isEmpty()) {

            mJudul.setText(idDeskripsi);

            Query query = databaseComments;

            adapter = new FirebaseRecyclerAdapter<CommentModel, AdapterShowComment>(CommentModel.class, R.layout.custom_comment, AdapterShowComment.class, query) {

                @Override
                protected void populateViewHolder(final AdapterShowComment viewHolder, CommentModel model, int position) {
                    viewHolder.mUser.setText(model.getUsername());
                    viewHolder.mIsi.setText(model.getIsi());
                    viewHolder.mWaktu.setText(CurrentDate);

                    final String list_id = getRef(position).getKey();
                    final String id_comment = model.getIdcomment();
                    final String rule = "admin";

                    databaseComments.child(list_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {


                            viewHolder.setItemClickListener(new ItemClickListener() {
                                @Override
                                public void onClick(View view, int position, boolean isLongClick) {

                                    if (mAuth.getCurrentUser().getUid().equals(id_comment)){
                                        databaseComments.child(list_id).removeValue();
                                        notifyItemRemoved(viewHolder.getAdapterPosition());
                                        notifyDataSetChanged();
                                    }else {
                                        if (mAuth.getCurrentUser().getEmail().equals("happymommyrpl@gmail.com")){
                                            databaseComments.child(list_id).removeValue();
                                            notifyItemRemoved(viewHolder.getAdapterPosition());
                                            notifyDataSetChanged();
                                        }else {

                                        }
                                    }

                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            };

            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);



        }

    }

    private void ShowList (){
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yy");
        long date = System.currentTimeMillis();
        final String CurrentDate = simpleDateFormat.format(date);

        if (getIntent() != null) {
            idRumah = getIntent().getStringExtra("IdKonten");
            idDeskripsi = getIntent().getStringExtra("IdDeskripsi");
        }
        if (!idRumah.isEmpty()) {

            mJudul.setText(idDeskripsi);

            Query query = databaseComments;

            adapter = new FirebaseRecyclerAdapter<CommentModel, AdapterShowComment>(CommentModel.class, R.layout.custom_comment, AdapterShowComment.class, query) {

                @Override
                protected void populateViewHolder(final AdapterShowComment viewHolder, CommentModel model, int position) {
                    viewHolder.mUser.setText(model.getUsername());
                    viewHolder.mIsi.setText(model.getIsi());
                    viewHolder.mWaktu.setText(CurrentDate);

                    final String list_id = getRef(position).getKey();
                    final String id_comment = model.getIdcomment();

                    databaseComments.child(list_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {


                            viewHolder.setItemClickListener(new ItemClickListener() {
                                @Override
                                public void onClick(View view, int position, boolean isLongClick) {

                                    if (mAuth.getCurrentUser().getUid().equals(id_comment)){
                                        databaseComments.child(list_id).removeValue();
                                        notifyItemRemoved(viewHolder.getAdapterPosition());
                                        notifyDataSetChanged();
                                    }else {
                                        if (mAuth.getCurrentUser().getEmail().equals("happymommyrpl@gmail.com")){
                                            databaseComments.child(list_id).removeValue();
                                            notifyItemRemoved(viewHolder.getAdapterPosition());
                                            notifyDataSetChanged();
                                        }else {

                                        }
                                    }

                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            };

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        SubmitComments();
    }

    private void SubmitComments() {
        databaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                InfoUser user = dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue(InfoUser.class);
                String review = mComment.getText().toString().trim();
                String hospital = mJudul.getText().toString();

                if (!TextUtils.isEmpty(review)){
                    String idComment = databaseComments.push().getKey();
                    String id = mAuth.getCurrentUser().getUid();
                    long timestamp = System.currentTimeMillis();
                    CommentModel track = new CommentModel(id, user.getNama(), review, hospital , timestamp*(-1));
                    databaseComments.child(idComment).setValue(track);
                    Toast.makeText(CommentActivity.this, "Comment Success", Toast.LENGTH_SHORT).show();
                    mComment.setText("");

                    ShowList();

                }else{
                    Toast.makeText(CommentActivity.this, "Tolong Isi Comment Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}