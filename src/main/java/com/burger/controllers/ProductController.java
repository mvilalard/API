package com.burger.controllers;

import com.burger.models.Menu;
import com.burger.models.Product;
import com.burger.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productService.getAll();

        if(products.size() == 0) {
            return new ResponseEntity<>(products, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Product> getOne(@PathVariable("id") int id) {
        Product product = productService.getOne(id);

        if(product == null) {
            return new ResponseEntity<>(product, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(path="/highlight")
    @ResponseBody
    public List<Product> getByHhighlight() {
        return productService.getByHighlight(1);
    }

    @GetMapping(path="/available")
    @ResponseBody
    public List<Product> getByAvailable() {
        return productService.getByAvailable(1);
    }

    @GetMapping("/promotion/{id}")
    @ResponseBody
    public ResponseEntity<List<Product>> getByPromotion(@PathVariable("id") int id) {
        List<Product> products = productService.getByPromotionId(id);

        if(products.size() == 0) {
            return new ResponseEntity<>(products, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Product> create(@RequestBody Product product) {

        Product p = productService.save(product);

        if(product == null) {
            System.out.println("ICI INTERNAL ERROR");
            return new ResponseEntity<>(p, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PutMapping("/")
    @ResponseBody
    public ResponseEntity<Product> update(@RequestBody Product product) {
        Product p = productService.save(product);

        if(product == null) {
            return new ResponseEntity<>(p, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable int id) {
        productService.deleteById(id);
    }
}
