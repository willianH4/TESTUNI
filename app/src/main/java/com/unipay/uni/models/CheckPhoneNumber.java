package com.unipay.uni.models;

public class CheckPhoneNumber {
    public String nombre;
    public String telefono;

    public CheckPhoneNumber() {
    }

    public CheckPhoneNumber(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
