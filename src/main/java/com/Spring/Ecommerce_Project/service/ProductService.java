package com.Spring.Ecommerce_Project.service;

import com.Spring.Ecommerce_Project.model.Product;
import com.Spring.Ecommerce_Project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
     return    productRepository.findAll();
    }

    public Product getProductsById(int id) {
        return productRepository.findById(id)
                .orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
    product.setImageName(imageFile.getOriginalFilename());
    product .setImageType(imageFile.getContentType());
    product.setImageDate(imageFile.getBytes());

     return productRepository.save(product);
    }
}
