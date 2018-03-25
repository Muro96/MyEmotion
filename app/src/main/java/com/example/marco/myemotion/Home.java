package com.example.marco.myemotion;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

public class Home extends Fragment{
    public View view;
    Button startButton;
    Button stopButton;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        startButton = (Button)view.findViewById(R.id.StartButton);
        stopButton = (Button) view.findViewById(R.id.StopButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sender sender = new Sender("START");
                Reciver reciver = new Reciver();
                new Thread(sender).start();
                new Thread(reciver).start();
                Log.e("comand ",""+sender.getCOMMAND());
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sender sender1 = new Sender("STOP");
                new Thread(sender1).start();
                Log.e("comand",""+sender1.getCOMMAND());
            }
        });
        return view;
    }


}


