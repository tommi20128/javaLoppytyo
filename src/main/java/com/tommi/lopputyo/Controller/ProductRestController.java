package com.tommi.lopputyo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tommi.lopputyo.Service.ProductService;
import com.tommi.lopputyo.data.Product;

@RestController
public class ProductRestController {
    
    ProductService productService = new ProductService();

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;      
    }

    //Haetaan kaikki tuotteet
    @GetMapping("/product")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    //Haetaan tuote id:n perusteella
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = productService.findProduct(id);

        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Etsitään tuote nimen perusteella toimii ainakin NHL23 kohdalla
    @GetMapping("/productname/{name}")
    public ResponseEntity<Product> getCustomerFname(@PathVariable String name) {
        Product product = productService.findProductByName(name);
    
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);       
    }

    //Lisätään tuote
    @PostMapping("/product")
    public String addProduct(@RequestBody Product product){
        boolean prod = productService.addProduct(product);
        if(prod == true) {
            return "Product added";
        } else {
            return "Product not added. Please fill all the fields";
        }
    }

    //Muokataan tuotetta id:n perusteella
    @PutMapping("/updateproduct/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product prod = productService.updateProduct(id, product);
        if(prod != null) {
            return prod;
        } else {
            return null;
        }
    }

    //Poistetaan tuote postmanissa id annetaan bodyyn
    @DeleteMapping("/delproduct")
    public String deleteProduct(@RequestBody Product product) {
        boolean prod = productService.removeProduct(product.getId());
        if(prod == true){
            return "Product deleted";
        } else {
            return "Product not found";
        }
    }


}
