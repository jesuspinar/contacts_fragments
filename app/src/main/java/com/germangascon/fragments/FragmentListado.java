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
    private IClickListener listener;

    public FragmentListado() {
        super(R.layout.fragment_listado);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvListado = view.findViewById(R.id.rvListado);
        AdaptadorContactos adaptador = new AdaptadorContactos(datos);
        adaptador.setListener(listener);
        rvListado.setAdapter(adaptador);
        rvListado.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    public void setContactosListener(Contacto[] contactos, IClickListener listener) {
        this.datos = contactos;
        this.listener = listener;
    }
}

