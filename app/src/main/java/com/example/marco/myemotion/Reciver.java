package com.example.marco.myemotion;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by marco on 05/03/18.
 */

public class Reciver implements Runnable {

    public final static String FOLDER = "MyEmotionVideo";
    private final String CAMERA_BASE_NAME = "mpu_camera.h264";
    private final String MPU_BASE_NAME = "mpu_data.txt";
    private final String TAG_ERR = "Errore";

    private int PORTOUT;
    private Context ctx;
    private String path_video = "";
    private String path_dati = "";

    public Reciver(int port, Context ctx) {
        this.PORTOUT = port;
        this.ctx = ctx;
    }

    @Override
    public void run() {
        Socket sc = null; // Socket dove aspettare le connessioni
        ServerSocket ssc; // Socket che fa da server sempre attivo
        String moment;
        File folder;
        String path;

        try {
            //Creo la mia dir
            //folder = ctx.getDir("", Context.MODE_PRIVATE);
            folder = new File(Environment.getExternalStorageDirectory(), FOLDER);
            if(!folder.exists()) Log.e(TAG_ERR, "" + folder.mkdir());
            else Log.e(TAG_ERR, "La cartella esiste!");
            // Inizializzo il socket per le connessioni in ingresso su quella porta
            ssc = new ServerSocket(PORTOUT);
            //Supporta fino a 100 connessioni (messo a caso per farlo andare)
            for (int j = 0; j < 100; j++) {
                moment = getTodayDate();
                // Video
                sc = ssc.accept();
                sc.setReuseAddress(true);
                path_video = moment + "_" + CAMERA_BASE_NAME;
                path_video = path_video.replace(" ", "_");
                new ArchiviaFile(path_video, sc.getInputStream(), ctx).run();

                // File dati
                sc = ssc.accept();
                path_dati = moment + "_" + MPU_BASE_NAME;
                path_dati = path_dati.replace(" ", "_");
                new ArchiviaFile(path_dati, sc.getInputStream(), ctx).run();

                // Faccio partire il thread che caricherà i dati nel DB
                new Thread(new Parser(path_video, path_dati, ctx)).start();
            } // for

            sc.close(); // Chiuso il socket
            ssc.close(); // Rilascio la porta in ricezione
        } catch (SocketException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(TAG_ERR, "Problema nella ricezione");
            e.printStackTrace();
        }

    } // run


    private String getTodayDate() {
        return new SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(new Date());
    }
}