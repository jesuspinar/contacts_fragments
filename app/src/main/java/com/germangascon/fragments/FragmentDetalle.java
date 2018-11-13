package com.germangascon.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentDetalle extends Fragment {
    private TextView tvName;
    private TextView tvSurnames;
    private TextView tvBirth;
    private TextView tvCompany;
    private TextView tvAddress;
    private TextView tvPhone1;
    private TextView tvPhone2;
    private TextView tvEmail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_detalle, container, false);

        tvName = (TextView)layout.findViewById(R.id.tvName);
        tvSurnames = (TextView)layout.findViewById(R.id.tvSurnames);
        tvBirth = (TextView)layout.findViewById(R.id.tvBirth);
        tvCompany = (TextView)layout.findViewById(R.id.tvCompany);
        tvAddress = (TextView)layout.findViewById(R.id.tvAddress);
        tvPhone1 = (TextView)layout.findViewById(R.id.tvPhone1);
        tvPhone2 = (TextView)layout.findViewById(R.id.tvPhone2);
        tvEmail = (TextView)layout.findViewById(R.id.tvEmail);

        return layout;
    }

    public void mostrarDetalle(Contacto contacto) {
        tvName.setText(contacto.getName());
        tvSurnames.setText(contacto.getFirstSurname() + " " + contacto.getSecondSurname());
        tvBirth.setText(contacto.getBirth());
        tvCompany.setText(contacto.getCompany());
        tvAddress.setText(contacto.getAddress());
        tvPhone1.setText(contacto.getPhone1());
        tvPhone2.setText(contacto.getPhone2());
        tvEmail.setText(contacto.getEmail());
    }
}
