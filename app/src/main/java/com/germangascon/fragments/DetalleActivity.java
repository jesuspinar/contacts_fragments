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
        if(savedInstanceState == null) {
            Contacto contacto = (Contacto) Objects.requireNonNull(getIntent().getSerializableExtra(EXTRA_TEXTO));
            Bundle bundle = new Bundle();
            bundle.putSerializable(FragmentDetalle.EXTRA_CONTACTO, contacto);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.FrgDetalle, FragmentDetalle.class, bundle)
                    .commit();
        }
    }
}
