package com.tommi.lopputyo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tommi.lopputyo.data.Product;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();

    //Luodaan muutama tuote
    public ProductService() {
        products.add(new Product("Call of Duty", "Mobile", 64.99));
        products.add(new Product("NHL23", "Nintendo", 79.95));
        products.add(new Product("Gods of War: Ragnarok", "PS5", 79.95));
        products.add(new Product("Elden Ring", "PC", 59.99));
    }

    // public void addProduct(Product product) {
    //     products.add(product);
    // }

    //Tein booleanilla jotta postmanilla saadaan parempi feedback
    //Lisätään tuote. Palauttaa true jos lisäys onnistui, muuten false. Vaatii että kaikki kentät on täytetty
    public boolean addProduct(Product product) {
        if(product.getName() != null && product.getPlatform() != null && product.getPrice() >= 0) {
            return products.add(product);
        } else {
            return false;
        }
    }

    //listaa kaikki tuotteet
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    //Etsi tuote id:n perusteella ja palauta se. Jos tuotetta ei löydy, palauta null
    public Product findProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    //Etsi tuote id:n perusteella ja palauta nimi, alusta ja hinta. Jos tuotetta ei löydy, palauta null
    public String getProductInfo(int id) {
        for (Product product : products){
            if (product.getId() == id) {
                String productPrice = String.valueOf(product.getPrice());
                return product.getName() + " " + product.getPlatform() + " " + productPrice;
            }
        }
        return null;
    }

    // Etsitään tuote nimen perusteella toimii ainakin NHL23 kohdalla
    public Product findProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    //palauttaa tuotteen hinnan id:n perusteella toimiikohan?
    public double productPrice(int id) {
        for (Product product : products) {
            if (product.getId() == id) {           
                return product.getPrice();
            }
        }
        return 0;
    }

     //muokataan tuotetta id:n perusteella 
     public Product updateProduct(int id, Product product) {
        Product updatedProduct = findProduct(id);
        if (updatedProduct != null) {
            try{
                if(product.getName() != null && product.getPlatform() != null && product.getPrice() >= 0) {
                    updatedProduct.setName(product.getName());
                    updatedProduct.setPlatform(product.getPlatform());
                    updatedProduct.setPrice(product.getPrice());
                    return updatedProduct;
                }
            } catch (Exception e) {
                throw new RuntimeException("Update failed");
            }
            return null;
        }
        return null;
    }
    
    //poista tuote id:n perusteella. Jos tuotetta ei ole, palauta false
    public boolean removeProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return products.remove(product);
            }
        }
        return false;
    }
}
