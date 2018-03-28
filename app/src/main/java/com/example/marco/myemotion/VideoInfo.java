package com.example.marco.myemotion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 05/03/18.
 */

public class VideoInfo extends AppCompatActivity {

    private ListView listinfo;
    private VideoView video;
    private MediaController mediaController;
    ArrayList<String> videoList = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoinfo);
        listinfo = (ListView) findViewById(R.id.listinfo);
        video = (VideoView) findViewById(R.id.videoView);


        video.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.akrapovic));


        mediaController = new MediaController(this);
        mediaController.setAnchorView(video);
        mediaController.setMediaPlayer(video);

        video.setMediaController(mediaController);
        video.requestFocus();
        video.start();

        Sessione sessione = (Sessione) getIntent().getParcelableExtra("videoInfo");
        videoList.add("Data : " + sessione.getData());
        videoList.add("Durata : €" + sessione.getDurata());


        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, videoList) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                text1.setTypeface(null, Typeface.BOLD);

                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(videoList.get(position).substring(0, videoList.get(position).indexOf(":")));

                text2.setText(videoList.get(position).substring(videoList.get(position).indexOf(":") + 1, videoList.get(position).length()));

                return view;
            }
        };
        listinfo.setAdapter(adapter);
        
    }
}











