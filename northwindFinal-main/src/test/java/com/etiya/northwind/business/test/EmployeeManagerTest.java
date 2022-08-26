package com.etiya.northwind.business.test;

import com.etiya.northwind.business.concretes.CustomerManager;
import com.etiya.northwind.business.concretes.EmployeeManager;
import com.etiya.northwind.business.requests.customerRequests.CreateCustomerRequest;
import com.etiya.northwind.business.requests.employeeRequests.CreateEmployeeRequest;
import com.etiya.northwind.core.exceptions.BusinessException;
import com.etiya.northwind.core.mapping.ModelMapperManager;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.dataAccess.abstracts.CustomerRepository;
import com.etiya.northwind.dataAccess.abstracts.EmployeeRepository;
import com.etiya.northwind.entities.concretes.Customer;
import com.etiya.northwind.entities.concretes.Employee;
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
    class EmployeeManagerTest {

        @InjectMocks
        private EmployeeManager employeeManager;
        @Mock
        private EmployeeRepository employeeRepository;
        private ModelMapperService modelMapperService;
        private Employee employee;
        private CreateEmployeeRequest createEmployeeRequest;

        @BeforeEach
        public void setup() {
            employeeRepository = Mockito.mock(EmployeeRepository.class);
            modelMapperService = new ModelMapperManager(new ModelMapper());
            employeeManager = new EmployeeManager(employeeRepository, modelMapperService);
            createEmployeeRequest = new CreateEmployeeRequest();
            Employee employee = new Employee();

        }

         @Test
        void when_adding_customer_id_exist_throw_business_exception() {
            CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest(20,"Fırat","Ozbor",null,6);
           //CreateEmployeeRequest createEmployeeRequest = this.modelMapperService.forRequest().map(employee, CreateEmployeeRequest.class);
           //Mockito.when(employeeRepository.existsById(20)).thenReturn(true);
            Assertions.assertThrows(BusinessException.class, () -> employeeManager.add(createEmployeeRequest));
        }

        @Test
        void when_deleting_customer_id_doesnt_exist_throw_business_exc() {
          //  Emp customer = new Customer("Patates",null,null,null,null,null);
            Assertions.assertThrows(BusinessException.class, () ->
                    employeeManager.delete(35));
        }
        @Test
        void when_seaching_customer_id_doesnt_exist_throw_business_exc() {
            //Customer customer = new Customer("Patates",null,null,null,null,null);
            Mockito.when(employeeRepository.findById(20)).thenReturn(Optional.empty());
            Assertions.assertThrows(BusinessException.class, () ->
                    employeeManager.getById(35));
        }

}
