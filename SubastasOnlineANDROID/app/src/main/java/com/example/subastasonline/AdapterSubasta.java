package com.example.subastasonline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterSubasta extends RecyclerView.Adapter<HolderSubasta> {

    List<Subasta> listaSubastas = new ArrayList<>();
    private Context c;

    public AdapterSubasta(Context c) {
        this.c = c;
    }

    public void addSubasta(Subasta s){
        listaSubastas.add(s);
        notifyItemInserted(listaSubastas.size());
    }

    @NonNull
    @Override
    public HolderSubasta onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_view_subastas, parent, false);
        return new HolderSubasta(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderSubasta holder, int position) {
        holder.getNombre_subasta().setText(listaSubastas.get(position).getNombreSubasta());
        holder.getNombre_producto().setText(listaSubastas.get(position).getNombreProducto());
        holder.getPrecio_subasta().setText(listaSubastas.get(position).getPrecioInicial());
        holder.getDescripcion_subasta().setText(listaSubastas.get(position).getDescripcionSubasta());
    }

    @Override
    public int getItemCount() {
        return listaSubastas.size();
    }
}
