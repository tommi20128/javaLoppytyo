package com.tommi.lopputyo.Controller;

import java.util.List;
import java.util.Map;

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

import com.tommi.lopputyo.Service.OrderService;
import com.tommi.lopputyo.Service.ProductService;
import com.tommi.lopputyo.data.Order;

@RestController
public class OrderRestController {

    OrderService orderService = new OrderService();
    ProductService productService = new ProductService();

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    //Haetaan kaikki tilaukset
    @GetMapping("/order")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    //Palautetaan tuotteen nimi, alusta ja hinta id:n perusteella
    @GetMapping("/orderprodname/{id}")
    public ResponseEntity<String> getProductName(@PathVariable int id) {
        String productName = orderService.getProductName(id);
        if (productName != null) {
            return new ResponseEntity<>(productName, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Haetaan tilauksen id, ja tuotteen tiedot id:n perusteella
    @GetMapping("/orderinfo/{id}")
    public ResponseEntity<String> getOrderInfo(@PathVariable int id) {
        String orderInfo = orderService.getOrderInfo(id);
        if (orderInfo != null) {
            return new ResponseEntity<>(orderInfo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Haetaan tilaus id:n perusteella
    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable int id) {
        Order order = orderService.findOrder(id);

        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Haetaan kaikkien tilauksien määrä ja yhteenlaskettu hinta
    @GetMapping("/totalordersinfo")
    public Map<String, Object> getTotalOrderInfo() {
        return orderService.getTotalOrderInfo();
    }

    //Lisätään tilaus
    @PostMapping("/order")
    public String addOrder(@RequestBody Order order) {
        boolean ord = orderService.addOrder(order);
        if(ord == true) {
            return "Order added";
        } else {
            return "Order not added. Please fill all the fields and make sure that product id and customer id exists.";
        }
    }

    //Päivitetään tilausta
    @PutMapping("/updateorder/{id}")
    public Order updateOrder(@PathVariable int id, @RequestBody Order order) {
        Order ord = orderService.updateOrder(id, order);
        if(ord != null) {
            return ord; //"Order edited";
        } else {
            return null; //"Order not edited. Please fill all the fields and make sure that product id and customer id exists.";
        }
    }

    //Poistetaan tilaus
    @DeleteMapping("/delorder")
    public String deleteOrder(@RequestBody Order order) {
        boolean ord = orderService.removeOrder(order.getId());
        if(ord == true){
            return "Order deleted";
        } else {
            return "Order not found";
        }
    }
   
}
