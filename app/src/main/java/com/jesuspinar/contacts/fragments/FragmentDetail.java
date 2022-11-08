package com.jesuspinar.contacts.fragments;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.jesuspinar.contacts.model.Contact;
import com.jesuspinar.fragments.R;

public class FragmentDetail extends Fragment {

    public interface IOnAttachListener {
        Contact getContact();
    }

    private TextView tvName;
    private TextView tvSurnames;
    private TextView tvBirth;
    private TextView tvCompany;
    private TextView tvAddress;
    private TextView tvPhone1;
    private TextView tvPhone2;
    private TextView tvEmail;
    private final StringBuilder sb;
    private Contact contact;

    public FragmentDetail() {
        super(R.layout.fragment_detalle);
        sb = new StringBuilder();
        contact = null;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName = view.findViewById(R.id.tvName);
        tvSurnames = view.findViewById(R.id.tvSurnames);
        tvBirth = view.findViewById(R.id.tvBirth);
        tvCompany = view.findViewById(R.id.tvCompany);
        tvAddress = view.findViewById(R.id.tvAddress);
        tvPhone1 = view.findViewById(R.id.tvPhone1);
        tvPhone2 = view.findViewById(R.id.tvPhone2);
        tvEmail = view.findViewById(R.id.tvEmail);
        if(contact != null)
            displayDetail(contact);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        contact = attachListener.getContact();
    }

    public void displayDetail(Contact contact) {
        tvName.setText(contact.getName());
        sb.setLength(0);
        sb.append(contact.getFirstSurname()).append(" ").append(contact.getSecondSurname());
        tvSurnames.setText(sb.toString());
        tvBirth.setText(contact.getBirth());
        tvCompany.setText(contact.getCompany());
        tvAddress.setText(contact.getAddress());
        tvPhone1.setText(contact.getPhone1());
        tvPhone2.setText(contact.getPhone2());
        tvEmail.setText(contact.getEmail());
    }
}
