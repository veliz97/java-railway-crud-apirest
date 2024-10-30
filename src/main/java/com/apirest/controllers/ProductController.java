package com.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.entities.Product;
import com.apirest.repositories.ProductRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/products")
public class ProductController{
    
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("No se encontro un producto con el ID " + id));
    }

    @PostMapping()
    public Product addProduct(@RequestBody Product product){
        return productRepository.save(product);
    }
    
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("No se encontro un producto con el ID " + id));
        
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());

        return productRepository.save(product);
    }
    
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("No se encontro un producto con el ID " + id));

        productRepository.delete(product);

        return "Se elimino el producto con el ID " + id;

    } 

}