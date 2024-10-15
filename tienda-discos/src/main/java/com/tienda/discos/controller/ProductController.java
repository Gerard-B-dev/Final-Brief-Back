
package com.tienda.discos.controller;

import com.tienda.discos.model.Product;
import com.tienda.discos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar las operaciones CRUD de productos.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * Obtiene todos los productos del usuario autenticado.
     *
     * @param authentication Información de autenticación.
     * @return Lista de productos.
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(Authentication authentication) {
        String email = authentication.getName();
        Long userId = productService.getUserIdByEmail(email);
        List<Product> products = productService.getAllProducts(userId);
        return ResponseEntity.ok(products);
    }

    /**
     * Crea un nuevo producto para el usuario autenticado.
     *
     * @param product        Producto a crear.
     * @param authentication Información de autenticación.
     * @return Producto creado.
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product, Authentication authentication) {
        String email = authentication.getName();
        Long userId = productService.getUserIdByEmail(email);
        Product createdProduct = productService.createProduct(product, userId);
        return ResponseEntity.ok(createdProduct);
    }

    /**
     * Actualiza un producto existente.
     *
     * @param id      ID del producto.
     * @param product Datos actualizados del producto.
     * @return Producto actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Elimina un producto existente.
     *
     * @param id ID del producto.
     * @return Estado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}

