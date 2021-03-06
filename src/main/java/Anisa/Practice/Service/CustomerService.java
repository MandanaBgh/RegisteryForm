package Anisa.Practice.Service;

import Anisa.Practice.Entity.Customer;

import java.util.List;

public interface CustomerService {

    //
    public List<Customer> getCustomers();

    public Customer customerId(int theId);

    public void saveCustomer(Customer theCustomer);

    void deleteCustomer(int theId);
}