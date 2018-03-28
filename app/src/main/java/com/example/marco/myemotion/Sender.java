package com.example.marco.myemotion;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by marco on 02/03/18.
 */

public class Sender extends AppCompatActivity implements Runnable   {

    private String ip;
    private int port;
    private String command;

    public Sender(String ip, int port, String command) {
        this.ip = ip;
        this.port = port;
        this.command = command;
    }

    @Override
    public void run() {
        try {
            Socket sc;
            PrintWriter ps;
            sc = new Socket(ip, port);
            ps = new PrintWriter(sc.getOutputStream(), true);
            ps.println(command);
            ps.flush();
            ps.close();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}