package com.example.marco.myemotion;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Home extends Fragment {
    public View view;
    Button startButton;
    Button stopButton;
    Button shutdownButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        startButton = view.findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Sender(MainActivity.extIP, MainActivity.com, "START")).start();
            }
        });

        stopButton = view.findViewById(R.id.StopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Sender(MainActivity.extIP, MainActivity.com, "STOP")).start();
            }
        });

        shutdownButton = view.findViewById(R.id.shutdownButton);
        shutdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Sender(MainActivity.extIP, MainActivity.com, "SHUTDOWN")).start();
            }
        });


        return view;
    }


}


