package com.rpl.happymommy.happymommy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rpl.happymommy.happymommy.Adapter.AdapterShowComment;
import com.rpl.happymommy.happymommy.Interface.ItemClickListener;
import com.rpl.happymommy.happymommy.Model.CommentModel;
import com.rpl.happymommy.happymommy.Model.InfoUser;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference databaseUser, databaseComments;

    private TextView mJudul;
    private EditText mComment;

    private String idRumah = "";
    private String idDeskripsi = "";

    private FirebaseAuth mAuth;

    //defined recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    //get FirebaseRecyelerAdapter dengan model CommentModel dan adapter AdapterShowComment
    private FirebaseRecyclerAdapter<CommentModel,AdapterShowComment> adapter;

    //calender
    private Calendar calendar;
    //format calender
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

        //get intent
        idRumah = getIntent().getStringExtra("IdKonten");

        //Lokasi database
        databaseUser = FirebaseDatabase.getInstance().getReference().child("User");
        databaseComments = FirebaseDatabase.getInstance().getReference("InfoRumahsakit").child(idRumah).child("Comment");

        //code nampilin calender
        calendar = Calendar.getInstance();
        //code format data yang akan di tampilkan
        simpleDateFormat = new SimpleDateFormat("dd-MM-yy");
        long date = System.currentTimeMillis();
        final String CurrentDate = simpleDateFormat.format(date);

        //get intent pake pemisalan
        if (getIntent() != null) {
            idRumah = getIntent().getStringExtra("IdKonten");
            idDeskripsi = getIntent().getStringExtra("IdDeskripsi");
        }
        if (!idRumah.isEmpty()) {

            mJudul.setText(idDeskripsi);

            //bikin Query dengan isinya database comment (Buat baca isi database)
            Query query = databaseComments;

            //code FirebaseAdapter
            adapter = new FirebaseRecyclerAdapter<CommentModel, AdapterShowComment>(CommentModel.class, R.layout.custom_comment, AdapterShowComment.class, query) {

                //untuk edit di viewholdernya
                @Override
                protected void populateViewHolder(final AdapterShowComment viewHolder, CommentModel model, int position) {
                    //pasang TextView dengan data get dari CommentModel
                    viewHolder.mUser.setText(model.getUsername());
                    viewHolder.mIsi.setText(model.getIsi());
                    viewHolder.mWaktu.setText(CurrentDate);

                    //Get posisi dan Key
                    final String list_id = getRef(position).getKey();
                    //Get Id comment
                    final String id_comment = model.getIdcomment();
                    final String rule = "admin";

                    //lakukan perubahan berdasarkan key di databasse
                    databaseComments.child(list_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            //pasang onClick pakai interface ItemClickListener
                            viewHolder.setItemClickListener(new ItemClickListener() {
                                @Override
                                public void onClick(View view, int position, boolean isLongClick) {

                                    //Pemisalan agar hanya user yang dapat delete commnet pribadi user
                                    if (mAuth.getCurrentUser().getUid().equals(id_comment)){
                                        databaseComments.child(list_id).removeValue();
                                        notifyItemRemoved(viewHolder.getAdapterPosition());
                                        notifyDataSetChanged();
                                    }else {
                                        //Pemisalan agar admin dapat delete semua comment (nentuin admin berdasarkan email)
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

            //kasih tau perubahan ke adapater
            adapter.notifyDataSetChanged();
            //pasang adapter
            recyclerView.setAdapter(adapter);



        }

    }

    private void ShowList (){
        //Sama seperti di atas
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

    //ketika di klik jalankan method Submit Comment(method tambah comment)
    @Override
    public void onClick(View view) {
        SubmitComments();
    }

    private void SubmitComments() {
        //add data untuk satu value
        databaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
            //ketika ada perubahan
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Model InfoUser bikin variable untuk ambil UID user
                InfoUser user = dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue(InfoUser.class);
                String review = mComment.getText().toString().trim();
                String hospital = mJudul.getText().toString();

                //jika field tidak kosong
                if (!TextUtils.isEmpty(review)){
                    //Buat Key baru
                    String idComment = databaseComments.push().getKey();
                    //ambil UID dari User yang sedang login
                    String id = mAuth.getCurrentUser().getUid();
                    //timestamp (cuman tidak dipakai)
                    long timestamp = System.currentTimeMillis();
                    //bikin data di database berdasarkan model CommentModel
                    CommentModel track = new CommentModel(id, user.getNama(), review, hospital , timestamp*(-1));
                    //tempatkan di key yang telah di push
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