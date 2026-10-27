package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paquetes")
public class PaquetesController {

    private final PaqueteService paquetesService;

    @Autowired
    public PaquetesController(PaqueteService paquetesService) {
        this.paquetesService = paquetesService;
    }

    @GetMapping
    public ResponseEntity<List<Paquete>> getAllPaquetes() {
        return new ResponseEntity<>(paquetesService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Paquete>> getPaquetesByQuery(
            @RequestParam String nombre) {
        return new ResponseEntity<>(
                paquetesService.findByNombre(nombre), HttpStatus.OK);
    }

    @GetMapping("/cabecera")
    public ResponseEntity<String> getAgentInfo(
            @RequestHeader("User-Agent") String userAgent) {
        return new ResponseEntity<>(
                "Información del cliente (User-Agent): " + userAgent,
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paquete> getPaqueteById(
            @PathVariable String id) {
        Paquete paquete = paquetesService.findById(id);

        if (paquete != null) {
            return new ResponseEntity<>(paquete, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Paquete> createPaquete(
            @RequestBody Paquete paquete) {
        return new ResponseEntity<>(
                paquetesService.save(paquete), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paquete> updatePaquete(
            @PathVariable String id,
            @RequestBody Paquete paquete) {
        Paquete existente = paquetesService.findById(id);

        if (existente != null) {
            paquete.setId(id);
            return new ResponseEntity<>(
                    paquetesService.update(paquete), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Paquete> patchUpdatePaquete(
            @PathVariable String id,
            @RequestBody Map<String, Object> updates) {
        Paquete paquete = paquetesService.patch(id, updates);

        if (paquete != null) {
            return new ResponseEntity<>(paquete, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaquete(
            @PathVariable String id) {
        if (paquetesService.findById(id) != null) {
            paquetesService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}