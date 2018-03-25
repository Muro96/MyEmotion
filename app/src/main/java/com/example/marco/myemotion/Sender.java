package com.example.marco.myemotion;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by marco on 02/03/18.
 */

public class Sender implements Runnable {

    private String COMMAND;
    Socket socket;
    PrintWriter pw;

    public Sender(String COMMAND) {
        this.COMMAND = COMMAND;
    }

    public String getCOMMAND(){
        return COMMAND;
    }

    @Override
    public void run() {

        try {
            socket = new Socket("192.168.4.1",9999);
            pw = new PrintWriter(socket.getOutputStream());
            pw.write(COMMAND);
            pw.flush();
            Log.d("sto inviando","yes");
            pw.close();
            socket.close();
        } catch (IOException e) {
            Log.d("doInBackground","error to create the socket");
        }
    }

}