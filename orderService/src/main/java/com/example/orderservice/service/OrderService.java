package com.example.orderservice.service;

import com.example.orderservice.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
  List<OrderEntity> getAllOrders();
  OrderEntity getOrderById(Long id);
  OrderEntity UpsertOrder(OrderEntity order);
  void deleteOrder(Long id);
  long countOrders();
  boolean doesOrderExist(Long id);
  List<OrderEntity> getOrdersByUserId(Long userId);
  List<OrderEntity> getOrdersByUserIdAndStatusIsNew(Long userId);
}
