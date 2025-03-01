package com.products.products;

import com.products.products.Service.ProductService;
import com.products.products.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class ProductServiceTest {

    private OrderRepository orderRepository;
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
