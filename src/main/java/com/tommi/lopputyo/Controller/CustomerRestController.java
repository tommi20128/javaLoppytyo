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

import com.tommi.lopputyo.Service.CustomerService;
import com.tommi.lopputyo.data.Customer;

@RestController
public class CustomerRestController {

    CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    //Listataan kaikki asiakkaat
    @GetMapping("/customer")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }
    
    //Etsitään asiakas id:n perusteella
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerId(@PathVariable int id) {
        Customer customer = customerService.findCustomerById(id);
    
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);       
    }

    //Etsitään asiakas etunimen perusteella
    @GetMapping("/customerfname/{fName}")
    public ResponseEntity<Customer> getCustomerFname(@PathVariable String fName) {
        Customer customer = customerService.findCustomerByFname(fName);
    
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);       
    }

    //Etsitään asiakas sukunimen perusteella
    @GetMapping("/customerlname/{lName}")
    public ResponseEntity<Customer> getCustomerLname(@PathVariable String lName) {
        Customer customer = customerService.findCustomerByLname(lName);
    
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);       
    }

    //Lisätään asiakas
    @PostMapping("/customer")
    public String addCustomer(@RequestBody Customer customer) {
        boolean cust = customerService.addCustomer(customer);
        if(cust == true) {
            return "Customer added";
        }
        return "Customer not added. Please fill all the fields";
    }  

    //Päivitetään asiakas
    @PutMapping("/updatecustomer/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        Customer cust = customerService.updateCustomer(id, customer);
        if(cust != null) {
            return cust;//"Customer updated";
        }
        return null; //"Customer not updated. Please fill all the fields"
    }

    //Poistetaan asiakas
    @DeleteMapping ("/delcustomer")
    public String deleteCustomer(@RequestBody Customer customer) {
        boolean cust = customerService.deleteCustomer(customer.getId());
        if(cust == true){
            return "Customer deleted";
        } else {
            return "Customer not found";
        }
    }

}
