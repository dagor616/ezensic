package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaqueteService {

    private final PaquetesRepository paquetesRepository;

    @Autowired
    public PaqueteService(PaquetesRepository paquetesRepository) {
        this.paquetesRepository = paquetesRepository;
        initSampleData();
    }

    // Inicializamos algunos datos de ejemplo
    private void initSampleData() {
        save(new Paquete("Paquete facial",
                "Limpieza e hidratación facial", 80000, 60));
        save(new Paquete("Paquete relajante",
                "Masaje relajante", 100000, 90));
        save(new Paquete("Paquete corporal",
                "Tratamiento corporal", 120000, 120));
    }

    // Crear un nuevo paquete
    public Paquete save(Paquete paquete) {
        return paquetesRepository.save(paquete);
    }

    // Obtener un paquete por ID
    public Paquete findById(String id) {
        return paquetesRepository.findById(id);
    }

    // Listar todos los paquetes
    public List<Paquete> findAll() {
        return paquetesRepository.findAll();
    }

    // Buscar por nombre
    public List<Paquete> findByNombre(String nombre) {
        return paquetesRepository.findByNombreContaining(nombre);
    }

    // Actualizar un paquete
    public Paquete update(Paquete paquete) {
        return paquetesRepository.update(paquete);
    }

    // Actualización parcial
    public Paquete patch(String id, Map<String, Object> updates) {
        Paquete paquete = paquetesRepository.findById(id);

        if (paquete != null) {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "nombre":
                        paquete.setNombre((String) value);
                        break;
                    case "descripcion":
                        paquete.setDescripcion((String) value);
                        break;
                    case "precio":
                        paquete.setPrecio(
                                ((Number) value).doubleValue());
                        break;
                    case "duracion":
                        paquete.setDuracion(
                                ((Number) value).intValue());
                        break;
                }
            });

            return paquetesRepository.update(paquete);
        }

        return null;
    }

    // Eliminar un paquete
    public void deleteById(String id) {
        paquetesRepository.deleteById(id);
    }
}