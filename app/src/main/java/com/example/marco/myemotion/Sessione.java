package com.example.marco.myemotion;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marco on 15/03/18.
 */

public class Sessione {
    private String index;
    private String data;
    private String durata;
    private String path_video;
    private String path_file;
    private ArrayList<Registrazione> registrazioni;

    private final String TAG = "ERROR_SESSION";

    public Sessione(String index, String data, String durata, String path_video, String path_file, ArrayList<Registrazione> registrazioni) {
        this.index = index;
        this.data = data;
        this.durata = durata;
        this.path_video = path_video;
        this.path_file = path_file;
        this.registrazioni = registrazioni;
    }

    public Sessione(String data, String durata, String path_video, String path_file, ArrayList<Registrazione> registrazioni) {
        this.data = data;
        this.durata = durata;
        this.path_video = path_video;
        this.path_file = path_file;
        this.registrazioni = registrazioni;
        this.index = "empty";
    }

    public String getIndex() {
        return index;
    }



    @Override
    public String toString() {
        return id_sessione+" "+data+" "+durata+" "+path_video+" "+path_file+" ";


    }

    public String getPath_video() {
        return path_video;
    }

    public String getPath_file() {
        return path_file;
    }

    public ArrayList<Registrazione> getRegistrazioni() {
        return registrazioni;
    }

    public boolean upload() {
        DBHelper dbh = new DBHelper(MainActivity.getContext());
        String interr;
        String id = "0";

        try {
            interr = "INSERT INTO Sessione(data, durata, path_video, path_mpu) " +
                    "VALUES('" + getData() + "', '" + getDurata() + "', '" + getPath_video() + "', '" + getPath_file() + "')";

            //Eseguo query


            //Prendo l'index generato

            for(Registrazione t : registrazioni) {
                interr = "INSERT INTO Registrazioni(x, y, time_stamp, fk_sessione) " +
                        "VALUES(" + cleaned(t.getX()) + ", " + cleaned(t.getY()) + ", " + cleaned(t.getTimestamp()) + ", " + id + ")";
                //Inserisco nel db le varie registrazioni linkate alla sessione attuale
            }
        } catch(Exception e) {
            Log.e(TAG, "SQLite issues");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private String cleaned(String s) {
        return (s.replace(",", "."));
    }

}

