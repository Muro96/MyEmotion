package com.example.marco.myemotion;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by marco on 21/03/18.
 */

public class Parser implements Runnable {

    private ArrayList<Registrazione> regs = new ArrayList<>();
    private String path_video;
    private String path_data;
    private Context ctx;

    private final String TAG = "ERROR_PARSER";

    public Parser(String path_video, String path_data, Context ctx) {
        this.path_video = path_video;
        this.path_data = path_data;
        this.ctx = ctx;
    }

    @Override
    public void run() {
        Scanner sc;
        String temp [];
        String time;
        Sessione sessione;

        try {
            // Compongo la lista delle registrazioni lette da file
            sc = new Scanner(new File(Environment.getExternalStorageDirectory(), Reciver.FOLDER + "/" + path_data));

            while(sc.hasNextLine()) {
                temp = sc.nextLine().split(";");
                if(temp.length == 3)
                    regs.add(new Registrazione(temp[0], temp[1], temp[2]));
                else
                    Log.e(TAG, "I campi sono di un numero sbagliato.\nLa registrazione verrà saltata!");
            }

            //Ancora meglio se prendo la parte iniziale del nome del video/dati in modo da renderlo omogeneo
            time = path_video.substring(0, 17);

            //Creo la sessione e la carico, quindi la salvo nella lista delle sessioni disponibili
            sessione = new Sessione(time, getVideoDuration(path_video), path_video, path_data, regs);

            if(sessione.upload()) MainActivity.sessioni.add(sessione);
            else Log.e(TAG, "Problemi durante il caricamento della sessione");

            Log.e(TAG, "" + MainActivity.sessioni.size());
            for (Sessione ses : MainActivity.sessioni) {
                System.out.println(sessione.toString());
            }

        } catch(IOException e) {
            e.printStackTrace();
            Log.e("Errore", "Importation error: File not found!");
        }

        viewFolderContent();
    }

    /**
     * Permette di recuperare la durate del video dal percorso
     * @param path_video
     * @return Durata del video
     */
    private String getVideoDuration(String path_video) {
        return  "empty";
    }

    private void viewFolderContent() {
        File folder = ctx.getDir("", Context.MODE_PRIVATE);

        if(folder != null && folder.isDirectory() && folder.list() != null)
            for(File p : folder.listFiles())
                Log.d(TAG, p.getName());
        else
            Log.e(TAG, "Mannaggia, xe vodo");
    }
}
