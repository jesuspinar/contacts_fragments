package com.germangascon.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements IContactosListener {

    FragmentListado frgListado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frgListado = (FragmentListado)getSupportFragmentManager().findFragmentById(R.id.FrgListado);
        frgListado.setContactosListener(this);
    }

    @Override
    public void onContactoSeleccionado(int position) {
        boolean hayDetalle = (getSupportFragmentManager().findFragmentById(R.id.FrgDetalle) != null);
        Contacto contacto = frgListado.getDatos()[position];
        if(hayDetalle) {
            FragmentDetalle frgDetalle = (FragmentDetalle)getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
            frgDetalle.mostrarDetalle(contacto);
        } else {
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra(DetalleActivity.EXTRA_TEXTO, contacto);
            startActivity(i);
        }
    }
}
