package com.example.order.controller;

import com.example.order.dto.*;
import com.example.order.model.Order;
import com.example.order.service.OrderService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }

    @GetMapping("/orderList")
    public List<OrderListResponse> getOrderList(){
        return orderService.getOrderList();
    }

    @GetMapping("/customerOrderList/{customerId}")
    public List<CustomerOrderListResponse> getCustomerOrderList(@PathVariable String customerId){
        return orderService.getCustomerOrderList(customerId);
    }

    @PutMapping("/packed")
    public void packedOrder(@RequestBody PackedRequest packedRequest){
        orderService.packedRequest(packedRequest);
    }

    @PutMapping("/dispatched")
    public void dispatchedOrder(@RequestBody DispatchedRequest dispatchedRequest){
        orderService.dispatchedRequest(dispatchedRequest);
    }

    @PutMapping("/canceled")
    public void canceledOrder(@RequestBody CanceledRequest canceledOrder){
        orderService.canceledRequest(canceledOrder);
    }
}
