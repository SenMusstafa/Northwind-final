package com.etiya.northwind.business.test;

import com.etiya.northwind.business.concretes.CategoryManager;
import com.etiya.northwind.business.requests.categoryRequests.CreateCategoryRequest;
import com.etiya.northwind.business.requests.customerRequests.CreateCustomerRequest;
import com.etiya.northwind.core.exceptions.BusinessException;
import com.etiya.northwind.core.mapping.ModelMapperManager;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.dataAccess.abstracts.CategoryRepository;
import com.etiya.northwind.dataAccess.abstracts.CustomerRepository;
import com.etiya.northwind.entities.concretes.Category;
import com.etiya.northwind.entities.concretes.Customer;
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
class CategoryManagerTest {

    @InjectMocks
    private CategoryManager categoryManager;
    @Mock
    private CategoryRepository categoryRepository;

    private ModelMapperService modelMapperService;
    private Category category;

    @BeforeEach
    public void setup() {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryManager = new CategoryManager(categoryRepository,modelMapperService);
    }
    @Test
    void when_adding_customer_id_exist_throw_business_exception() {
        CreateCategoryRequest createCategoryRequest = this.modelMapperService.forRequest().map(category, CreateCategoryRequest.class);
        Mockito.when(categoryRepository.isCategoryNameExists("Gıda")).thenReturn(true);
        Assertions.assertThrows(BusinessException.class, () -> categoryManager.add(createCategoryRequest));
    }
    @Test
    void when_deleting_customer_id_doesnt_exist_throw_business_exc() {
        Category category = new Category(10,"Gıda","asd",null);
        Assertions.assertThrows(BusinessException.class, () ->
                categoryManager.delete(10));
    }
}
