package com.example.kontaqtebi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter extends BaseAdapter {

    Context context;
    ArrayList<contact> contacts;
    LayoutInflater inflater;

    public adapter(Context context, ArrayList<contact> contacts) {
        this.context = context;
        this.contacts = contacts;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contacts.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.lv, parent, false);
        }

        TextView tvIcon = convertView.findViewById(R.id.tvIcon);
        TextView tvName = convertView.findViewById(R.id.tvName);

        contact c = contacts.get(position);

        tvName.setText(c.getName());

        if (c.getName() != null && !c.getName().isEmpty()) {
            tvIcon.setText(
                    String.valueOf(c.getName().charAt(0)).toUpperCase()
            );
        }

        convertView.setOnClickListener(v -> {
            Intent i = new Intent(context, meore.class);
            i.putExtra("name", c.getName());
            i.putExtra("phone", c.getPhone());
            context.startActivity(i);
        });

        return convertView;
    }
}
