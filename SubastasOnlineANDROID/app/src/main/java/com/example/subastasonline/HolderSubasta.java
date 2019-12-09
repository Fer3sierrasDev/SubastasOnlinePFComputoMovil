package com.example.subastasonline;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderSubasta extends RecyclerView.ViewHolder {

    private TextView nombre_subasta;
    private TextView nombre_producto;
    private TextView precio_subasta;
    private TextView descripcion_subasta;
    private Button btn_entrar_subasta;

    public HolderSubasta(@NonNull View itemView) {
        super(itemView);
        nombre_subasta = (TextView) itemView.findViewById(R.id.ed_nombre_subasta_lista_info);
        nombre_producto = (TextView) itemView.findViewById(R.id.ed_nombre_subasta_producto_lista_info);
        precio_subasta = (TextView) itemView.findViewById(R.id.ed_precio_subasta_lista_info);
        descripcion_subasta = (TextView) itemView.findViewById(R.id.ed_descripcion_subasta_lista_info);
        btn_entrar_subasta = (Button) itemView.findViewById(R.id.btn_entrar_subasta_lista);
    }

    public TextView getNombre_subasta() {
        return nombre_subasta;
    }

    public void setNombre_subasta(TextView nombre_subasta) {
        this.nombre_subasta = nombre_subasta;
    }

    public TextView getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(TextView nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public TextView getPrecio_subasta() {
        return precio_subasta;
    }

    public void setPrecio_subasta(TextView precio_subasta) {
        this.precio_subasta = precio_subasta;
    }

    public TextView getDescripcion_subasta() {
        return descripcion_subasta;
    }

    public void setDescripcion_subasta(TextView descripcion_subasta) {
        this.descripcion_subasta = descripcion_subasta;
    }

    public Button getBtn_entrar_subasta() {
        return btn_entrar_subasta;
    }

    public void setBtn_entrar_subasta(Button btn_entrar_subasta) {
        this.btn_entrar_subasta = btn_entrar_subasta;
    }
}
