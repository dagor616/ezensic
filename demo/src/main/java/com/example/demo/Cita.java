package com.example.demo;

import java.util.UUID;

public class Cita {

    private String id;
    private String nombreCliente;
    private String email;
    private String servicio;
    private String fecha;
    private String hora;
    private String estado;

    public Cita() {
        this.id = UUID.randomUUID().toString();
    }

    public Cita(String nombreCliente, String email,
                String servicio, String fecha,
                String hora, String estado) {
        this.id = UUID.randomUUID().toString();
        this.nombreCliente = nombreCliente;
        this.email = email;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
