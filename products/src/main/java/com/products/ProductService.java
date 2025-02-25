package com.products;

import com.products.products.OrderRepository;
import com.products.products.repositories.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private OrderRepository orderRepository;

    public Orders saveOrder(Orders orders){
        return orderRepository.save(orders);
    }

    public Orders updateProduct(Long id, Orders updatedorders){
        Optional<Orders> existingProduct = orderRepository.findById(id);
        if(existingProduct.isPresent()){
            Orders orders = existingProduct.get();
            orders.setId(updatedorders.getId());
            orders.setProductDesc(updatedorders.getProductDesc());
            orders.setProductName(updatedorders.getProductName());
            orders.setPrice(updatedorders.getPrice());
            orders.setQuantity(updatedorders.getQuantity());
            return orderRepository.save(orders);
        }else{
            return null;
        }
    }

    public boolean deleteOrder(Long id){
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Orders> getOrderById(Long id){
        return orderRepository.findById(id);
    }
}
