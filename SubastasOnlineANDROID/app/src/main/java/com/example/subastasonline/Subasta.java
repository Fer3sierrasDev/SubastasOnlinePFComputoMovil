package com.example.subastasonline;

public class Subasta {

    private String nombreSubasta;
    private String nombreProducto;
    private String precioInicial;
    private String descripcionSubasta;


    public Subasta() {

    }

    public Subasta(String nombreSubasta, String nombreProducto, String precioInicial, String descripcionSubasta) {
        this.nombreSubasta = nombreSubasta;
        this.nombreProducto = nombreProducto;
        this.precioInicial = precioInicial;
        this.descripcionSubasta = descripcionSubasta;
    }

    public String getNombreSubasta() {
        return nombreSubasta;
    }

    public void setNombreSubasta(String nombreSubasta) {
        this.nombreSubasta = nombreSubasta;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(String precioInicial) {
        this.precioInicial = precioInicial;
    }

    public String getDescripcionSubasta() {
        return descripcionSubasta;
    }

    public void setDescripcionSubasta(String descripcionSubasta) {
        this.descripcionSubasta = descripcionSubasta;
    }
}
