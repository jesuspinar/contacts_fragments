package com.germangascon.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements IContactosListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentListado frgListado = (FragmentListado)getFragmentManager().findFragmentById(R.id.FrgListado);
        //Si hemos utilizado la librería de soporte deberemos hacerlo de la siguiente forma:
        //FragmentListado frgListado = (FragmentListado)getSupportFragmentManager().findFragmentById(R.id.FrgListado);
        frgListado.setContactosListener(this);
    }

    @Override
    public void onContactoSeleccionado(Contacto c) {
        boolean hayDetalle = (getFragmentManager().findFragmentById(R.id.FrgDetalle) != null);
        if(hayDetalle) {
            ((FragmentDetalle)getFragmentManager().findFragmentById(R.id.FrgDetalle)).mostrarDetalle(c);
        } else {
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra(DetalleActivity.EXTRA_TEXTO, c);
            startActivity(i);

        }
    }
}
