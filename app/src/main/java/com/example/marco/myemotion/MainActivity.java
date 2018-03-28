package com.example.marco.myemotion;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static Context context;
    private static DBmanager manager;
    private String TAG = "ERROR_MAINACT";

    public static ArrayList<Sessione> sessioni = new ArrayList<>();

    public final static int PACKET_SIZE = 512;
    public final static int com = 9999; // Porta per l'invio dei comandi
    public final static int recport = 9000; // Porta per la ricezione dei dati
    //public final static String extIP = "192.168.1.156"; //Home IP
    public static String extIP = "192.168.4.1"; // WiFi direct IP

    public static Context getContext() {
        return context;
    }

    /**
     * Navigazione per il menù alla base
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle("Home");
                    Home fragmentHome = new Home();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content, fragmentHome);
                    transaction.commit();
                    return true;
                case R.id.navigation_archive:
                    setTitle("Archive");
                    Archieve fragmentArchieve = new Archieve();
                    FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                    transaction1.replace(R.id.content, fragmentArchieve);
                    transaction1.commit();
                    return true;
                case R.id.navigation_options:
                    setTitle("Options");
                    return true;
            }
            return false;
        }
    };

    /**
     * Permette di abilitare il wifi
     */
    public void enableWifi() {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
        intent.setComponent(cn);
        //intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            Toast.makeText(getBaseContext(), "Wifi already enabled", Toast.LENGTH_LONG).show();
        } else {
            //WifiConfiguration wifiConfiguration = new WifiConfiguration();
            // wifiConfiguration.SSID = "\"".concat("VeniceConnected").concat("\"");
            //int netId = wifiManager.addNetwork(wifiConfiguration);
            // boolean wifiEnabled = wifiManager.enableNetwork(netId, true);
            wifiManager.setWifiEnabled(true);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Home fragment = new Home();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();

        // Faccio partire il Thread che aspetta di ricevere il video con i file dati
        new Thread(new Reciver(recport, getApplicationContext())).start();
    }
}


