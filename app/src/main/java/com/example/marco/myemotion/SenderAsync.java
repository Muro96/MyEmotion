package com.example.marco.myemotion;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by marco on 02/03/18.
 */

public class SenderAsync extends AsyncTask<String, Void, SenderAsync>  {

    private int port;
    private String command;
    private String ip;

    @Override
    protected SenderAsync doInBackground(String... voids) {
        try {
            this.port = Integer.parseInt(voids[1]);
            this.command = voids[2];
            this.ip = voids[0];
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
        return null;
    }

}