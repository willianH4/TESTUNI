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
import com.unipay.uni.models.Transaccion;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ListaTransaccionesAdapter extends RecyclerView.Adapter<com.unipay.uni.ui.adapters.ListaTransaccionesAdapter.TransaccionHolder>{


    private Context mCtx;
    List<Transaccion> listaTransacciones;

    public ListaTransaccionesAdapter(Context mCtx, ArrayList<Transaccion> listaTransacciones){
        this.mCtx = mCtx;
        this.listaTransacciones = listaTransacciones;
    }

    @Override
    public TransaccionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.historial_transacciones, null);
        return new TransaccionHolder(view);
    }

    @Override
    public void onBindViewHolder(TransaccionHolder holder, int position) {
        Transaccion transac = listaTransacciones.get(position);
        holder.tvOrigen.setText(transac.getPhoneNumberFrom());
        holder.tvDestino.setText(transac.getPhoneNumberTo());
        holder.tvConcepto.setText(transac.getConcept());
        holder.tvFecha.setText(transac.getCreateDate());
        holder.tvMonto.setText((int) transac.getBalance());
    }

    @Override
    public int getItemCount() {
        return listaTransacciones.size();
    }

    public static class TransaccionHolder extends RecyclerView.ViewHolder {
        TextView tvOrigen, tvDestino, tvConcepto, tvFecha, tvMonto;
        public TransaccionHolder(View itemView) {
            super(itemView);
            tvOrigen = itemView.findViewById(R.id.tvOrigen);
            tvDestino = itemView.findViewById(R.id.tvDestino);
            tvConcepto = itemView.findViewById(R.id.tvConcepto);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvMonto = itemView.findViewById(R.id.tvMonto);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
