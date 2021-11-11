package com.germangascon.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;

import java.util.Objects;

public class DetalleActivity extends AppCompatActivity {

    public static final String EXTRA_TEXTO = "com.germangascon.fragments.EXTRA_TEXTO";

    public DetalleActivity() {
        super(R.layout.activity_detalle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Contacto contacto = (Contacto) Objects.requireNonNull(getIntent().getSerializableExtra(EXTRA_TEXTO));
        FragmentOnAttachListener listener = new FragmentOnAttachListener() {
            @Override
            public void onAttachFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
                if(fragment.getId() == R.id.FrgDetalle) {
                    ((FragmentDetalle)(fragment)).mostrarDetalle(contacto);
                }
            }
        };
    }
}
