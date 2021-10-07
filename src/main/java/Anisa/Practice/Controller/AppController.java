package Anisa.Practice.Controller;

import Anisa.Practice.Entity.Customer;
import Anisa.Practice.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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


    @GetMapping(value = {"/", "/Home"})
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

    @GetMapping("/imageDisplay/{id}")
    // @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
    public void showImage(@PathVariable("id") Integer itemId, HttpServletResponse response, HttpServletRequest request) {
        try {
            Customer item = customerService.customerId(itemId);
            byte[] image = item.getImage();
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(item.getImage());
            response.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId,
                                    Model theModel) {

        Customer theCustomer = customerService.customerId(theId);
        theModel.addAttribute("customer", theCustomer);
        return "AddCustomer";
    }


    @PostMapping("/saveCustomer")
    //  @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer,
                               @RequestParam("photo") CommonsMultipartFile file) {
        try {

            byte[] image = file.getBytes();
            theCustomer.setImage(image);

            customerService.saveCustomer(theCustomer);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/Home";

    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {

        // delete the customer
        customerService.deleteCustomer(theId);

        return "redirect:/Home";
    }


}