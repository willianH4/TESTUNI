package com.unipay.uni.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unipay.uni.R;
import com.unipay.uni.models.CheckPhoneNumber;

import java.util.ArrayList;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder> {

    ArrayList<CheckPhoneNumber> listaContactos;

    public ListaContactosAdapter(Context context, ArrayList<CheckPhoneNumber> listaContactos){
        this.listaContactos = listaContactos;
    }

    @NonNull
    @Override
    public ListaContactosAdapter.ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacto, null, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaContactosAdapter.ContactoViewHolder holder, int position) {
        holder.tvNombre.setText(listaContactos.get(position).getNombre());
        holder.tvTelefono.setText(listaContactos.get(position).getTelefono());
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombre, tvTelefono;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
        }
    }
}
