package com.tpe.service;

import com.tpe.domain.Customer;
import com.tpe.domain.OrderItem;
import com.tpe.domain.Product;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CustomerService customerService;

    private final ProductService productService;

    public List<OrderItem> getAllOrders() {
        return orderRepository.findAll();
    }


    public OrderItem findById(Long id) {
       return orderRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Order is not found by id: "+ id));

    }

    public void deleteOrder(Long id) {
        findById(id);
        orderRepository.deleteById(id);
    }


    public void createOrder(Long cid, Long prod, Integer quantity) {
        Customer customer = customerService.getCustomerById(cid);
        Product product = productService.findById(prod);

        OrderItem order;
        // aynı müşteri aynı siparişi veriyorsa sadece miktarı arttıralım yeni bir sipariş olmasın
        //farklı ise yeni sipariş oluşturalım.



    }

}
