package com.germangascon.fragments;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AdaptadorContactos extends RecyclerView.Adapter<AdaptadorContactos.ContactosViewHolder> {
    private final Contacto[] datos;
    private final IClickListener listener;

    public AdaptadorContactos(Contacto[] datos, IClickListener listener) {
        this.datos = datos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contacto, viewGroup, false);
        return new ContactosViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactosViewHolder contactosViewHolder, int position) {
        Contacto contacto = datos[position];
        contactosViewHolder.bindContacto(contacto);
    }

    @Override
    public int getItemCount() {
        return datos.length;
    }

    public static class ContactosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvName;
        private final TextView tvPhone1;
        private final StringBuilder sb;
        private final IClickListener listener;

        public ContactosViewHolder(@NonNull View itemView, IClickListener listener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone1 = itemView.findViewById(R.id.tvPhone1);
            sb = new StringBuilder();
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        public void bindContacto(Contacto contacto) {
            sb.setLength(0);
            sb.append(contacto.getName()).append(" ").append(contacto.getFirstSurname());
            tvName.setText(sb.toString());
            tvPhone1.setText(contacto.getPhone1());
        }

        @Override
        public void onClick(View v) {
            if(listener != null) {
                listener.onClick(getAdapterPosition());
            }
        }
    }
}
