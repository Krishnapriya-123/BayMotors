package com.baymotors.services;
import com.baymotors.dao.CustomerDao;
import com.baymotors.models.Customer;

import java.util.List;

public class CustomerService {
	public static List<Customer> listCustomers() {
		List<Customer> customers = CustomerDao.getAllCustomers();
		 
		return customers;
	}
	
	public static void addCustomer(Customer customer) {
		CustomerDao.addCustomer(customer);
	}
}
