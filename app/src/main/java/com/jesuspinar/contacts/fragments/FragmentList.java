package com.jesuspinar.contacts.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.jesuspinar.contacts.controller.IOnClickListener;
import com.jesuspinar.contacts.controller.ContactAdapter;
import com.jesuspinar.contacts.model.Contact;
import com.jesuspinar.fragments.R;

public class FragmentList extends Fragment {
    private Contact[] datos;
    private IOnClickListener clickListener;

    public interface IOnAttachListener{
        Contact[] getContacts();
    }

    public FragmentList() {
        super(R.layout.fragment_listado);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ContactAdapter adapter = new ContactAdapter(getContext(), datos, clickListener);

        RecyclerView recyclerView = view.findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        clickListener = (IOnClickListener) context;
        IOnAttachListener attachListener = (IOnAttachListener) context;
        datos = attachListener.getContacts();
    }
}

