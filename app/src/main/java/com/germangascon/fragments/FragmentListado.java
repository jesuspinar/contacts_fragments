package com.germangascon.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class FragmentListado extends Fragment {
    private Contacto[] datos;
    private IContactosListener listener;

    public FragmentListado() {
        super(R.layout.fragment_listado);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ContactParser parser = new ContactParser(requireContext());
        if(parser.parse()) {
            this.datos = parser.getContactos();
        }
        RecyclerView rvListado = view.findViewById(R.id.rvListado);
        rvListado.setAdapter(new AdaptadorContactos(datos, listener));
        rvListado.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    public void setContactosListener(IContactosListener listener) {
        this.listener = listener;
    }

    public Contacto[] getDatos() {
        return datos;
    }
}

