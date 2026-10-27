package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    @Autowired
    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    // Obtener todas las citas
    @GetMapping
    public ResponseEntity<List<Cita>> getAllCitas() {
        List<Cita> citas = citaService.findAll();
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

    // Buscar por nombre del cliente
    @GetMapping("/buscar")
    public ResponseEntity<List<Cita>> getCitasByQuery(
            @RequestParam String nombre) {
        List<Cita> citas = citaService.findByNombre(nombre);
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

    // Cabeceras
    @GetMapping("/cabecera")
    public ResponseEntity<String> getAgentInfo(
            @RequestHeader("User-Agent") String userAgent) {
        String info = "Información del cliente (User-Agent): " + userAgent;
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    // Obtener una cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(
            @PathVariable String id) {
        Cita cita = citaService.findById(id);

        if (cita != null) {
            return new ResponseEntity<>(cita, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear una nueva cita
    @PostMapping
    public ResponseEntity<Cita> createCita(
            @RequestBody Cita cita) {
        Cita newCita = citaService.save(cita);
        return new ResponseEntity<>(newCita, HttpStatus.CREATED);
    }

    // Actualizar una cita existente
    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCita(
            @PathVariable String id,
            @RequestBody Cita cita) {

        Cita existingCita = citaService.findById(id);

        if (existingCita != null) {
            cita.setId(id);
            Cita updatedCita = citaService.update(cita);
            return new ResponseEntity<>(updatedCita, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar solo una parte de los datos
    @PatchMapping("/{id}")
    public ResponseEntity<Cita> patchUpdateCita(
            @PathVariable String id,
            @RequestBody Map<String, Object> updates) {

        Cita updatedCita = citaService.patch(id, updates);

        if (updatedCita != null) {
            return new ResponseEntity<>(updatedCita, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una cita
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(
            @PathVariable String id) {

        Cita existingCita = citaService.findById(id);

        if (existingCita != null) {
            citaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}