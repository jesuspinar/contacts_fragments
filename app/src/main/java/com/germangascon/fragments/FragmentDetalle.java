package com.germangascon.fragments;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
    private StringBuilder sb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_detalle, container, false);

        tvName = layout.findViewById(R.id.tvName);
        tvSurnames = layout.findViewById(R.id.tvSurnames);
        tvBirth = layout.findViewById(R.id.tvBirth);
        tvCompany = layout.findViewById(R.id.tvCompany);
        tvAddress = layout.findViewById(R.id.tvAddress);
        tvPhone1 = layout.findViewById(R.id.tvPhone1);
        tvPhone2 = layout.findViewById(R.id.tvPhone2);
        tvEmail = layout.findViewById(R.id.tvEmail);

        sb = new StringBuilder();

        return layout;
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
