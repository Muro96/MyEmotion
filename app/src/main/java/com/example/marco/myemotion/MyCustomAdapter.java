package com.example.marco.myemotion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by marco on 05/03/18.
 */

public class MyCustomAdapter extends ArrayAdapter {
    private  static final String TAG = "MyCustomAdapter";
    private Context ct;
    private int textViewResourceId;



    public MyCustomAdapter(Context ct, int textViewResourceId, List sessione) {
        super(ct, textViewResourceId,sessione);
        this.ct = ct;
        this.textViewResourceId=textViewResourceId;

    }

    private class ViewHolder {

        TextView data;
        TextView durata;
    }




    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        final View result;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(ct);
            convertView = inflater.inflate(textViewResourceId, parent , false);

            holder = new ViewHolder();
            holder.data = (TextView) convertView.findViewById(R.id.textData);
            holder.durata = (TextView) convertView.findViewById(R.id.textDurata);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        result = convertView;
        holder.data.setText("data");
        holder.durata.setText("durata");
        return convertView;


    }

}

