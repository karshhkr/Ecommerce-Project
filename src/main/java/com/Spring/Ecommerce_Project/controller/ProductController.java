package com.Spring.Ecommerce_Project.controller;

import com.Spring.Ecommerce_Project.model.Product;
import com.Spring.Ecommerce_Project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {




    @Autowired
private ProductService service;
    @GetMapping("/products")
    public ResponseEntity <List<Product> >getAllProducts(){
//        List<Product> products = new ArrayList<>();
        return new ResponseEntity<>( service.getAllProducts(),  HttpStatus.OK);
    }


    @GetMapping("/products/{id}")
    public ResponseEntity< Product> getProduct(@PathVariable int id) {

        Product product = service.getProductsById(id);


        // check if the products are there
        if (product != null) {
            return new ResponseEntity<>(service.getProductsById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
        @PostMapping("/products")
        public ResponseEntity<?> addProduct (@RequestPart Product product,
                           @RequestPart MultipartFile imageFile) {
            try {
                Product product1 = service.addProduct(product, imageFile);
                return new ResponseEntity<>(product1, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {

        Product product = service.getProductsById(productId);
        byte[] imageFile= product.getImageDate();

        return  ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);

        }
}
