package com.example.marco.myemotion;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Archieve extends Fragment {
    private View view;
    private ListView list;
    private ArrayList<String> videolist = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_archieve,container, false);
        list = (ListView) view.findViewById(R.id.archievelist);
        videolist.add("ciao");
        populateList();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Archieve.this.getActivity(),VideoInfo.class);
                intent.putExtra("videoInfo",videolist.get(position));
                startActivity(intent);
            }
        });

        return view;
    }

    public void populateList(){
        list.setAdapter(new MyCustomAdapter(getContext(),R.layout.listarchieve,videolist));
    }

}
