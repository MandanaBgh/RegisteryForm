package Anisa.Practice.Controller;

import Anisa.Practice.Entity.Customer;
import Anisa.Practice.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @GetMapping("/Home")
    public String showHomePage() {

        return "Home";
    }

    @GetMapping("/")
    public String homePage(Model theModel) {
        List<Customer> customers = customerService.getCustomers();

        theModel.addAttribute("customers", customers);
        return "Home";
    }

    @GetMapping("/AddNewCustomer")
    public String newCustomer(Model theModel) {
        Customer theCustomer = new Customer();

        theModel.addAttribute("customer", theCustomer);


        return "AddCustomer";
    }

    @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
    public void showImage(@RequestParam("id") Integer itemId, HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {


        Customer item = customerService.customerId(itemId);
        byte[] image = item.getImage();
        response.setContentType("image/jpeg");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(image);

        response.getOutputStream().close();


    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

        // save the customer using our service
        customerService.saveCustomer(theCustomer);


        return "Home";
    }


}