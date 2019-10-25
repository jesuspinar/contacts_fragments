package com.germangascon.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorContactos extends ArrayAdapter<Contacto> {
    private Context context;
    private Contacto[] datos;
    private StringBuilder sb;

    public AdaptadorContactos(Fragment context, Contacto[] datos) {
        super(context.getActivity(), R.layout.listitem_contacto, datos);
        this.datos = datos;
        this.context = context.getActivity();
        this.sb = new StringBuilder();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.listitem_contacto, parent, false);
        TextView tvName = item.findViewById(R.id.tvName);
        sb.setLength(0);
        sb.append(datos[position].getName()).append(" ").append(datos[position].getFirstSurname());
        tvName.setText(sb.toString());
        TextView tvPhone1 = item.findViewById(R.id.tvPhone1);
        tvPhone1.setText(datos[position].getPhone1());
        return item;
    }
}
