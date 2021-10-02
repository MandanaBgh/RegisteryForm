package Anisa.Practice.CustomerDAO;

import Anisa.Practice.Entity.Customer;

import java.util.List;

public interface CustomerDAO {

    public List<Customer> getCustomers();

    public Customer customerId(int theId);

    public void saveCustomer(Customer theCustomer);
}