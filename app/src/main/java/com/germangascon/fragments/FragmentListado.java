package com.germangascon.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentListado extends Fragment {
    private Contacto[] datos;
    private RecyclerView rvListado;
    private IContactosListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ContactParser parser = new ContactParser(getActivity());
        if(parser.parse()) {
            this.datos = parser.getContactos();
        }
        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvListado = getView().findViewById(R.id.rvListado);
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

