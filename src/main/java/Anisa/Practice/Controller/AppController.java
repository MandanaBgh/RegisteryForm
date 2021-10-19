package Anisa.Practice.Controller;

import Anisa.Practice.Entity.Customer;
import Anisa.Practice.Entity.Students;
import Anisa.Practice.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import java.util.List;

@Controller

public class AppController {
    @Autowired
    private CustomerService customerService;


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


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


    //  @PostMapping("/saveCustomer")
    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult theBindingResult,
                               @RequestParam("photo") CommonsMultipartFile file) {


        if (theBindingResult.hasErrors()) {
            return "AddCustomer";
        } else {
            try {

                byte[] image = file.getBytes();
                customer.setImage(image);

                customerService.saveCustomer(customer);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/Home";
        }
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {

        // delete the customer
        customerService.deleteCustomer(theId);

        return "redirect:/Home";
    }


    @RequestMapping("/processForm")
    public String processForm(
            @Valid @ModelAttribute("customer") Customer theCustomer,
            BindingResult theBindingResult) {


        if (theBindingResult.hasErrors()) {
            return "customer-form";
        } else {
            return "customer-confirmation";
        }
    }


}