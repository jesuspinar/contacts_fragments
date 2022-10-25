package com.germangascon.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;

public class MainActivity extends AppCompatActivity implements IClickListener, FragmentOnAttachListener {
    private final static String KEY_CONTACTOS = "com.germangascon.fragments.contactos";
    private FragmentListado frgListado;
    private boolean tabletLayout;
    private Contacto[] contactos;

    public MainActivity() {
        super(R.layout.activity_main);
        frgListado = null;
        tabletLayout = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabletLayout = findViewById(R.id.FrgDetalle) != null;

        if(savedInstanceState == null) {
            ContactParser parser = new ContactParser(this);
            if(parser.parse()) {
                this.contactos = parser.getContactos();
            }
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.FrgListado, FragmentListado.class, null)
                    .commit();

            manager.addFragmentOnAttachListener(this);
        } else {
            contactos = (Contacto[]) savedInstanceState.getSerializable(KEY_CONTACTOS);
            frgListado = (FragmentListado) getSupportFragmentManager().findFragmentById(R.id.FrgListado);
            if (frgListado != null)
                frgListado.setContactosListener(contactos, this);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_CONTACTOS, contactos);
    }

    @Override
    public void onClick(int position) {
        Contacto contacto = contactos[position];
        if(tabletLayout) {
            ((FragmentDetalle)getSupportFragmentManager().findFragmentById(R.id.FrgDetalle)).mostrarDetalle(contactos[position]);
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
                        .replace(R.id.FrgDetalle, FragmentDetalle.class, bundle)
                        .commit();
            }
        }
    }
}
