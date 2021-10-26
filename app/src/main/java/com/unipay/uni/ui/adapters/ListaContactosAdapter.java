package com.unipay.uni.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unipay.uni.R;
import com.unipay.uni.models.CheckPhoneNumber;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder> {

    private Context mCtx;
    private int selectedStarPosition = -1;
    ArrayList<CheckPhoneNumber> listaContactos;
    ArrayList<CheckPhoneNumber> contactosTotales;
    // Lista para contactos de la busqueda
    ArrayList<CheckPhoneNumber> listaBusqueda;
    private AdapterView.OnItemClickListener onItemClickListener;

    public ListaContactosAdapter(Context mCtx, ArrayList<CheckPhoneNumber> contactosTotales){
        this.mCtx = mCtx;
        this.contactosTotales = contactosTotales;
//        this.contactosSinUni = contactosSinUni;
        listaBusqueda = new ArrayList<>();
        contactosTotales.addAll(listaBusqueda);
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.contacto, null);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactoViewHolder holder, int position) {
        CheckPhoneNumber phone = contactosTotales.get(position);
//        CheckPhoneNumber sinUni = contactosSinUni.get(position);
        holder.rbContacto.setChecked(position == selectedStarPosition);
        holder.tvNombre.setText(phone.getContactName());
        holder.tvTelefono.setText(phone.getPhoneNumber());
    }

    public void filtrado(String svBuscar){
        int longitudCadena = svBuscar.length();
        if (longitudCadena == 0) {
            contactosTotales.clear();
            contactosTotales.addAll(listaBusqueda);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<CheckPhoneNumber> coleccion = contactosTotales.stream().
                        filter(i -> i.getContactName().toLowerCase().contains(svBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                contactosTotales.clear();
                contactosTotales.addAll(coleccion);
            } else {
                for (CheckPhoneNumber c: listaBusqueda
                     ) {
                    if (c.getContactName().toLowerCase().contains(svBuscar.toLowerCase()));
                    contactosTotales.add(c);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return contactosTotales.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombre, tvTelefono;
        RadioButton rbContacto;

        public ContactoViewHolder(View itemView) {
            super(itemView);
            rbContacto = itemView.findViewById(R.id.rbContacto);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
