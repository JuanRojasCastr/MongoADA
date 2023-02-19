package org.adaschool.api.controller.product;

import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;
import org.adaschool.api.service.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

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
    List<Product> getAll() {
        return productService.all();
    }

    @PostMapping
    Product createProduct(@RequestBody ProductDto productDto) {
        System.out.println("Product: " + productDto);
        Product product = new Product(productDto);
        return productService.save(product);
    }

    @GetMapping("/{id}")
    Product findById(@PathVariable String id) throws Exception {
        Optional<Product> optionalUser = productService.findById(id);
        if (optionalUser.isPresent())
            return optionalUser.get();
        else throw new Exception("USER NOF FOUND");
    }
}
