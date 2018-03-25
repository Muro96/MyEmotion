package com.example.marco.myemotion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.MediaController;
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
    String[] f;
    ArrayList<String> arrayFile = new ArrayList<>();


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

        int i;

        AssetManager assetManager = getResources().getAssets();
        try {
            f = assetManager.list("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (f == null) {
            Log.e("directory empty!", "");
        } else {
            for (i = 0; i < f.length; i++) {
                if (f[i].contains(".mp4")) {
                    Log.e("asdfgh", "" + f[i]);
                }
            }
        }




      /*  try {
            int i;
            int count;
            byte[] buffer = new byte[4096]; // or 4096, or more
            i = 0;


            while ((count = inputStream.read(buffer)) > 0) {
                Log.e("sdfj",""+inputStream.read(buffer));
                Log.i("Info", i++ + " - Letto dal buffer"+buffer+" #bytes: " + count);

            } */


    }
}











