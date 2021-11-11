package com.germangascon.fragments;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.View;
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
    private final StringBuilder sb;

    public FragmentDetalle() {
        super(R.layout.fragment_detalle);
        sb = new StringBuilder();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvName = view.findViewById(R.id.tvName);
        tvSurnames = view.findViewById(R.id.tvSurnames);
        tvBirth = view.findViewById(R.id.tvBirth);
        tvCompany = view.findViewById(R.id.tvCompany);
        tvAddress = view.findViewById(R.id.tvAddress);
        tvPhone1 = view.findViewById(R.id.tvPhone1);
        tvPhone2 = view.findViewById(R.id.tvPhone2);
        tvEmail = view.findViewById(R.id.tvEmail);
    }

    public void mostrarDetalle(Contacto contacto) {
        tvName.setText(contacto.getName());
        sb.setLength(0);
        sb.append(contacto.getName()).append(" ").append(contacto.getFirstSurname());
        tvSurnames.setText(sb.toString());
        tvBirth.setText(contacto.getBirth());
        tvCompany.setText(contacto.getCompany());
        tvAddress.setText(contacto.getAddress());
        tvPhone1.setText(contacto.getPhone1());
        tvPhone2.setText(contacto.getPhone2());
        tvEmail.setText(contacto.getEmail());
    }
}
