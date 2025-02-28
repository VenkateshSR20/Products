package com.products.products.controller;

import com.products.ProductService;
import com.products.products.OrderRepository;
import com.products.products.repositories.Orders;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Browser URL check - http://localhost:8080/swagger-ui/index.html#/Product/updateProduct

//Product Controller class
@Tag(name = "Product", description = "Product Management APIs")
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/hi")
    public String sayHi() {
        return "Hi";
    }
//    public List<Orders> getAllOrders(){
//        return orderRepository.findAll();
//    }

    @Operation(summary = "Create a new product", tags = { "products", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = Orders.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/Insert")
    private ResponseEntity<Orders> createProduct(@RequestBody Orders orders) {
        Orders savedorder = productService.saveOrder(orders);
        return new ResponseEntity<>(savedorder, HttpStatus.CREATED);
    }

    @Operation(summary = "Update product", tags = { "products", "put" })
    @ApiResponses({
       @ApiResponse(responseCode = "200", content = {
               @Content(schema = @Schema(implementation = Orders.class), mediaType = "application/json") }),
       @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PutMapping("/Update/{id}")
    private ResponseEntity<Orders> updateProduct(@PathVariable Long id,@RequestBody Orders orders){
        orders =  productService.updateProduct(id, orders);
        if(orders != null){
            //ok means 200
            return ResponseEntity.ok(orders);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/Delete/{id}")
    private ResponseEntity<String>  deleteProduct(@PathVariable("id") long id) {
        if(productService.deleteOrder(id)) {
            return ResponseEntity.ok("Order deleted Successfully");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/View")
    private ResponseEntity<List<Orders>> getAllOrders(){
        return ResponseEntity.ok(productService.getAllOrders());
    }

    @GetMapping("/View/{id}")
    private ResponseEntity<Orders> getProductById(@PathVariable Long id) {
        Optional<Orders> order = productService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
