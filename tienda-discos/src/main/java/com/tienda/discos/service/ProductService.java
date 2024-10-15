package com.tienda.discos.service;
import com.tienda.discos.model.Product;
import com.tienda.discos.model.User;
import com.tienda.discos.repository.ProductRepository;
import com.tienda.discos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    /**
     * Obtiene todos los productos de un usuario específico.
     *
     * @param userId ID del usuario.
     * @return Lista de productos.
     */
    public List<Product> getAllProducts(Long userId) {
        return productRepository.findByUserId(userId);
    }
    /**
     * Crea un nuevo producto para un usuario específico.
     *
     * @param product Producto a crear.
     * @param userId  ID del usuario.
     * @return Producto creado.
     */
    public Product createProduct(Product product, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        product.setUser(user);
        return productRepository.save(product);
    }
    /**
     * Actualiza un producto existente.
     *
     * @param id      ID del producto.
     * @param product Datos actualizados del producto.
     * @return Producto actualizado.
     */
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        existingProduct.setTitle(product.getTitle());
        existingProduct.setCoverImage(product.getCoverImage());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        return productRepository.save(existingProduct);
    }
    /**
     * Elimina un producto por su ID.
     *
     * @param id ID del producto.
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    /**
     * Obtiene el ID del usuario basado en el correo electrónico.
     *
     * @param email Correo electrónico del usuario.
     * @return ID del usuario.
     */
    public Long getUserIdByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user.getId();
        }
        throw new RuntimeException("Usuario no encontrado");
    }
}
