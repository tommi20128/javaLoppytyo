package com.tommi.lopputyo.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tommi.lopputyo.data.Order;

@Service
public class OrderService {
    
    private List<Order> orders = new ArrayList<>();
    private CustomerService customerService;
    private ProductService productService;
    
    //lisätään muutama tilaus.
    public OrderService() {
        orders.add(new Order(1, 1));
        orders.add(new Order(2, 2));
        orders.add(new Order(3, 3));
        orders.add(new Order(4, 4));
        customerService = new CustomerService();
        productService = new ProductService();
    }

    //Lisätään tilaus listaan. Varmistetaan että asiakkaan id ja tuotteen id löytyy.
    public boolean addOrder(Order order) {
        if(order.getCustomerId() >= 0 && order.getCustomerId() <= customerService.getCustomers().size() 
        && order.getProductId() >= 0 && order.getProductId() <= productService.getProducts().size()) {
            return orders.add(order);
        } else {
            return false;
        }
    }

    //Lista kaikista tilauksista
    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    //Etsitään tilaus id:n perusteella
    public Order findOrder(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    //Hakee tuotteen tiedot id:n perusteella ja palauttaa ne
    public String getProductName(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                int pid = order.getProductId();
                String productInfo = productService.getProductInfo(pid);
                return productInfo;
            }
        }
        return null;
    }

    //Tähän piti tulla metodi joka hakee asiakkaan nimen ja tuotteen tiedot sekä päivämäärän id:n perusteella
    //Asiakkaan nimi on kuitenkin aina null enkä tiedä syytä joten palautetaan pelkästään tilauksen id, tuote ja päivämäärä
    public String getOrderInfo(int id){
        for (Order order : orders) {
            if(order.getId() == id) {
                int prodId = order.getProductId();
                //int custId = order.getCustomerId();
          
                String productInfo = productService.getProductInfo(prodId);
                if (productInfo == null) {
                    return "Product not found for order " + order.getId();
                }

                //tämä koodi on ongelma!!! palauttaa aina null
                //String customerInfo = customerService.getCustomerFullName(custId);
                //if (customerInfo == null) {
                //  return "Customer not found for order " + order.getId();
                // }

                return order.getId() + " " + productInfo + " " + order.getorderDate();             
            }
        }
        return null;
    }

    //muokkaa tilausta id:n perusteella
    public Order updateOrder(int id, Order order) {
        for (Order updateOrder : orders) {
            try{
                if (updateOrder.getId() == id) {
                    updateOrder.setCustomerId(order.getCustomerId());
                    updateOrder.setProductId(order.getProductId());
                    return updateOrder;
                }
            }
            catch(Exception e) {
                throw new RuntimeException("Update failed");
            }
        }
        return null;
    }
      
    //poista tilaus id:n perusteella. Jos tuotetta ei ole. palauta false
    public boolean removeOrder(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return orders.remove(order);
            }
        }
        return false;
    }

    //palauttaa kaikkien tilausten lukumäärän ja hinnan
    public Map<String, Object> getTotalOrderInfo() {
        Map<String,Object> orderPrice = new HashMap<>();

        orderPrice.put("Total Orders ", orders.size());
        
        double totalPrice = 0;
        for (Order order : orders) {  
            totalPrice += productService.productPrice(order.getProductId()); 
        }
        orderPrice.put("Total Price ", totalPrice);

        return orderPrice;
    }
 
}
