package com.example.marco.myemotion;

import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothServerSocket;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    public Context context;
    public static DBmanager manager;
    public Sessione s;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new DBmanager(this);
        try {
            manager.getDbhelper().add(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // enableWifi();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Home fragment = new Home();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }


    public void enableWifi() {
        Intent intent = new Intent(Intent.ACTION_MAIN,null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ComponentName cn = new ComponentName("com.android.settings","com.android.settings.wifi.WifiSettings");
        intent.setComponent(cn);
        //intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        /*WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            Toast.makeText(getBaseContext(), "Wifi already enabled", Toast.LENGTH_LONG).show();
        } else {
            //WifiConfiguration wifiConfiguration = new WifiConfiguration();
            // wifiConfiguration.SSID = "\"".concat("VeniceConnected").concat("\"");
            //int netId = wifiManager.addNetwork(wifiConfiguration);
            // boolean wifiEnabled = wifiManager.enableNetwork(netId, true);
            wifiManager.setWifiEnabled(true); */


        }

    }


