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

public class Sender extends AsyncTask<String,Void,Void> {
    Socket socket;
    DataOutputStream data;
    PrintWriter pw;

    @Override
    protected Void doInBackground(String... voids) {

        String message = voids[0];
        try {
            socket = new Socket("192.168.4.13",9999);
            pw = new PrintWriter(socket.getOutputStream());
            pw.write(message);
            pw.flush();
            pw.close();
            socket.close();
        } catch (IOException e) {
            Log.d("doInBackground","error to create the socket");
        }
        return null;
    }

}
