package com.germangascon.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class FragmentListado extends Fragment {
    private Contacto[] datos;
    private ListView lstListado;
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
        lstListado = (ListView)getView().findViewById(R.id.LstListado);
        lstListado.setAdapter(new AdaptadorContactos(this, datos));
        lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                if(listener!=null) {
                    listener.onContactoSeleccionado((Contacto)lstListado.getAdapter().getItem(pos));
                }
            }
        });
    }

    public void setContactosListener(IContactosListener listener) {
        this.listener = listener;
    }
}

