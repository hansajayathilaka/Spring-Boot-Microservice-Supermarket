package com.example.order.service;

import com.example.order.dto.*;
import com.example.order.model.Order;
import com.example.order.model.OrderStatus;
import com.example.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    public String createOrder(OrderRequest orderRequest){
        Order order = Order.builder()
                .placedDate(LocalDateTime.now())
                .orderStatus(OrderStatus.IN_PROGRESS)
                .customerId(orderRequest.getCustomerId())
                .orderQuantity(orderRequest.getOrderQuantity())
                .build();

        orderRepository.save(order);
        System.out.println(order);
        log.info("Product {} is saved", order.getOrderId());
        return "Order " + order.getOrderId() + " is saved";
    }

    public void packedRequest(PackedRequest packedRequest){
        Order order = orderRepository.findById(packedRequest.getOrderId())
                .orElse(null);
        assert order != null;
        order.setOrderId(packedRequest.getOrderId());
        order.setOrderStatus(OrderStatus.PACKED);
        order.setStoreKeeperId(packedRequest.getStoreKeeperId());
        orderRepository.save(order);
    }
    public void dispatchedRequest(DispatchedRequest dispatchedRequest){
        Order order = orderRepository.findById(dispatchedRequest.getOrderId())
                .orElse(null);
        assert order != null;
        order.setOrderStatus(OrderStatus.DISPATCHED);
        order.setDeliveryPersonId(dispatchedRequest.getDeliveryPersonId());
        orderRepository.save(order);
    }
    public void canceledRequest(CanceledRequest canceledRequest){
        Order order = orderRepository.findById(canceledRequest.getOrderId())
                .orElse(null);
        assert order != null;
        order.setOrderStatus(OrderStatus.CANCELED);
        orderRepository.save(order);
    }
    public List<OrderListResponse> getOrderList(){
        return orderRepository.findAll().stream().map(this::mapToOrderListResponse).toList();
    }

    public List<CustomerOrderListResponse> getCustomerOrderList(String customerId){
        return orderRepository.findByCustomerId(customerId).stream().map(this::mapToCustomerOrderListResponse).toList();
    }

    private CustomerOrderListResponse mapToCustomerOrderListResponse(Order order){
        return CustomerOrderListResponse.builder()
                .orderId(order.getOrderId())
                .placedDate(order.getPlacedDate())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    private OrderListResponse mapToOrderListResponse(Order order){
        return OrderListResponse.builder()
                .orderId(order.getOrderId())
                .placedDate(order.getPlacedDate())
                .dispatchedDate(order.getDispatchedDate())
                .orderStatus(order.getOrderStatus())
                .customerId(order.getCustomerId())
                .storeKeeperId(order.getStoreKeeperId())
                .deliveryPersonId(order.getDeliveryPersonId())
                .orderQuantity(order.getOrderQuantity())
                .build();
    }
}
