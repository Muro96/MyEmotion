package com.example.marco.myemotion;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by marco on 05/03/18.
 */

public class Reciver extends AppCompatActivity implements Runnable {
    private int upPort = 0;
    @Override
    public void run() {
        while (true) {
            try {
                int count, i;
                Date currentTime = Calendar.getInstance().getTime();
                Socket sc = new ServerSocket(9000 + upPort).accept();
                upPort++;


                sc.setReuseAddress(true);

                //Where save file received
                FileOutputStream out = new FileOutputStream("mpu_camera.h264");


                //Where read data
                DataInputStream in = new DataInputStream(new BufferedInputStream(sc.getInputStream()));

                byte[] buffer = new byte[8192]; // or 4096, or more
                i = 0;

                while ((count = in.read(buffer)) > 0) {
                    out.write(buffer, 0, count);
                    Log.i("Info", i++ + " - Letto dal buffer"+buffer+" #bytes: " + count);
                }

              //  out.close();
                in.close();
                sc.close();

            } catch (IOException ex) {
                Log.e("Error", "Errore durante la creazione del socket di ricezione."+ex);
            }
        }
    }


}