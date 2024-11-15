package com.example.orderservice.repository;

import com.example.orderservice.entity.OrderEntity;

import java.util.List;

public interface OderCustomRepository {
  List<OrderEntity> findOrderByUserIdAndStatusIsNew(Long userId);
}
