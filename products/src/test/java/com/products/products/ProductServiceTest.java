package com.products.products;

import com.products.ProductService;
import com.products.products.repositories.Orders;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class ProductServiceTest {

    private  OrderRepository orderRepository;
    private ProductService productService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    /*
    @Test
    void testGetAllProducts() {
        Orders orders = new Orders();
        orders.setId(53);
        orders.setProductName("Books");
        orders.setQuantity("15");
        orders.setPrice("105");
        orders.setProductDesc("C++Python");

        List<Orders> orderss = productService.getAllOrders();

        assertNotNull(orderss);
        assertEquals(2, orderss.size());
    }
    /*
    @Test
    void testGetProductIDExists(){
        Orders orders = new Orders();
        assertEquals();
    }*/


}
