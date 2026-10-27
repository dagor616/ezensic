package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class PaquetesRepository {

    // Simulamos una base de datos con un Map
    private final Map<String, Paquete> baseDeDatos = new HashMap<>();

    // Guardar un paquete
    public Paquete save(Paquete paquete) {
        baseDeDatos.put(paquete.getId(), paquete);
        return paquete;
    }

    // Encontrar paquete por ID
    public Paquete findById(String id) {
        return baseDeDatos.get(id);
    }

    // Listar todos los paquetes
    public List<Paquete> findAll() {
        return new ArrayList<>(baseDeDatos.values());
    }

    // Filtrar por nombre
    public List<Paquete> findByNombreContaining(String nombre) {
        return baseDeDatos.values().stream()
                .filter(paquete -> paquete.getNombre()
                        .toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Eliminar un paquete
    public void deleteById(String id) {
        baseDeDatos.remove(id);
    }

    // Actualizar un paquete
    public Paquete update(Paquete paquete) {
        if (baseDeDatos.containsKey(paquete.getId())) {
            baseDeDatos.put(paquete.getId(), paquete);
            return paquete;
        }
        return null;
    }
}