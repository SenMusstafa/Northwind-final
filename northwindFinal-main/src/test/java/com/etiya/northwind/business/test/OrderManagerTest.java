package com.etiya.northwind.business.test;

import com.etiya.northwind.business.concretes.CustomerManager;
import com.etiya.northwind.business.concretes.OrderManager;
import com.etiya.northwind.business.requests.customerRequests.CreateCustomerRequest;
import com.etiya.northwind.business.requests.orderRequests.CreateOrderRequest;
import com.etiya.northwind.core.exceptions.BusinessException;
import com.etiya.northwind.core.mapping.ModelMapperManager;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.dataAccess.abstracts.CustomerRepository;
import com.etiya.northwind.dataAccess.abstracts.OrderRepository;
import com.etiya.northwind.entities.concretes.Customer;
import com.etiya.northwind.entities.concretes.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Executable;
import java.util.Optional;

class OrderManagerTest {


    @InjectMocks
    private OrderManager orderManager;
    @Mock
    private OrderRepository orderRepository;
    private ModelMapperService modelMapperService;
    private Order order;

    @BeforeEach
    public void setup() {
        orderRepository = Mockito.mock(OrderRepository.class);
        modelMapperService = new ModelMapperManager(new ModelMapper());
        orderManager = new OrderManager(orderRepository, modelMapperService);

        }

    @Test
    void when_adding_order_id_exist_throw_business_exception() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(20,"asd",30,null);
        //CreateOrderRequest createOrderRequest = this.modelMapperService.forRequest().map(order, CreateOrderRequest.class);
        Mockito.when(orderRepository.existsById(20)).thenReturn(true);
        //Executable executable = ()->this.orderManager.getById(20);
        Assertions.assertThrows(BusinessException.class,() ->
                orderManager.getById(20));
    }

    @Test
    void when_deleting_order_id_doesnt_exist_throw_business_exc() {
        Order order = new Order(20,null,null,null,null);
        Mockito.when(orderRepository.existsById(10)).thenReturn(true);
        Assertions.assertThrows(BusinessException.class,() ->
                orderManager.delete(20));
    }
    @Test
    void when_seaching_order_id_doesnt_exist_throw_business_exc() {
        Customer customer = new Customer("Patates",null,null,null,null,null);
        Mockito.when(orderRepository.findById(20)).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () ->
                orderManager.getById(20));
    }
}
