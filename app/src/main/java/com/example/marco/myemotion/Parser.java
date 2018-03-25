package com.example.marco.myemotion;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by marco on 21/03/18.
 */

public class Parser implements Runnable{

    private InputStream data;
    private ArrayList<Registrazione> regs;

    public Parser(InputStream data) {
        this.data = data;
        this.regs = new ArrayList<>();
    }

    @Override
    public void run() {
        Scanner sc;
        String temp [];
        Sessione sessione;
        String adesso;

        try {
            sc = new Scanner(data);

            while(sc.hasNextLine()) {
                temp = sc.nextLine().split(";");
                regs.add(new Registrazione(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]), Long.parseLong(temp[2])));
            }

            //adesso = Calendar.getInstance().get
            //essione = new Sessione(this, regs)

          //  sessione.upload();

        } catch(Exception e) {
            Log.e("Errore", "Importation error!");
        }
    }
}
