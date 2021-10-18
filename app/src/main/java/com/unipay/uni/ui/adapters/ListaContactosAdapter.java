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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ListaContactosAdapter extends RecyclerView.Adapter<com.unipay.uni.ui.adapters.ListaContactosAdapter.ContactoViewHolder> {

    private Context mCtx;
    List<CheckPhoneNumber> listaContactos;

    public ListaContactosAdapter(Context mCtx, ArrayList<CheckPhoneNumber> listaContactos){
        this.mCtx = mCtx;
        this.listaContactos = listaContactos;
    }

    @NonNull
    @Override
    public ListaContactosAdapter.ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.contacto, null);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactoViewHolder holder, int position) {
        CheckPhoneNumber phone = listaContactos.get(position);
        holder.tvNombre.setText(phone.getContactName());
        holder.tvTelefono.setText(phone.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombre, tvTelefono;

        public ContactoViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
