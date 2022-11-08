package com.jesuspinar.contacts.controller;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jesuspinar.contacts.model.Contact;
import com.jesuspinar.fragments.R;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactsViewHolder> {
    private final Context context; //TODO: when contact has img
    private final Contact[] data;
    private IOnClickListener listener;

    public ContactAdapter(Context context, Contact[] data, IOnClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contacto, viewGroup, false);
        return new ContactsViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder contactosViewHolder, int position) {
        Contact contacto = data[position];
        contactosViewHolder.bindContacto(contacto);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public static class ContactsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvName;
        private final TextView tvPhone1;
        private final StringBuilder sb;
        private final IOnClickListener listener;

        public ContactsViewHolder(@NonNull View itemView, IOnClickListener listener) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvPhone1 = itemView.findViewById(R.id.tvPhone1);
            sb = new StringBuilder();

            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        public void bindContacto(Contact contact) {
            sb.setLength(0);
            sb.append(contact.getName()).append(" ").append(contact.getFirstSurname());
            tvName.setText(sb.toString());
            tvPhone1.setText(contact.getPhone1());
        }

        @Override
        public void onClick(View v) {
            if(listener != null) {
                listener.onClick(getAdapterPosition());
            }
        }
    }
}
