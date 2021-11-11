package com.germangascon.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements IContactosListener {

    private FragmentListado frgListado;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            frgListado = (FragmentListado) getSupportFragmentManager().findFragmentById(R.id.FrgListado);
            assert frgListado != null;
            frgListado.setContactosListener(this);
        }
    }

    @Override
    public void onContactoSeleccionado(int position) {
        boolean hayDetalle = (getSupportFragmentManager().findFragmentById(R.id.FrgDetalle) != null);
        Contacto contacto = frgListado.getDatos()[position];
        if(hayDetalle) {
            FragmentDetalle frgDetalle = (FragmentDetalle)getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
            assert frgDetalle != null;
            frgDetalle.mostrarDetalle(contacto);
        } else {
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra(DetalleActivity.EXTRA_TEXTO, contacto);
            startActivity(i);
        }
    }
}
