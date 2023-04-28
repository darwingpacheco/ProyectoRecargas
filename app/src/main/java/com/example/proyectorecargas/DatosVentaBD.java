package com.example.proyectorecargas;

public class DatosVentaBD {
    private String email;
    private String descripcion;
    private String valor;
    private String telefono;
    private String fecha;
    private String operador;



    public DatosVentaBD(String email, String descripcion, String valor, String telefono, String fecha, String operador) {
        this.email = email;
        this.descripcion = descripcion;
        this.valor = valor;
        this.telefono = telefono;
        this.fecha = fecha;
        this.operador = operador;


    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public void add(DatosVentaBD db){
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }
}
