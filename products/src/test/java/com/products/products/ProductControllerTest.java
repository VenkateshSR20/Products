package com.products.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.products.ProductService;
import com.products.products.controller.ProductController;
import com.products.products.repositories.Orders;
import io.swagger.models.Response;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    //Save
    @Test
    void testcreateProduct() throws Exception{
        //Should i need to mention all pdts in db?
        Orders ordersone = new Orders();
        ordersone.setPrice("100");
        ordersone.setProductName("Books");
        ordersone.setProductDesc("Science");
        ordersone.setQuantity("10");
        Mockito.when(productService.saveOrder(Mockito.any())).thenReturn(ordersone);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(ordersone);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("http://localhost:8080/api/Insert").content(json).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        if(result.getResponse().getStatus() == 201){
            System.out.println("Message created Successfully");
        }else {
            System.out.println("Message not created");
        }
    }

    @Test
    void testupdateProduct() throws Exception {
        Orders ordersupdate = new Orders();
        ordersupdate.setId(1);
        ordersupdate.setPrice("500");
        Mockito.when(productService.updateProduct(Mockito.any(),Mockito.any())).thenReturn(ordersupdate);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString((ordersupdate));
        RequestBuilder requestBuilder =  MockMvcRequestBuilders.put("http://localhost:8080/api/Update/1").content(json).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        assertEquals(result.getResponse().getStatus(), 200);

    }

    @Test
    void testdeleteProduct() throws Exception {
        Orders ordersdelete = new Orders();
        ordersdelete.setId(1);
        Mockito.when(productService.deleteOrder(1L)).thenReturn(Boolean.valueOf("SUCCESS"));
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString((ordersdelete));
        RequestBuilder requestBuilder =  MockMvcRequestBuilders.delete("http://localhost:8080/api/Delete/1").content(json).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/api/Delete/1",1L));
    }

    /*
    Mockito.when(studentService.retrieveCourse(Mockito.anyString(),
 Mockito.anyString())).thenReturn(mockCourse);

 RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
 "/students/Student1/courses/Course1").accept(
 MediaType.APPLICATION_JSON);

 MvcResult result = mockMvc.perform(requestBuilder).andReturn();

 System.out.println(result.getResponse());
 String expected = "{\"id\":\"Course1\",\"name\":\"Spring\",\"description\":\"10 Steps\"}";


 // {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

 JSONAssert.assertEquals(expected, result.getResponse()
 .getContentAsString(), false);
 }
     */
}
