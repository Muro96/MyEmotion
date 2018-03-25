package com.example.marco.myemotion;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by marco on 15/03/18.
 */

public class Sessione implements Parcelable {
    String id_sessione;
    Context ct;
    String data;
    String durata;
    String path_video;
    String path_file;
    private ArrayList<Registrazione> registrazioni;

    public Sessione(Context ct, String data, String durata, String path_video, String path_file, ArrayList<Registrazione> registrazioni) {
        this.ct = ct;
        this.data = data;
        this.durata = durata;
        this.path_video = path_video;
        this.path_file = path_file;
        this.registrazioni = registrazioni;
    }

    public Sessione(String id_sessione, String data, String durata, String path_video, String path_file) {
        this.id_sessione=id_sessione;
        this.data=data;
        this.durata=durata;
        this.path_video=path_video;
        this.path_file=path_file;

    }



    @Override
    public String toString() {
        return id_sessione+" "+data+" "+durata+" "+path_video+" "+path_file+" ";


    }

    /**
     *
     * @param in Cosa che passa oggetti tra activity
     */
    public Sessione(Parcel in) {
        String[] data = new String[5];
        in.readStringArray(data);
        this.id_sessione=data[0];
        this.data=data[1];
        this.durata=data[2];
        this.path_video=data[3];
        this.path_file=data[4];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeStringArray(new String[]{
                this.id_sessione,
                this.data,
                this.durata,
                this.path_video,
                this.path_file

        });

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public Sessione createFromParcel(Parcel parcel) {
            return new Sessione(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return new Sessione[i];
        }
    };

    public void upload() {
        DBHelper dbh = new DBHelper(ct);
        String interr;
        // Inserimento dati sessione data, durata, path_video, path_file
        interr = "INSERT INT Sessioni(data,..) VALUES('";

        //esegui query

        //GET INDICE DELLA SESSIONE

        interr = "INSERT INTO REGISTRAZIONI(VAL1, VAL2, FK_SESSIONE";


    }

    private String cleaned(String s) {
        return s.replace(",", ".");
    }

}

