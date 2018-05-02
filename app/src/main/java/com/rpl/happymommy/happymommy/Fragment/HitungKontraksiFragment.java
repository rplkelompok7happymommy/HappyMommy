package com.rpl.happymommy.happymommy.Fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rpl.happymommy.happymommy.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HitungKontraksiFragment extends Fragment {

    private TextView textView, twaktu ;

    private Button start, pause, reset ;

    private long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;

    private Handler handler;

    private int Seconds, Minutes, MilliSeconds ;

    private ListView listView ;

    private String[] ListElements = new String[] {  };

    private List<String> ListElementsArrayList ;

    private  ArrayAdapter<String> adapter ;

    private Activity activity;

    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;


    public HitungKontraksiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hitung_kontraksi, container, false);

        textView = (TextView)view.findViewById(R.id.textView);
        twaktu = (TextView)view.findViewById(R.id.waktu);
        twaktu.setVisibility(View.INVISIBLE);
        start = (Button)view.findViewById(R.id.button);
        pause = (Button)view.findViewById(R.id.button2);
        pause.setVisibility(View.INVISIBLE);
        reset = (Button)view.findViewById(R.id.button3);
        listView = (ListView)view.findViewById(R.id.listview1);


        handler = new Handler() ;

        ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));

        adapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1,
                ListElementsArrayList
        );

        listView.setAdapter(adapter);

        Thread t = new Thread(){
            public void run() {
                try {
                    while (!isInterrupted()){
                        Thread.sleep(1000);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                calendar = Calendar.getInstance();
                                simpleDateFormat = new SimpleDateFormat("dd-MM-yy / HH:mm:ss");
                                long date = System.currentTimeMillis();
                                String CurrentDate = simpleDateFormat.format(date);
                                twaktu.setText(CurrentDate);

                            }
                        });
                    }
                }catch (InterruptedException e){

                }

            }
        };

        t.start();

        String waktu = twaktu.getText().toString();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MillisecondTime = 0L ;
                StartTime = 0L ;
                TimeBuff = 0L ;
                UpdateTime = 0L ;
                Seconds = 0 ;
                Minutes = 0 ;
                MilliSeconds = 0 ;

                textView.setText("00:00:00");

                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                start.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimeBuff += MillisecondTime;

                handler.removeCallbacks(runnable);

                pause.setVisibility(View.INVISIBLE);
                start.setVisibility(View.VISIBLE);

                reset.setEnabled(true);

                ListElementsArrayList.add(textView.getText().toString()+ "                         " + twaktu.getText().toString() );

                adapter.notifyDataSetChanged();

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListElementsArrayList.clear();

                adapter.notifyDataSetChanged();

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            textView.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };

}