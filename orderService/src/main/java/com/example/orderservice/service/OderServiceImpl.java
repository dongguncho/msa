package com.example.orderservice.service;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.repository.OderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OderServiceImpl implements OrderService {

  private final OderRepository oderRepository;

  @Autowired
  public OderServiceImpl(OderRepository oderRepository) {
    this.oderRepository = oderRepository;
  }

  @Override
  public List<OrderEntity> getAllOrders() {
    List<OrderEntity> orders = oderRepository.findAll();
    if (orders.isEmpty()) {
      throw new RuntimeException("No orders found");
    }
    return orders;
  }

  @Override
  public OrderEntity getOrderById(Long id) {
    return oderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
  }

  @Override
  public OrderEntity UpsertOrder(OrderEntity order) {
    return oderRepository.save(order);
  }

  @Override
  public void deleteOrder(Long id) {
    oderRepository.deleteById(id);
  }

  @Override
  public long countOrders() {
    return oderRepository.count();
  }

  @Override
  public boolean doesOrderExist(Long id) {
    return oderRepository.existsById(id);
  }

  @Override
  public List<OrderEntity> getOrdersByUserId(Long userId) {
    return oderRepository.findByUserId(userId);
  }

  @Override
  public List<OrderEntity> getOrdersByUserIdAndStatusIsNew(Long userId) {
    return oderRepository.findOrderByUserIdAndStatusIsNew(userId);
  }
}
