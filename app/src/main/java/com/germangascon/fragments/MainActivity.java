package com.germangascon.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;

public class MainActivity extends AppCompatActivity implements IContactosListener, FragmentOnAttachListener {
    private FragmentListado frgListado;
    private FragmentDetalle frgDetalle;
    private boolean tabletLayout;
    private Contacto[] contactos;

    public MainActivity() {
        super(R.layout.activity_main);
        frgListado = null;
        frgDetalle = null;
        tabletLayout = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabletLayout = findViewById(R.id.FrgDetalle) != null;

        ContactParser parser = new ContactParser(this);
        if(parser.parse()) {
            this.contactos = parser.getContactos();
        }

        if(savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.FrgListado, FragmentListado.class, null)
                    .commit();

            manager.addFragmentOnAttachListener(this);
        }
    }

    @Override
    public void onContactoSeleccionado(int position) {
        Contacto contacto = frgListado.getDatos()[position];
        if(tabletLayout) {
            frgDetalle.mostrarDetalle(contacto);
        } else {
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra(DetalleActivity.EXTRA_TEXTO, contacto);
            startActivity(i);
        }
    }

    @Override
    public void onAttachFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        if(fragment.getId() == R.id.FrgListado) {
            frgListado = (FragmentListado) fragment;
            frgListado.setContactosListener(contactos, this);
            if(tabletLayout) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(FragmentDetalle.EXTRA_CONTACTO, contactos[0]);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.FrgDetalle, FragmentDetalle.class, bundle)
                        .commit();

            }
        }
        if(fragment.getId() == R.id.FrgDetalle) {
            frgDetalle = (FragmentDetalle) fragment;
        }
    }
}
