package com.experis.mytunes.rest_controllers;

import com.experis.mytunes.dataAccess.CustomerDataHandler;
import com.experis.mytunes.models.BigSpender;
import com.experis.mytunes.models.Country;
import com.experis.mytunes.models.Customer;
import com.experis.mytunes.models.TopGenre;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CustomerController {
    CustomerDataHandler customerHandler = new CustomerDataHandler();

    //Get all
    @RequestMapping(value = "/api/customers", method = RequestMethod.GET)
    public ArrayList<Customer> getCustomers(){
        //Getting all customers in DB but only task specified fields
        return customerHandler.getAllCustomers();
    }

    //add new customer
    @RequestMapping(value = "/api/customer", method = RequestMethod.POST)
    public Customer addNewCustomer(@RequestBody Customer customer) {
        return customerHandler.addCustomer(customer);
    }

    //update customer
    @RequestMapping(value = "/api/customer", method = RequestMethod.PUT)
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerHandler.updateCustomer(customer);
    }

    //get number per country
    @RequestMapping(value = "/api/customercountry", method = RequestMethod.GET)
    public ArrayList<Country> sortedByCountry() {
        return  customerHandler.getAllByCountry();
    }

    //get biggest spender
    @RequestMapping(value = "/api/bigspender", method = RequestMethod.GET)
    public ArrayList<BigSpender> getBiggestSpenders() {
        return customerHandler.getBigSpenders();
    }

    //customers most popular genre
    @RequestMapping(value = "/api/customergenre", method = RequestMethod.GET)
    public ArrayList<TopGenre> getCustomerGenre(@RequestParam(value="userId") int userId){
        ArrayList<TopGenre> sqlResult = customerHandler.getCustomerGenre(userId);
        ArrayList<TopGenre> finalResult = new ArrayList<>();

        for(int i = 0; i < sqlResult.size(); i++) {
            finalResult.add(sqlResult.get(i));
            if(sqlResult.get(i).getBoughtGenres() == sqlResult.get(i+1).getBoughtGenres()) {
                continue;
            }
            break;
        }

        return finalResult;
    }






    @RequestMapping(value = "/api/test", method = RequestMethod.GET)
    public String hello() {
        return "Success!";
    }

}
