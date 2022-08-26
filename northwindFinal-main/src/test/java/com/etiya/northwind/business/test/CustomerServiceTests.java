package com.etiya.northwind.business.test;


import com.etiya.northwind.business.abstracts.CustomerService;
import com.etiya.northwind.business.concretes.CustomerManager;
import com.etiya.northwind.business.requests.cartRequests.CreateCartRequest;
import com.etiya.northwind.business.requests.customerRequests.CreateCustomerRequest;
import com.etiya.northwind.core.exceptions.BusinessException;
import com.etiya.northwind.core.mapping.ModelMapperManager;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.dataAccess.abstracts.CustomerRepository;
import com.etiya.northwind.entities.concretes.Customer;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerManagerTest {

    @InjectMocks
    private CustomerManager customerManager;
    @Mock
    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;
    private Customer customer;

    @BeforeEach
    public void setup() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        modelMapperService = new ModelMapperManager(new ModelMapper());
        customerManager = new CustomerManager(customerRepository, modelMapperService);


        customer = new Customer();
        customer.setCustomerId("Patates");
        customer.setCompanyName("Migros");
        customer.setContactName("Adem Bey");
        customer.setContactTitle("Manav");
    }

    @Test
    void when_adding_customer_id_exist_throw_business_exception() {
        CreateCustomerRequest createCustomerRequest = this.modelMapperService.forRequest().map(customer, CreateCustomerRequest.class);
        Mockito.when(customerRepository.existsById("Patates")).thenReturn(true);
        Assertions.assertThrows(BusinessException.class, () -> customerManager.add(createCustomerRequest));
    }

    @Test
    void when_deleting_customer_id_doesnt_exist_throw_business_exc() {
        Customer customer = new Customer("Patates",null,null,null,null,null);
        Assertions.assertThrows(BusinessException.class, () ->
        customerManager.delete("Patates"));
    }
    @Test
    void when_seaching_customer_id_doesnt_exist_throw_business_exc() {
        //Customer customer = new Customer("Patates",null,null,null,null,null);
        Mockito.when(customerRepository.findById("Patates")).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () ->
                customerManager.findById("Patates"));
    }
}



















