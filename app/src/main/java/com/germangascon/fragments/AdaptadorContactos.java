package com.germangascon.fragments;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorContactos extends ArrayAdapter<Contacto> {
    private Context context;
    private Contacto[] datos;

    public AdaptadorContactos(Fragment context, Contacto[] datos) {
        super(context.getActivity(), R.layout.listitem_contacto, datos);
        this.datos = datos;
        this.context = context.getActivity();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.listitem_contacto, null);
        TextView tvName = (TextView)item.findViewById(R.id.tvName);
        tvName.setText(datos[position].getName() + " " + datos[position].getFirstSurname());
        TextView tvPhone1 = (TextView)item.findViewById(R.id.tvPhone1);
        tvPhone1.setText(datos[position].getPhone1());
        return item;
    }
}
