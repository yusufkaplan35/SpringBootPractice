package com.tpe.controller;

import com.tpe.domain.OrderItem;
import com.tpe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    // Tüm siparişleri getirme
    @GetMapping
    public ResponseEntity<List<OrderItem>> orderList(){

        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> orderListById(@PathVariable Long id){

        return ResponseEntity.ok(orderService.findById(id));

    }

    @DeleteMapping("/custom")
    public ResponseEntity<String> deleteOrder(@RequestParam("id") Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Deleted is successfully");

    }


    // 27-a-sipariş oluşturma ->http://localhost:8080/orders/save/filter?cid=1&prod=1&quant=3
    //farklı üründe yeni sipariş, aynı üründe sayı artırılır

    @PostMapping("/save/filter")
    public ResponseEntity<String> createOrder(@RequestParam("cid") Long cid,
                                              @RequestParam("prod") Long prod,
                                              @RequestParam("quant") Integer quantity){
        orderService.createOrder(cid,prod,quantity);

        return new ResponseEntity<>("Order is created succesfully.", HttpStatus.CREATED);
    }




    //30-a-Id ile sipariş miktarını update etme ->http://localhost:8080/orders/update/5/quantity/30 + PUT
    //quantity=0 ise siparişi sil, aksi halde miktarı güncelle







}
