package com.example.marco.myemotion;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by paolo on 26/03/2018.
 */

public class ArchiviaFile{
    private String filename;
    private InputStream instream;
    private Context ctx;

    public ArchiviaFile(String filename, InputStream instream, Context ctx) {
        this.filename = filename;
        this.instream = instream;
        this.ctx = ctx;
    }

    public void run() {
        OutputStreamWriter out;
        FileOutputStream fos;
        DataInputStream in;
        byte[] buffer;
        int count, i;
        long bytes = 0;

        try {
            //out = new OutputStreamWriter(ctx.openFileOutput(filename, Context.MODE_PRIVATE));
            //out = new OutputStreamWriter();
            fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), Reciver.FOLDER + "/" + filename));
            in = new DataInputStream(new BufferedInputStream(instream));
            buffer = new byte[MainActivity.PACKET_SIZE];

            i = 0;
            while ((count = in.read(buffer)) > 0) {
                //out.write(buffer.toString());
                fos.write(buffer);
                Log.i("Communication", i++ + " - Letto dal buffer #bytes: " + count);
                bytes = bytes + count;
            }
            Log.i("Communication", "Terminato di leggere il video.\nBytes letti: " + bytes);

            fos.close();
            //out.close(); // Chiudo lo stream per il salvataggio
            in.close(); // Chiudo lo stream in ingresso
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
