package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductosRepository productosRepository;

    @Autowired
    public ProductoService(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
        initSampleData();
    }

    // Inicializamos algunos datos de ejemplo
    private void initSampleData() {
        save(new Producto("Crema facial",
                "Crema hidratante", 35000, 20));
        save(new Producto("Protector solar",
                "Protección solar facial", 45000, 15));
        save(new Producto("Jabón facial",
                "Limpieza facial", 18000, 30));
    }

    // Crear un nuevo producto
    public Producto save(Producto producto) {
        return productosRepository.save(producto);
    }

    // Obtener un producto por ID
    public Producto findById(String id) {
        return productosRepository.findById(id);
    }

    // Listar todos los productos
    public List<Producto> findAll() {
        return productosRepository.findAll();
    }

    // Buscar por nombre
    public List<Producto> findByNombre(String nombre) {
        return productosRepository.findByNombreContaining(nombre);
    }

    // Actualizar un producto
    public Producto update(Producto producto) {
        return productosRepository.update(producto);
    }

    // Actualización parcial
    public Producto patch(String id, Map<String, Object> updates) {
        Producto producto = productosRepository.findById(id);

        if (producto != null) {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "nombre":
                        producto.setNombre((String) value);
                        break;
                    case "descripcion":
                        producto.setDescripcion((String) value);
                        break;
                    case "precio":
                        producto.setPrecio(
                                ((Number) value).doubleValue());
                        break;
                    case "stock":
                        producto.setStock(
                                ((Number) value).intValue());
                        break;
                }
            });

            return productosRepository.update(producto);
        }

        return null;
    }

    // Eliminar un producto
    public void deleteById(String id) {
        productosRepository.deleteById(id);
    }
}