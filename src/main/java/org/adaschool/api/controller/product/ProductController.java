package org.adaschool.api.controller.product;

import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;
import org.adaschool.api.service.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/v1/product")
@Controller
@Service
public class ProductController {

    private final ProductsService productService;

    public ProductController(@Autowired ProductsService productService) {
        this.productService = productService;
    }

    @GetMapping
    ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.all());
    }

    @PostMapping
    ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product product = new Product(productDto);
        URI createdProductUri = URI.create("");
        return ResponseEntity.created(createdProductUri).body(productService.save(product));
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> findById(@PathVariable String id) throws Exception {
        Optional<Product> optionalUser = productService.findById(id);
        if (optionalUser.isPresent())
            return ResponseEntity.ok(optionalUser.get());
        else throw new Exception("USER NOT FOUND");
    }

    @PutMapping("/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) throws Exception {
        Optional<Product> optionalUser = productService.findById(id);
        if (optionalUser.isPresent())
            return ResponseEntity.ok(productService.update(product, id));
        else throw new Exception("USER NOT FOUND");
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable String id) throws Exception {
        Optional<Product> optionalUser = productService.findById(id);
        if (optionalUser.isPresent()) {
            productService.deleteById(id);
            return ResponseEntity.ok(null);
        }
        else throw new Exception("USER NOT FOUND");
    }
}
