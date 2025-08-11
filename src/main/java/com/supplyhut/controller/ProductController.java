package com.supplyhut.controller;

import com.supplyhut.model.Product;
import com.supplyhut.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Add new product
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Get all products
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Update product stock
    @PutMapping("/update/{id}")
    public String updateStock(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product p = product.get();
            p.setStock(updatedProduct.getStock());
            p.setPrice(updatedProduct.getPrice());
            return "Updated stock and price for " + productRepository.save(p).getName();
        }
        return "Product not found";
    }

    // Delete product
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "Product deleted successfully";
    }
}
