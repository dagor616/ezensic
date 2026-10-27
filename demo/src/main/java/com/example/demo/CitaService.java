package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaService {

    private final CitaRepository citaRepository;

    @Autowired
    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
        initSampleData();
    }

    // Inicializamos algunos datos de ejemplo
    private void initSampleData() {
        save(new Cita("Juan Pérez", "juan@example.com",
                "Limpieza facial", "2026-11-01",
                "09:00", "Pendiente"));

        save(new Cita("María López", "maria@example.com",
                "Masaje relajante", "2026-11-02",
                "10:00", "Confirmada"));

        save(new Cita("Carlos Ruiz", "carlos@example.com",
                "Tratamiento corporal", "2026-11-03",
                "14:00", "Pendiente"));
    }

    // Crear una nueva cita
    public Cita save(Cita cita) {
        return citaRepository.save(cita);
    }

    // Obtener una cita por ID
    public Cita findById(String id) {
        return citaRepository.findById(id);
    }

    // Listar todas las citas
    public List<Cita> findAll() {
        return citaRepository.findAll();
    }

    // Buscar por nombre del cliente
    public List<Cita> findByNombre(String nombre) {
        return citaRepository.findByNombreContaining(nombre);
    }

    // Actualizar una cita
    public Cita update(Cita cita) {
        return citaRepository.update(cita);
    }

    // Actualización parcial
    public Cita patch(String id, Map<String, Object> updates) {
        Cita cita = citaRepository.findById(id);

        if (cita != null) {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "nombreCliente":
                        cita.setNombreCliente((String) value);
                        break;
                    case "email":
                        cita.setEmail((String) value);
                        break;
                    case "servicio":
                        cita.setServicio((String) value);
                        break;
                    case "fecha":
                        cita.setFecha((String) value);
                        break;
                    case "hora":
                        cita.setHora((String) value);
                        break;
                    case "estado":
                        cita.setEstado((String) value);
                        break;
                }
            });

            return citaRepository.update(cita);
        }

        return null;
    }

    // Eliminar una cita
    public void deleteById(String id) {
        citaRepository.deleteById(id);
    }
}
