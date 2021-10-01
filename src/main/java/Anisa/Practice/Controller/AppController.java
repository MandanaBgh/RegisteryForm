package Anisa.Practice.Controller;

import Anisa.Practice.Entity.Customer;
import Anisa.Practice.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private CustomerService customerService;

    // add mapping for GET /customers
    @GetMapping("/customers")
    public List<Customer> getCustomers() {

        return customerService.getCustomers();

    }

    @GetMapping("/")
    public String homePage(Model theModel) {
        List<Customer> customers = customerService.getCustomers();

        theModel.addAttribute("customers", customers);
        return "Home";
    }
}