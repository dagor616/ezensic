package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class CitaRepository {

    // Simulamos una base de datos con un Map
    private final Map<String, Cita> baseDeDatos = new HashMap<>();

    // Guardar una cita
    public Cita save(Cita cita) {
        baseDeDatos.put(cita.getId(), cita);
        return cita;
    }

    // Encontrar cita por ID
    public Cita findById(String id) {
        return baseDeDatos.get(id);
    }

    // Listar todas las citas
    public List<Cita> findAll() {
        return new ArrayList<>(baseDeDatos.values());
    }

    // Filtrar por nombre del cliente
    public List<Cita> findByNombreContaining(String nombre) {
        return baseDeDatos.values().stream()
                .filter(cita -> cita.getNombreCliente()
                        .toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Eliminar una cita
    public void deleteById(String id) {
        baseDeDatos.remove(id);
    }

    // Actualizar una cita
    public Cita update(Cita cita) {
        if (baseDeDatos.containsKey(cita.getId())) {
            baseDeDatos.put(cita.getId(), cita);
            return cita;
        }
        return null;
    }
}
