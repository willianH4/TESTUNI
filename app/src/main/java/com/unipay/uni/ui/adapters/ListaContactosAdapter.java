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
import java.util.Locale;
import java.util.stream.Collectors;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder> {

    private Context mCtx;
    ArrayList<CheckPhoneNumber> listaContactos;
    // Lista para contactos de la busqueda
    ArrayList<CheckPhoneNumber> listaBusqueda;

    public ListaContactosAdapter(Context mCtx, ArrayList<CheckPhoneNumber> listaContactos){
        this.mCtx = mCtx;
        this.listaContactos = listaContactos;
        listaBusqueda = new ArrayList<>();
        listaContactos.addAll(listaBusqueda);
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

    public void filtrado(String svBuscar){
        int longitudCadena = svBuscar.length();
        if (longitudCadena == 0) {
            listaContactos.clear();
            listaContactos.addAll(listaBusqueda);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<CheckPhoneNumber> coleccion = listaContactos.stream().
                        filter(i -> i.getContactName().toLowerCase().contains(svBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaContactos.clear();
                listaContactos.addAll(coleccion);
            } else {
                for (CheckPhoneNumber c: listaBusqueda
                     ) {
                    if (c.getContactName().toLowerCase().contains(svBuscar.toLowerCase()));
                    listaContactos.add(c);
                }
            }
        }
        notifyDataSetChanged();
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
