package com.tommi.lopputyo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tommi.lopputyo.data.Customer;
import com.tommi.lopputyo.data.VipCustomer;

@Service
public class CustomerService {   

    private List<Customer> customers = new ArrayList<>();

    //luodaan automaattisesti muutama käyttäjä
    public CustomerService() {
        customers.add(new Customer("Erkki", "Merkki", "Postikatu 1", "0501234567"));
        customers.add(new Customer("Jukka", "Perustukka", "Palmutie 2", "0507654321"));
        customers.add(new Customer("Alma", "Kassi", "Rahatie 2", "0501122334"));
        customers.add(new VipCustomer("Pasi", "Kasi", "Kasipasikuja 4", "0408484848", 0.9));
    }
 
    // Tein booleanilla jotta postmanilla saadaan parempi feedback
    //Lisätään asiakas. Palauttaa true jos lisäys onnistui, muuten false. Vaatii että kaikki kentät on täytetty
    public boolean addCustomer(Customer customer) {
        if(customer.getfName() != null && customer.getlName() != null 
        && customer.getAddress() != null && customer.getPhone() != null) {
            return customers.add(customer);
        } else {
            return false;
        }
    }

    //listaa kaikki asiakkaat
    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    //etsi asiakas id:n perusteella ja palauta se. Jos asiakasta ei löydy palauta null
    public Customer findCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    // Etsitään asiakas etunimen perusteella
    public Customer findCustomerByFname(String fName) {
        for (Customer customer : customers) {
            if (customer.getfName().equals(fName)) {
                return customer;
            }
        }
        return null;
    }
    
    // Etsitään asiankas sukunimen perusteella
    public Customer findCustomerByLname(String lName) {
        for (Customer customer : customers) {
            if (customer.getlName().equals(lName)) {
                return customer;
            }
        }
        return null;
    }

    // Palauttaa asiakkaan etu- ja sukunimen id:n perusteella.
    public String getCustomerFullName(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer.getfName() + " " + customer.getlName();
            }
        }
        return null;
    }

     // Palauttaa true jos asiakas on vip asiakas, muuten false
     public boolean isVipCustomer() {
        for (Customer customer : customers) {            
                if (customer instanceof VipCustomer) {
                    return true;
                }
                else {
                    return false;
                }
            }       
        return false;
    }

    //Päivitetään asiakas
    public Customer updateCustomer(int id, Customer customer) {
        Customer updatedCustomer = findCustomerById(id);
        if (updatedCustomer != null) {
            try {
                if(customer.getfName() != null && customer.getlName() != null 
                && customer.getAddress() != null && customer.getPhone() != null){
                    updatedCustomer.setfName(customer.getfName());
                    updatedCustomer.setlName(customer.getlName());
                    updatedCustomer.setAddress(customer.getAddress());
                    updatedCustomer.setPhone(customer.getPhone());
                    return updatedCustomer;
                }
                
            } catch (Exception e) {
                throw new RuntimeException("Update failed");
            }
            return null;
        }
        return null;
    }
    
    //Poistetaan asiakas id:n perusteella. Jos asiakasta ei ole, palauta false
    public boolean deleteCustomer(int id) {
        Customer customer = findCustomerById(id);
        
        if (customer != null) {
            return customers.remove(customer);
        }
        return false;
    }

}
